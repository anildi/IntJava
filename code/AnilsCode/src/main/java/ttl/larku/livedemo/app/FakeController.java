package ttl.larku.livedemo.app;

import ttl.larku.livedemo.domain.Student;
import ttl.larku.livedemo.service.StudentService;

import java.time.LocalDate;
import java.util.List;

/**
 * @author whynot
 */
public class FakeController {

    StudentService ss = new StudentService();
    public static void main(String[] args) {
        FakeController fc = new FakeController();
        Student student = new Student("Joey", LocalDate.of(1999, 10, 10), Student.Status.FULL_TIME);
        fc.postAStudent(student);

        fc.getRequestForAllStudents();
    }

    public void postAStudent(Student student) {
       Student newStudent = ss.createStudent(student);

        List<Student> students = ss.getAllStudents();
        System.out.println("students.size: " + students.size());
        for(Student s : students) {
            System.out.println(s);
        }
    }

    public void getRequestForAllStudents() {
        List<Student> students = ss.getAllStudents();
        System.out.println("GetRequest students.size: " + students.size());
        for(Student s : students) {
            System.out.println(s);
        }
    }
}
