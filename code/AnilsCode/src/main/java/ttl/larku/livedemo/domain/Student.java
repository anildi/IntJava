package ttl.larku.livedemo.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author whynot
 */
public class Student {

    public enum Status {
        FULL_TIME,
        PART_TIME,
        HIBERNATING
    }

    private int id;
    private String name;
    private LocalDate dob;
//    private String [] phoneNumbers = new String[10];
    private List<String> phoneNumbers;
    private Status status;

    public Student() {
    }

    public Student(String name, LocalDate dob, Status status) {
        this(name, dob, status, new ArrayList<>());
    }

    public Student(String name, LocalDate dob, Status status, String ... phoneNumbers) {
        this(name, dob, status, new ArrayList<>());
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.status = status;
        this.phoneNumbers.addAll(Arrays.asList(phoneNumbers));
    }

    public Student(String name, LocalDate dob, Status status, List<String> phoneNumbers) {
        //init(id, name, dob, status, phoneNumbers);
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.phoneNumbers = phoneNumbers;
        this.status = status;
    }

    public void init(String name, LocalDate dob, Status status, List<String> phoneNumbers) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.phoneNumbers = phoneNumbers;
        this.status = status;
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

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", phoneNumbers=" + phoneNumbers +
                ", status=" + status +
                '}';
    }
}
