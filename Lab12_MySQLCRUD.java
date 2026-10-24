import java.sql.*;

public class Lab12_MySQLCRUD {

    private static final String URL = "jdbc:mysql://localhost:3306/ICE_PUST";
    private static final String USER = "root";
    private static final String PASSWORD = ""; 

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void insertData(int id, String name, String dept) {
        String sql = "INSERT INTO students (id, name, dept) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, dept);
            pstmt.executeUpdate();
            System.out.println("[INSERT] Student added successfully with ID: " + id);

        } catch (SQLException e) {
            System.err.println("Insert Error: " + e.getMessage());
        }
    }

    public static void showData() {
        String sql = "SELECT * FROM students";
        System.out.println("\n--- Current Students List ---");
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.printf("%-10s %-20s %-10s\n", "ID", "Name", "Department");
            System.out.println("----------------------------------------");

            while (rs.next()) {
                System.out.printf("%-10d %-20s %-10s\n", 
                        rs.getInt("id"), rs.getString("name"), rs.getString("dept"));
            }
            System.out.println();

        } catch (SQLException e) {
            System.err.println("Select Error: " + e.getMessage());
        }
    }

    public static void editData(int id, String newName, String newDept) {
        String sql = "UPDATE students SET name = ?, dept = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newName);
            pstmt.setString(2, newDept);
            pstmt.setInt(3, id);

            int updated = pstmt.executeUpdate();
            if (updated > 0) {
                System.out.println("[UPDATE] Student record updated for ID: " + id);
            }

        } catch (SQLException e) {
            System.err.println("Update Error: " + e.getMessage());
        }
    }

    public static void deleteData(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int deleted = pstmt.executeUpdate();
            if (deleted > 0) {
                System.out.println("[DELETE] Student record deleted with ID: " + id);
            }

        } catch (SQLException e) {
            System.err.println("Delete Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Lab Experiment 12: MySQL CRUD Operations ===\n");

        insertData(101, "Sabbir Ahmed", "ICE");
        insertData(102, "Tanvir Hasan", "CSE");

        showData();

        editData(101, "Himel Sheikh (Updated)", "ICE");
        showData();

        deleteData(102);
        showData();
    }
}

//CREATE DATABASE IF NOT EXISTS ICE_PUST;

//USE ICE_PUST;

//CREATE TABLE IF NOT EXISTS students (
    //id INT PRIMARY KEY,
    //name VARCHAR(100),
    //dept VARCHAR(50)
//); */