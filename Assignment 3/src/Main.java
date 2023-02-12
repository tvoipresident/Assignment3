import java.util.Scanner;
import java.sql.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/users", "postgres", "B22083011t");
            Authorization authorization = new Authorization(connection);


            int tries = 0;
            while (tries < 3) {
                System.out.print("Username: ");
                String username = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();

                if (authorization.login(username, password)) {
                    System.out.println("Access granted.");
                    break;
                } else {
                    System.out.println("Access denied.");
                    tries++;
                    if (tries == 3) {
                        String captcha = Captcha.generate();
                        System.out.println("Too many incorrect attempts. Please enter the captcha: " + captcha);
                        String input = scanner.nextLine();
                        if (input.equals(captcha)) {
                            System.out.println("Captcha entered successfully.");
                            tries = 0;
                        } else {
                            System.out.println("Incorrect captcha.");
                        }
                    }
                }
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
