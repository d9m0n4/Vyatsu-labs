-- Добавляем новые столбцы в таблицу products
ALTER TABLE products
    ADD COLUMN view_count int DEFAULT 0,
    ADD COLUMN category varchar(50) NOT NULL DEFAULT 'General';

-- Вставляем новые данные
INSERT INTO products (title, price, view_count, category) VALUES
    ('Samsung Smartphone 101', 30000, 0, 'Smartphone'),
    ('Apple Laptop 102', 60000, 0, 'Laptop'),
    ('LG Home Appliance 103', 20000, 0, 'Home Appliance'),
    ('Xiaomi Tablet 104', 15000, 0, 'Tablet'),
    ('Sony Smartwatch 105', 12000, 0, 'Smartwatch');
