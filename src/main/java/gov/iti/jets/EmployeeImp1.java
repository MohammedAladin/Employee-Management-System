package gov.iti.jets;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

/**
 * Implementation of EmployeeDAO and Entity interfaces for managing Employee data.
 */
public class EmployeeImp1 implements EmployeeDAO, Entity {

    private final Connection con;

    // Constructor to initialize the connection
    public EmployeeImp1(Connection connection) {
        this.con = connection;
    }

    // Method to create the Employee table if it doesn't exist
    @Override
    public void createTable() {
        try (Statement statement = con.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS Employee (" +
                    "Id INT PRIMARY KEY," +
                    "F_Name VARCHAR(255)," +
                    "L_Name VARCHAR(255)," +
                    "Gender VARCHAR(10)," +
                    "Age INT," +
                    "Address VARCHAR(255)," +
                    "Phone_Number VARCHAR(15)," +
                    "Vacation_Balance INT DEFAULT 30" +
                    ")";

            // Execute the SQL query to create the table
            int res = statement.executeUpdate(createTableQuery);
            System.out.println("Employee table created successfully. Rows affected: " + res);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to check if a table exists in the database
    @Override
    public boolean isTableExists(String tableName) throws SQLException {
        try (Statement statement = con.createStatement()) {
            String query = "SHOW TABLES LIKE '" + tableName + "'";
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet.next();
        }
    }

    // Method to check if an employee with a given ID exists
    @Override
    public boolean isEntityExists(int id) throws SQLException {
        String query = "SELECT F_NAME FROM EMPLOYEE WHERE ID =  ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }

    // Method to insert an employee using PreparedStatement
    @Override
    public void insert(Employee emp) {
        String insertQuery = "INSERT INTO Employee (Id, F_Name, L_Name, Gender, Age, Address, Phone_Number, Vacation_Balance) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement insertStatement = con.prepareStatement(insertQuery)) {
            // Set parameters for the PreparedStatement
            insertStatement.setInt(1, emp.getId());
            insertStatement.setString(2, emp.getFirstName());
            insertStatement.setString(3, emp.getLastName());
            insertStatement.setString(4, emp.getGender());
            insertStatement.setInt(5, emp.getAge());
            insertStatement.setString(6, emp.getAddress());
            insertStatement.setString(7, emp.getPhoneNumber());
            insertStatement.setInt(8, emp.getVacationBalance());

            // Execute the update
            int rowsAffected = insertStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert an employee using CachedRowSet
    @Override
    public void insertByCached(Employee employee) {
        try {
            con.setAutoCommit(false);
            CachedRowSet rowSet = RowSetProvider.newFactory().createCachedRowSet();
            Properties prop = new Properties();

            prop.load(new FileInputStream(MyConnection.propPath));
            rowSet.setUrl(prop.getProperty("URL"));
            rowSet.setUsername(prop.getProperty("User"));
            rowSet.setPassword(prop.getProperty("Password"));

            rowSet.moveToInsertRow();
            rowSet.updateInt("Id", employee.getId());
            rowSet.updateString("f_name", employee.getFirstName());
            rowSet.updateString("l_name", employee.getLastName());
            rowSet.updateInt(5, employee.getAge());
            rowSet.updateInt(8, employee.getVacationBalance());
            rowSet.updateString(4, employee.getGender());
            rowSet.updateString(6, employee.getAddress());
            rowSet.updateString(7, employee.getPhoneNumber());

            rowSet.insertRow();
            rowSet.moveToCurrentRow();

            rowSet.acceptChanges(con);
            con.setAutoCommit(true);

            System.out.println("\nAccepting changes in the database...");
            System.out.println("\nChanges have been successfully made to the database.");

        } catch (SQLException | IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    // Method to perform a batch update
    @Override
    public void updateBatch(String[] queries) {
        try (Statement statement = con.createStatement()) {
            con.setAutoCommit(false);

            for (String query : queries) {
                statement.addBatch(query);
            }

            int[] updateCounts = statement.executeBatch();
            con.commit();
            con.setAutoCommit(true);

            System.out.println("Batch update completed. Rows affected: " + updateCounts.length);

        } catch (SQLException e) {
            System.out.println("Exception On Batch: " + e.getMessage());
        }
    }

    // Method to retrieve all employees from the database
    @Override
    public void getAll() {
        try (Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM EMPLOYEE");

            while (rs.next()) {
                System.out.println("First Name: " + rs.getString("F_Name") + " Last Name: " + rs.getString("L_Name"));
            }

        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    // Method to retrieve a specific employee by ID (to be implemented if needed)
    @Override
    public void getEmployee(int id) {
        // Implement this method if needed
    }

    // Method to update an employee's information
    @Override
    public void updateEmployee(int id, Employee emp) {
        try (Statement statement = con.createStatement()) {

            ResultSet rs = statement.executeQuery("SELECT * FROM EMPLOYEE WHERE ID = " + id + "");

            rs.updateInt(1, emp.getId());
            rs.updateInt(5, emp.getAge());
            rs.updateInt(8, emp.getVacationBalance());
            rs.updateString(2, emp.getFirstName());
            rs.updateString(3, emp.getLastName());
            rs.updateString(4, emp.getGender());
            rs.updateString(6, emp.getAddress());
            rs.updateString(7, emp.getPhoneNumber());

            rs.updateRow();

        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
