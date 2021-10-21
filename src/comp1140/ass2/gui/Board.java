package comp1140.ass2.gui;

import comp1140.ass2.Controller.Controller;
import comp1140.ass2.gui.guiPieces.GuiBoard;
import comp1140.ass2.gui.guiPieces.GuiSkybox;
import comp1140.ass2.gui.guiPieces.Menu;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * A very simple viewer for piece placements in the Cublino game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
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

    Label turnDisplayer;
    Button takeBack;
    Button toMenu;

    boolean inGame = false;
    boolean pauseMenuVisible = false;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cublino");

        root = new Group();

        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        menu = new Menu(this);

        showMenu();

        primaryStage.setScene(scene);
        primaryStage.show();

        turnDisplayer = new Label("White");
        turnDisplayer.setAlignment(Pos.TOP_LEFT);
        turnDisplayer.setTextAlignment(TextAlignment.LEFT);
        turnDisplayer.setLayoutX(40);
        turnDisplayer.setLayoutY(20);
        turnDisplayer.setPrefWidth(100);
        turnDisplayer.setStyle("-fx-background-color: white;");
        turnDisplayer.setScaleX(1.5);
        turnDisplayer.setScaleY(1.5);

        takeBack = new Button("Takeback");
        takeBack.setOnMouseClicked(e -> {game.takeBack();});
        takeBack.setLayoutX(VIEWER_WIDTH/2-50);
        takeBack.setLayoutY(VIEWER_HEIGHT/2-20);
        takeBack.setPrefWidth(100);
        takeBack.setTextAlignment(TextAlignment.CENTER);

        toMenu = new Button("Menu");
        toMenu.setOnMouseClicked(e -> {showMenu();});
        toMenu.setLayoutX(VIEWER_WIDTH/2-50);
        toMenu.setLayoutY(VIEWER_HEIGHT/2+20);
        toMenu.setPrefWidth(100);
        toMenu.setTextAlignment(TextAlignment.CENTER);

        Rectangle pauseBackground = new Rectangle();
        pauseBackground.setLayoutX(0);
        pauseBackground.setLayoutY(0);
        pauseBackground.setWidth(VIEWER_WIDTH);
        pauseBackground.setHeight(VIEWER_HEIGHT);
        pauseBackground.setFill(Color.BLACK);
        pauseBackground.setOpacity(0.5);

        scene.setOnKeyPressed(e -> {
            if (inGame && e.getCode() == KeyCode.ESCAPE) {
                pauseMenuVisible = !pauseMenuVisible;
                if (pauseMenuVisible) root.getChildren().addAll(pauseBackground, takeBack, toMenu);
                else root.getChildren().removeAll(pauseBackground, takeBack, toMenu);
            }
        });
    }

    public void showMenu() {
        inGame = false;
        root.getChildren().clear();
        root.getChildren().add(menu);
    }

    public void startGame(boolean isPur, GuiSkybox.Locale locale, Controller[] controllers) throws Exception {
        inGame = true;
        pauseMenuVisible = false;
        root.getChildren().clear();
        game = new GuiBoard((isPur ? "P" : "C") + "Wa1Wb1Wc1Wd1We1Wf1Wg1va7vb7vc7vd7ve7vf7vg7", locale, controllers, isPur,true, turnDisplayer);
        root.getChildren().addAll(game, turnDisplayer);
    }
}
