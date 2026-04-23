CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    gender VARCHAR(10),
    weight DECIMAL(5,2),
    height DECIMAL(5,2),
    activity VARCHAR(50),
    bmi DECIMAL(4,2),
    calories DECIMAL(8,2),
    protein DECIMAL(6,2),
    carbs DECIMAL(6,2),
    fats DECIMAL(6,2),
    bmi_status VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
