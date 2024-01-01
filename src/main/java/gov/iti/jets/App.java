package gov.iti.jets;

import java.sql.Connection;
import java.sql.SQLException;

public class App 
{
    public static void main( String[] args )
    {
       // Initialize the database connection
        Connection connection = MyConnection.getMyConnection();

        
        
        // Create an instance of EmployeeImp1 with the database connection
        EmployeeImp1 employeeDAO = new EmployeeImp1(connection);

        // Create an instance of ConsoleUserInterface with the EmployeeDAO
        ConsoleUserInterface userInterface = new ConsoleUserInterface(employeeDAO, employeeDAO);

        // Display the menu and handle user input
        userInterface.displayMenu();
        userInterface.handleUserInput();

        // Close the database connection
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
