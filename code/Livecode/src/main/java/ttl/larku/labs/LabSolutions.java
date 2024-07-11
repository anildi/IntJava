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
        //lab6();
//        lab7();
        //lab8();
        lab9();
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

    /*
    7.Write a method to calculate the average age of Customers who have the status
Restricted.
     */

    public static void lab7() {
        StudentService service = new StudentService();
        DBInit.populateStudents(service);

        List<Student> students = service.getAllStudents();

        var refResult = students.stream()
                .filter(s -> s.getStatus() == Student.Status.PART_TIME)
                .map(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS))
                .toList();

        var optAverage = students.stream()
                .filter(s -> s.getStatus() == Student.Status.PART_TIME)
                .mapToLong(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS))
                .average();

//        if(optAverage.isPresent()) {
//            double result = optAverage.getAsDouble();
//            out.println("avg: " + result);
//        } else {
//
//        }

//        students.stream()
//                .filter(s -> s.getStatus() == Student.Status.PART_TIME)
//                .mapToLong(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS))
//                .average()
//                .ifPresent(d -> out.println("average: " + d));

        var result = students.stream()
                .filter(s -> s.getStatus() == Student.Status.PART_TIME)
                .mapToLong(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS))
                .average();
                //.orElse(0.0);

//        students.stream()
//                .filter(s -> s.getStatus() == Student.Status.PART_TIME)
//                .mapToLong(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS))
//                .average()
//                .ifPresentOrElse(d -> out.println("average: " + d),
//                        () -> out.println("No Value for Average"));

        var result2 = students.stream()
                .filter(s -> s.getStatus() == Student.Status.PART_TIME)
                .mapToLong(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS))
                .summaryStatistics();

        out.println("result2: " + result2);

    }

    public void javatypes() {
        int i;
        Integer ir;

        long l;
        Long lr;

        double d;

        Student student = new Student("Joe", LocalDate.now());
    }

    /*
8.Write a method to return all the phone numbers of all customers. Make sure your test
data includes at least some customers with phone numbers.
*/
    public static void lab8() {
        StudentService service = new StudentService();
        DBInit.populateStudents(service);

        List<Student> students = service.getAllStudents();

        var badResult = students.stream()
                .map(s -> s.getPhoneNumbers())
                .toList();

        var result = students.stream()
                .flatMap(s -> s.getPhoneNumbers().stream())
                .toList();

        result.forEach(out::println);
    }
    /*

9.Write a method to return only the first phone number, if any, for all customers. For
your test data, make sure that some of your customers have multiple phone numbers,
and at least one customer has no phone numbers.
     */
    public static void lab9() {
        StudentService service = new StudentService();
        DBInit.populateStudents(service);

        List<Student> students = service.getAllStudents();

        var result = students.stream()
                .flatMap(s -> s.getPhoneNumbers().stream().limit(1))
                .toList();

        result.forEach(out::println);
    }

}
