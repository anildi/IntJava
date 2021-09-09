package ttl.larku.app;

import ttl.larku.dao.inmemory.InMemoryCourseDAO;
import ttl.larku.dao.inmemory.InMemoryStudentDAO;
import ttl.larku.domain.Student;
import ttl.larku.service.CourseService;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author whynot
 */
public class FlatMapEtc {
    public static void main(String[] args) {
        FlatMapEtc so = new FlatMapEtc();
        so.flatMapper();

    }

    public void byHand() {
        StudentService ss = new StudentService();
        init(ss);
        List<String> phones = new ArrayList<>();
        List<Student> students = ss.getAllStudents();
        for(Student student : students) {
            for(String s : student.getPhoneNumbers()) {
                phones.add(s);
            }
        }

    }

    public void flatMapper() {
        StudentService ss = new StudentService();
        init(ss);

        int [] iarr = {0, 10, 30};
        List<Student> students = ss.getAllStudents();
        List<String> result = students.stream()
                .peek(s -> System.out.println("Peek 1: " + s))
                .flatMap(s -> s.getPhoneNumbers().stream())
                .peek(s -> System.out.println("Peek 2: " + s))
                .collect(Collectors.toList());

        result.forEach(System.out::println);

    }

    public void primitiveStreams() {
        StudentService ss = new StudentService();
        init(ss);

        int [] iarr = {0, 10, 30};
        List<Student> students = ss.getAllStudents();
        OptionalDouble optAverage = students.stream()
                .filter(s -> s.getDob() != null)
                .mapToLong(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS))
                        .average();

       students.stream()
                .filter(s -> s.getDob() != null)
                .mapToLong(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS))
                .average()
               .ifPresentOrElse(d -> System.out.println("Average is: " + d), () -> System.out.println("Stream was empty"));

    }

    public void referenceOptional() {
        StudentService ss = new StudentService();
        init(ss);

        int [] iarr = {0, 10, 30};
        List<Student> students = ss.getAllStudents();
        Optional<Student> student = students.stream()
                .filter(s -> s.getStatus() == Student.Status.PART_TIME)
                        .findFirst();

        student.ifPresent(s -> System.out.println("Found: " + s));

        students.stream()
                .filter(s -> s.getStatus() == Student.Status.PART_TIME)
                .findFirst().ifPresent(s -> System.out.println("Found: " + s));

    }

    public static void init(StudentService ss) {
        ((InMemoryStudentDAO)ss.getStudentDAO()).createStore();
        ss.createStudent("Manoj", LocalDate.of(1988, 10, 2), Student.Status.FULL_TIME, "282 939 9944");
        ss.createStudent("Charlene", LocalDate.of(1999, 8, 14), Student.Status.FULL_TIME, "282 898 2145", "298 75 83833");
        ss.createStudent("Firoze", LocalDate.of(2002, 5, 2), Student.Status.HIBERNATING, "228 678 8765", "220 8795 26795");
        ss.createStudent("Joe", LocalDate.of(1948, 9, 26), Student.Status.PART_TIME, "3838 678 3838");
    }

    public static void init(CourseService cs) {
        ((InMemoryCourseDAO)cs.getCourseDAO()).createStore();
        cs.createCourse("Math-101", "Intro To Math");
        cs.createCourse("Math-201", "More Math");
        cs.createCourse("Phys-101", "Baby Physics");
    }
}
