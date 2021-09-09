package ttl.larku.app;

import ttl.larku.dao.inmemory.InMemoryCourseDAO;
import ttl.larku.dao.inmemory.InMemoryStudentDAO;
import ttl.larku.domain.Student;
import ttl.larku.service.CourseService;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MethodReferences {

	int value;

	public static void main(String[] args) {
		MethodReferences so = new MethodReferences();
		so.methodReferences();
	}


	public void methodReferences() {
		StudentService ss = new StudentService();
		init(ss);

		List<Student> students = ss.getAllStudents();
		List<Student> result = bestChecker(students, s -> s.getName().startsWith("M"));

//		void 	accept(T t)

		result.forEach(s -> System.out.println(s));
		result.forEach(s -> prettyPrint(s));


		result.forEach(System.out::println);

		result.forEach(this::prettyPrint);

//		for(Student s : result) {
//			System.out.println(s);
//		}
	}

	public void prettyPrint(Student s) {
		//lots of code
		System.out.println("<<<" + s + ">>>>");
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

	interface GenericChecker<T>  {
		public boolean check(T s);
	}

	public <T> List<T> almostBestChecker(List<T> input, GenericChecker<T> checker) {
		List<T> result = new ArrayList<>();
		for(T elem : input) {
			if(checker.check(elem)) {
				result.add(elem);
			}
		}
		return result;
	}

	public List<Student> evenBetterChecker(List<Student> input, GenericChecker<Student> checker) {
		List<Student> result = new ArrayList<>();
		for(Student student : input) {
			if(checker.check(student)) {
				result.add(student);
			}
		}
		return result;
	}


	interface Checker  {
		public boolean check(Student s);
	}

	public List<Student> betterChecker(List<Student> input, Checker checker) {
		List<Student> result = new ArrayList<>();
		for(Student student : input) {
			if(checker.check(student)) {
				result.add(student);
			}
		}
		return result;
	}

	class NameWithMChecker implements Checker
	{
		@Override
		public boolean check(Student s) {
			return s.getName().startsWith("M");
		}
	}


	public List<Student> findStudentsWithM(List<Student> input, String prefix) {
		List<Student> result = new ArrayList<>();
		for(Student student : input) {
			if(student.getName().startsWith(prefix)) {
				result.add(student);
			}
		}
		return result;
	}

	public List<Student> findStudentsWithStatus(List<Student> input, Student.Status status) {
		List<Student> result = new ArrayList<>();
		for(Student student : input) {
			if(student.getStatus() == status) {
				result.add(student);
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
