# Contact Management System

## Project Description
The Contact Management System is a Java-based console application used to manage contact details. Users can add, view, search, edit and delete contacts.

## Features
- Add new contacts
- View all saved contacts
- Search contacts using name or phone number
- Edit existing contact details
- Delete contacts
- Validate 10-digit phone numbers
- Validate email addresses
- Prevent duplicate phone numbers
- Store contacts permanently using Java Serialization

## Technologies Used
- Java
- Object-Oriented Programming (OOP)
- ArrayList
- Java Serialization
- VS Code

## Project Structure

```text
ContactManagementApp/
│
├── Contact.java
├── ContactService.java
├── Main.java
├── contacts.dat
└── README.md
```

## Requirements
- Java Development Kit (JDK)
- Visual Studio Code or any Java-supported IDE

## How to Run the Project

1. Open the project folder in VS Code.
2. Open the terminal.
3. Compile the Java files:

```bash
javac Contact.java ContactService.java Main.java
```

4. Run the application:

```bash
java Main
```

## Validation
- Phone numbers must contain exactly 10 digits.
- Email addresses must follow a valid email format.
- Duplicate phone numbers are not allowed.

## Data Storage
The application uses Java Serialization to save contact information in a file named `contacts.dat`. Saved contacts can be loaded when the application starts again.

## Author
Alagu Priya N.

## Project Type
Java Programming Internship Project