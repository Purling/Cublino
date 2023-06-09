package comp1140.ass2.gui;


import comp1140.ass2.controller.Controller;
import comp1140.ass2.gui.guipieces.GuiBoard;
import comp1140.ass2.gui.guipieces.GuiDie;
import comp1140.ass2.gui.guipieces.GuiSkybox;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
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
public class Viewer extends Application {

    /* board layout */
    private static final int VIEWER_WIDTH = 933;
    private static final int VIEWER_HEIGHT = 700;

    private final Group root = new Group();
    private final Group controls = new Group();
    private TextField textField;

    private GuiBoard boardSubscene;

    private final Controller p1 = new Controller(Controller.ControllerType.HUMAN, "Player 1", GuiDie.Skin.PLAIN_WHITE);
    private final Controller p2 = new Controller(Controller.ControllerType.HUMAN, "Player 2", GuiDie.Skin.PLAIN_BLACK);

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */
    void makePlacement(String placement) throws Exception {
        root.getChildren().remove(boardSubscene);
        boardSubscene = new GuiBoard(placement, GuiSkybox.Locale.NONE, new Controller[] {p1, p2}, true, false, null);
        root.getChildren().add(boardSubscene);
    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button refresh = new Button("Refresh");
        refresh.setOnAction(actionEvent -> {
            try {
                makePlacement(textField.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
            textField.clear();
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, refresh);
        hb.setSpacing(10);
        hb.setLayoutX(230);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);

        Label instructions = new Label("Drag the mouse horizontally to rotate view");
        instructions.setLayoutX(10);
        instructions.setLayoutY(10);
        controls.getChildren().add(instructions);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Use a 3D sub scene for rendering the board alongside (behind) the controls

        boardSubscene = new GuiBoard("", GuiSkybox.Locale.NONE, new Controller[] {p1, p2}, true, false, null);

        primaryStage.setTitle("Cublino Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        makeControls();
        root.getChildren().addAll(boardSubscene, controls);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
