package ttl.larku.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author whynot
 */
public class Student {

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
        this.phoneNumbers = phoneNumbers;
    }

    public Student(int id, String name, LocalDate dob) {
        this(id, name, dob, Status.FULL_TIME, new ArrayList<>());
//        this.id = id;
//        this.name = name;
//        this.dob = dob;
//        this.status = Status.FULL_TIME;
//        this.phoneNumbers = new ArrayList<>();
    }

    public Student(String name, LocalDate dob) {
        this(0, name, dob, Status.FULL_TIME, new ArrayList<>());
//        this.id = id;
//        this.name = name;
//        this.dob = dob;
//        this.status = Status.FULL_TIME;
//        this.phoneNumbers = new ArrayList<>();
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
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
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
}
