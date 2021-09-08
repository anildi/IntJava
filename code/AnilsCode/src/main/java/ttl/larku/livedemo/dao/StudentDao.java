package ttl.larku.livedemo.dao;

import ttl.larku.livedemo.domain.Student;

import java.util.List;

/**
 * @author whynot
 */
public interface StudentDao {
    Student insert(Student student);

    boolean delete(int id);

    //TODO - fixme
    boolean update(Student student);

    Student get(int id);

    List<Student> getAll();
}
