/**
 * UML:
 *      > 3 CharacterCard, scelte a random fanno parte della GameBoard
 */

package it.polimi.ingsw.Model;

public class CharacterCard implements Effect {
    private int price;
    private Component components;
    private Effect effect;
    private int effect_id;

    @Override
    public void GetEffect() {
        switch(effect_id) {
            case 1:
                StudentToIsland FirstEffect = new StudentToIsland();
                FirstEffect.GetEffect();
            case 2:
                ProfessorControl SecondEffect = new ProfessorControl();
                SecondEffect.GetEffect();
            case 3:
                ChosenIsland ThirdEffect = new ChosenIsland();
                ThirdEffect.GetEffect();
            case 4:
                MoveAgainMotherNature FourthEffect = new MoveAgainMotherNature();
                FourthEffect.GetEffect();
            case 5:
                NoEntryTilesEffect FifthEffect = new NoEntryTilesEffect();
                FifthEffect.GetEffect();
            case 6:
                NoCountTower SixthEffect = new NoCountTower();
                SixthEffect.GetEffect();
            case 7:
                FromCartToEntrance SeventhEffect = new FromCartToEntrance();
                SeventhEffect.GetEffect();
            case 8:
                FromEntranceToDiningRoom EighthEffect = new FromEntranceToDiningRoom();
                EighthEffect.GetEffect();
            case 9:
                PlusTwoEffect NinthEffect = new PlusTwoEffect();
                NinthEffect.GetEffect();
            case 10:
                StudentToDiningRoom TenthEffect = new StudentToDiningRoom();
                TenthEffect.GetEffect();
            case 11:
                NoColorCount EleventhEffect = new NoColorCount();
                EleventhEffect.GetEffect();
            case 12:
                ReplaceStudentsInBag TwelfthEffect = new ReplaceStudentsInBag();
                TwelfthEffect.GetEffect();
        }
    }
}
