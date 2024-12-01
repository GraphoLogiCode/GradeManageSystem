import java.util.ArrayList;
import java.util.List;

public class ClassRoom {
    private List<Student> studentsInClassroom;
    private String roomType;
    private int capacity;
    private Teacher teacher;

    // Constructor
    public ClassRoom(String roomType, int capacity, Teacher teacher) {
        this.roomType = roomType;
        this.capacity = capacity;
        this.teacher = teacher;
        this.studentsInClassroom = new ArrayList<>();
    }

    // Method to add a student to the classroom
    public void addStudent(Student student) {
        if (studentsInClassroom.size() >= capacity) {
            System.out.println("Cannot add student " + student.getName() + ": Classroom is at full capacity.");
            return;
        }
        if (studentsInClassroom.contains(student)) {
            System.out.println("Student " + student.getName() + " is already in the classroom.");
            return;
        }
        studentsInClassroom.add(student);
        System.out.println("Student " + student.getName() + " has been added to the classroom.");
    }

    // Getters and setters
    public List<Student> getStudentsInClassroom() {
        return studentsInClassroom;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getCapacity() {
        return capacity;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}