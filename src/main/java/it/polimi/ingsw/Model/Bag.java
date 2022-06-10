package it.polimi.ingsw.Model;


import java.util.Random;

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
        while (i<num){
            switch (random.nextInt(5)){
                case 0:
                    if(studentsGenerated[0]<maxNum){
                        arr[0]++;
                        studentsGenerated[0]++;
                        i++;
                    }
                    break;
                case 1:
                    if(studentsGenerated[1]<maxNum){
                        arr[1]++;
                        studentsGenerated[1]++;
                        i++;
                    }
                    break;
                case 2:
                    if(studentsGenerated[2]<maxNum){
                        arr[2]++;
                        studentsGenerated[2]++;
                        i++;
                    }
                    break;
                case 3:
                    if(studentsGenerated[3]<maxNum){
                        arr[3]++;
                        studentsGenerated[3]++;
                        i++;
                    }
                    break;
                case 4:
                    if(studentsGenerated[4]<maxNum){
                        arr[4]++;
                        studentsGenerated[4]++;
                        i++;
                    }
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
