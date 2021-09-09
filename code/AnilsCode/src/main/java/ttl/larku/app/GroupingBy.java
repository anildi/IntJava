package ttl.larku.app;

import ttl.larku.dao.inmemory.InMemoryCourseDAO;
import ttl.larku.dao.inmemory.InMemoryStudentDAO;
import ttl.larku.domain.Student;
import ttl.larku.service.CourseService;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author whynot
 */
public class GroupingBy {
    public static void main(String[] args) {
        GroupingBy so = new GroupingBy();
        so.groupingBy();

    }

    public void byHand() {
        StudentService ss = new StudentService();
        init(ss);
        List<String> phones = new ArrayList<>();
        List<Student> students = ss.getAllStudents();
        Map<Integer, Student> map = new HashMap<>();
        for(Student student : students) {
            map.put(student.getId(), student);
        }
    }

    public void groupingBy() {
        StudentService ss = new StudentService();
        init(ss);

        List<Student> students = ss.getAllStudents();
        Map<Student.Status, List<Student>> map =
                students.stream()
                        .collect(Collectors.groupingBy(s -> s.getStatus()));
//        map.forEach((key, value) -> System.out.println(key + ": " + value));

        Map<Student.Status, Long> map3 =
                students.stream()
                        .collect(Collectors.groupingBy(s -> s.getStatus(), Collectors.counting()));

        Map<Integer, Student> map2 =
                students.stream()
                        .collect(Collectors.toMap(s -> s.getId(), s -> s, (k1, k2) -> k1));

        map2.forEach((key, value) -> System.out.println(key + ": " + value));
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
