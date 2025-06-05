import tkinter as tk
from tkinter import ttk, messagebox
from datetime import datetime
from abc import ABC, abstractmethod
from statistics import median
from collections import defaultdict
import sqlite3

class Measurement(ABC):
    def __init__(self, timestamp, sensor_id, reading_id, value):
        self.timestamp = timestamp
        self.sensor_id = sensor_id
        self.reading_id = reading_id
        self.value = value

    @abstractmethod
    def get_type(self):
        pass

    def to_tuple(self):
        return (self.get_type(), self.timestamp, self.sensor_id, self.reading_id, self.value)

    def get_datetime(self):
        return datetime.strptime(self.timestamp, "%Y-%m-%d %H:%M:%S")

    def get_date(self):
        return self.get_datetime().date()


class ThermometerReading(Measurement):
    def get_type(self):
        return "Термометр"


class BarometerReading(Measurement):
    def get_type(self):
        return "Барометр"


class MeasurementApp:
    def __init__(self, root):
        self.root = root
        self.root.title("Показания приборов")

        # Подключение к базе данных
        self.conn = sqlite3.connect("measurements.db")
        self.cursor = self.conn.cursor()
        self.cursor.execute("""
            CREATE TABLE IF NOT EXISTS readings (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                type TEXT NOT NULL,
                timestamp TEXT NOT NULL,
                sensor_id TEXT NOT NULL,
                reading_id TEXT NOT NULL,
                value REAL NOT NULL
            )
        """)
        self.conn.commit()

        self.measurements = []

        columns = ("Тип", "Дата и время", "Номер датчика", "Номер измерения", "Значение")
        self.tree = ttk.Treeview(root, columns=columns, show="headings")
        for col in columns:
            self.tree.heading(col, text=col)
            self.tree.column(col, anchor="center", width=140)
        self.tree.pack(fill=tk.BOTH, expand=True)

        btn_frame = tk.Frame(root)
        btn_frame.pack(pady=10)
        tk.Button(btn_frame, text="Добавить термометр", command=self.add_thermometer).pack(side=tk.LEFT, padx=5)
        tk.Button(btn_frame, text="Добавить барометр", command=self.add_barometer).pack(side=tk.LEFT, padx=5)

        filter_frame = tk.Frame(root)
        filter_frame.pack(pady=10)

        tk.Label(filter_frame, text="С (YYYY-MM-DD):").pack(side=tk.LEFT)
        self.start_entry = tk.Entry(filter_frame, width=12)
        self.start_entry.pack(side=tk.LEFT, padx=5)

        tk.Label(filter_frame, text="По (YYYY-MM-DD):").pack(side=tk.LEFT)
        self.end_entry = tk.Entry(filter_frame, width=12)
        self.end_entry.pack(side=tk.LEFT, padx=5)

        tk.Button(filter_frame, text="Фильтровать", command=self.filter_by_date).pack(side=tk.LEFT, padx=5)
        tk.Button(filter_frame, text="Медиана по дням", command=self.show_median_by_day).pack(side=tk.LEFT, padx=5)

        self.load_measurements_from_db()

    def load_measurements_from_db(self):
        self.cursor.execute("SELECT type, timestamp, sensor_id, reading_id, value FROM readings")
        for row in self.cursor.fetchall():
            typ, timestamp, sensor_id, reading_id, value = row
            obj = ThermometerReading(timestamp, sensor_id, reading_id, value) if typ == "Термометр" else BarometerReading(timestamp, sensor_id, reading_id, value)
            self.measurements.append(obj)
            self.tree.insert("", tk.END, values=obj.to_tuple())

    def add_measurement(self, measurement):
        self.measurements.append(measurement)
        self.cursor.execute("""
            INSERT INTO readings (type, timestamp, sensor_id, reading_id, value)
            VALUES (?, ?, ?, ?, ?)
        """, measurement.to_tuple())
        self.conn.commit()
        self.tree.insert("", tk.END, values=measurement.to_tuple())

    def add_thermometer(self):
        reading = ThermometerReading(
            timestamp=datetime.now().strftime("%Y-%m-%d %H:%M:%S"),
            sensor_id="T-001",
            reading_id=str(len(self.measurements) + 1),
            value=round(20 + 5 * (len(self.measurements) % 3), 2)
        )
        self.add_measurement(reading)

    def add_barometer(self):
        reading = BarometerReading(
            timestamp=datetime.now().strftime("%Y-%m-%d %H:%M:%S"),
            sensor_id="B-001",
            reading_id=str(len(self.measurements) + 1),
            value=round(750 + 2 * (len(self.measurements) % 5), 2)
        )
        self.add_measurement(reading)

    def filter_by_date(self):
        start_str = self.start_entry.get()
        end_str = self.end_entry.get()
        try:
            start_date = datetime.strptime(start_str, "%Y-%m-%d").date()
            end_date = datetime.strptime(end_str, "%Y-%m-%d").date()
        except ValueError:
            messagebox.showerror("Ошибка", "Неверный формат даты. Используйте YYYY-MM-DD.")
            return

        self.tree.delete(*self.tree.get_children())

        for m in self.measurements:
            if start_date <= m.get_date() <= end_date:
                self.tree.insert("", tk.END, values=m.to_tuple())

    def show_median_by_day(self):
        start_str = self.start_entry.get()
        end_str = self.end_entry.get()
        try:
            start_date = datetime.strptime(start_str, "%Y-%m-%d").date()
            end_date = datetime.strptime(end_str, "%Y-%m-%d").date()
        except ValueError:
            messagebox.showerror("Ошибка", "Неверный формат даты. Используйте YYYY-MM-DD.")
            return

        daily_values = defaultdict(list)

        for m in self.measurements:
            if start_date <= m.get_date() <= end_date:
                daily_values[m.get_date()].append(m.value)

        if not daily_values:
            messagebox.showinfo("Результат", "Нет данных в выбранный период.")
            return

        result_window = tk.Toplevel(self.root)
        result_window.title("Медианные значения по дням")

        for day, values in sorted(daily_values.items()):
            med = median(values)
            label = tk.Label(result_window, text=f"{day}: {med}")
            label.pack()

    def __del__(self):
        self.conn.close()

if __name__ == "__main__":
    root = tk.Tk()
    app = MeasurementApp(root)
    root.geometry("850x500")
    root.mainloop()
