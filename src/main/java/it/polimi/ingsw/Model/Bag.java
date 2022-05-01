package it.polimi.ingsw.Model;

import java.util.ArrayList;

public class Bag {
    private int yellow_extracted;
    private int green_extracted;
    private int pink_extracted;
    private int blue_extracted;
    private int red_extracted;

    public Bag(){
        yellow_extracted = 0;
        green_extracted = 0;
        pink_extracted = 0;
        blue_extracted = 0;
        red_extracted = 0;
    }

    public ArrayList<Student> getStudent(int num){
        ArrayList<Student> generated = new ArrayList<Student>();
        int i = 0;
        Student student;
        while(i < num){
            student = new Student(Color.randomLetter());
            switch (student.color){
                case RED:
                    if(red_extracted < 24){
                        generated.add(student);
                        red_extracted++;
                        i++;
                    }
                case BLUE:
                    if(blue_extracted < 24){
                        generated.add(student);
                        blue_extracted++;
                        i++;
                    }
                case PINK:
                    if(pink_extracted < 24){
                        generated.add(student);
                        pink_extracted++;
                        i++;
                    }
                case GREEN:
                    if(green_extracted < 24){
                        generated.add(student);
                        green_extracted++;
                        i++;
                    }
                case YELLOW:
                    if(yellow_extracted < 24){
                        generated.add(student);
                        yellow_extracted++;
                        i++;
                    }
            }
        }
        return generated;
    }

    public int getSumExtracted() {
        return yellow_extracted+green_extracted+pink_extracted+blue_extracted+red_extracted;
    }

    public void addStudents(Color color, int numStudents){
        switch (color){
            case PINK:
                pink_extracted = pink_extracted - numStudents;
                break;
            case GREEN:
                green_extracted = green_extracted - numStudents;
                break;
            case YELLOW:
                yellow_extracted = yellow_extracted - numStudents;
                break;
            case BLUE:
                blue_extracted = blue_extracted - numStudents;
                break;
            case RED:
                red_extracted = red_extracted - numStudents;
                break;
        }
    }
}
