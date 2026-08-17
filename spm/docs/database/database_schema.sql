CREATE DATABASE IF NOT EXISTS supplier_rating_db;
USE supplier_rating_db;

CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(30) NOT NULL
);

CREATE TABLE suppliers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    supplier_code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(150) NOT NULL,
    email VARCHAR(150),
    phone VARCHAR(30),
    address VARCHAR(255),
    active BOOLEAN DEFAULT TRUE
);

CREATE TABLE products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(150) NOT NULL,
    category VARCHAR(100),
    unit_price DECIMAL(12,2) DEFAULT 0
);

CREATE TABLE purchase_orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_number VARCHAR(50) NOT NULL UNIQUE,
    supplier_id BIGINT NOT NULL,
    order_date DATE,
    total_amount DECIMAL(14,2) DEFAULT 0,
    status VARCHAR(30),
    CONSTRAINT fk_order_supplier
        FOREIGN KEY (supplier_id) REFERENCES suppliers(id)
);

CREATE TABLE order_items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(12,2) NOT NULL,
    CONSTRAINT fk_item_order
        FOREIGN KEY (order_id) REFERENCES purchase_orders(id),
    CONSTRAINT fk_item_product
        FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE deliveries (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT NOT NULL,
    expected_date DATE,
    actual_date DATE,
    quantity_received INT DEFAULT 0,
    status VARCHAR(30),
    CONSTRAINT fk_delivery_order
        FOREIGN KEY (order_id) REFERENCES purchase_orders(id)
);

CREATE TABLE supplier_ratings (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    supplier_id BIGINT NOT NULL,
    quality_score DECIMAL(5,2) NOT NULL,
    delivery_score DECIMAL(5,2) NOT NULL,
    cost_score DECIMAL(5,2) NOT NULL,
    quantity_accuracy_score DECIMAL(5,2) NOT NULL,
    communication_score DECIMAL(5,2) NOT NULL,
    compliance_score DECIMAL(5,2) NOT NULL,
    overall_score DECIMAL(5,2) NOT NULL,
    rating_category VARCHAR(30) NOT NULL,
    rating_date DATE,
    CONSTRAINT fk_rating_supplier
        FOREIGN KEY (supplier_id) REFERENCES suppliers(id)
);

CREATE TABLE feedback (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    supplier_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    comments VARCHAR(1000) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_feedback_supplier
        FOREIGN KEY (supplier_id) REFERENCES suppliers(id),
    CONSTRAINT fk_feedback_user
        FOREIGN KEY (user_id) REFERENCES users(id)
);
