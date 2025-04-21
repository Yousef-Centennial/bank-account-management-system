# Bank Account Management System

A Spring Boot application for bank managers to analyze customer data and manage accounts. This application implements an MVC frontend with Thymeleaf templates that consumes REST APIs for all data operations.

## Features

- Bank manager authentication (login and self-registration)
- Customer management (view, create, update, delete)
- Account management (view, create, update, delete)
- Analytics dashboard with various reports:
    - Account types by total balance
    - Account type distribution by customer city
    - Account types with high overdraft limits
- Separation of concerns with dedicated REST API layer
- Responsive UI with Bootstrap styling

## Architecture

This application follows a layered architecture:

- **REST API Layer**: Handles all database operations and exposes RESTful endpoints
- **MVC Layer**: Consumes the REST APIs and presents data using Thymeleaf templates
- **Database Layer**: MySQL database with three related tables

## Technologies Used

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Spring MVC
- Thymeleaf templates
- REST API
- MySQL
- Bootstrap 5
- Maven

## Database Schema

### Customer
- customerId (PK)
- username
- password
- customerName
- dob (Date of Birth)
- address
- city
- postalcode
- emailId
- phone

### AccountType
- accountTypeId (PK)
- accountTypeName
- accountTypeDesc
- minimumBalanceAmount
- hasOverDraft

### Account
- accountNumber (PK)
- accountTypeId (FK)
- customerId (FK)
- balance
- overDraftLimit

## Project Structure

```
bank-account-management-system/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/bank/accountmanagement/
│       │       ├── controller/
│       │       │   ├── mvc/           # MVC controllers
│       │       │   └── rest/          # REST controllers
│       │       ├── model/             # Entity classes
│       │       ├── repository/        # JPA repositories
│       │       ├── service/           # Business logic services
│       │       ├── dto/               # Data Transfer Objects
│       │       └── config/            # Configuration classes
│       └── resources/
│           ├── static/
│           │   ├── css/
│           │   ├── js/
│           │   └── images/
│           ├── templates/             # Thymeleaf templates
│           │   ├── fragments/
│           │   ├── manager/
│           │   ├── customer/
│           │   ├── account/
│           │   └── reports/
│           └── application.properties
└── pom.xml
```

## Setup Instructions

### Prerequisites
- Java 17+
- Maven
- MySQL 8.0+

### Database Setup
1. Create a MySQL database
2. Update `application.properties` with your database credentials
3. The application will create the tables on startup

### Running the Application
1. Clone the repository
```
git clone https://github.com/yourusername/bank-account-management-system.git
```

2. Navigate to the project directory
```
cd bank-account-management-system
```

3. Build and run the application
```
mvn spring-boot:run
```

4. Access the application at: http://localhost:8080

## API Endpoints

### Authentication
- `POST /api/managers/register` - Register a new bank manager
- `POST /api/managers/login` - Authenticate a bank manager

### Customers
- `GET /api/customers` - Get all customers
- `GET /api/customers/{id}` - Get a specific customer
- `POST /api/customers` - Create a new customer
- `PUT /api/customers/{id}` - Update a customer
- `DELETE /api/customers/{id}` - Delete a customer

### Accounts
- `GET /api/accounts` - Get all accounts
- `GET /api/accounts/{id}` - Get a specific account
- `POST /api/accounts` - Create a new account
- `PUT /api/accounts/{id}` - Update an account
- `DELETE /api/accounts/{id}` - Delete an account
- `GET /api/accounts/customer/{customerId}` - Get accounts by customer

### Account Types
- `GET /api/accounttypes` - Get all account types
- `GET /api/accounttypes/{id}` - Get a specific account type
- `POST /api/accounttypes` - Create a new account type
- `PUT /api/accounttypes/{id}` - Update an account type
- `DELETE /api/accounttypes/{id}` - Delete an account type

### Analytics
- `GET /api/analytics/balance-by-accounttype` - Get total balance by account type
- `GET /api/analytics/accounttypes-by-city` - Get account types distribution by city
- `GET /api/analytics/high-overdraft-accounts` - Get account types with high overdraft limits

## Screenshots


## Author


## License
