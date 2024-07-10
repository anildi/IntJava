package ttl.larku.app;

import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.lang.System.out;

/**
 * @author whynot
 */
public class StreamsApp {

    public static void main(String[] args) {
        StreamsApp sortingApp = new StreamsApp();
        sortingApp.composeOperationsWithStream();
    }

    public void findAge() {
        StudentService service = new StudentService();
        DBInit.populateStudents(service);

        List<Student> students = service.getAllStudents();

        Student s1 = students.get(0);

        long age = s1.getDob().until(LocalDate.now(), ChronoUnit.YEARS);

    }

    public void composeOperationsWithStream() {
        StudentService service = new StudentService();
        DBInit.populateStudents(service);

        List<Student> students = service.getAllStudents();

        List<String> result = new ArrayList<>();
        for(Student s : students) {
            if(s.getName().startsWith("M")) {
                result.add(s.getName());
            }
        }

        List<String> namesOnly = students.stream()
                .peek(s -> out.println("Peek1: " + s))
                .filter(s -> s.getName().startsWith("M"))
                .peek(s -> out.println("Peek2: " + s))
                .map(s -> s.getName())
                .peek(s -> out.println("Peek3: " + s))
                .toList();

//        namesOnly.forEach(out::println);
        var st = students.stream()
                .peek(s -> out.println("Peek1: " + s))
                .filter(s -> s.getName().startsWith("M"))
//                .peek(s -> out.println("Peek2: " + s))
//                .map(s -> s.getName())
                .peek(s -> out.println("Peek3: " + s))
                .count();

    }

    public void composeOperationsByHand() {
        StudentService service = new StudentService();
        DBInit.populateStudents(service);

        List<Student> students = service.getAllStudents();

        List<Student> withM = bestChecker(students, s -> s.getName().startsWith("M"));
        List<String> namesOnly = bestGetProps(withM, s -> s.getName());

        List<String> nameOnly2 = bestGetProps(bestChecker(students, s -> s.getName().startsWith("M")), s -> s.getName());

        nameOnly2.forEach(out::println);

    }

    public <T, R> List<R> bestGetProps(List<T> input, Function<T, R> extractor) {
        List<R> result = new ArrayList<>();
        for (T s : input) {
            result.add(extractor.apply(s));
        }

        return result;
    }

    public <T> List<T> bestChecker(List<T> input, Predicate<T> checker) {
        List<T> result = new ArrayList<>();
        for (T s : input) {
            if (checker.test(s)) {
                result.add(s);
            }
        }

        return result;
    }

    public <T, R> List<R> betterGetProps(List<T> input, GenericExtractor<T, R> extractor) {
        List<R> result = new ArrayList<>();
        for (T s : input) {
            result.add(extractor.extract(s));
        }

        return result;
    }

    public interface GenericExtractor<T, R> {
        public R extract(T t);
    }

    public List<String> getProps(List<Student> input, Extractor extractor) {
        List<String> result = new ArrayList<>();
        for (Student s : input) {
            result.add(extractor.extract(s));
        }

        return result;
    }

    public interface Extractor {
        public String extract(Student student);
    }

    public class NameExtractor implements Extractor {
        @Override
        public String extract(Student student) {
            return student.getName();
        }
    }


    public List<String> getNames(List<Student> input) {
        List<String> result = new ArrayList<>();
        for (Student s : input) {
            result.add(s.getName());
        }

        return result;
    }

    public List<String> getBlahs(List<Student> input) {
        List<String> result = new ArrayList<>();
        for (Student s : input) {
            result.add(s.getBlah());
        }

        return result;
    }

//    public List<LocalDate> getDobs(List<Student> input) {
//        List<LocalDate> result = new ArrayList<>();
//        for(Student s : input) {
//            result.add(s.getDob());
//        }
//
//        return result;
//    }

}
