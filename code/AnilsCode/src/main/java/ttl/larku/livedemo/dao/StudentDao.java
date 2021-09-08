package ttl.larku.livedemo.dao;

import ttl.larku.livedemo.domain.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author whynot
 */
public class StudentDao {
    private Map<Integer, Student> students = new HashMap<>();

    public Student insert(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    public boolean delete(int id) {
        return students.remove(id) != null;
    }

    //TODO - fixme
    public boolean update(Student student) {
        if(!students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return true;
        }
        return false;
    }

    public Student get(int id) {
        return students.get(id);
    }

    public List<Student> getAll() {
        List<Student> all = new ArrayList<>(students.values());
        return all;
    }
}

