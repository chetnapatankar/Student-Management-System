
// STEP 1: Student class
import java.util.*;
import java.io.*;
class Student {
    String name;
    int rollNumber;
    double marks;

    public Student(String name, int rollNumber, double marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }
}
// STEP 2: Main class starts here
public class StudentManagementSystem {

    // this is our list of students
    static ArrayList<Student> students = new ArrayList<>();

    // scanner for user input
    static Scanner sc = new Scanner(System.in);

    public static void menu() {
        while (true) {
          System.out.println("\n--- Student Management System ---");
          System.out.println("1. Add Student");
          System.out.println("2. Remove Student");
          System.out.println("3. Search Student");
          System.out.println("4. Display All Students");
          System.out.println("5. Exit");
          System.out.println("6. Update Student");
          System.out.println("7. Sort by Marks (High to Low)");
          System.out.println("8. Sort by Marks (Low to High)");
          System.out.println("9. Save Students to File");      
          System.out.println("10. Load Students from File");

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
              case 7 -> sortHighToLow();
              case 8 -> sortLowToHigh();
              case 9 -> saveToFile();
              case 10 -> loadFromFile();


              default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
    public static void addStudent() {
    System.out.print("Enter name: ");
    sc.nextLine(); // consume leftover newline
    String name = sc.nextLine();

    System.out.print("Enter roll number: ");
    int roll = sc.nextInt();

    System.out.print("Enter marks: ");
    double marks = sc.nextDouble();

    Student s = new Student(name, roll, marks);
    students.add(s);

    System.out.println("Student added successfully!");
}
public static void removeStudent() {
    System.out.print("Enter roll number to remove: ");
    int roll = sc.nextInt();

    for (int i = 0; i < students.size(); i++) {
        if (students.get(i).rollNumber == roll) {
            students.remove(i);
            System.out.println("Student removed successfully!");
            return;
        }
    }

    System.out.println("Student not found!");
}
public static void searchStudent() {
    System.out.print("Enter roll number to search: ");
    int roll = sc.nextInt();

    for (Student s : students) {
        if (s.rollNumber == roll) {
            System.out.println("\n--- Student Found ---");
            System.out.println("Name: " + s.name);
            System.out.println("Roll Number: " + s.rollNumber);
            System.out.println("Marks: " + s.marks);
            return;
        }
    }

    System.out.println("Student not found!");
}
public static void displayStudents() {
    if (students.isEmpty()) {
        System.out.println("No students available.");
        return;
    }

    System.out.println("\n--- List of All Students ---");
    for (Student s : students) {
        System.out.println("Name: " + s.name);
        System.out.println("Roll Number: " + s.rollNumber);
        System.out.println("Marks: " + s.marks);
        System.out.println("-----------------------------");
    }
}
public static void updateStudent() {
    System.out.print("Enter roll number of the student to update: ");
    int roll = sc.nextInt();

    for (Student s : students) {
        if (s.rollNumber == roll) {

            System.out.println("\nWhat do you want to update?");
            System.out.println("1. Name");
            System.out.println("2. Marks");
            System.out.println("3. Both");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clearing newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();
                    s.name = newName;
                    System.out.println("Name updated!");
                }
                case 2 -> {
                    System.out.print("Enter new marks: ");
                    double newMarks = sc.nextDouble();
                    s.marks = newMarks;
                    System.out.println("Marks updated!");
                }
                case 3 -> {
                    System.out.print("Enter new name: ");
                    String newName2 = sc.nextLine();
                    System.out.print("Enter new marks: ");
                    double newMarks2 = sc.nextDouble();
                    s.name = newName2;
                    s.marks = newMarks2;
                    System.out.println("Student info updated!");
                }
                default -> System.out.println("Invalid choice!");
            }

            return;
        }
    }

    System.out.println("Student not found!");
}
public static void sortHighToLow() {
    if (students.isEmpty()) {
        System.out.println("No students to sort!");
        return;
    }

    students.sort((a, b) -> Double.compare(b.marks, a.marks));

    System.out.println("Sorted by Marks (High to Low):");
    displayStudents();
}
public static void sortLowToHigh() {
    if (students.isEmpty()) {
        System.out.println("No students to sort!");
        return;
    }

    students.sort((a, b) -> Double.compare(a.marks, b.marks));

    System.out.println("Sorted by Marks (Low to High):");
    displayStudents();
}
    public static void saveToFile() {
    try {
        FileWriter fw = new FileWriter("students.txt");
        for (Student s : students) {
            fw.write(s.name + "," + s.rollNumber + "," + s.marks + "\n");
        }
        fw.close();
        System.out.println("Students saved to file!");
    } catch (Exception e) {
        System.out.println("Error saving file: " + e.getMessage());
    }
    }
    public static void loadFromFile() {
    try {
        File file = new File("students.txt");
        if (!file.exists()) {
            System.out.println("No saved file found!");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        students.clear();  // Remove old data

        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            String name = data[0];
            int roll = Integer.parseInt(data[1]);
            double marks = Double.parseDouble(data[2]);
            students.add(new Student(name, roll, marks));
        }
        br.close();
        System.out.println("Students loaded from file!");
    } catch (Exception e) {
        System.out.println("Error reading file: " + e.getMessage());
    }
    }
    public static boolean login() {
    String correctUser = "admin";
    String correctPass = "1234";

    Scanner sc = new Scanner(System.in);

    System.out.print("Enter username: ");
    String user = sc.nextLine();

    System.out.print("Enter password: ");
    String pass = sc.nextLine();

    if (user.equals(correctUser) && pass.equals(correctPass)) {
        System.out.println("Login successful!");
        return true;
    } else {
        System.out.println("Wrong credentials! Access denied.");
        return false;
    }
    }

    public static void main(String[] args) {
      if (login()) {
        menu();
     }
    }

}
