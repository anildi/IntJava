package ttl.larku.app;

import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.System.out;

/**
 * @author whynot
 */
public class SortingApp {

    public static void main(String[] args) {
        SortingApp sortingApp = new SortingApp();
        //sortingApp.naturalOrderSort();
        sortingApp.sortByName();
    }

    public void naturalOrderSort() {
        StudentService service = new StudentService();
        DBInit.populateStudents(service);

        List<Student> students = service.getAllStudents();

        Collections.sort(students);

        students.forEach(out::println);
    }

    public void sortByName() {
        StudentService service = new StudentService();
        DBInit.populateStudents(service);

        List<Student> students = service.getAllStudents();

        NameComparator nc = new NameComparator();

        DobComparator dc = new DobComparator();

        //Collections.sort(students, nc);
        Collections.sort(students, dc);

        students.forEach(out::println);
    }

    class DobComparator implements Comparator<Student> {

        @Override
        public int compare(Student student1, Student student2) {
            int result = student1.getDob().compareTo(student2.getDob());
            if(result == 0) {
                result = student1.getName().compareTo(student2.getName());
            }

            return result;
        }
    }

    class NameComparator implements Comparator<Student> {

        @Override
        public int compare(Student student1, Student student2) {
            return student1.getName().compareTo(student2.getName());
        }
    }

    //By any order
    public static <T> void sort(List<T> list, Comparator<T> c) {

    }

    //Natural order
    public static <T extends Comparable<T>> void sort(List<T> list) {

    }
}
