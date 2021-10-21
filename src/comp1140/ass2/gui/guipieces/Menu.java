package comp1140.ass2.gui.guipieces;

import comp1140.ass2.controller.Controller;
import comp1140.ass2.gui.Board;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * A menu containing all the options for the game,
 * displayed at the start of the program and in between every game
 *
 * Author: Zane Gates
 */
public class Menu extends Group {

    Board board;
    ChoiceBox<String> gameMode;
    ChoiceBox<String> skybox;
    PlayerMenu p1Menu;
    PlayerMenu p2Menu;

    /**
     * Constructs a menu
     * @param board the board GUI to which this menu belongs
     */
    public Menu(Board board) {

        this.board = board;

        // Create text showing the game's title
        Text title = new Text();
        title.setText("Cublino");
        title.setScaleX(4);
        title.setScaleY(4);
        title.setLayoutX(230);
        title.setLayoutY(110);

        // Create text showing the authors
        Text authors = new Text();
        authors.setText("Yuechen Liu / Zane Gates / Ziling Ouyang");
        authors.setScaleX(1.5);
        authors.setScaleY(1.5);
        authors.setLayoutX(395);
        authors.setLayoutY(120);

        // A dropdown menu containing the options for the skybox
        skybox = new ChoiceBox<>();
        skybox.getItems().addAll("Random", "Placid", "Siberian", "Martian", "Overcast");
        skybox.getSelectionModel().select(0);
        skybox.setLayoutX(520);
        skybox.setPrefWidth(200);
        skybox.setLayoutY(250);

        // The menus containing the options for each player controller
        p1Menu = new PlayerMenu(0);
        p2Menu = new PlayerMenu(1);
        p1Menu.setLayoutY(150);
        p2Menu.setLayoutY(200);

        // Create icon images for each of the players
        ImageView icon1 = new ImageView();
        icon1.setImage(GuiBoard.imageFromAsset("avatar/player1logo.png"));
        icon1.setScaleX(0.1);
        icon1.setScaleY(0.1);
        icon1.setLayoutX(190-256);
        icon1.setLayoutY(150+10-256);
        ImageView icon2 = new ImageView();
        icon2.setImage(GuiBoard.imageFromAsset("avatar/player2logo.png"));
        icon2.setScaleX(0.1);
        icon2.setScaleY(0.1);
        icon2.setLayoutX(190-256);
        icon2.setLayoutY(200+10-256);

        // A dropdown menu for the two available game modes
        gameMode = new ChoiceBox<>();
        gameMode.getItems().addAll("Pur Cublino", "Contra Cublino");
        gameMode.getSelectionModel().select(0);
        gameMode.setLayoutX(200);
        gameMode.setPrefWidth(300);
        gameMode.setLayoutY(250);

        // A button that starts the game when pressed
        Button beginButton = new Button("Begin");
        beginButton.setOnAction(actionEvent -> { startGame(); });
        beginButton.setLayoutX(600);
        beginButton.setPrefWidth(120);
        beginButton.setLayoutY(300);

        // Create a multiline text to show the instructions
        Text instructions = new Text();
        instructions.setText("Instructions:\n" +
                "Hold LMB and drag your mouse to rotate the view. \n" +
                "Press and hold LMB over a tile with a die to select it,\n" +
                "then press RMB on another tile to make a move\n" +
                "and repeat to make further steps, then release LMB\n" +
                "to complete your turn. Press Escape to bring up \n" +
                "the pause menu with further options.\n");
        instructions.setLayoutX(250);
        instructions.setWrappingWidth(400);
        instructions.setLayoutY(425);
        instructions.setTextAlignment(TextAlignment.CENTER);

        getChildren().addAll(title, authors, skybox, icon1, p1Menu, icon2, p2Menu, gameMode, beginButton, instructions);
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
            name.setPrefWidth(310);

            // A horizontal box to contain each of these settings
            HBox hb = new HBox();
            hb.getChildren().addAll(controller, skin, name);
            hb.setSpacing(10);
            hb.setLayoutX(200);
            getChildren().add(hb);
        }

        /**
         * Constructs a controller from the player settings
         * @return a corresponding Controller
         */
        public Controller asController() {
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
            case 2 -> GuiSkybox.Locale.TUNDRA;
            case 3 -> GuiSkybox.Locale.MARS;
            case 4 -> GuiSkybox.Locale.SKY;
            default -> GuiSkybox.Locale.NONE;
        };
    }

    /**
     * Starts the game.
     */
    public void startGame() {
        try {
            board.startGame(gameMode.getSelectionModel().getSelectedIndex() == 0,
                    skyboxFromDropdown(skybox.getSelectionModel().getSelectedIndex()),
                    new Controller[] {p1Menu.asController(), p2Menu.asController()});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
