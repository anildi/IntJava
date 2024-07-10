package ttl.larku.app;

import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.System.out;

/**
 * @author whynot
 */
public class MappingApp {

    public static void main(String[] args) {
        MappingApp sortingApp = new MappingApp();
        //sortingApp.badMap();
        sortingApp.composeOperations();
    }

    public void badMap() {
        StudentService service = new StudentService();
        DBInit.populateStudents(service);

        List<Student> students = service.getAllStudents();

//        List<String> names = getNames(students);
        //List<String> names = getProps(students, new NameExtractor());
        List<String> names = getProps(students,  s -> s.getName());

        names.forEach(out::println);
        //List<String> blahs = getProps(students,  s -> s.getBlah());
        List<String> blahs = betterGetProps(students,  s -> s.getBlah());
        blahs.forEach(out::println);

        //List<LocalDate> dates = betterGetProps(students,  s -> s.getDob());
        List<LocalDate> dates = bestGetProps(students, s -> s.getDob());
        dates.forEach(out::println);
    }

    public void composeOperations() {
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
        for(T s : input) {
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
        for(T s : input) {
            result.add(extractor.extract(s));
        }

        return result;
    }

    public interface GenericExtractor<T, R> {
        public R extract(T t);
    }

    public List<String> getProps(List<Student> input, Extractor extractor) {
        List<String> result = new ArrayList<>();
        for(Student s : input) {
            result.add(extractor.extract(s));
        }

        return result;
    }

    public interface Extractor {
        public String extract(Student student);
    }

    public class NameExtractor implements  Extractor
    {
        @Override
        public String extract(Student student) {
            return student.getName();
        }
    }


    public List<String> getNames(List<Student> input) {
        List<String> result = new ArrayList<>();
        for(Student s : input) {
            result.add(s.getName());
        }

        return result;
    }

    public List<String> getBlahs(List<Student> input) {
        List<String> result = new ArrayList<>();
        for(Student s : input) {
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
