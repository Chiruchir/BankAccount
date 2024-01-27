import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
class Student {
    private String studentId;
    private String name;
    private Map<Course, Grade> enrolledCourses;
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.enrolledCourses = new HashMap<>();
    }
    public String getStudentId() {
        return studentId;
    }
    public String getName() {
        return name;
    }
    public void enrollInCourse(Course course) {
        enrolledCourses.put(course, new Grade());
    }
    public void assignGrade(Course course, double gradeValue) {
        Grade grade = enrolledCourses.get(course);
        if (grade != null) {
            grade.setGradeValue(gradeValue);
        }
    }
    public double calculateGPA() {
        double totalGradePoints = 0.0;
        int totalCredits = 0;

        for (Map.Entry<Course, Grade> entry : enrolledCourses.entrySet()) {
            Course course = entry.getKey();
            Grade grade = entry.getValue();
            totalGradePoints += grade.getGradeValue() * course.getCredits();
            totalCredits += course.getCredits();
        }
        return totalCredits != 0 ? totalGradePoints / totalCredits : 0.0;
    }
}
class Course {
    private String courseId;
    private String courseName;
    private int credits;
    public Course(String courseId, String courseName, int credits) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
    }
    public String getCourseId() {
        return courseId;
    }
    public String getCourseName() {
        return courseName;
    }
    public int getCredits() {
        return credits;
    }
}
class Grade {
    private double gradeValue;
    public double getGradeValue() {
        return gradeValue;
    }
    public void setGradeValue(double gradeValue) {
        if (gradeValue >= 0.0 && gradeValue <= 4.0) {
            this.gradeValue = gradeValue;
        } else {
            System.out.println("Invalid grade value. Grade value should be between 0.0 and 4.0.");
        }
    }
}
public class StudentManagementSystem {
    public static void main(String[] args) {
        // Sample usage
        Student student1 = new Student("S001", "John Doe");
        Course mathCourse = new Course("C001", "Mathematics", 3);
        Course physicsCourse = new Course("C002", "Physics", 4);
        student1.enrollInCourse(mathCourse);
        student1.enrollInCourse(physicsCourse);
        student1.assignGrade(mathCourse, 3.5);
        student1.assignGrade(physicsCourse, 4.0);
        System.out.println("Student: " + student1.getName());
        System.out.println("GPA: " + student1.calculateGPA());
    }
}
