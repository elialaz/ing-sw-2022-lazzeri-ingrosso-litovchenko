package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentToDiningRoom implements Effect{
    private List<Student> CardStudents;

    public StudentToDiningRoom() {
        CardStudents= new ArrayList<>();
        int i;
        for ( i=0;i<4;i++)
            CardStudents.add(i,new Student(Color.randomLetter()));
    }

    public List<Student> getCardStudents() {
        return CardStudents;
    }

    public void GetEffect() {
        Scanner inputreader = new Scanner(System.in);
        System.out.println("insert the position of the student you choose:");
        int scelto= inputreader.nextInt();
        CardStudents.get(scelto).MoveToDiningRoom(Corridor c );
        CardStudents.add(new Student(Color.randomLetter()));

    }
}
