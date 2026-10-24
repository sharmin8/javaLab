import java.sql.*;

public class Lab11_MSAccessDemo {
    private static final String DB_URL = "jdbc:ucanaccess://ICE_PUST.accdb";

    public static void insertStudent(String name, String email, String phone) {
        String sql = "INSERT INTO Student (Name, Email, Phone) VALUES (?, ?, ?)";
        try (Connection c = DriverManager.getConnection(DB_URL);
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, name);
            p.setString(2, email);
            p.setString(3, phone);
            p.executeUpdate();
            System.out.println("Inserted: " + name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayAllStudents() {
        String sql = "SELECT * FROM Student";
        try (Connection c = DriverManager.getConnection(DB_URL);
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            System.out.println("\n--- Students ---");
            while (rs.next()) {
                System.out.printf("%s | %s | %s\n", rs.getString("Name"), rs.getString("Email"), rs.getString("Phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        insertStudent("Sharmin", "sharmin@ice.pust.ac.bd", "01711000000");
        insertStudent("Sumon", "sumon@ice.pust.ac.bd", "01822000000");
        insertStudent("Saikat", "saikat@ice.pust.ac.bd", "01933000000");
        displayAllStudents();
    }
}