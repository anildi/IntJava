package ttl.larku.livedemo.service;

import ttl.larku.livedemo.dao.DaoFactory;
import ttl.larku.livedemo.dao.InMemoryStudentDao;
import ttl.larku.livedemo.dao.JPAStudentDao;
import ttl.larku.livedemo.dao.StudentDao;
import ttl.larku.livedemo.domain.Student;

import java.util.List;

/**
 * @author whynot
 */
public class StudentService {

    private StudentDao studentDao = DaoFactory.studentDao();
//    private StudentDao studentDao = new JPAStudentDao();

    public Student createStudent(Student student) {
        return studentDao.insert(student);
    }

    public Student getStudent(int id) {
        return studentDao.get(id);
    }

    public List<Student> getAllStudents() {
        return studentDao.getAll();
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
}


