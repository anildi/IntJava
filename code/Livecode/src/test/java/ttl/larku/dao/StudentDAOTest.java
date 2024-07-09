package ttl.larku.dao;

import org.junit.Test;
import ttl.larku.domain.Student;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author whynot
 */
public class StudentDAOTest {

    private StudentDAO dao = new StudentDAO();

    @Test
    public void testInsertStudent() {
        Student student1 = new Student("Zuzu", LocalDate.of(1960, 8, 3));
        dao.insert(student1);

        Student student2 = new Student("Sammy", LocalDate.of(1960, 8, 3));
        dao.insert(student2);

        int count = dao.count();

        assertEquals(2, count);

        List<Student> allStudents = dao.findAll();
        assertEquals(2, allStudents.size());
    }

    @Test
    public void testFindById() {
        Student student1 = new Student("Zuzu", LocalDate.of(1960, 8, 3));
        dao.insert(student1);

        Student found   = dao.findById(student1.getId());
        assertNotNull(found);

        Student nonExisting = dao.findById(2000);
        assertNull(nonExisting);
    }
}
