package it.polimi.ingsw.Model;

import java.util.List;
import java.util.Scanner;

public class FromCartToEntrance implements Effect{
    private List<Student> students;
    private SchoolBoard S;

    public FromCartToEntrance(){
        for (int i=0; i<6; i++){
            students.add(i, new Student(Color.randomLetter()));
        }
    }

    @Override
    public void GetEffect() {
        Scanner inputreader = new Scanner(System.in);
        System.out.println("how many students do you want to change? maX3!");
        int numstudchanged = inputreader.nextInt();
        int position = 0;

        for (int j=0; j<numstudchanged; j++){
            System.out.println("select the position of students you want to move from card to entrance:");
            position = inputreader.nextInt();
            S.getEntranceStudent().add(students.get(position));
            students.remove(position);
        }

        for (int z=0; z<numstudchanged; z++){
            System.out.println("select now the position of students you want to move from entrance to card:");
            position = inputreader.nextInt();
            students.add(S.getEntranceStudent().get(position));
            S.getEntranceStudent().remove(position);
        }

    }
}
