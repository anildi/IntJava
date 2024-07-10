package ttl.larku.app;

import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.lang.System.out;

/**
 * @author whynot
 */
public class FilteringApp {

    public static void main(String[] args) {
        FilteringApp sortingApp = new FilteringApp();
        //sortingApp.badFilter();
//        sortingApp.betterFilter();
        sortingApp.evenBetterFilter();
    }

    public void badFilter() {
        StudentService service = new StudentService();
        DBInit.populateStudents(service);

        List<Student> students = service.getAllStudents();

        List<Student> withM = getStudentsWithM(students, "F");

        List<Student> isYounger = getStudentsYoungerThan(students, LocalDate.of(1970, 1, 1));

        //withM.forEach(out::println);
        isYounger.forEach(out::println);
    }

    public void betterFilter() {
        StudentService service = new StudentService();
        DBInit.populateStudents(service);

        List<Student> students = service.getAllStudents();

        WithMChecker withMChecker = new WithMChecker();
        Checker checker = new Checker() {
            @Override
            public boolean test(Student student) {
                return student.getName().startsWith("M");
            }
        };

        Checker ch2 = (Student student) -> {
                return student.getName().startsWith("M");
            };

        Checker ch3 = (student) -> student.getName().startsWith("M");

        Checker ch4 = student -> student.getName().startsWith("M");


        //List<Student> result = betterChecker(students, ch4);
//        List<Student> result = betterChecker(students, s ->  s.getName().startsWith("M"));

        List<Student> youngerThan = betterChecker(students, s ->  s.getDob().isAfter(LocalDate.of(1970, 1, 1)));

        youngerThan.forEach(out::println);
    }

    public void evenBetterFilter() {
        StudentService service = new StudentService();
        DBInit.populateStudents(service);

        List<Student> students = service.getAllStudents();

        List<Student> youngerThan = evenBetterChecker(students, s ->  s.getDob().isAfter(LocalDate.of(1970, 1, 1)));

        youngerThan.forEach(out::println);

        var listOfStrings = List.of("one", "two", "threeeeee");

        //List<String> longStrings = evenBetterChecker(listOfStrings, s -> s.length() > 3);
        List<String> longStrings = bestChecker(listOfStrings, s -> s.length() > 3);

        longStrings.forEach(out::println);
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

    public <T> List<T> evenBetterChecker(List<T> input, GenericChecker<T> checker) {
        List<T> result = new ArrayList<>();
        for (T s : input) {
            if (checker.test(s)) {
                result.add(s);
            }
        }

        return result;
    }

    @FunctionalInterface
    public interface GenericChecker<T> {
        public boolean test(T student);
    }

    public List<Student> betterChecker(List<Student> input, Checker checker) {
        List<Student> result = new ArrayList<>();
        for (Student s : input) {
            if (checker.test(s)) {
                result.add(s);
            }
        }

        return result;
    }

    interface Checker {
        public boolean test(Student student);
    }

    class WithMChecker implements Checker {
        @Override
        public boolean test(Student student) {
            return student.getName().startsWith("M");
        }
    }

    public List<Student> getStudentsWithM(List<Student> input, String prefix) {
        List<Student> result = new ArrayList<>();
        for (Student s : input) {
            if (s.getName().startsWith(prefix)) {
                result.add(s);
            }
        }

        return result;
    }

    public List<Student> getStudentsYoungerThan(List<Student> input, LocalDate targetDate) {
        List<Student> result = new ArrayList<>();
        for (Student s : input) {
            if (s.getDob().isAfter(targetDate)) {
                result.add(s);
            }
        }

        return result;
    }

    public List<Student> getStudentsOlderThan(List<Student> input, LocalDate targetDate) {
        List<Student> result = new ArrayList<>();
        for (Student s : input) {
            if (s.getDob().isBefore(targetDate)) {
                result.add(s);
            }
        }

        return result;
    }
}
