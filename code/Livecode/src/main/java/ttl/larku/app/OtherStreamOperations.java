package ttl.larku.app;

import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.System.out;

/**
 * @author whynot
 */
public class OtherStreamOperations {

    public static void main(String[] args) {
        OtherStreamOperations sortingApp = new OtherStreamOperations();
        //sortingApp.findFirst();
        sortingApp.betterFindFirst();
    }

    private StudentService service;
    public OtherStreamOperations() {
        service = new StudentService();
        DBInit.populateStudents(service);
    }

    public void findFirst() {

        List<Student> students = service.getAllStudents();

        Optional<Student> allPhoneNumbers = students.stream()
                .filter(s -> s.getStatus() == Student.Status.PART_TIME)
                .findFirst();

        if(allPhoneNumbers.isPresent()) {
            var result = allPhoneNumbers.get();
            out.println("result: " + result);
        }
    }

    public void betterFindFirst() {

        List<Student> students = service.getAllStudents();

        students.stream()
                .filter(s -> s.getStatus() == Student.Status.PART_TIME)
                .findFirst()
                .ifPresent(s -> out.println(s));

        //If present get result, else get default result
        Student s = students.stream()
                .filter(st -> st.getStatus() == Student.Status.PART_TIME)
                .findFirst()
                .orElse(null);

        Student s2 = students.stream()
                .filter(st -> st.getStatus() == Student.Status.PART_TIME)
                .findFirst()
                .orElseThrow();

    }

    public void findMaxStudent() {

        List<Student> students = service.getAllStudents();

        var result = students.stream()
                .max((s1, s2) -> s1.getName().compareTo(s2.getName()));

    }

}
