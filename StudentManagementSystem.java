import java.sql.*;
import java.util.Scanner;

public class StudentManagementSystem {

    // --- DATABASE CONFIGURATION ---
    static final String DB_URL = "jdbc:mysql://localhost:3306/student_db";
    static final String DB_USER = "root"; 
    // CHANGE THIS PASSWORD IF NEEDED:
    static final String DB_PASSWORD = "root1234"; 

    static Scanner sc = new Scanner(System.in);

    // --- HELPER METHOD TO CONNECT TO DB ---
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static void menu() {
        while (true) {
            System.out.println("\n--- Student Management System (Powered by SQL) ---");
            System.out.println("1. Add Student (INSERT)");
            System.out.println("2. Remove Student (DELETE)");
            System.out.println("3. Search Student (SELECT)");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.println("6. Update Student (UPDATE)");
            
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> removeStudent();
                case 3 -> searchStudent();
                case 4 -> displayStudents();
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                case 6 -> updateStudent();
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // --- 1. ADD STUDENT (JDBC INSERT) ---
    public static void addStudent() {
        try {
            System.out.print("Enter name: ");
            sc.nextLine(); 
            String name = sc.nextLine();
            System.out.print("Enter roll number: ");
            int roll = sc.nextInt();
            System.out.print("Enter marks: ");
            double marks = sc.nextDouble();

            Connection conn = getConnection();
            String query = "INSERT INTO students (name, roll_number, marks) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, name);
            pstmt.setInt(2, roll);
            pstmt.setDouble(3, marks);
            
            int rowsAffected = pstmt.executeUpdate(); 
            if(rowsAffected > 0) {
                System.out.println("SUCCESS: Student added to Database!");
            }
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("Error Adding Student: " + e.getMessage());
        }
    }

    // --- 2. REMOVE STUDENT (JDBC DELETE) ---
    public static void removeStudent() {
        try {
            System.out.print("Enter roll number to remove: ");
            int roll = sc.nextInt();

            Connection conn = getConnection();
            String query = "DELETE FROM students WHERE roll_number = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, roll);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("SUCCESS: Student removed from Database!");
            } else {
                System.out.println("Student not found.");
            }
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error removing student: " + e.getMessage());
        }
    }

    // --- 3. SEARCH STUDENT (JDBC SELECT) ---
    public static void searchStudent() {
        try {
            System.out.print("Enter roll number to search: ");
            int roll = sc.nextInt();

            Connection conn = getConnection();
            String query = "SELECT * FROM students WHERE roll_number = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, roll);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("\n--- Student Found in DB ---");
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Roll: " + rs.getInt("roll_number"));
                System.out.println("Marks: " + rs.getDouble("marks"));
            } else {
                System.out.println("Student not found!");
            }
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error searching: " + e.getMessage());
        }
    }

    // --- 4. DISPLAY ALL (JDBC SELECT ALL) ---
    public static void displayStudents() {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

            System.out.println("\n--- List of All Students (Fetched from MySQL) ---");
            while (rs.next()) {
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Roll Number: " + rs.getInt("roll_number"));
                System.out.println("Marks: " + rs.getDouble("marks"));
                System.out.println("-----------------------------");
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error displaying students: " + e.getMessage());
        }
    }

    // --- 6. UPDATE STUDENT (JDBC UPDATE) ---
    public static void updateStudent() {
        try {
            System.out.print("Enter roll number to update: ");
            int roll = sc.nextInt();
            System.out.print("Enter new Marks: ");
            double newMarks = sc.nextDouble();

            Connection conn = getConnection();
            String query = "UPDATE students SET marks = ? WHERE roll_number = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1, newMarks);
            pstmt.setInt(2, roll);

            int rows = pstmt.executeUpdate();
            if(rows > 0) System.out.println("SUCCESS: Marks updated in Database!");
            else System.out.println("Student not found.");
            
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error updating: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Connecting to Database...");
        menu(); 
    }
}

    
