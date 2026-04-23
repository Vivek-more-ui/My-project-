import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/fitness_app", "root", "password"
            );

            while (true) {
                System.out.println("\n===== FITNESS APPLICATION =====");
                System.out.println("1. Add User & Calculate Plan");
                System.out.println("2. View All Users");
                System.out.println("3. Exit");
                System.out.print("Enter choice: ");

                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    System.out.print("Name: ");
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
                        System.out.println("\n--- RESULT ---");
                        System.out.println("Name: " + rs.getString("name"));
                        System.out.println("BMI: " + rs.getDouble("bmi"));
                        System.out.println("Calories: " + rs.getDouble("calories"));
                        System.out.println("Protein: " + rs.getDouble("protein"));
                        System.out.println("Carbs: " + rs.getDouble("carbs"));
                        System.out.println("Fats: " + rs.getDouble("fats"));
                        System.out.println("Status: " + rs.getString("bmi_status"));
                    }

                } else if (choice == 2) {
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM users");

                    System.out.println("\n--- ALL USERS ---");
                    while (rs.next()) {
                        System.out.println(
                            rs.getInt("id") + " | " +
                            rs.getString("name") + " | BMI: " +
                            rs.getDouble("bmi") + " | " +
                            rs.getString("bmi_status")
                        );
                    }

                } else if (choice == 3) {
                    System.out.println("Exiting...");
                    break;
                } else {
                    System.out.println("Invalid choice!");
                }
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
