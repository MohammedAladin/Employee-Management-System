# Employee Management System

## Overview

This project is an Employee Management System implemented in Java, utilizing JDBC for database interactions. It allows users to perform various operations such as creating an employee table, inserting employees, displaying employee information, updating employee details, and performing batch updates.

Also this Java project uses JDBC for seamless database interactions. Key components include:

- **Statements:** Executing SQL queries and updates, with PreparedStatements for performance and security.

- **Batches:** Efficient execution of multiple SQL statements in a single round-trip, optimizing database interactions.

- **ResultSets:** Retrieving and iterating over query results for displaying employee information.

- **CachedRowSet:** Offline manipulation of result sets, enhancing performance through reduced database connection time.

These components collectively enhance the efficiency of the Employee Management System.

## Table of Contents

- [Project Structure](#project-structure)
- [Setup](#setup)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Project Structure

The project is organized into the following packages:

- **gov.iti.jets:** Contains the main implementation classes.
- **Resources:** Contains the properties file used for database configuration.

## Setup

### 1. Create a MySQL Database

Before running the project, you need to create a MySQL database to store the employee information. Follow these steps:

   - Use a MySQL client or command-line interface to create a new database. For example:

     ```sql
     CREATE DATABASE employee_management_system;
     ```

   - Optionally, create a user and grant necessary privileges:

     ```sql
     CREATE USER 'your_username'@'localhost' IDENTIFIED BY 'your_password';
     GRANT ALL PRIVILEGES ON employee_management_system.* TO 'your_username'@'localhost';
     FLUSH PRIVILEGES;
     ```

### 2. Update the `dp.properties` File

In your project's `Resources` folder, locate the `dp.properties` file. Open the file and update the following properties with your MySQL database credentials:

   ```properties
   URL=jdbc:mysql://localhost:3306/employee_management_system
   User=your_username
   Password=your_password
   ```
### 3. Build and Run the Project
To build and run the Maven project, follow these steps:

#### 1. Clone the Repository:
 ```git clone https://github.com/MohammedAladin/employee-management-system.git```

#### 2. Navigate to the Project Directory:
 ```cd employee-management-system```

#### 3. Build and Run with Maven:
Maven simplifies the build process. Use the following commands:
 ```mvn clean install compile exec:java```

## Usage
The application provides a console-based user interface for managing employee data. The main menu allows users to perform various operations, including creating the employee table, inserting employees, displaying employee information, updating employees, and performing batch updates.

Follow the on-screen prompts to interact with the system and perform the desired operations.

## Contributing
Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or create a pull request. Follow the Contributing Guidelines for details.

## License
This project is licensed under the MIT License - see the LICENSE file for details.
