package ttl.larku.dao;

import ttl.larku.domain.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author whynot
 */
public class MysqlStudentDAO implements StudentDAO{

    //private Map<Integer, Student> students = new HashMap<>();
    private Map<Integer, Student> students = new ConcurrentHashMap<>();
    //private int nextId = 1;
    private AtomicInteger nextId = new AtomicInteger(1);

//    private List<Student> studentList = new ArrayList<>();

    public Student insert(Student student) {
        //jint id = nextId++;
        int id = nextId.getAndIncrement();
        student.setId(id);
        student.setName("Mysql " + student.getName());
        students.put(student.getId(), student);

        return student;
    }

    public boolean update(Student student) {
        return students.replace(student.getId(), student) != null;
    }

    public boolean delete(int id) {
        return students.remove(id) != null;
    }

    public Student findById(int id) {
        return students.get(id);
    }

    public List<Student> findAll() {
      return new ArrayList<>(students.values());
    }

    public int count() {
        return students.size();
    }
}
