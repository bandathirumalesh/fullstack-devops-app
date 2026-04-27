<<<<<<< HEAD
# springboot-devops-project
=======
# College Student Registration Portal

A simple full-stack web application for managing college student registration.  
This project is built with **Spring Boot** for the backend, **Thymeleaf** for the frontend, and **H2 Database** for data storage.

It is a good beginner-friendly project if you want to learn how a web application works with:

- frontend pages
- backend controllers and services
- database storage
- login and user registration
- form handling and validation

## Project Overview

This application allows users to:

- create a new account
- log in to the system
- log out safely
- register college students
- store student data in the database
- view registered students in the dashboard
- inspect the database using the H2 console

The application is designed so you can open it directly in **IntelliJ IDEA** and run it easily.

## Tech Stack

- Java 21
- Spring Boot 3
- Spring MVC
- Thymeleaf
- Spring Data JPA
- H2 Database
- Maven
- HTML/CSS

## Main Features

### 1. User Login

The application starts with a login page.  
Existing users can enter their email and password to access the dashboard.

### 2. New User Registration

If the user does not have an account, they can create one from the same login page using the **Create New User** form.

### 3. Student Registration

After login, the user is redirected to the dashboard where they can add student details such as:

- student name
- roll number
- email
- department
- year of study
- phone number
- address

### 4. Student List

All registered students are shown on the dashboard and stored in the database.

### 5. Database Support

This project uses **H2 file-based database**, so the data is stored locally in the project folder and remains available after restarting the app.

### 6. H2 Database Console

You can open the H2 console in the browser and view the tables and stored records directly.

## Default Demo Login

The application automatically creates a demo admin user when the database is empty.

- Email: `admin@collegeportal.com`
- Password: `admin123`

You can use this account to log in immediately after starting the application.

## How The Application Works

### Frontend

The frontend is built using **Thymeleaf templates**:

- `login.html` for login and new user registration
- `dashboard.html` for student registration and viewing student records

The CSS styling is stored in:

- `src/main/resources/static/css/styles.css`

### Backend

The backend is built using Spring Boot and contains:

- controllers for handling page requests and form submissions
- services for business logic
- repositories for database access
- entities for database tables

### Database

The database stores two main types of records:

- application users
- registered students

The H2 database file is created in:

- `data/collegeportaldb.mv.db`

## Project Structure

```text
simpleapp/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/com/example/simpleapp/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   └── service/
│   │   └── resources/
│   │       ├── static/css/
│   │       ├── templates/
│   │       └── application.properties
│   └── test/
└── data/
```

## Important Files

### Application Entry Point

- `src/main/java/com/example/simpleapp/SimpleappApplication.java`

This is the main Spring Boot class used to start the application.

### Controllers

- `AuthController.java`
- `DashboardController.java`

These handle:

- login
- user registration
- logout
- dashboard view
- student registration submission

### DTOs

- `LoginForm.java`
- `UserRegistrationForm.java`
- `StudentForm.java`

These classes are used to collect and validate form data from the frontend.

### Models / Entities

- `AppUser.java`
- `Student.java`

These classes represent the database tables.

### Repositories

- `AppUserRepository.java`
- `StudentRepository.java`

These classes communicate with the database using Spring Data JPA.

### Services

- `AuthService.java`
- `StudentService.java`

These contain the business logic for:

- authenticating users
- registering new users
- saving students
- loading students from the database

### Configuration

- `DataInitializer.java`
- `application.properties`

These files configure the database and create the default demo user.

## Validation Included

This project includes basic validation for forms.

Examples:

- email must be in valid format
- password must be at least 6 characters
- confirm password must match password
- student phone number must be 10 digits
- roll number must be unique
- user email must be unique

## How To Run The Project

## Option 1: Run In IntelliJ IDEA

1. Open **IntelliJ IDEA**
2. Click **Open**
3. Select this project folder
4. Wait for IntelliJ to import the Maven dependencies
5. Open `SimpleappApplication.java`
6. Click **Run**
7. Open your browser and go to:

```text
http://localhost:8080
```

## Option 2: Run From Terminal

Use this command in the project folder:

```powershell
mvn -Dmaven.repo.local=.m2\repository spring-boot:run
```

## How To Test The Build

To run the test:

```powershell
mvn -Dmaven.repo.local=.m2\repository test
```

## Application URLs

### Main Login Page

```text
http://localhost:8080/login
```

### Dashboard

After login, the application opens:

```text
http://localhost:8080/dashboard
```

### H2 Database Console

```text
http://localhost:8080/h2-console
```

Use these database settings inside the H2 console:

- JDBC URL: `jdbc:h2:file:./data/collegeportaldb`
- Username: `sa`
- Password: leave blank

## Step-By-Step Usage

1. Start the application
2. Open the login page
3. Log in using the demo user or create a new account
4. Open the dashboard
5. Fill in the student registration form
6. Submit the form
7. View the registered student in the student list
8. Open the H2 console if you want to inspect the database directly

## Database Tables

This application creates these main tables:

- `app_users`
- `students`

## Security Note

This is a simple educational project.  
Passwords are stored in hashed form using **BCrypt**, but the overall login system is still basic and intended for learning/demo purposes.

For a production application, you would normally add:

- Spring Security full configuration
- role-based access control
- CSRF protection strategy
- stronger session management
- audit logs
- production database such as MySQL or PostgreSQL

## Future Improvements

You can extend this project by adding:

- edit student details
- delete student record
- search students
- pagination
- admin and staff roles
- file upload for student documents
- MySQL or PostgreSQL integration
- REST API support

## Why This Project Is Useful

This project is useful for beginners because it demonstrates how a real web application is organized:

- frontend form pages
- backend request handling
- database persistence
- authentication flow
- form validation
- Maven project structure

It can be used as:

- a college mini project
- a Spring Boot learning project
- a Java full-stack practice project
- a starter template for student management systems

## Author Notes

If you upload this project to GitHub, you can use this README directly.  
You can also add screenshots later for:

- login page
- create user form
- dashboard
- registered students list
- H2 database console
>>>>>>> 1bdfece (Initial commit)
