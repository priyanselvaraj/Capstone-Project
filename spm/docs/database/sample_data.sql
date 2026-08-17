USE supplier_rating_db;
INSERT INTO suppliers(supplier_code,name,email,phone,address,active) VALUES
('SUP001','ABC Components','abc@example.com','9876543210','Chennai',TRUE),
('SUP002','Global Materials','global@example.com','9876501234','Coimbatore',TRUE)
ON DUPLICATE KEY UPDATE name=VALUES(name);
INSERT INTO products(product_code,name,category,unit_price) VALUES
('P001','Steel Bolt','Hardware',12.50),
('P002','Copper Wire','Electrical',85.00)
ON DUPLICATE KEY UPDATE name=VALUES(name);
