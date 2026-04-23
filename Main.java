import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/fitness_app", "root", "password"
            );

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Age: ");
            int age = sc.nextInt();

            sc.nextLine();

            System.out.print("Gender (Male/Female): ");
            String gender = sc.nextLine();

            System.out.print("Weight: ");
            double weight = sc.nextDouble();

            System.out.print("Height: ");
            double height = sc.nextDouble();

            sc.nextLine();

            System.out.print("Activity (Sedentary/Light/Moderate/Active): ");
            String activity = sc.nextLine();

            CallableStatement cs = con.prepareCall("{CALL CalculateNutritionPlan(?,?,?,?,?,?)}");

            cs.setString(1, name);
            cs.setInt(2, age);
            cs.setString(3, gender);
            cs.setDouble(4, weight);
            cs.setDouble(5, height);
            cs.setString(6, activity);

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                System.out.println("\n--- Result ---");
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("BMI: " + rs.getDouble("bmi"));
                System.out.println("Calories: " + rs.getDouble("calories"));
                System.out.println("Protein: " + rs.getDouble("protein"));
                System.out.println("Carbs: " + rs.getDouble("carbs"));
                System.out.println("Fats: " + rs.getDouble("fats"));
                System.out.println("Status: " + rs.getString("bmi_status"));
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
