package ttl.larku.labs;

import ttl.larku.app.DBInit;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.out;

/**
 * @author whynot
 */
public class LabSolutions {

    public static void main(String[] args) {
        //lab4();
        //lab5();
        lab6();
    }

    /*
    4.Write a method to return the names of customers who have a status of Privileged. Use
Streams.
     */
    public static void lab4() {

        StudentService service = new StudentService();
        DBInit.populateStudents(service);

        List<Student> students = service.getAllStudents();

        var result = students.stream()
                .filter(s -> s.getStatus() == Student.Status.FULL_TIME)
                .map(s -> s.getName())
                .toList();

        result.forEach(name -> out.println(name));
    }


    /*
    5.Write a method to return a list of the ages of all Customers who have a status of
Normal.
     */
    public static void lab5() {
        StudentService service = new StudentService();
        DBInit.populateStudents(service);

        List<Student> students = service.getAllStudents();

        var result = students.stream()
                .filter(s -> s.getStatus() == Student.Status.PART_TIME)
                .map(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS))
                .collect(Collectors.toSet());

        result.forEach(out::println);
    }

    /*
   6.Write a method to return the number of customers who are 20 years or older. To
calculate number of years from a LocalDate use:
myDate.until(LocalDate.now(), ChronoUnit.YEARS)
     */

    public static void lab6() {
        StudentService service = new StudentService();
        DBInit.populateStudents(service);

        List<Student> students = service.getAllStudents();

        var result = students.stream()
                .filter(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS) >= 20)
                .count();

        out.println("result: " + result);

    }

}
