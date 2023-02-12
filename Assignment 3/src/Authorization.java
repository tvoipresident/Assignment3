import java.sql.*;
class Authorization {
    private Connection connection;

    public Authorization(Connection connection) {
        this.connection = connection;
    }

    public boolean login(String username, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
    }
}