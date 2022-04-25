package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;

public class IslandTile {
    private final int idTile;
    private TowerColor Tower;
    private List<Student> studentOnIsland;
    private boolean union;
    private ArrayList<Integer> unionIsland;
    private boolean MotherNature;

    public IslandTile(int id, Student start, boolean first){
        if (first){
            MotherNature = true;
        }
        idTile = id;
        studentOnIsland.add(start);
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
}
