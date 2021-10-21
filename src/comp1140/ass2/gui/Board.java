package comp1140.ass2.gui;

import comp1140.ass2.Controller.Controller;
import comp1140.ass2.gui.guiPieces.GuiBoard;
import comp1140.ass2.gui.guiPieces.Menu;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

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

    Group root;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cublino");

        root = new Group();

        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(new Menu(this));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void startGame(boolean isPur, Controller[] controllers) throws Exception {
        root.getChildren().clear();

        Label turnDisplayer = new Label("White");
        turnDisplayer.setAlignment(Pos.TOP_LEFT);
        turnDisplayer.setTextAlignment(TextAlignment.LEFT);
        turnDisplayer.setLayoutX(40);
        turnDisplayer.setLayoutY(20);
        turnDisplayer.setPrefWidth(100);
        turnDisplayer.setStyle("-fx-background-color: white;");
        turnDisplayer.setScaleX(1.5);
        turnDisplayer.setScaleY(1.5);

        Button takeBack = new Button("Takeback");
        takeBack.setOnMouseClicked(e -> {game.takeBack();});
        takeBack.setLayoutX(VIEWER_WIDTH-100);
        takeBack.setLayoutY(50);

        game = new GuiBoard((isPur ? "P" : "C") + "Wa1Wb1Wc1Wd1We1Wf1Wg1va7vb7vc7vd7ve7vf7vg7", controllers, isPur,true, turnDisplayer);

        root.getChildren().addAll(game, turnDisplayer, takeBack);
    }
}
