package it.polimi.ingsw.Model;

import java.util.ArrayList;

public class CloudTile {
    private ArrayList<Student> students;

    public ArrayList<Student> getStudents() {
        ArrayList<Student> copia = (ArrayList<Student>) students.clone();
        students.clear();
        return copia;
    }

    public void setStudents(Bag generator, int num) {
        this.students = generator.getStudent(num);
    }
}
