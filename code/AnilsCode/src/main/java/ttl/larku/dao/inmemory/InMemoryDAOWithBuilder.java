package ttl.larku.dao.inmemory;

import ttl.larku.dao.BaseDAO;
import ttl.larku.domain.StudentWithBuilder;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryDAOWithBuilder implements BaseDAO<StudentWithBuilder>{

	private Map<Integer, StudentWithBuilder> students = new ConcurrentHashMap<>();
	private static AtomicInteger nextId = new AtomicInteger(0);
	
	public void update(StudentWithBuilder newStudent) {
	    students.computeIfPresent(newStudent.getId(), (k, oldStudent) -> newStudent);
//		if(students.containsKey(updateObject.getId())) {
//			students.put(updateObject.getId(), updateObject);
//		}
	}

	public void delete(StudentWithBuilder student) {
		students.remove(student.getId());
	}

	public StudentWithBuilder create(StudentWithBuilder newObject) {
		//Create a new Id
		int newId = nextId.incrementAndGet();
		StudentWithBuilder tmp = new StudentWithBuilder.Builder().id(newId).name(newObject.getName()).phoneNumber(newObject.getPhoneNumber())
				.status(newObject.getStatus()).build();
		students.put(newId, newObject);
		
		return newObject;
	}

	public StudentWithBuilder get(int id) {
		return students.get(id);
	}

	/**
	 * If we return a List, that implies an order, so we should probably
	 * specify the order explicitly in documentation.  Here we are
	 * going to return a List sorted by id.
	 *
	 */
	public List<StudentWithBuilder> getAll() {
		List<StudentWithBuilder> result = students.values().stream()
				.sorted(Comparator.comparing(StudentWithBuilder::getId))
				.collect(Collectors.toList());
		return result;
	}

	public void deleteStore() {
		students = null;
		nextId.set(0);
	}

	public void createStore() {
		students = new ConcurrentHashMap<>();
		nextId.set(0);
	}

	public Map<Integer, StudentWithBuilder> getStudents() {
		return students;
	}
}
