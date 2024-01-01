package gov.iti.jets;

import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleUserInterface implements UserInterface {
    private final EmployeeDAO employeeDAO;
    private final Entity entity;
    private final Scanner scanner;

    public ConsoleUserInterface(EmployeeDAO employeeDAO, Entity entity) {
        this.employeeDAO = employeeDAO;
        this.entity = entity;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void displayMenu() {
        System.out.println("=== Employee Management System ===");
        System.out.println("1. Create Employee Table");
        System.out.println("2. Insert Employee");
        System.out.println("3. Display All Employees");
        System.out.println("4. Display Employee by ID");
        System.out.println("5. Update Employee by ID");
        System.out.println("6. Update Batch");
        System.out.println("7. Exit");
        System.out.print("Select an option (1-7): ");
    }

    @Override
    public void handleUserInput() {
        int choice;

        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Create Employee Table
                    entity.createTable();
                    break;
                case 2:
                    // Insert Employee
                    handleInsertEmployee();
                    break;
                case 3:
                    // Display All Employees
                    employeeDAO.getAll();
                    break;
                case 4:
                    // Display Employee by ID
                    handleDisplayEmployeeById();
                    break;
                case 5:
                    // Update Employee by ID
                    handleUpdateEmployeeById();
                    break;
                case 6:
                    // Update Batch
                    handleUpdateBatch();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a number between 1 and 7.");
            }
        } while (choice != 7);
    }

    private void handleInsertEmployee() {
        System.out.println("=== Insert Employee ===");

        // Prompt user for employee details
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        try {
            // Check if the employee with the given ID already exists
            if (entity.isEntityExists(id)) {
                System.out.println("Employee with ID " + id + " already exists. Cannot insert.");
            } else {
                System.out.print("Enter First Name: ");
                String firstName = scanner.nextLine();

                System.out.print("Enter Last Name: ");
                String lastName = scanner.nextLine();

                System.out.print("Enter Gender: ");
                String gender = scanner.nextLine();

                System.out.print("Enter Age: ");
                int age = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                System.out.print("Enter Address: ");
                String address = scanner.nextLine();

                System.out.print("Enter Phone Number: ");
                String phoneNumber = scanner.nextLine();

                System.out.print("Enter Vacation Balance: ");
                int vacationBalance = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                // Create an Employee object
                Employee employee = new Employee(id, age, vacationBalance, firstName, lastName, gender, address, phoneNumber);

                // Insert the employee into the database
                employeeDAO.insertByCached(employee);

                System.out.println("Employee inserted successfully!\n");
            }
        } catch (SQLException e) {
            System.out.println("Error while checking for employee existence: " + e.getMessage());
        }
    }

    private void handleDisplayEmployeeById() {
        System.out.println("=== Display Employee by ID ===");

        // Prompt user for employee ID
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        try {
            // Check if the employee exists
            if (entity.isEntityExists(id)) {
                // Retrieve and display the employee information
                employeeDAO.getEmployee(id);
            } else {
                System.out.println("Employee with ID " + id + " does not exist.");
            }
        } catch (SQLException e) {
            System.out.println("Error while checking for employee existence: " + e.getMessage());
        }
    }

    private void handleUpdateEmployeeById() {
        System.out.println("=== Update Employee by ID ===");

        // Prompt user for employee ID
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Check if the employee with the given ID exists
        try {
            if (entity.isEntityExists(id)) {
                // Prompt user for updated employee details
                System.out.print("Enter New First Name: ");
                String firstName = scanner.nextLine();

                System.out.print("Enter New Last Name: ");
                String lastName = scanner.nextLine();

                System.out.print("Enter New Gender: ");
                String gender = scanner.nextLine();

                System.out.print("Enter New Age: ");
                int age = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                System.out.print("Enter New Address: ");
                String address = scanner.nextLine();

                System.out.print("Enter New Phone Number: ");
                String phoneNumber = scanner.nextLine();

                System.out.print("Enter New Vacation Balance: ");
                int vacationBalance = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                // Create an Employee object with updated information
                Employee updatedEmployee = new Employee(id, age, vacationBalance, firstName, lastName, gender, address, phoneNumber);

                // Update the employee in the database
                employeeDAO.updateEmployee(id, updatedEmployee);

                System.out.println("Employee updated successfully!\n");
            } else {
                System.out.println("Employee with ID " + id + " does not exist.\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleUpdateBatch() {
        System.out.println("=== Update Batch ===");

        // Prompt user for SQL queries (multiple lines)
        System.out.println("Enter SQL queries for batch update (type 'done' to finish):");
        StringBuilder batchQueries = new StringBuilder();
        String queryLine;

        while (true) {
            queryLine = scanner.nextLine();

            if (queryLine.equalsIgnoreCase("done")) {
                break;
            }

            batchQueries.append(queryLine).append("\n");
        }

        // Split the queries into an array
        String[] queries = batchQueries.toString().split("\n");
            // Perform batch update
        employeeDAO.updateBatch(queries);
        System.out.println("Batch update completed.\n");
        
    }
}
