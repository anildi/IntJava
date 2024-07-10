package ttl.larku.app;

import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.System.out;

/**
 * @author whynot
 */
public class StreamFlatMapApp {

    public static void main(String[] args) {
        StreamFlatMapApp sortingApp = new StreamFlatMapApp();
//        sortingApp.flatMapByHand();
        sortingApp.flatMapWithStream();
    }

    private StudentService service;
    public StreamFlatMapApp() {
        service = new StudentService();
        DBInit.populateStudents(service);
    }

    public void flatMapWithStream() {

        List<Student> students = service.getAllStudents();

        var badPhoneNumbers = students.stream()
                .map(s -> s.getPhoneNumbers())
//                .flatMap(s -> s.getPhoneNumbers().stream())
//                .peek(x -> {
//                    out.println("x: " + x);
//                })
                .toList();

        var allPhoneNumbers = students.stream()
                .peek(s -> out.println("Peek1: " + s))
                .flatMap(s -> s.getPhoneNumbers().stream())
                .peek(s -> out.println("Peek2: " + s))
                .toList();

        allPhoneNumbers.forEach(out::println);

    }

    public void flatMapByHand() {

        List<Student> students = service.getAllStudents();

        //List<String> result = new ArrayList<>();
        var result = new ArrayList<>();
        for(Student s : students) {
            List<String> phoneNumbers = s.getPhoneNumbers();
//            result.add(phoneNumbers);
            for(String pn : phoneNumbers) {
                result.add(pn);
            }
        }

        result.forEach(out::println);

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
