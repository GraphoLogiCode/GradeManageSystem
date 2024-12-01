import java.util.Arrays;


public abstract class Student {
    private String name;
    private static int age;
    private static int gradYear;
    private String major;
    private int studentID;
    private char letterGrade;
    private float currentPoints;
    private float maxPoints;
    private boolean[] attendance;
    private String userID;
    private String password;

    // Default constructor
    public Student() {
        this.name = "Stew Dent";
        this.age = 18;
        this.gradYear = 2028;
        this.major = "Computer Science";
        this.studentID = 1893;
        this.letterGrade = 'A';
        this.currentPoints = 100;
        this.maxPoints = 100;
        this.attendance = new boolean[30]; // Default to 30 days
        Arrays.fill(this.attendance, false);
        this.userID = "sd1893";
        this.password = "password1234";
    }

    public Student(String name, int age, int gradYear, String major, int studentID, char letterGrade,
                   float currentPoints, float maxPoints, boolean[] attendance, String userID, String password) {
        this.name = name;
        this.age = age;
        this.gradYear = gradYear;
        this.major = major;
        this.studentID = studentID;
        this.letterGrade = letterGrade;
        this.currentPoints = currentPoints;
        this.maxPoints = maxPoints;
        this.attendance = attendance;
        this.userID = userID;
        this.password = password;
    }

    // Abstract method to be implemented by subclasses
    public abstract String returnStudentInfo();

    public float calculateAttendance() {
        int presentDays = 0;
        for (boolean day : attendance) {
            if (day) {
                presentDays++;
            }
        }
        float percentage = (float) presentDays / attendance.length * 100;
        return Math.round(percentage * 10) / 10.0f;
    }

    public double calculatePercentGrade() {
        double percent = (double) currentPoints / maxPoints * 100;
        return Math.round(percent * 10) / 10.0;
    }

    public void updateLetterGrade() {
        float gradePercentage = (currentPoints / maxPoints) * 100;
        if (gradePercentage >= 90) {
            letterGrade = 'A';
        } else if (gradePercentage >= 80) {
            letterGrade = 'B';
        } else if (gradePercentage >= 70) {
            letterGrade = 'C';
        } else if (gradePercentage >= 60) {
            letterGrade = 'D';
        } else {
            letterGrade = 'F';
        }
    }

    public boolean checkPassing() {
        return letterGrade == 'A' || letterGrade == 'B' || letterGrade == 'C';
    }

    public void switchMajor(String newMajor) {
        major = newMajor;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getStudentID() {
        return studentID;
    }

    public char getLetterGrade() {
        return letterGrade;
    }

    public String getMajor() {
        return major;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public boolean[] getAttendance() {
        return attendance;
    }

    public void setAttendance(boolean[] attendance) {
        this.attendance = attendance;
    }

    public float getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(float currentPoints) {
        this.currentPoints = currentPoints;
    }

    public float getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(float maxPoints) {
        this.maxPoints = maxPoints;
    }

    public void setLetterGrade(char letterGrade) {
        this.letterGrade = letterGrade;
    }
    public static class InPersonStudent extends Student {

        // Parameterized constructor
        public InPersonStudent(String name, int age, int gradYear, String major, int studentID, char letterGrade,
                               float currentPoints, float maxPoints, boolean[] attendance, String userID, String password) {
            super(name, age, gradYear, major, studentID, letterGrade, currentPoints, maxPoints, attendance, userID, password);
        }

        @Override
        public String returnStudentInfo() {
            return "Student Information:\n" +
                    "-------------------\n" +
                    "Name: " + getName() + "\n" +
                    "Age: " + age + "\n" +
                    "Graduation Year: " + gradYear + "\n" +
                    "Major: " + getMajor() + "\n" +
                    "Student ID: " + getStudentID() + "\n" +
                    "Letter Grade: " + getLetterGrade() + "\n" +
                    "Points: " + getCurrentPoints() + "/" + getMaxPoints() + "\n" +
                    "Attendance: " + calculateAttendance() + "%\n" +
                    "User ID: " + getUserID() + "\n" +
                    "Password: info protected";
        }
    }

    public static class RemoteStudent extends Student {
        private String location;

        // Parameterized constructor
        public RemoteStudent(String name, int age, int gradYear, String major, int studentID,
                             char letterGrade, float currentPoints, float maxPoints, boolean[] attendance,
                             String userID, String password, String location) {
            super(name, age, gradYear, major, studentID, letterGrade, currentPoints, maxPoints,
                    attendance, userID, password);
            this.location = location;
        }

        @Override
        public String returnStudentInfo() {
            return "Student Information:\n" +
                    "-------------------\n" +
                    "Name: " + getName() + "\n" +
                    "Age: " + age + "\n" +
                    "Graduation Year: " + gradYear + "\n" +
                    "Major: " + getMajor() + "\n" +
                    "Student ID: " + getStudentID() + "\n" +
                    "Letter Grade: " + getLetterGrade() + "\n" +
                    "Points: " + getCurrentPoints() + "/" + getMaxPoints() + "\n" +
                    "Attendance: " + calculateAttendance() + "%\n" +
                    "Location: " + location + "\n" +
                    "User ID: " + getUserID() + "\n" +
                    "Password: info protected";
        }

        public void setLocation(String newLocation) {
            location = newLocation;
        }

        public void useVPN() {
            location = "hidden";
        }
    }

    public static class ExchangeStudent extends Student {
        private String englishAbility;

        // Parameterized constructor
        public ExchangeStudent(String name, int age, int gradYear, String major, int studentID, char letterGrade,
                               float currentPoints, float maxPoints, boolean[] attendance, String userID, String password, String englishAbility) {
            super(name, age, gradYear, major, studentID, letterGrade, currentPoints,
                    maxPoints, attendance, userID, password);
            this.englishAbility = englishAbility;
        }

        @Override
        public String returnStudentInfo() {
            return "Student Information:\n" +
                    "-------------------\n" +
                    "Name: " + getName() + "\n" +
                    "Age: " + age + "\n" +
                    "Graduation Year: " + gradYear + "\n" +
                    "Major: " + getMajor() + "\n" +
                    "Student ID: " + getStudentID() + "\n" +
                    "Letter Grade: " + getLetterGrade() + "\n" +
                    "Points: " + getCurrentPoints() + "/" + getMaxPoints() + "\n" +
                    "Attendance: " + String.format("%.2f", calculateAttendance()) + "%\n" +
                    "English Ability: " + englishAbility + "\n" +
                    "User ID: " + getUserID() + "\n" +
                    "Password: info protected";
        }
    }
}
