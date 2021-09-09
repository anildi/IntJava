package ttl.larku.livedemo.app;

import ttl.larku.livedemo.domain.Student;
import ttl.larku.livedemo.service.StudentService;

import java.time.LocalDate;

/**
 * @author whynot
 */
public class StudentUtils {

    public static void initStudentService(StudentService ss) {
        ss.createStudent(new Student("Sammy", LocalDate.of(2000, 10, 10), Student.Status.FULL_TIME, "383 939 9393"));
        ss.createStudent(new Student("Johanna", LocalDate.of(1956, 10, 10), Student.Status.PART_TIME));
        ss.createStudent(new Student("Avinash", LocalDate.of(1970, 8, 14), Student.Status.PART_TIME, "9393 83 9393", "38383 93 93"));
        ss.createStudent(new Student("Geetha", LocalDate.of(1980, 9, 22), Student.Status.HIBERNATING, "6585 93 91292", "33 8883 93"));
        ss.createStudent(new Student("Vandana", LocalDate.of(2010, 10, 10), Student.Status.FULL_TIME, "89 999", "444 9494 949", "295 544 5858"));
    }
}
