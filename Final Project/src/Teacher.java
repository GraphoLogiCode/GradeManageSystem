
import java.util.ArrayList;
import java.util.List;


public class Teacher {
    private String name;
    private String qualification;
    private String subject;
    private List<ClassRoom> classrooms;
    private String userID;
    private String password;

    public Teacher(String name, String qualification, String subject, String userID, String password) {
        this.name = name;
        this.qualification = qualification;
        this.subject = subject;
        this.userID = userID;
        this.password = password;
        this.classrooms = new ArrayList<>();
    }

    public String returnTeacherInfo() {
        return "Teacher Information:\n" +
                "-------------------\n" +
                "Name: " + name + "\n" +
                "Qualification: " + qualification + "\n" +
                "Subject: " + subject;
    }

    // Methods to manage classrooms
    public void addClassRoom(ClassRoom classRoom) {
        classrooms.add(classRoom);
    }

    public List<ClassRoom> getClassRooms() {
        return classrooms;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    // Additional methods for teacher functionalities can be added here
}