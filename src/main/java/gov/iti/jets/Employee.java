package gov.iti.jets;

import java.sql.*;


public class Employee{
    private int id, age, vacationBalance;
    private String firstName, lastName, gender, address, phoneNumber;

    public Employee(int id, int age, int vacationBalance, String firstName, String lastName,
                    String gender, String address, String phoneNumber) {
        this.id = id;
        this.age = age;
        this.vacationBalance = vacationBalance;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setVacationBalance(int vacationBalance) {
        this.vacationBalance = vacationBalance;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public int getVacationBalance() {
        return vacationBalance;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static void createTableEmployee() {
        Connection con = MyConnection.getMyConnection();

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

            int res = statement.executeUpdate(createTableQuery);
            System.out.println("Employee table created successfully." + res);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  
}
