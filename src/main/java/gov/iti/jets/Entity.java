package gov.iti.jets;

import java.sql.SQLException;

public interface Entity {
    
    void createTable();
    boolean isTableExists(String tName) throws SQLException;
    boolean isEntityExists(int id) throws SQLException;
    

}
