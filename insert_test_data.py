import sqlite3
from datetime import datetime

# Подключение к существующей базе данных
conn = sqlite3.connect("measurements.db")
cursor = conn.cursor()

# Примеры данных с разными датами
sample_data = [
    ("Термометр", "2024-06-01 10:00:00", "T-001", "100", 22.5),
    ("Термометр", "2024-06-01 12:30:00", "T-001", "101", 23.0),
    ("Барометр", "2024-06-02 09:15:00", "B-001", "200", 755.2),
    ("Термометр", "2024-06-02 15:45:00", "T-001", "102", 21.7),
    ("Барометр", "2024-06-03 11:00:00", "B-001", "201", 752.0),
    ("Барометр", "2024-06-03 13:20:00", "B-001", "202", 754.3),
]

# Вставка данных
cursor.executemany("""
    INSERT INTO readings (type, timestamp, sensor_id, reading_id, value)
    VALUES (?, ?, ?, ?, ?)
""", sample_data)

conn.commit()
conn.close()

print("Тестовые данные успешно добавлены!")
