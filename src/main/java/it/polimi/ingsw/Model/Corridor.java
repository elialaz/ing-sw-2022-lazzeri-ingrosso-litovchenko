package it.polimi.ingsw.Model;

public class Corridor{
    private final Color color;
    private int studentNumber;
    private boolean professor;
    public Corridor(Color color){
        this.color = color;
        this.studentNumber = 0;
        this.professor = false;
    }

    public boolean addStudent() {
        this.studentNumber++;
        return this.studentNumber == 3 || this.studentNumber == 6 || this.studentNumber == 9;
    }
    public void addStudent(int num) {
        this.studentNumber += num;
    }

    public void setProfessor(boolean professor) {
        this.professor = professor;
    }

    public int getStudentNumber() {
        return this.studentNumber;
    }

    public boolean isProfessor() {
        return professor;
    }

    public Color getColor() {
        return color;
    }
    public boolean isColor(Color color){
        return color == this.color;
    }
}
