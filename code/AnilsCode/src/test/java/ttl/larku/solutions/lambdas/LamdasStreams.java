package ttl.larku.solutions.lambdas;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import ttl.larku.app.RegistrationApp;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.toList;

/**
 * @author whynot
 */
public class LamdasStreams {

    class Data implements Comparable<Data>{
        public final int id;
        public final String place;

        public Data(int id, String place) {
            this.id = id;
            this.place = place;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "id=" + id +
                    ", place='" + place + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Data other) {
            return Integer.compare(this.id, other.id);
        }
    }

    @Test
    public void lab1() {
        List<Data> list = new ArrayList<>();
        list.add(new Data(1, "Atlanta"));
        list.add(new Data(10, "San Francisco"));
        list.add(new Data(5, "Quebec"));
        list.add(new Data(8, "Mumbai"));
        list.add(new Data(33, "Beijing"));

        Collections.sort(list);
        for(Data d : list) {
            System.out.println(d);
        }
    }

    //public static <T> void sort(List<T> list, Comparator<T> c) {}
    @Test
    public void lab2() {
        List<Data> list = new ArrayList<>();
        list.add(new Data(1, "Atlanta"));
        list.add(new Data(10, "San Francisco"));
        list.add(new Data(5, "Quebec"));
        list.add(new Data(8, "Mumbai"));
        list.add(new Data(33, "Beijing"));

        Comparator<Data> c1 = (data1, data2) -> data1.place.compareTo(data2.place);
        Collections.sort(list, c1);

        Collections.sort(list, (data1, data2) -> data1.place.compareTo(data2.place));
        for(Data d : list) {
            System.out.println(d);
        }
    }

    /*
    Write a method to return the names of students who have a status of Privileged. Use
    Streams.
    */
    @Test
    public void lab4() {
        StudentService ss = new StudentService();
        RegistrationApp.init(ss);
        List<Student> students = ss.getAllStudents();

        List<String> names = students.stream()
                .filter(s -> s.getStatus() == Student.Status.FULL_TIME)
                .map(Student::getName)
                .collect(toList());

    }

   /*

5. Write a method to return a list of the ages of all Customers who have a status of
    Normal.
    */

    @Test
    public void lab5() {
        StudentService ss = new StudentService();
        RegistrationApp.init(ss);
        List<Student> students = ss.getAllStudents();

        List<Long> names = students.stream()
                .filter(s -> s.getDob() != null && s.getStatus() == Student.Status.PART_TIME)
                .map(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS))
//                .map(s -> {
//                    LocalDate dob = s.getDob();
//                    long age = -1;
//                    if (dob != null) {
//                        age = s.getDob().until(LocalDate.now(), ChronoUnit.YEARS);
//                    }
//                    return age;
//                })
                .collect(toList());

    }
    /*
6. Write a method to return the number of customers who are 20 years or older. To
    calculate number of years from a LocalDate use:
            myDate.until(LocalDate.now(), ChronoUnit.YEARS)

     */
    @Test
    public void lab6() {
        StudentService ss = new StudentService();
        RegistrationApp.init(ss);
        List<Student> students = ss.getAllStudents();

        long olderThan20 = students.stream()
                .filter(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS) > 20).count();

    }

    /*
    7. Write a method to calculate the average age of Customers who have the status
    Restricted.
    */
    @Test
    public void lab7() {
        StudentService ss = new StudentService();
        RegistrationApp.init(ss);
        List<Student> students = ss.getAllStudents();

        var avgAge = students.stream()
                .mapToLong(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS))
                .average();

        var stats = students.stream()
                .mapToLong(s -> s.getDob().until(LocalDate.now(), ChronoUnit.YEARS))
                .summaryStatistics();
        System.out.println(stats);

    }

    @Test
    public void otherWaysToMakeAStream() {
        IntStream.range(0, 10).forEach(i -> System.out.println(i));

        IntStream.iterate(0, i -> i < 25, (i) -> i + 1)
                .map(i -> i * i)
                .forEach(System.out::println);
    }
    /*
8. Write a method to return all the phone numbers of all customers. Make sure your test
    data includes at least some customers with phone numbers.
    */
    @Test
    public void lab8() {
        StudentService ss = new StudentService();
        RegistrationApp.init(ss);
        List<Student> students = ss.getAllStudents();

        List<String> result = students.stream()
                .flatMap(s -> s.getPhoneNumbers().stream())
                .collect(toList());

    }
    /*
9. Write a method to return only the first phone number, if any, for all customers. For
    your test data, make sure that some of your customers have multiple phone numbers,
    and at least one customer has no phone numbers.
     */
    @Test
    public void lab9() {
        StudentService ss = new StudentService();
        RegistrationApp.init(ss);
        List<Student> students = ss.getAllStudents();

        List<String> result = students.stream()
                .filter(s -> s.getPhoneNumbers().size() > 0)
                .map(s -> s.getPhoneNumbers().get(0))
                .collect(toList());

        var result1 = students.stream()
                .map(s -> {
                    if(s.getPhoneNumbers().size() > 0) {
                        return Optional.of(s.getPhoneNumbers().get(0));
                    } else {
                        return Optional.<String>empty();
                    }
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());

        System.out.println("result: " + result + ", result1: " + result1);

    }

    @Test
    public void lab9_II() {
        StudentService ss = new StudentService();
        RegistrationApp.init(ss);
        List<Student> students = ss.getAllStudents();


        var result = students.stream()
                .map(s -> s.getPhoneNumbers().stream().findFirst())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());

        var result1 = students.stream()
                .flatMap(s -> s.getPhoneNumbers().stream().findFirst().stream())
                .collect(toList());


        System.out.println("result: " + result + ", result1: " + result1);

    }
}
