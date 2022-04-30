package it.polimi.ingsw.Model;

import java.util.ArrayList;

public class CloudTile {
    private final int studentsNum;
    private ArrayList<Student> students;

    public CloudTile(int studentsNum){
        this.studentsNum = studentsNum;

    }
    public ArrayList<Student> getStudents() {
        ArrayList<Student> copia = (ArrayList<Student>) students.clone();
        students.clear();
        return copia;
    }

    public void setStudents(Bag generator) {
        this.students = generator.getStudent(studentsNum);
    }
}
