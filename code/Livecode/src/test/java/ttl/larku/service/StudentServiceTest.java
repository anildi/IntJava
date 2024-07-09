package ttl.larku.service;

import org.junit.Test;
import ttl.larku.domain.Student;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author whynot
 */
public class StudentServiceTest {


    @Test
    public void testCreateStudent() {
        StudentService studentService = new StudentService();
        Student student = new Student("Francine", LocalDate.of(2000, 10, 10));

        Student added = studentService.createStudent(student);

        assertTrue(student.getId() != 0);

        int count = studentService.getAllStudents().size();
        assertEquals(1, count);
    }
}
