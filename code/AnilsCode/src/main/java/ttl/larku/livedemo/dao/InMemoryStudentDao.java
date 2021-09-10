package ttl.larku.livedemo.dao;

import ttl.larku.livedemo.domain.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author whynot
 */
public class InMemoryStudentDao implements StudentDao {
    private Map<Integer, Student> students = new ConcurrentHashMap<>();
    //private static int nextId = 1;
    private AtomicInteger nextId = new AtomicInteger(1);

    //TODO - fixme
    @Override
    public boolean update(Student student) {
        return students.computeIfPresent(student.getId(), (key, oldValue) -> student) != null;

//        if(students.containsKey(student.getId())) {
//            students.put(student.getId(), student);
//            return true;
//        }
//        return false;
    }

    @Override
    public Student insert(Student student) {
//        int id = nextId++;
        int id = nextId.getAndIncrement();
        student.setName("InMem: " + student.getName());
        student.setId(id);
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public boolean delete(int id) {
        return students.remove(id) != null;
    }


    @Override
    public Student get(int id) {
        return students.get(id);
    }

    private List<Student> studentsToo = new ArrayList<>();

    @Override
    public List<Student> getAll() {
//        return List.copyOf(studentsToo);
        List<Student> all = List.copyOf(students.values());
        return all;
    }
}









