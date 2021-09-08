package ttl.larku.app;

import ttl.larku.dao.inmemory.InMemoryCourseDAO;
import ttl.larku.dao.inmemory.InMemoryStudentDAO;
import ttl.larku.domain.Course;
import ttl.larku.domain.Student;
import ttl.larku.service.CourseService;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingOne {

	int value;

	public static void main(String[] args) {
		SortingOne so = new SortingOne();
		//so.doIt();
		so.moreSorting();
	}


	public void doIt() {
		StudentService ss = new StudentService();
		init(ss);

		List<Student> students = ss.getAllStudents();
		Collections.sort(students);

		for(Student s : students) {
			System.out.println(s);
		}

		NameComparator nc = new NameComparator();
		Collections.sort(students, nc);

		System.out.println("by name");
		for(Student s : students) {
			System.out.println(s);
		}
	}

	public void moreSorting() {
		StudentService ss = new StudentService();
		init(ss);

		List<Student> students = ss.getAllStudents();
		Collections.sort(students);

		for(Student s : students) {
			System.out.println(s);
		}

		Comparator<Student> nc = new NameComparator();

		Comparator<Student> nc2 = new Comparator<>() {
			@Override
			public int compare(Student student1, Student student2) {
				return student1.getName().compareTo(student2.getName());
			}
		};

		Comparator<Student> nc3 = (Student student1, Student student2) -> {
				return student1.getName().compareTo(student2.getName());
			};

		//int 	compare(T o1, T o2)

		Comparator<Student> nc4 = (Student student1, Student student2) -> student1.getName().compareTo(student2.getName());

		Comparator<Student> nc5 = (student1, student2) -> student1.getName().compareTo(student2.getName());


		Collections.sort(students, nc5);

		Collections.sort(students, (student1, student2) -> {
			return student1.getName().compareTo(student2.getName());
		});

		System.out.println("by name");
		for(Student s : students) {
			System.out.println(s);
		}
	}

	class NameComparator implements Comparator<Student>
	{
		@Override
		public int compare(Student student1, Student student2) {
			return student1.getName().compareTo(student2.getName());
		}
	}

	public static <T extends Comparable<T>> void sort(List<T> list){

	}

	public static <T> void sort(List<T> list, Comparator<T> c) {}


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
