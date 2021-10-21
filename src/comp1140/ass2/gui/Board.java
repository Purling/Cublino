package comp1140.ass2.gui;

import comp1140.ass2.controller.Controller;
import comp1140.ass2.gui.guipieces.GuiBoard;
import comp1140.ass2.gui.guipieces.GuiSkybox;
import comp1140.ass2.gui.guipieces.Menu;
import javafx.animation.AnimationTimer;
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
    Button restart;

    boolean inGame = false;
    boolean pauseMenuVisible = false;

    boolean startingFadeNow = false;
    double initialTime = 0;
    FadeAction fadingTo;

    enum FadeAction {NONE, MENU, GAME};

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
        takeBack = new Button("Takeback move");
        takeBack.setOnMouseClicked(e -> {game.takeBack();});
        takeBack.setLayoutX(VIEWER_WIDTH*0.5-100);
        takeBack.setLayoutY(VIEWER_HEIGHT*0.5-50);
        takeBack.setPrefWidth(200);
        takeBack.setTextAlignment(TextAlignment.CENTER);

        // Construct a button to restart the game without returning to the menu
        restart = new Button("Restart game");
        restart.setOnMouseClicked(e -> {showMenu(); menu.startGame();});
        restart.setLayoutX(VIEWER_WIDTH*0.5-100);
        restart.setLayoutY(VIEWER_HEIGHT*0.5);
        restart.setPrefWidth(200);
        restart.setTextAlignment(TextAlignment.CENTER);

        // Construct a button to return to the menu
        toMenu = new Button("Return to menu");
        toMenu.setOnMouseClicked(e -> {showMenu();});
        toMenu.setLayoutX(VIEWER_WIDTH*0.5-100);
        toMenu.setLayoutY(VIEWER_HEIGHT*0.5+50);
        toMenu.setPrefWidth(200);
        toMenu.setTextAlignment(TextAlignment.CENTER);

        // Construct a translucent gray rectangle as a background for the pause menu
        Rectangle pauseBackground = new Rectangle();
        pauseBackground.setLayoutX(0);
        pauseBackground.setLayoutY(0);
        pauseBackground.setWidth(VIEWER_WIDTH);
        pauseBackground.setHeight(VIEWER_HEIGHT);
        pauseBackground.setFill(Color.BLACK);
        pauseBackground.setOpacity(0.5);

        // Construct a translucent gray rectangle as a background for the pause menu
        Rectangle fadeBackground = new Rectangle();
        fadeBackground.setLayoutX(0);
        fadeBackground.setLayoutY(0);
        fadeBackground.setWidth(VIEWER_WIDTH);
        fadeBackground.setHeight(VIEWER_HEIGHT);
        fadeBackground.setFill(Color.BLACK);
        fadeBackground.setOpacity(0);


        new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (startingFadeNow) {
                    initialTime = l;
                    startingFadeNow = false;
                }
                double progress = (l-initialTime)/1e9;
                double opacity = 2*(0.5-Math.abs(progress-0.5));
                fadeBackground.setOpacity(opacity);
                if (opacity <= 0 && root.getChildren().contains(fadeBackground)) {
                    root.getChildren().remove(fadeBackground);
                } else if (opacity > 0 && !root.getChildren().contains(fadeBackground)) {
                    root.getChildren().add(fadeBackground);
                }


            }
        }.start();

        // When ESCAPE is pressed while in a game, toggle the visibility of the pause menu
        scene.setOnKeyPressed(e -> {
            if (inGame && e.getCode() == KeyCode.ESCAPE) {
                pauseMenuVisible = !pauseMenuVisible;
                if (pauseMenuVisible) root.getChildren().addAll(pauseBackground, takeBack, toMenu, restart);
                else root.getChildren().removeAll(pauseBackground, takeBack, toMenu, restart);
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
        startingFadeNow = true;
        fadingTo = FadeAction.MENU;
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
        startingFadeNow = true;
        fadingTo = FadeAction.GAME;
    }
}
