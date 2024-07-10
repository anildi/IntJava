package ttl.larku.app;

import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.util.List;

import static java.lang.System.out;

/**
 * @author whynot
 */
public class SortingApp {

    public static void main(String[] args) {
        SortingApp sortingApp = new SortingApp();
        sortingApp.naturalOrderSort();
    }

    public void naturalOrderSort() {
        StudentService service = new StudentService();
        DBInit.populateStudents(service);

        List<Student> students = service.getAllStudents();
        students.forEach(out::println);
    }
}
