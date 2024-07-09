package ttl.larku.service;

import ttl.larku.dao.StudentDAO;
import ttl.larku.domain.Student;

import java.util.List;

/**
 * @author whynot
 */
public class StudentService {

    private StudentDAO studentDAO = new StudentDAO();

    public Student createStudent(Student student) {
        return studentDAO.insert(student);
    }

    public Student getStudent(int id) {
        return studentDAO.findById(id);
    }

    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }
}
