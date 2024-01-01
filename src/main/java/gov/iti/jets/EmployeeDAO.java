package gov.iti.jets;


public interface EmployeeDAO {
    

    void insert(Employee employee);

    void insertByCached(Employee employee);

    void getEmployee(int id);

    void updateEmployee(int id, Employee emp);

    void updateBatch(String [] queries);

    void getAll();



    // Additional methods for querying and updating employees
}