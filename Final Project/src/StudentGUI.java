import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGUI extends JPanel {
    protected JLabel userIDLabel, switchMajorLabel;
    protected JButton attendanceButton, checkGradeButton, checkPassingButton, infoButton;
    protected JTextField attendancePercentageTextField, gradeTextField, checkPassingTextField;
    protected JComboBox<String> switchMajorComboBox;
    String[] majors = {
            "Business", "Nursing", "Psychology", "Biology", "Engineering",
            "Communication", "Finance", "Comp Sci", "Economics", "History", "English"
    };

    public StudentGUI(Student student) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.insets = new Insets(5, 5, 40, 5);
        gbc.gridx = 1;
        gbc.gridy = 0;
        userIDLabel = new JLabel("Hello " + student.getName() + "   (User ID: " + student.getUserID() + ")");
        add(userIDLabel, gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        attendanceButton = new JButton("Attendance %");
        add(attendanceButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        attendancePercentageTextField = new JTextField(10);
        attendancePercentageTextField.setEditable(false);
        add(attendancePercentageTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        checkGradeButton = new JButton("Check Grade: ");
        add(checkGradeButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gradeTextField = new JTextField(10);
        gradeTextField.setEditable(false);
        add(gradeTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        checkPassingButton = new JButton("Check Passing Status");
        add(checkPassingButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        checkPassingTextField = new JTextField(10);
        checkPassingTextField.setEditable(false);
        add(checkPassingTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        switchMajorLabel = new JLabel("Switch Major: ");
        add(switchMajorLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        switchMajorComboBox = new JComboBox<>(majors);
        switchMajorComboBox.setSelectedItem(student.getMajor());
        add(switchMajorComboBox, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        infoButton = new JButton("Student info");
        add(infoButton, gbc);

        // Additional components for RemoteStudent
        JButton vpnButton = null;
        if (student instanceof Student.RemoteStudent) {
            Student.RemoteStudent remoteStudent = (Student.RemoteStudent) student;
            vpnButton = new JButton("Use VPN");
            gbc.gridx = 2;
            gbc.gridy = 2;
            add(vpnButton, gbc);

            vpnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remoteStudent.useVPN();
                    JOptionPane.showMessageDialog(null, "Location is now hidden");
                }
            });
        }

        attendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float percent = student.calculateAttendance();
                String formattedPercent = String.format("%.1f", percent);
                attendancePercentageTextField.setText(formattedPercent + "%");
            }
        });

        checkGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                student.updateLetterGrade();
                double percent = student.calculatePercentGrade();
                String formattedPercent = String.format("%.1f", percent);
                gradeTextField.setText(student.getLetterGrade() + ", " + formattedPercent + "%");
            }
        });

        checkPassingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean b = student.checkPassing();
                if (b) {
                    checkPassingTextField.setText("Passing! Congrats");
                } else {
                    checkPassingTextField.setText("Failing.");
                }
            }
        });

        switchMajorComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newMajor = (String) switchMajorComboBox.getSelectedItem();
                student.switchMajor(newMajor);
            }
        });

        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, student.returnStudentInfo());
            }
        });
    }

    public static JPanel teacherViewOfStudent(Teacher teacher, Student student) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userIDLabel, switchMajorLabel;
        JButton attendanceButton, checkGradeButton, checkPassingButton, updateGradeButton, updateAttendanceButton;
        JTextField attendancePercentageTextField, gradeTextField, checkPassingTextField;

        gbc.insets = new Insets(5, 5, 40, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Hello " + teacher.getName() + "!"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        userIDLabel = new JLabel("Currently viewing " + student.getName() + "'s info. (User ID: " + student.getUserID() + ")");
        panel.add(userIDLabel, gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        attendanceButton = new JButton("Attendance %");
        panel.add(attendanceButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        attendancePercentageTextField = new JTextField(10);
        attendancePercentageTextField.setEditable(false);
        panel.add(attendancePercentageTextField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        updateAttendanceButton = new JButton("Update Attendance");
        panel.add(updateAttendanceButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        checkGradeButton = new JButton("Check Grade: ");
        panel.add(checkGradeButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gradeTextField = new JTextField(10);
        gradeTextField.setEditable(false);
        panel.add(gradeTextField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        updateGradeButton = new JButton("Update Grade");
        panel.add(updateGradeButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        checkPassingButton = new JButton("Check Passing Status");
        panel.add(checkPassingButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        checkPassingTextField = new JTextField(10);
        checkPassingTextField.setEditable(false);
        panel.add(checkPassingTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        switchMajorLabel = new JLabel("Major: ");
        panel.add(switchMajorLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(new JLabel(student.getMajor()), gbc);

        attendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float percent = student.calculateAttendance();
                String formattedPercent = String.format("%.1f", percent);
                attendancePercentageTextField.setText(formattedPercent + "%");
            }
        });

        checkGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                student.updateLetterGrade();
                double percent = student.calculatePercentGrade();
                String formattedPercent = String.format("%.1f", percent);
                gradeTextField.setText(student.getLetterGrade() + ", " + formattedPercent + "%");
            }
        });

        checkPassingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean b = student.checkPassing();
                if (b) {
                    checkPassingTextField.setText("The student is passing!");
                } else {
                    checkPassingTextField.setText("The student is failing.");
                }
            }
        });

        updateGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newGradeStr = JOptionPane.showInputDialog(panel, "Enter new current points:");
                if (newGradeStr != null) {
                    try {
                        float newPoints = Float.parseFloat(newGradeStr);
                        student.setCurrentPoints(newPoints);
                        student.updateLetterGrade();
                        JOptionPane.showMessageDialog(panel, "Grade updated successfully.");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(panel, "Invalid input. Please enter a number.");
                    }
                }
            }
        });

        updateAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show attendance calendar
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
                saveButton.addActionListener(ae -> {
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
        });

        return panel;
    }
}