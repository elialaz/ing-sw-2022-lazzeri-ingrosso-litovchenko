package it.polimi.ingsw.Client.View.gui.Scene;

public class NumPlayerScene {
    private int minPlayers;
    private int maxPlayers;

/*  @FXML
    private Text numPlayerRequest;
    @FXML
    private TextField numPlayerInsert;
    @FXML
    private Text numPlayerAnswer;
    @FXML
    private Button numPlayerButton;

    @FXML
    public void initialize(){
        numPlayerButton.setDisable(false);
        numPlayerAnswer.setVisible(false);

        createGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this:: onCreateGameButtonClick);
        joinGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this:: onJoinGameButtonClick);
    }

     private void onNumPlayerButtonClick(){
        if(minPlayers< numPlayerInsert.getText()< maxPlayers){
            numPlayerAnswer.setText("OK!, choose now a gameid");
            numPlayerAnswer.setVisible(true);
            scene.changeScene(event, )
        }
        else{
            numPlayerAnswer.setText("Error; insert a correct num of players")
            numPlayerAnswer.setVisible(true);
        }
    }
 */

    public void setPlayersRange(int minPlayers, int maxPlayers) {
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
    }
}
