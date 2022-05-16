package it.polimi.ingsw.Model_old;

import java.util.ArrayList;
import java.util.List;

public class IslandTile {
    private final int idTile;
    private TowerColor Tower;
    private ArrayList<Student> studentOnIsland;
    private boolean union;
    private ArrayList<Integer> unionIsland;
    private boolean MotherNature;

    private int noentrytile;

    private boolean nocolorcount;

    private Color colortonotcount;
    private boolean notowercount;

    public IslandTile(int id, Student start, boolean first){
        nocolorcount = false;
        notowercount = false;
        studentOnIsland = new ArrayList<Student>();
        if (first){
            MotherNature = true;
        }
        idTile = id;
        studentOnIsland.add(start);
        union = false;
        noentrytile = 0;
    }

    public IslandTile(int id, boolean first){
        studentOnIsland = new ArrayList<Student>();
        if (first){
            MotherNature = true;
        }
        idTile = id;
        union = false;
    }

    public Color getColortonotcount() {
        return colortonotcount;
    }

    public void setColortonotcount(Color colortonotcount) {
        this.colortonotcount = colortonotcount;
    }

    public boolean isNocolorcount() {
        return nocolorcount;
    }

    public boolean isNotowercount() {
        return notowercount;
    }

    public void setNocolorcount(boolean nocolorcount) {
        this.nocolorcount = nocolorcount;
    }

    public void setNotowercount(boolean notowercount) {
        this.notowercount = notowercount;
    }

    public boolean isNoentrytile() {
        return noentrytile>0;
    }

    public void removeEntryTile(){
        noentrytile--;
    }

    public void setNoentrytile() {
        noentrytile++;
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

    //TODO sistemare unione e calcolo in caso isole unite, nocount tower e no count colorstudent, influenza in squadra da sistemare
    public int Influence (Player player){
        int influence = 0;
        if(player.hasbonus()){
            influence = influence + 2;
            player.removeBonus();
        }
        if (this.isMotherNature()){
            Corridor C = player.getBoard().getCorridor(Color.BLUE);
            if (C.isProfessor()) {
                for (Student s : studentOnIsland) {
                    if (s.color == Color.BLUE) {
                        influence++;
                    }
                }
            }
            C = player.getBoard().getCorridor(Color.PINK);
            if (C.isProfessor()) {
                for (Student s : studentOnIsland) {
                    if (s.color == Color.PINK) {
                        influence++;
                    }
                }
            }
            C = player.getBoard().getCorridor(Color.YELLOW);
            if (C.isProfessor()) {
                for (Student s : studentOnIsland) {
                    if (s.color == Color.YELLOW) {
                        influence++;
                    }
                }
            }
            C = player.getBoard().getCorridor(Color.GREEN);
            if (C.isProfessor()) {
                for (Student s : studentOnIsland) {
                    if (s.color == Color.GREEN) {
                        influence++;
                    }
                }
            }
            C = player.getBoard().getCorridor(Color.RED);
            if (C.isProfessor()) {
                for (Student s : studentOnIsland) {
                    if (s.color == Color.RED) {
                        influence++;
                    }
                }
            }
        }
        if (this.getTower() == player.getTowerColor()) {
            influence++;
        }
        return influence;
    }
}