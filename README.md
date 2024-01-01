# Employee Management System

## Overview

This project is an Employee Management System implemented in Java, utilizing JDBC for database interactions. It allows users to perform various operations such as creating an employee table, inserting employees, displaying employee information, updating employee details, and performing batch updates.

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
