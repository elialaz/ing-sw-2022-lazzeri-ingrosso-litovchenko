

package it.polimi.ingsw.Model;
import java.util.ArrayList;
import java.util.List;

public class AssistantDeck {
    private ArrayList<AssistantCard> deck;

    public AssistantDeck() {
        List<Integer> values = AssistantCard.getValidValue();
        List<Integer> maxMovesMotherNature = AssistantCard.getValidMaxMoveMotherNature();

        deck = new ArrayList<>();

        int i = 0, j = 0;
        for (int value: values){
            deck.add(new AssistantCard(value, maxMovesMotherNature.get(j)));
            i++;
            if(i%2 == 0)
                j++;
        }
    }
}
