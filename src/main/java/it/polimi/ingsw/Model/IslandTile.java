package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;

public class IslandTile {
    private final int idTile;
    private TowerColor Tower;
    private ArrayList<Student> studentOnIsland;
    private boolean union;
    private ArrayList<Integer> unionIsland;
    private boolean MotherNature;

    public IslandTile(int id, Student start, boolean first){
        studentOnIsland = new ArrayList<Student>();
        if (first){
            MotherNature = true;
        }
        idTile = id;
        studentOnIsland.add(start);
        union = false;
    }

    public IslandTile(int id, boolean first){
        studentOnIsland = new ArrayList<Student>();
        if (first){
            MotherNature = true;
        }
        idTile = id;
        union = false;
    }

    public void setTower(TowerColor tower) {
        Tower = tower;
    }

    public TowerColor getTower() {
        return Tower;
    }

    public boolean isMotherNature() {
        return MotherNature;
    }

    public void setMotherNature(boolean motherNature) {
        MotherNature = motherNature;
    }

    public boolean isUnion() {
        return union;
    }

    public int getIdTile() {
        return idTile;
    }

    public void setUnion(boolean union) {
        this.union = union;
    }

    public void addStudentOnIsland(Student studentMovedToIsland) {
        studentOnIsland.add(studentMovedToIsland);
    }

    public List<Student> getStudentOnIsland() {
        return studentOnIsland;
    }

    public ArrayList<Integer> getUnionIsland() {
        return unionIsland;
    }

    public void addUnionIsland(int id) {
        unionIsland.add(id);
    }

    public boolean hasTower() {
        return Tower != null;
    }

    public int Influence (Player player){
        List<Student> StudentsOnIsland;
        int influence = 0;
        if (this.isMotherNature());
            StudentsOnIsland = this.getStudentOnIsland();
            Corridor C = player.getBoard().getCorridor(Color.BLUE);
            if (C.isProfessor());
                for (Student s: StudentsOnIsland) {
                    if(s.color == Color.BLUE);
                        influence ++;
                }
            C = player.getBoard().getCorridor(Color.PINK);
            if (C.isProfessor());
                for (Student s: StudentsOnIsland) {
                    if (s.color == Color.PINK) ;
                    influence++;
                }
            C = player.getBoard().getCorridor(Color.YELLOW);
            if (C.isProfessor());
                for (Student s: StudentsOnIsland) {
                    if(s.color == Color.YELLOW);
                    influence ++;
                }
            C = player.getBoard().getCorridor(Color.GREEN);
            if (C.isProfessor());
                for (Student s: StudentsOnIsland) {
                    if(s.color == Color.GREEN);
                    influence ++;
                }
            C = player.getBoard().getCorridor(Color.RED);
            if (C.isProfessor());
                for (Student s: StudentsOnIsland) {
                    if(s.color == Color.RED);
                    influence ++;
                }
        if (this.getTower() == player.getTower());
            influence ++;
        return influence;
    }
}
