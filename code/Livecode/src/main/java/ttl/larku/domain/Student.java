package ttl.larku.domain;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author whynot
 */
public class Student implements Comparable<Student>{


    public enum Status  {
        FULL_TIME,
        PART_TIME,
        HIBERNATING
    }

    private int id;
    private String name;
    private LocalDate dob;

    private Status status;
    //private String [] phoneNumbers = new String[10];
    private List<String> phoneNumbers = new ArrayList<>();

    public Student(int id, String name, LocalDate dob, Status status, List<String> phoneNumbers) {
//        init(id, name, dob, status, phoneNumbers);
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.status = status;
        if(phoneNumbers != null) {
            this.phoneNumbers.addAll(phoneNumbers);
        }
    }

    public Student(String name, LocalDate dob, Status status, List<String> phoneNumbers) {
        this(0, name, dob, status, phoneNumbers);
    }

    public Student(String name, LocalDate dob, Status status) {
        this(0, name, dob, status, new ArrayList<>());
    }

    public Student(String name, LocalDate dob) {
        this(0, name, dob, Status.FULL_TIME, new ArrayList<>());
    }

//    private void init(int id, String name, LocalDate dob, Status status, List<String> phoneNumbers) {
//        this.id = id;
//        this.name = name;
//        this.dob = dob;
//        this.status = status;
//        this.phoneNumbers = phoneNumbers;
//    }

    public Student() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<String> getPhoneNumbers() {
        return List.copyOf(phoneNumbers);
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        if(phoneNumbers != null) {
            this.phoneNumbers.addAll(phoneNumbers);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", status=" + status +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id);
//        return this.name.compareTo(other.name);
    }
}
