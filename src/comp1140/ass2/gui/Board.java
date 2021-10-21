package comp1140.ass2.gui;

import comp1140.ass2.controller.Controller;
import comp1140.ass2.gui.guipieces.GuiBoard;
import comp1140.ass2.gui.guipieces.GuiSkybox;
import comp1140.ass2.gui.guipieces.Menu;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * A comprehensive class for playing games of Cublino.
 *
 * @author Zane Gates
 */
public class Board extends Application {

    /* board layout */
    private static final int VIEWER_WIDTH = 933;
    private static final int VIEWER_HEIGHT = 700;

    GuiBoard game;
    Menu menu;

    Group root;

    ImageView turnDisplayer;
    Button takeBack;
    Button toMenu;

    boolean inGame = false;
    boolean pauseMenuVisible = false;

    /**
     * Starts the application
     * @param primaryStage the stage from which the application will be built
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cublino");

        root = new Group();

        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        menu = new Menu(this);

        showMenu();

        primaryStage.setScene(scene);
        primaryStage.show();

        // Construct the label that shows whose turn it is currently
        turnDisplayer = new ImageView();
        turnDisplayer.setFitWidth(64);
        turnDisplayer.setFitHeight(64);
        turnDisplayer.setSmooth(true);
        turnDisplayer.setLayoutX(20);
        turnDisplayer.setLayoutY(20);

        // Construct the take-back button
        takeBack = new Button("Takeback");
        takeBack.setOnMouseClicked(e -> {game.takeBack();});
        takeBack.setLayoutX(VIEWER_WIDTH*0.5-50);
        takeBack.setLayoutY(VIEWER_HEIGHT*0.5-20);
        takeBack.setPrefWidth(100);
        takeBack.setTextAlignment(TextAlignment.CENTER);

        // Construct a button to return to the menu
        toMenu = new Button("Menu");
        toMenu.setOnMouseClicked(e -> {showMenu();});
        toMenu.setLayoutX(VIEWER_WIDTH*0.5-50);
        toMenu.setLayoutY(VIEWER_HEIGHT*0.5+20);
        toMenu.setPrefWidth(100);
        toMenu.setTextAlignment(TextAlignment.CENTER);

        // Construct a translucent gray rectangle as a background for the pause menu
        Rectangle pauseBackground = new Rectangle();
        pauseBackground.setLayoutX(0);
        pauseBackground.setLayoutY(0);
        pauseBackground.setWidth(VIEWER_WIDTH);
        pauseBackground.setHeight(VIEWER_HEIGHT);
        pauseBackground.setFill(Color.BLACK);
        pauseBackground.setOpacity(0.5);

        // When ESCAPE is pressed while in a game, toggle the visibility of the pause menu
        scene.setOnKeyPressed(e -> {
            if (inGame && e.getCode() == KeyCode.ESCAPE) {
                pauseMenuVisible = !pauseMenuVisible;
                if (pauseMenuVisible) root.getChildren().addAll(pauseBackground, takeBack, toMenu);
                else root.getChildren().removeAll(pauseBackground, takeBack, toMenu);
            }
        });
    }

    /**
     * Shows the menu, and hides anything else currently visible, including the game and the pause menu
     */
    public void showMenu() {
        inGame = false;
        root.getChildren().clear();
        root.getChildren().add(menu);
    }

    /**
     * Starts a game using settings read from the menu
     * @param isPur whether the game-mode is Pur or Contra
     * @param locale the skybox for this game
     * @param controllers the controllers for each players
     * @throws Exception if an invalid combination of parameters is given
     */
    public void startGame(boolean isPur, GuiSkybox.Locale locale, Controller[] controllers) throws Exception {
        inGame = true;
        pauseMenuVisible = false;
        root.getChildren().clear();
        game = new GuiBoard((isPur ? "P" : "C") + "Wa1Wb1Wc1Wd1We1Wf1Wg1va7vb7vc7vd7ve7vf7vg7", locale, controllers, isPur,true, turnDisplayer);
        root.getChildren().addAll(game, turnDisplayer);
    }
}
