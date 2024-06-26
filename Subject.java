package HomeWorkIO;

import java.io.Serializable;

public class Subject implements Serializable {
    private String name;

    public Subject(){

    }
    public Subject(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
