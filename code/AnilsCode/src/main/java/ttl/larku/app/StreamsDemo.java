package ttl.larku.app;

import ttl.larku.dao.inmemory.InMemoryCourseDAO;
import ttl.larku.dao.inmemory.InMemoryStudentDAO;
import ttl.larku.domain.Student;
import ttl.larku.service.CourseService;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author whynot
 */
public class StreamsDemo {
    public static void main(String[] args) {
        StreamsDemo so = new StreamsDemo();
		//so.findThings();
        so.collectors();
    }


    public void findThings() {
        StudentService ss = new StudentService();
        init(ss);

        List<Student> students = ss.getAllStudents();
        List<Student> result = students.stream()
                .filter(s -> s.getName().startsWith("M"))
                .collect(Collectors.toList());

        //List<Student> result2 = students.stream()
        List<String> result2 = students.stream()
                .peek(s -> System.out.println("Peek 1: " + s))
                .filter(s -> s.getName().startsWith("M") || s.getName().startsWith("F"))
                .peek(s -> System.out.println("Peek 2: " + s))
                .map(s -> s.getName())
                .peek(s -> System.out.println("Peek 3: " + s))
                .collect(Collectors.toList());

        for(String s : result2) {
            System.out.println(s);
        }
    }

    public void collectors() {
        StudentService ss = new StudentService();
        init(ss);

        List<Student> students = ss.getAllStudents();

        List<String> result2 = students.stream()
                .filter(s -> s.getName().startsWith("M") || s.getName().startsWith("F"))
                .map(Student::getName)
                .collect(Collectors.toList());

        long count = students.stream()
                .filter(s -> s.getName().startsWith("M") || s.getName().startsWith("F"))
                .map(Student::getName).count();

        String namesCsv = students.stream()
                .filter(s -> s.getName().startsWith("M") || s.getName().startsWith("F"))
                .map(Student::getName)
                .collect(Collectors.joining(", "));

        System.out.println("count: " + count + ", namesCsv: " + namesCsv);

//        for(String s : result2) {
//            System.out.println(s);
//        }
    }

    public <T> List<T> bestChecker(List<T> input, Predicate<T> checker) {
        List<T> result = new ArrayList<>();
        for(T elem : input) {
            if(checker.test(elem)) {
                result.add(elem);
            }
        }
        return result;
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
