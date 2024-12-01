import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class GradeManagementSystem extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private String userType;

    private Student[] students = {
            new Student.InPersonStudent("Alice", 20, 2025, "Computer Science", 1234, 'B', 85, 100, new boolean[30], "a1234", "password1234"),
            new Student.RemoteStudent("Bob", 22, 2024, "Engineering", 6789, 'C', 75, 100, new boolean[30], "b6789", "password6789", "New York"),
            new Student.ExchangeStudent("Charlie", 21, 2023, "Business", 1112, 'D', 65, 100, new boolean[30], "c1112", "password1112", "Proficient")
    };

    private Teacher[] teachers = {
            new Teacher("Edd U. Cator", "Master's in Computer Science from UCLA", "Computer Science", "ec2024", "TeacherPassword54")
    };

    Student studentUser;
    Teacher teacherUser;

    public GradeManagementSystem() {
        setTitle("Grade Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create sample classrooms and assign to teacher
        ClassRoom classRoom1 = new ClassRoom("Auditorium", 30, teachers[0]);
        ClassRoom classRoom2 = new ClassRoom("Lab", 20, teachers[0]);

        // Add students to classrooms
        classRoom1.addStudent(students[0]);
        classRoom1.addStudent(students[1]);
        classRoom2.addStudent(students[2]);

        // Assign classrooms to teacher
        teachers[0].addClassRoom(classRoom1);
        teachers[0].addClassRoom(classRoom2);

        JPanel loginPanel = createLoginPanel();
        cardPanel.add(loginPanel, "LoginPanel");

        // Adds the cardPanel to the JFrame!
        add(cardPanel);
        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel userIDLabel = new JLabel("User ID:");
        JLabel passwordLabel = new JLabel("Password:");
        JTextField userIDField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(userIDLabel, gbc);
        gbc.gridx = 1;
        panel.add(userIDField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userID = userIDField.getText();
                String password = new String(passwordField.getPassword());
                if (authenticateStudent(userID, password)) {
                    JOptionPane.showMessageDialog(panel, "Student login successful!");
                    StudentGUI studentMenu = new StudentGUI(studentUser);
                    cardPanel.add(studentMenu, "StudentPanel");
                    cardLayout.show(cardPanel, "StudentPanel");
                    userIDField.setText("");
                    passwordField.setText("");

                    JButton logoutButton = new JButton("Logout");
                    studentMenu.add(logoutButton);
                    logoutButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cardLayout.show(cardPanel, "LoginPanel");
                            userType = null;
                            cardPanel.remove(studentMenu);
                        }
                    });
                } else if (authenticateTeacher(userID, password)) {
                    JOptionPane.showMessageDialog(panel, "Teacher login successful!");
                    userIDField.setText("");
                    passwordField.setText("");
                    JPanel teacherMenu = createTeacherMenu();
                    cardPanel.add(teacherMenu, "TeacherMenu");
                    cardLayout.show(cardPanel, "TeacherMenu");
                    userType = "Teacher";
                } else {
                    userIDField.setText("");
                    passwordField.setText("");
                    JOptionPane.showMessageDialog(panel, "Invalid user or password!");
                }
            }
        });

        return panel;
    }
    private JPanel createTeacherMenu() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Components for classroom selection
        JLabel classroomLabel = new JLabel("Select Classroom: ");
        JComboBox<String> classroomComboBox = new JComboBox<>();
        for (ClassRoom cr : teacherUser.getClassRooms()) {
            classroomComboBox.addItem(cr.getRoomType());
        }

        // Components for student selection
        JLabel studentLabel = new JLabel("Select Student: ");
        JComboBox<String> studentComboBox = new JComboBox<>();

        // Update student list based on selected classroom
        classroomComboBox.addActionListener(e -> {
            studentComboBox.removeAllItems();
            String selectedClassroom = (String) classroomComboBox.getSelectedItem();
            for (ClassRoom cr : teacherUser.getClassRooms()) {
                if (cr.getRoomType().equals(selectedClassroom)) {
                    for (Student s : cr.getStudentsInClassroom()) {
                        studentComboBox.addItem(s.getName() + " (" + s.getUserID() + ")");
                    }
                    break;
                }
            }
        });

        // Initialize student list
        if (classroomComboBox.getItemCount() > 0) {
            classroomComboBox.setSelectedIndex(0);
        }

        JButton updateAttendanceButton = new JButton("Update Attendance");
        JButton viewStudentButton = new JButton("View Student Info");
        JButton logoutButton = new JButton("Logout");

        // Layout components
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(classroomLabel, gbc);
        gbc.gridx = 1;
        panel.add(classroomComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(studentLabel, gbc);
        gbc.gridx = 1;
        panel.add(studentComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(updateAttendanceButton, gbc);
        gbc.gridx = 1;
        panel.add(viewStudentButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(logoutButton, gbc);

        // Action listener for updating attendance
        updateAttendanceButton.addActionListener(e -> {
            String selectedStudentInfo = (String) studentComboBox.getSelectedItem();
            if (selectedStudentInfo != null) {
                String userID = selectedStudentInfo.substring(selectedStudentInfo.indexOf("(") + 1, selectedStudentInfo.indexOf(")"));
                Student selectedStudent = findStudentByUserID(userID);
                if (selectedStudent != null) {
                    showAttendanceCalendar(selectedStudent);
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Please select a student.");
            }
        });

        // Action listener for viewing student info
        viewStudentButton.addActionListener(e -> {
            String selectedStudentInfo = (String) studentComboBox.getSelectedItem();
            if (selectedStudentInfo != null) {
                String userID = selectedStudentInfo.substring(selectedStudentInfo.indexOf("(") + 1, selectedStudentInfo.indexOf(")"));
                Student selectedStudent = findStudentByUserID(userID);
                if (selectedStudent != null) {
                    JPanel studentInfoPanel = StudentGUI.teacherViewOfStudent(teacherUser, selectedStudent);
                    JButton backButton = new JButton("Back");
                    studentInfoPanel.add(backButton);
                    backButton.addActionListener(ae -> {
                        cardLayout.show(cardPanel, "TeacherMenu");
                        cardPanel.remove(studentInfoPanel);
                    });
                    cardPanel.add(studentInfoPanel, "StudentInfoPanel");
                    cardLayout.show(cardPanel, "StudentInfoPanel");
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Please select a student.");
            }
        });

        logoutButton.addActionListener(e -> {
            cardLayout.show(cardPanel, "LoginPanel");
            userType = null;
            cardPanel.remove(panel);
        });

        return panel;
    }

    private Student findStudentByUserID(String userID) {
        for (Student s : students) {
            if (s.getUserID().equals(userID)) {
                return s;
            }
        }
        return null;
    }

    private void showAttendanceCalendar(Student student) {
        JFrame calendarFrame = new JFrame("Attendance Calendar for " + student.getName());
        calendarFrame.setSize(400, 400);
        calendarFrame.setLayout(new BorderLayout());

        JPanel calendarPanel = new JPanel(new GridLayout(6, 7));
        JCheckBox[] dayCheckBoxes = new JCheckBox[30];

        boolean[] attendance = student.getAttendance();
        for (int i = 0; i < 30; i++) {
            String dayLabel = "Day " + (i + 1);
            boolean attended = i < attendance.length && attendance[i];
            dayCheckBoxes[i] = new JCheckBox(dayLabel, attended);
            calendarPanel.add(dayCheckBoxes[i]);
        }

        JButton saveButton = new JButton("Save Attendance");
        saveButton.addActionListener(e -> {
            boolean[] newAttendance = new boolean[30];
            for (int i = 0; i < 30; i++) {
                newAttendance[i] = dayCheckBoxes[i].isSelected();
            }
            student.setAttendance(newAttendance);
            JOptionPane.showMessageDialog(calendarFrame, "Attendance updated successfully.");
            calendarFrame.dispose();
        });

        calendarFrame.add(new JScrollPane(calendarPanel), BorderLayout.CENTER);
        calendarFrame.add(saveButton, BorderLayout.SOUTH);
        calendarFrame.setVisible(true);
    }

    private boolean authenticateStudent(String userID, String password) {
        for (Student student : students) {
            if (student.getUserID().equals(userID) && student.getPassword().equals(password)) {
                studentUser = student;
                return true;
            }
        }
        return false;
    }

    private boolean authenticateTeacher(String userID, String password) {
        for (Teacher teacher : teachers) {
            if (teacher.getUserID().equals(userID) && teacher.getPassword().equals(password)) {
                teacherUser = teacher;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        GradeManagementSystem gradeManagementSystem = new GradeManagementSystem();
    }
}