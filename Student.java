package HomeWorkIO;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    private String surName;
    private int average;

    private Teacher teacher;

    public Student(int id, String name, String surName, int average, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.average = average;
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", average=" + average +
                ", teacher=" + teacher +
                '}';
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

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
