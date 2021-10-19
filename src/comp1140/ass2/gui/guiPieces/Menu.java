package comp1140.ass2.gui.guiPieces;

import comp1140.ass2.Controller.Controller;
import comp1140.ass2.gui.Board;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class Menu extends Group {
    public Menu(Board board) {
        PlayerMenu p1Menu = new PlayerMenu(0);
        PlayerMenu p2Menu = new PlayerMenu(1);
        p1Menu.setLayoutY(100);
        p2Menu.setLayoutY(150);

        ChoiceBox gameMode = new ChoiceBox();
        gameMode.getItems().addAll("Pur Cublino", "Contra Cublino");
        gameMode.getSelectionModel().select(0);
        gameMode.setLayoutX(100);
        gameMode.setPrefWidth(250);
        gameMode.setLayoutY(200);

        Button beginButton = new Button("Begin");
        beginButton.setOnAction(actionEvent -> {
            try {
                board.startGame(gameMode.getSelectionModel().getSelectedIndex() == 0,
                        new Controller[] {p1Menu.asController(), p2Menu.asController()});
            } catch (Exception e) {
                e.printStackTrace();
            }});
        beginButton.setLayoutX(400);
        beginButton.setLayoutY(200);

        getChildren().addAll(p1Menu, p2Menu, gameMode, beginButton);
    }

    public class PlayerMenu extends Group {

        ChoiceBox controller;
        TextField name;
        ChoiceBox skin;

        private String defaultName;
        private int index;

        private final String[] defaultNames = {"Player 1", "Player 2", "Easy AI 1", "Easy AI 2", "Difficult AI 1", "Difficult AI 2"};

        public PlayerMenu(int index) {
            this.index = index;

            controller = new ChoiceBox();
            controller.getItems().addAll("Human", "Easy AI", "Difficult AI");
            controller.getSelectionModel().select(index);

            controller.setOnAction(e -> {checkDefaultName();});

            skin = new ChoiceBox();
            skin.getItems().addAll("White", "Black", "Gilded");
            skin.setPrefWidth(100);
            skin.getSelectionModel().select(index);

            name = new TextField();
            defaultName = (index == 0 ? "Player" : "Easy AI");
            name.setText(nameFromControllerSetting(index) + " " + (index + 1));
            name.setPrefWidth(300);

            HBox hb = new HBox();
            hb.getChildren().addAll(controller, skin, name);
            hb.setSpacing(10);
            hb.setLayoutX(100);
            getChildren().add(hb);
        }

        public Controller asController() {
            return new Controller(controller.getSelectionModel().getSelectedIndex() == 0,
                    name.getText(), skinFromDropdown(skin.getSelectionModel().getSelectedIndex()));
        }

        public GuiDie.Skin skinFromDropdown(int i) {
            switch(i) {
                case 0: return GuiDie.Skin.PLAIN_WHITE;
                case 1: return GuiDie.Skin.PLAIN_BLACK;
                case 2: return GuiDie.Skin.GILDED;
                default: return GuiDie.Skin.NONE;
            }
        }

        public String nameFromControllerSetting(int i) {
            switch(i) {
                case 0: return "Player";
                case 1: return "Easy AI";
                case 2: return "Difficult AI";
                default: return "";
            }
        }

        public void checkDefaultName() {
            for (String n : defaultNames) {
                if (n.equals(name.getText())) {
                    name.setText(nameFromControllerSetting(controller.getSelectionModel().getSelectedIndex()) + " " + (index + 1));
                    return;
                }
            }
        }
    }
}
