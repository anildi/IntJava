package ttl.larku.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ttl.larku.dao.inmemory.InMemoryStudentDAO;
import ttl.larku.domain.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class StudentServiceMockTest {

	@Mock
	private InMemoryStudentDAO dao;

	@InjectMocks
	private StudentService studentService;
	
	private Student getStudent;
	private Student newStudent;
	private int goodId = 1;
	private int badId = 1000;
	
	private List<Student> students;

	@BeforeEach
	public void setup() {
		getStudent = new Student("Joe", LocalDate.of(1995, 5, 14), Student.Status.FULL_TIME, ("838 939 0202"));
		getStudent.setId(goodId);
		newStudent = new Student("Sammy", LocalDate.of(1995, 5, 14), Student.Status.PART_TIME, "928 749 0303");
		newStudent.setId(goodId+1);
		
		students = new ArrayList<>();
		students.add(getStudent);
		students.add(newStudent);

		Mockito.when(dao.get(goodId)).thenReturn(getStudent);
		Mockito.when(dao.get(badId)).thenReturn(null);
		Mockito.when(dao.create(Mockito.any())).thenReturn(newStudent);
		Mockito.doNothing().when(dao).update(getStudent);
		Mockito.when(dao.getAll()).thenReturn(students);
	}
	

	@Test
	public void getStudentGood() throws Exception{
		Student result = studentService.getStudent(goodId);
		assertEquals("Joe", result.getName());
		//Mockito.verify(dao).get(Mockito.any(int.class));
		Mockito.verify(dao).get(goodId);
	}
	@Test
	public void testGetStudentWithBadId() {
		Student student = studentService.getStudent(badId);
		
		assertNull(student );
		Mockito.verify(dao).get(Mockito.any(int.class));
	}

	@Test
	public void testCreateStudent() {
		Student student = studentService.createStudent("Sammy", LocalDate.of(1995, 5, 14), Student.Status.HIBERNATING, "982 749 0033");
		
		assertNotEquals(0, student.getId());
		Mockito.verify(dao).create(Mockito.any(Student.class));
	}

	@Test
	public void testCreateStudentWithStudent() {
		Student student = studentService.createStudent(newStudent);
		
		assertNotEquals(0, student.getId());
		Mockito.verify(dao).create(Mockito.any(Student.class));
	}

	@Test
	public void deleteGoodStudent() {
		studentService.deleteStudent(goodId);
		
		Mockito.verify(dao).get(goodId);
		Mockito.verify(dao).delete(getStudent);
	}

	@Test
	public void deleteStudentWithBadId() {
		studentService.deleteStudent(badId);
		
		Mockito.verify(dao).get(badId);
	}

	@Test
	public void testUpdateStudent() {
		Student student = studentService.getStudent(goodId);
		studentService.updateStudent(student);
		
		Mockito.verify(dao).update(student);
	}

	@Test
	public void testGetAll() {
		List<Student> students = studentService.getAllStudents();
		
		assertEquals(2, students.size());
		Mockito.verify(dao).getAll();
	}
}
