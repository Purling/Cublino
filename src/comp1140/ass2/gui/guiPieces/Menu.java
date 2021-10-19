package comp1140.ass2.gui.guiPieces;

import comp1140.ass2.gui.Board;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class Menu extends Group {
    public Menu(Board board) {
        /*Label label1 = new Label("Placement:");
        TextField textField = new TextField();
        textField.setPrefWidth(300);*/

        ChoiceBox gameMode = new ChoiceBox();
        gameMode.getItems().addAll("Pur Cublino", "Contra Cublino");
        gameMode.getSelectionModel().select(0);
        gameMode.setLayoutX(100);
        gameMode.setPrefWidth(250);
        gameMode.setLayoutY(200);

        Button beginButton = new Button("Begin");
        beginButton.setOnAction(actionEvent -> {
            try {
                board.startGame(gameMode.getSelectionModel().getSelectedIndex() == 0);
            } catch (Exception e) {
                e.printStackTrace();
            }});
        beginButton.setLayoutX(400);
        beginButton.setLayoutY(200);

        getChildren().addAll(gameMode, beginButton);

        /*HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, refresh);
        hb.setSpacing(10);
        hb.setLayoutX(230);
        getChildren().add(hb);*/

        /*Label instructions = new Label("Drag the mouse horizontally to rotate view");
        instructions.setLayoutX(10);
        instructions.setLayoutY(10);
        getChildren().add(instructions);*/
    }
}
