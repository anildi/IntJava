package ttl.larku.app;

import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.util.List;

/**
 * @author whynot
 */
public class DBInit {

    public static void populateStudents(StudentService service) {
        service.createStudent(new Student("Martin", LocalDate.of(1960, 8, 5), Student.Status.FULL_TIME));
        service.createStudent(new Student("Francine", LocalDate.of(1990, 7, 5), Student.Status.PART_TIME, List.of("383 93 922929")));
        service.createStudent(new Student("Rachna", LocalDate.of(2000, 9, 4), Student.Status.HIBERNATING, List.of("383 83 90", "33 83393 7765894")));
        service.createStudent(new Student("Madhu", LocalDate.of(1987, 10, 4), Student.Status.HIBERNATING, List.of("987 77 834383")));
        service.createStudent(new Student("Josefina", LocalDate.of(1997, 10, 4), Student.Status.PART_TIME));
        service.createStudent(new Student("Nelson", LocalDate.of(1960, 10, 4), Student.Status.FULL_TIME, List.of("967 83 37362")));
        service.createStudent(new Student("Firdaus", LocalDate.of(2010, 11, 4), Student.Status.HIBERNATING, List.of("769 37 83383")));
        service.createStudent(new Student("Manoj", LocalDate.of(1957, 10, 4), Student.Status.FULL_TIME, List.of("97 38 83382")));
    }
}
