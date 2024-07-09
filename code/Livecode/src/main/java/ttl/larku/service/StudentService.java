package ttl.larku.service;

import ttl.larku.dao.DaoFactory;
import ttl.larku.dao.InMemoryStudentDAO;
import ttl.larku.dao.MysqlStudentDAO;
import ttl.larku.dao.StudentDAO;
import ttl.larku.domain.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author whynot
 */
public class StudentService {

    List<String> someList = new ArrayList<>();
//    ArrayList<String> someList = new ArrayList<>();

    //    private InMemoryStudentDAO studentDAO = new InMemoryStudentDAO();
    //private MysqlStudentDAO studentDAO = new MysqlStudentDAO();
//    private StudentDAO studentDAO = new MysqlStudentDAO();
//    private StudentDAO studentDAO = new InMemoryStudentDAO();

    private StudentDAO studentDAO;

    public StudentService() {
        studentDAO = DaoFactory.studentDAO();

        var dao2 = DaoFactory.studentDAO();


        System.out.println("Made the daos");
    }

    public Student createStudent(Student student) {
        return studentDAO.insert(student);
    }

    public Student getStudent(int id) {
        return studentDAO.findById(id);
    }

    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }


    //    public InMemoryStudentDAO getStudentDAO() {
//    public MysqlStudentDAO getStudentDAO() {
    public StudentDAO getStudentDAO() {
        return studentDAO;
    }
}
