package ttl.larku.dao;

import ttl.larku.domain.Student;

import java.util.List;

/**
 * @author whynot
 */
public interface StudentDAO {
    Student insert(Student student);

    boolean update(Student student);

    boolean delete(int id);

    Student findById(int id);

    List<Student> findAll();

    int count();
}
