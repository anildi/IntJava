package ttl.larku.livedemo.dao;

import org.junit.jupiter.api.Test;
import ttl.larku.livedemo.domain.Student;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author whynot
 */
public class StudentDaoTest {

    @Test
    public void testCreateAndGet() {
        InMemoryStudentDao studentDao = new InMemoryStudentDao();
        //Student student = new Student(1, "Joey", LocalDate.of(1999, 10, 10), Student.Status.FULL_TIME);
        Student student = new Student("Joey", LocalDate.of(1999, 10, 10), Student.Status.FULL_TIME);

        student = studentDao.insert(student);
        //assertEquals(1, student.getId());
        assertTrue(student.getId() > 0);

        Student inserted = studentDao.get(student.getId());

        assertTrue(inserted.getName().contains("Joey"));
        assertEquals(1, studentDao.getAll().size());
    }

    @Test
    public void testUpdateStudentGood() {
        InMemoryStudentDao studentDao = new InMemoryStudentDao();
        Student student = new Student("Joey", LocalDate.of(1999, 10, 10), Student.Status.FULL_TIME);

        student = studentDao.insert(student);

        Student updateAble = studentDao.get(student.getId());

        updateAble.setStatus(Student.Status.HIBERNATING);

        boolean result = studentDao.update(updateAble);
        assertTrue(result);

        Student finalStudent = studentDao.get(student.getId());

        assertEquals(Student.Status.HIBERNATING, finalStudent.getStatus());
        //assertEquals(1, studentDao.getAll().size());
        assertEquals(1, studentDao.getAll().size());
    }

    @Test
    public void testUpdateStudentNonExistent() {
        InMemoryStudentDao studentDao = new InMemoryStudentDao();
        Student student = new Student("Joey", LocalDate.of(1999, 10, 10), Student.Status.FULL_TIME);

        studentDao.insert(student);

        Student updateAble = studentDao.get(student.getId());

        updateAble.setId(9999);
        updateAble.setStatus(Student.Status.HIBERNATING);

        boolean result = studentDao.update(updateAble);
        assertFalse(result);
    }

}

