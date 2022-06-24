package it.polimi.ingsw.Model;


import java.util.Random;

//TODO aggiungere check per estrazione random altrimenti entra in loop

/**
 * Bag class for students generation
 * @author elia_laz
 **/
public class Bag {

    private int[] studentsGenerated;
    private Random random;
    private int maxNum;

    /**
     * Constructor
     * @author elia_laz
     * @param maxNum max number of the students generated
     **/
    public Bag(int maxNum){
        studentsGenerated = new int[]{0, 0, 0, 0, 0};
        random = new Random();
        this.maxNum = maxNum;
    }

    /**
     * Students Generation/Random extraction
     * @author elia_laz
     * @param num Number of Students that need to be generated
     * @return integer array represent the students extracted
     **/
    public int[] getStudents(int num){
        int[] arr = {0,0,0,0,0};
        int i=0;
        int cont;
        while (i<num){
            cont=0;
            for(int j=0; j<5; j++){
                if(studentsGenerated[i]==maxNum){
                    cont++;
                }
            }
            if(cont!=5){
                int extracted = random.nextInt(6-cont) - 1;
                cont=0;
                for(int j=0; j<5; j++){
                    if(studentsGenerated[i]!=maxNum && extracted==cont){
                        arr[i]++;
                        studentsGenerated[i]++;
                        i++;
                    }
                    else if(studentsGenerated[i]!=maxNum){
                        cont++;
                    }
                }
            }
            else{
                break;
            }
        }
        return arr;
    }

    /**
     * Students inserction
     * @author elia_laz
     * @param students Students that need to be reinserted inside the Bag
     **/
    public void addStudents(int[] students){
        for(int i=0; i<5; i++){
            if(students[i]!=0){
                studentsGenerated[i] -= students[i];
            }
        }
    }
}
