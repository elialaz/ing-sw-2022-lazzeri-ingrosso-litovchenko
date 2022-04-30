package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;

public class SchoolBoard {
    private List<Student> EntranceStudent;
    private final ArrayList<Corridor> DiningRoom;
    private int NumTower;
    private final Player ownedby;

    public SchoolBoard(Bag bag, Player player, int townum, int studentnum){
        this.DiningRoom = new ArrayList<Corridor>();
        this.DiningRoom.add(new Corridor(Color.GREEN));
        this.DiningRoom.add(new Corridor(Color.RED));
        this.DiningRoom.add(new Corridor(Color.YELLOW));
        this.DiningRoom.add(new Corridor(Color.PINK));
        this.DiningRoom.add(new Corridor(Color.BLUE));
        this.NumTower = townum;
        this.ownedby = player;
        this.EntranceStudent = bag.getStudent(studentnum);
    }

    public int getNumTower() {
        return NumTower;
    }

    public void addTower(){
        this.NumTower++;
    }

    public void removeTower(){
        this.NumTower--;
    }

    public void addProfessor(Color color){
        for (Corridor corridor: this.DiningRoom) {
            if(corridor.isColor(color)){
                corridor.setProfessor(true);
            }
        }
    }

    public void removeProfessor(Color color){
        for (Corridor corridor: this.DiningRoom) {
            if(corridor.isColor(color)){
                corridor.setProfessor(false);
            }
        }
    }

    public List<Student> getEntranceStudent(){
        return EntranceStudent;
    }

    public void moveToCorridor(int studentNum, GameBoard board){
        for (Corridor corridor: this.DiningRoom) {
            if(corridor.isColor(EntranceStudent.get(studentNum).color)){
                if(corridor.addStudent()){
                    this.ownedby.addNumCoin(1);
                    board.removeCoin(1);
                }
            }
        }
        EntranceStudent.remove(studentNum);
    }

    public boolean hasProf(Color c) {
        for (Corridor c1: DiningRoom) {
            if(c1.isColor(c)){
                return c1.isProfessor();
            }
        }
        return false;
    }

    public int getCorridorStudent(Color color) {
        for (Corridor c: DiningRoom) {
            if (c.isColor(color)){
                return c.getStudentNumber();
            }
        }
        return 0;
    }

    public Corridor getCorridor(Color color){
        for (Corridor x:DiningRoom) {
            if (x.getColor() == color);
            return x;
        }
    }
}
