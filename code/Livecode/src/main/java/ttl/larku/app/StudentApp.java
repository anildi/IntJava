package ttl.larku.app;

import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.util.List;

/**
 * @author whynot
 */
public class StudentApp {

    public static void main(String[] args) {
        StudentApp studentApp = new StudentApp();
        studentApp.postAStudent();

        studentApp.getAllStudentRequest();
    }

    StudentService studentService = new StudentService();

    public void postAStudent() {
//        StudentService studentService = new StudentService();
        Student student = new Student("Francine", LocalDate.of(2000, 10, 10));


        studentService.createStudent(student);

        List<Student> students = studentService.getAllStudents();
        System.out.println("students: " + students.size());

        for(Student s: students) {
            System.out.println("s: " + s.toString());
        }
    }

    public void getAllStudentRequest() {
        System.out.println("In Get Request");


        List<Student> students = studentService.getAllStudents();
        System.out.println("students: " + students.size());

        for(Student s: students) {
            System.out.println("s: " + s.toString());
        }
    }

}
