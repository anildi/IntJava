package ttl.larku.livedemo.service;

import org.junit.jupiter.api.Test;
import ttl.larku.livedemo.domain.Student;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author whynot
 */
public class StudentServiceTest {

    @Test
    public void testCreateStudent() {
        StudentService ss = new StudentService();
        Student student = new Student("Joey", LocalDate.of(1999, 10, 10), Student.Status.FULL_TIME);

        student = ss.createStudent(student);
        assertTrue(student.getId() > 0);

        List<Student> all = ss.getAllStudents();
        assertEquals(1, all.size());
    }
}
