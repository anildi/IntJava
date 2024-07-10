package ttl.larku.app;

import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author whynot
 */
public class StreamGroupingApp {

    public static void main(String[] args) {
        StreamGroupingApp sortingApp = new StreamGroupingApp();
//        sortingApp.flatMapByHand();
        //sortingApp.groupingBy();
        sortingApp.groupingByCount();
    }

    private StudentService service;

    public StreamGroupingApp() {
        service = new StudentService();
        DBInit.populateStudents(service);
    }

    public void groupingBy() {
        List<Student> students = service.getAllStudents();

        var result = students.stream()
                .collect(Collectors.groupingBy(s -> s.getStatus()));

        result.forEach((k, v) -> System.out.println("k:" + k + ", v: " + v));

    }

    public void groupingByCount() {
        List<Student> students = service.getAllStudents();

        var result = students.stream()
                .collect(Collectors.groupingBy(s -> s.getStatus(), Collectors.counting()));

        result.forEach((k, v) -> System.out.println("k:" + k + ", v: " + v));

    }
}

