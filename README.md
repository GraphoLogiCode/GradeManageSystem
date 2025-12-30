# GradeManageSystem

A Java Swing-based desktop application for managing student grades, attendance, and classroom information. 

## Features

- **Dual User Roles**:  Separate login interfaces for Students and Teachers
- **Student Management**: Track grades, attendance, and student information
- **Classroom Organization**: Manage multiple classrooms with assigned students
- **Attendance Tracking**: Interactive 30-day attendance calendar
- **Student Types**: Support for In-Person, Remote, and Exchange students
- **Secure Authentication**: User ID and password-based login system

## System Requirements

- Java Development Kit (JDK) 8 or higher
- Java Swing (included in JDK)

## Project Structure

```
Final Project/
├── src/
│   ├── GradeManagementSystem.java  # Main application & GUI
│   ├── Student.java                # Student class hierarchy
│   ├── StudentGUI.java             # Student interface panels
│   ├── Teacher. java                # Teacher management
│   └── ClassRoom.java              # Classroom management
└── out/                            # Compiled classes
```

## Usage

### Running the Application

```bash
cd "Final Project/src"
javac *.java
java GradeManagementSystem
```

### Demo Credentials

**Students:**
- Alice: `a1234` / `password1234`
- Bob: `b6789` / `password6789`
- Charlie: `c1112` / `password1112`

**Teacher:**
- Edd U. Cator: `ec2024` / `TeacherPassword54`

## Functionality

### Teacher Capabilities
- View all classrooms and enrolled students
- Update student attendance via interactive calendar
- View detailed student information
- Manage multiple classrooms simultaneously

### Student Capabilities
- View personal grade information
- Check attendance records
- Access profile details

## Classes Overview

| Class | Description |
|-------|-------------|
| `GradeManagementSystem` | Main application with login and navigation |
| `Student` | Base class with subclasses for different student types |
| `Teacher` | Manages teacher data and classroom assignments |
| `ClassRoom` | Handles classroom roster and capacity |
| `StudentGUI` | Student-facing interface components |

## License

This project is open source and available for educational purposes.
