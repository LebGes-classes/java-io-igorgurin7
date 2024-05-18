package HomeWorkIO;

import java.io.Serializable;

public class Teacher implements Serializable {
    private String fullName;

    private Subject subject;

    public Teacher(){

    }

    public Teacher(String fullName, Subject subject) {
        this.fullName = fullName;
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "fullName='" + fullName + '\'' +
                ", Subject='" + subject + '\'' +
                '}';
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
