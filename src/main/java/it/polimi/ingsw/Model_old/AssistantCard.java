/**
 * Le 10 carte assistente, compongono il AssistantDeck
 *  UML:
 *      > L'AssistantDeck dipende dalle AssistantCard
 *      > Il player usa il AssistantDeck specifico (scelto fra i 4)
 */

package it.polimi.ingsw.Model_old;
import java.util.List;
import java.util.Arrays;

public class AssistantCard{
    private int value;
    private int maxMoveMotherNature;

    public AssistantCard(int value, int maxMoveMotherNature) {
        setValue(value);
        setMaxMoveMotherNature(maxMoveMotherNature);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        List<Integer> validValue = getValidValue();
        if (validValue.contains(value))
            this.value = value;
        else
            throw new IllegalArgumentException("Passed a non-valid Assistant Card value");
    }

    public int getMaxMoveMotherNature() {
        return maxMoveMotherNature;
    }

    public void setMaxMoveMotherNature(int maxMoveMotherNature) {
        List<Integer> validMaxMoveMotherNature = getValidMaxMoveMotherNature();
        if (validMaxMoveMotherNature.contains(value))
            this.maxMoveMotherNature = maxMoveMotherNature;
        else
            throw new IllegalArgumentException("Passed a non-valid Mother Nature movement value in Assistant Card");
    }

    public static List<Integer> getValidValue(){ return Arrays.asList(1,2,3,4,5,6,7,8,9,10); }

    public static List<Integer> getValidMaxMoveMotherNature(){
        return Arrays.asList(1,2,3,4,5);
    }

    // public boolean IsAlreadyUsed() {}

}
