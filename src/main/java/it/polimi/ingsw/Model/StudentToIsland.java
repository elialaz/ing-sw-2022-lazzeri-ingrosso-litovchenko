package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentToIsland implements Effect{
    private List<Student> students;

    public StudentToIsland() {
        students= new ArrayList<>();
        int i;
        for ( i=0;i<4;i++)
            students.add(i,new Student(Color.randomLetter()));
    }

    public void GetEffect() {
        Scanner inputreader = new Scanner(System.in);
        System.out.println("insert the position of the student you choose:");
        int scelto= inputreader.nextInt();
        students.get(scelto).MoveToIsland(Id_island);
        students.add(new Student(Color.randomLetter()));
    }
}
