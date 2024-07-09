package ttl.larku.domain;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author whynot
 */
public class StudentTest {

    @Test
    public void testCreateStudent() {
        Student student = new Student();
        student.setId(10);
        student.setName("Frank");

        assertEquals(10, student.getId());

        assertNull(student.getDob());
    }

    @Test
    public void testCreateStudentWithAllArgsConstructor() {
        Student student = new Student(10, "Frank", LocalDate.of(2000, 10, 10),
                Student.Status.FULL_TIME, new ArrayList<>());
        student.setId(10);
        student.setName("Frank");

        assertEquals(10, student.getId());

        assertNotNull(student.getDob());
    }

    @Test
    public void testCreateStudentWithNoIdConstructor() {
        Student student = new Student("Frank", LocalDate.of(2000, 10, 10));

        assertEquals(0, student.getId());
        assertEquals("Frank", student.getName());

        assertNotNull(student.getDob());
    }
}
