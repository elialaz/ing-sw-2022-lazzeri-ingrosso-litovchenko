package it.polimi.ingsw.Model;

import java.util.Random;

/**
 * Bag class used to generate students
 * @author elia_laz
 **/
public class Bag {

    private final int[] studentsGenerated;
    private final Random random;
    private final int maxNum;

    /**
     * Constructor for the Bag class
     * @param maxNum max number of the students generated
     **/
    public Bag(int maxNum){
        studentsGenerated = new int[]{0, 0, 0, 0, 0};
        random = new Random();
        this.maxNum = maxNum;
    }

    /**
     * Students Generation/Random extraction
     * @param num Number of Students that need to be generated
     * @return int array to represent the extracted students
     **/
    public int[] getStudents(int num){
        int[] arr = {0,0,0,0,0};
        int i=0;
        int count;
        while (i<num){
            count=0;
            for(int j=0; j<5; j++){
                if(studentsGenerated[j]==maxNum){
                    count++;
                }
            }
            if(count!=5){
                int extracted = random.nextInt(5-count);
                count=0;
                int j=0;
                while(j<5){
                    if(studentsGenerated[j]!=maxNum && extracted==count){
                        arr[j]++;
                        studentsGenerated[j]++;
                        i++;
                        j=6;
                    }
                    else if(studentsGenerated[j]!=maxNum && extracted!=count){
                        count++;
                    }
                    j++;
                }
            }
            else{
                break;
            }
        }
        return arr;
    }

    /**
     * Students insertion
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

    public int getMaxNum() {
        return maxNum;
    }
}
