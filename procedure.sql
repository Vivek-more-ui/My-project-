DELIMITER //

CREATE PROCEDURE CalculateNutritionPlan(
    IN p_name VARCHAR(100),
    IN p_age INT,
    IN p_gender VARCHAR(10),
    IN p_weight DECIMAL(5,2),
    IN p_height DECIMAL(5,2),
    IN p_activity VARCHAR(50)
)
BEGIN
    DECLARE v_bmi DECIMAL(4,2);
    DECLARE v_bmr DECIMAL(8,2);
    DECLARE v_tdee DECIMAL(8,2);
    DECLARE v_protein DECIMAL(6,2);
    DECLARE v_carbs DECIMAL(6,2);
    DECLARE v_fats DECIMAL(6,2);
    DECLARE v_status VARCHAR(20);

    SET v_bmi = (p_weight / POW(p_height/100, 2));

    IF p_gender = 'Male' THEN
        SET v_bmr = 88.362 + (13.397 * p_weight) + (4.799 * p_height) - (5.677 * p_age);
    ELSE
        SET v_bmr = 447.593 + (9.247 * p_weight) + (3.098 * p_height) - (4.330 * p_age);
    END IF;

    SET v_tdee = v_bmr * 1.55;

    SET v_protein = p_weight * 2.2;
    SET v_carbs = (v_tdee * 0.5) / 4;
    SET v_fats = (v_tdee * 0.25) / 9;

    IF v_bmi < 18.5 THEN
        SET v_status = 'Underweight';
    ELSEIF v_bmi < 25 THEN
        SET v_status = 'Normal';
    ELSEIF v_bmi < 30 THEN
        SET v_status = 'Overweight';
    ELSE
        SET v_status = 'Obese';
    END IF;

    INSERT INTO users(name, age, gender, weight, height, activity, bmi, calories, protein, carbs, fats, bmi_status)
    VALUES(p_name, p_age, p_gender, p_weight, p_height, p_activity, v_bmi, v_tdee, v_protein, v_carbs, v_fats, v_status);

    SELECT * FROM users WHERE id = LAST_INSERT_ID();
END //

DELIMITER ;
