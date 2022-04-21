package it.polimi.ingsw.Model;

import java.util.ArrayList;

public class Bag {
    private int extracted;
    public Bag(){
        this.extracted = 0;
    }

    public ArrayList<Student> getStudent(int num){
        ArrayList<Student> generated = new ArrayList<Student>();
        for (int i = 0; i < num; i++){
            generated.add(new Student(Color.randomLetter()));
        }
        this.extracted += num;
        return generated;
    }

    public int getExtracted() {
        return extracted;
    }
}
