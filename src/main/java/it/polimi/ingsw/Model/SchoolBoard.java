package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;

public class SchoolBoard {
    private List<Student> EntranceStudent;
    private ArrayList<Corridor> DiningRoom;
    private int NumTower;
    private Player ownedby;

    public SchoolBoard(Bag bag, Player player){
        this.DiningRoom = new ArrayList<Corridor>();
        this.DiningRoom.add(new Corridor(Color.GREEN));
        this.DiningRoom.add(new Corridor(Color.RED));
        this.DiningRoom.add(new Corridor(Color.YELLOW));
        this.DiningRoom.add(new Corridor(Color.PINK));
        this.DiningRoom.add(new Corridor(Color.BLUE));
        this.NumTower = 8;
        this.ownedby = player;
        this.EntranceStudent = bag.getStudent(9);
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

    public void moveToCorridor(int studentNum){
        for (Corridor corridor: this.DiningRoom) {
            if(corridor.isColor(EntranceStudent.get(studentNum).color)){
                if(corridor.addStudent()){
                    this.ownedby.addNumCoin(1);
                }
            }
        }
        EntranceStudent.remove(studentNum);
    }

    public Student moveToIsland(int num){
        Student islandStudent = EntranceStudent.get(num);
        EntranceStudent.remove(num);
        return  islandStudent;
    }

    public Corridor getCorridor(Color color){
        for (Corridor x:DiningRoom) {
            if (x.getColor() == color);
            return x;
        }
    }
}
