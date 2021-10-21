package comp1140.ass2.gui.guiPieces;

import comp1140.ass2.Controller.Controller;
import comp1140.ass2.gui.Board;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

/**
 * A menu containing all the options for the game,
 * displayed at the start of the program and in between every game
 *
 * Author: Zane Gates
 */
public class Menu extends Group {
    /**
     * Constructs a menu
     * @param board the board GUI to which this menu belongs
     */
    public Menu(Board board) {
        // A dropdown menu containing the options for the skybox
        ChoiceBox<String> skybox = new ChoiceBox<>();
        skybox.getItems().addAll("Random", "Placid", "Tuscan", "Siberian", "Martian", "Overcast");
        skybox.getSelectionModel().select(0);
        skybox.setLayoutX(100);
        skybox.setPrefWidth(150);
        skybox.setLayoutY(50);

        // The menus containing the options for each player controller
        PlayerMenu p1Menu = new PlayerMenu(0);
        PlayerMenu p2Menu = new PlayerMenu(1);
        p1Menu.setLayoutY(100);
        p2Menu.setLayoutY(150);

        // A dropdown menu for the two available game modes
        ChoiceBox<String> gameMode = new ChoiceBox<>();
        gameMode.getItems().addAll("Pur Cublino", "Contra Cublino");
        gameMode.getSelectionModel().select(0);
        gameMode.setLayoutX(100);
        gameMode.setPrefWidth(250);
        gameMode.setLayoutY(200);

        // A button that starts the game when pressed
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

    // Stores settings relevant to each player
    public static class PlayerMenu extends Group {

        ChoiceBox<String> controller;
        TextField name;
        ChoiceBox<String> skin;

        private final int index;

        // A list of the names that can be set by default
        private final String[] defaultNames = {"Player 1", "Player 2", "Easy AI 1", "Easy AI 2", "Difficult AI 1", "Difficult AI 2"};

        /**
         * Construct a menu for a player
         * @param index this player's number (0 for white, 1 for black)
         */
        private PlayerMenu(int index) {
            this.index = index;

            // A dropdown for the settings for controller, either Human or one of the AI difficulties
            controller = new ChoiceBox<>();
            controller.getItems().addAll("Human", "Easy AI", "Difficult AI");
            controller.getSelectionModel().select(index);
            // When the difficulty is updated, if the name is default, update that too
            controller.setOnAction(e -> checkDefaultName());

            // A dropdown containing the available player skins
            skin = new ChoiceBox<>();
            skin.getItems().addAll("White", "Black", "Oak", "Cloudy", "Marshmellow",
                    "Mint", "Deepsea", "Neon", "Pumpkin", "Starry", "Gilded");
            skin.setPrefWidth(100);
            skin.getSelectionModel().select(index);

            // A text field for the player's name
            name = new TextField();
            name.setText(nameFromControllerSetting(index) + " " + (index + 1));
            name.setPrefWidth(300);

            // A horizontal box to contain each of these settings
            HBox hb = new HBox();
            hb.getChildren().addAll(controller, skin, name);
            hb.setSpacing(10);
            hb.setLayoutX(100);
            getChildren().add(hb);
        }

        /**
         * Constructs a controller from the player settings
         * @return a corresponding Controller
         */
        private Controller asController() {
            return new Controller(typeFromDropdown(controller.getSelectionModel().getSelectedIndex()),
                    name.getText(), skinFromDropdown(skin.getSelectionModel().getSelectedIndex()));
        }

        /**
         * Finds the controller type, as an enum, from the dropdown menu
         * @param i the index in the dropdown
         * @return the ControllerType
         */
        private Controller.ControllerType typeFromDropdown(int i) {
            return switch (i) {
                case 1 -> Controller.ControllerType.EASY_AI;
                case 2 -> Controller.ControllerType.DIFFICULT_AI;
                default -> Controller.ControllerType.HUMAN;
            };
        }

        /**
         * Finds the skin, as an enum, from the dropdown menu
         * @param i the index in the dropdown
         * @return the GuiDie.Skin
         */
        private GuiDie.Skin skinFromDropdown(int i) {
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

        /**
         * Finds the default name for a particular controller setting
         * @param i the difficulty setting index
         * @return the corresponding name, as a String
         */
        private String nameFromControllerSetting(int i) {
            return switch (i) {
                case 0 -> "Player";
                case 1 -> "Easy AI";
                case 2 -> "Difficult AI";
                default -> "";
            };
        }

        /**
         * If the currently set name is a default name, but not the one corresponding to the current player settings, update it.
         */
        private void checkDefaultName() {
            for (String n : defaultNames) {
                if (n.equals(name.getText())) {
                    name.setText(nameFromControllerSetting(controller.getSelectionModel().getSelectedIndex()) + " " + (index + 1));
                    return;
                }
            }
        }
    }

    /**
     * Finds the locale, as a enum, from the dropdown
     * @param i the index of the dropdown
     * @return the corresponding GuiSkybox.Locale
     */
    public GuiSkybox.Locale skyboxFromDropdown(int i) {
        return switch (i) {
            case 0 -> GuiSkybox.Locale.RANDOM;
            case 1 -> GuiSkybox.Locale.LAKE;
            case 2 -> GuiSkybox.Locale.WHEAT;
            case 3 -> GuiSkybox.Locale.TUNDRA;
            case 4 -> GuiSkybox.Locale.MARS;
            case 5 -> GuiSkybox.Locale.SKY;
            default -> GuiSkybox.Locale.NONE;
        };
    }
}
