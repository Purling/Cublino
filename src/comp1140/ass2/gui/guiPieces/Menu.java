package comp1140.ass2.gui.guiPieces;

import comp1140.ass2.Controller.Controller;
import comp1140.ass2.gui.Board;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class Menu extends Group {
    public Menu(Board board) {

        ChoiceBox<String> skybox = new ChoiceBox<>();
        skybox.getItems().addAll("Random", "Lake", "Tundra", "Mars");
        skybox.getSelectionModel().select(0);
        skybox.setLayoutX(100);
        skybox.setPrefWidth(150);
        skybox.setLayoutY(50);

        PlayerMenu p1Menu = new PlayerMenu(0);
        PlayerMenu p2Menu = new PlayerMenu(1);
        p1Menu.setLayoutY(100);
        p2Menu.setLayoutY(150);

        ChoiceBox<String> gameMode = new ChoiceBox<>();
        gameMode.getItems().addAll("Pur Cublino", "Contra Cublino");
        gameMode.getSelectionModel().select(0);
        gameMode.setLayoutX(100);
        gameMode.setPrefWidth(250);
        gameMode.setLayoutY(200);

        Button beginButton = new Button("Begin");
        beginButton.setOnAction(actionEvent -> {
            try {
                board.startGame(gameMode.getSelectionModel().getSelectedIndex() == 0,
                        skyboxFromDropdown(skybox.getSelectionModel().getSelectedIndex()),
                        new Controller[] {p1Menu.asController(), p2Menu.asController()});
            } catch (Exception e) {
                e.printStackTrace();
            }});
        beginButton.setLayoutX(400);
        beginButton.setLayoutY(200);

        getChildren().addAll(skybox, p1Menu, p2Menu, gameMode, beginButton);
    }

    public static class PlayerMenu extends Group {

        ChoiceBox<String> controller;
        TextField name;
        ChoiceBox<String> skin;

        private final int index;

        private final String[] defaultNames = {"Player 1", "Player 2", "Easy AI 1", "Easy AI 2", "Difficult AI 1", "Difficult AI 2"};

        public PlayerMenu(int index) {
            this.index = index;

            controller = new ChoiceBox<>();
            controller.getItems().addAll("Human", "Easy AI", "Difficult AI");
            controller.getSelectionModel().select(index);

            controller.setOnAction(e -> checkDefaultName());

            skin = new ChoiceBox<>();
            skin.getItems().addAll("White", "Black", "Oak", "Cloudy", "Marshmellow",
                    "Mint", "Deepsea", "Neon", "Pumpkin", "Starry", "Gilded");
            skin.setPrefWidth(100);
            skin.getSelectionModel().select(index);

            name = new TextField();
            name.setText(nameFromControllerSetting(index) + " " + (index + 1));
            name.setPrefWidth(300);

            HBox hb = new HBox();
            hb.getChildren().addAll(controller, skin, name);
            hb.setSpacing(10);
            hb.setLayoutX(100);
            getChildren().add(hb);
        }

        public Controller asController() {
            return new Controller(typeFromDropdown(controller.getSelectionModel().getSelectedIndex()),
                    name.getText(), skinFromDropdown(skin.getSelectionModel().getSelectedIndex()));
        }

        public Controller.ControllerType typeFromDropdown(int i) {
            return switch (i) {
                case 1 -> Controller.ControllerType.EASY_AI;
                case 2 -> Controller.ControllerType.DIFFICULT_AI;
                default -> Controller.ControllerType.HUMAN;
            };
        }

        public GuiDie.Skin skinFromDropdown(int i) {
            return switch (i) {
                case 0 -> GuiDie.Skin.PLAIN_WHITE;
                case 1 -> GuiDie.Skin.PLAIN_BLACK;
                case 2 -> GuiDie.Skin.OAK;
                case 3 -> GuiDie.Skin.CLOUDY;
                case 4 -> GuiDie.Skin.MARSH_MELLOW;
                case 5 -> GuiDie.Skin.MINT;
                case 6 -> GuiDie.Skin.DEEP_SEA;
                case 7 -> GuiDie.Skin.NEON;
                case 8 -> GuiDie.Skin.PUMPKIN;
                case 9 -> GuiDie.Skin.STARRY;
                case 10 -> GuiDie.Skin.GILDED;
                default -> GuiDie.Skin.NONE;
            };
        }

        public String nameFromControllerSetting(int i) {
            return switch (i) {
                case 0 -> "Player";
                case 1 -> "Easy AI";
                case 2 -> "Difficult AI";
                default -> "";
            };
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

    public GuiSkybox.Locale skyboxFromDropdown(int i) {
        return switch (i) {
            case 0 -> GuiSkybox.Locale.RANDOM;
            case 1 -> GuiSkybox.Locale.LAKE;
            case 2 -> GuiSkybox.Locale.TUNDRA;
            case 3 -> GuiSkybox.Locale.MARS;
            default -> GuiSkybox.Locale.NONE;
        };
    }
}
