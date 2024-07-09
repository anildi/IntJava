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
public class InMemoryStudentDAO implements StudentDAO {

    //private Map<Integer, Student> students = new HashMap<>();
    private Map<Integer, Student> students = new ConcurrentHashMap<>();
    //private int nextId = 1;
    private AtomicInteger nextId = new AtomicInteger(1);

//    private List<Student> studentList = new ArrayList<>();

    @Override
    public Student insert(Student student) {
        //jint id = nextId++;
        int id = nextId.getAndIncrement();
        student.setId(id);
        student.setName("InMemory " + student.getName());
        students.put(student.getId(), student);

        return student;
    }

    @Override
    public boolean update(Student student) {
        return students.replace(student.getId(), student) != null;
    }

    @Override
    public boolean delete(int id) {
        return students.remove(id) != null;
    }

    @Override
    public Student findById(int id) {
        return students.get(id);
    }

    @Override
    public List<Student> findAll() {
      return new ArrayList<>(students.values());
    }

    @Override
    public int count() {
        return students.size();
    }
}
