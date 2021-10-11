package comp1140.ass2.gui.guiPieces;

import comp1140.ass2.GameLogic.Game;
import comp1140.ass2.GameLogic.PurCublino;
import comp1140.ass2.State.Die;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import comp1140.ass2.State.Boards;

import javafx.scene.*;

/**
 * Displays a possibly interactive board.
 *
 * @author Zane Gates
 */
public class GuiBoard extends SubScene {

    /* board layout */
    private static final int VIEWER_WIDTH = 933;
    private static final int VIEWER_HEIGHT = 700;

    private static final String URI_BASE = "assets/";
    private final Group root = new Group();

    double mouseX = 0;

    public GuiDie selectedDie = null;

    GuiTile[][] boardTiles;

    PurCublino game;

    boolean permitsMoveMaking;

    Label turnLabel;

    List<GuiDie> GuiDies = new ArrayList<>();

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */
    public GuiBoard(String placement, boolean playable, Label turnLabel) throws Exception {
        super(new Group(), VIEWER_WIDTH, VIEWER_HEIGHT, true, SceneAntialiasing.BALANCED);

        this.permitsMoveMaking = playable;
        if (permitsMoveMaking && turnLabel == null)
            throw new InvalidSetupException("Any playable board must have an associated HUD");
        this.turnLabel = turnLabel;

        this.setRoot(root);
        boardTiles = new GuiTile[7][7];

        try {
            game = new PurCublino(true, new Boards(placement));
        } catch (Exception e) {return;} // If the user inputs an invalid board state, do not update the display

        // Iterate over every tile in the board
        for (int y = 0; y < 7; y++) {
            for (int x = 0; x < 7; x++) {
                // Construct the checkerboard tile
                boardTiles[x][y] = new GuiTile(new Position(x, y), this);
                root.getChildren().add(boardTiles[x][y]);

                // If the game state contains a die at the current position, construct it as well
                Die die = game.getBoard().getAt(x, y);
                if (die != null) {
                    GuiDie m = new GuiDie(die, this);
                    root.getChildren().add(m);
                    GuiDies.add(m);
                }
            }
        }

        // Connect mouse controls to their corresponding actions
        setEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            if (e.isPrimaryButtonDown()) {
                if (permitsMoveMaking && e.isSecondaryButtonDown()) handleApplyStep();
                else handleSelectDie(e.getSceneX());
                redrawSelection();
            }
        });

        setEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            if (selectedDie == null) handleBoardRotate(e.getSceneX());
        });

        setEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            if (permitsMoveMaking && !e.isPrimaryButtonDown()) handleDieRelease();
        });

        // Allow the board group to be rotated as the mouse is dragged
        root.setRotationAxis(new Point3D(0, 1, 0));

        // Establish, position and rotate camera
        PerspectiveCamera camera = new PerspectiveCamera();
        camera.setRotationAxis(new Point3D(1, 0, 0));
        camera.setTranslateY(100-VIEWER_HEIGHT*0.75);
        camera.setTranslateZ(-250);
        camera.setTranslateX(-VIEWER_WIDTH*0.5);
        camera.setRotate(-30);
        setCamera(camera);

        // Establish soft white lighting to remove shading and shadows
        root.getChildren().add(new AmbientLight(Color.WHITE));

        setOnDragDetected(e -> startFullDrag());
    }

    private void handleSelectDie(double xPosition) {
        mouseDown = true;
        selectedTiles.clear();
        selectedTiles.add(mouseOverTile);

        for (GuiDie m : GuiDies) {
            if (m.die.getPosition().equals(mouseOverTile.toString())
                    && m.die.isWhite() == game.getCurrentPlayer().isWhite()) {
                selectedDie = m;
                return;
            }
        }

        selectedDie = null;
        mouseX = xPosition;
    }

    private void handleApplyStep() {
        game.applyStep(selectedDie.die, mouseOverTile.toString());
        selectedTiles.add(new Position(selectedDie.die.getX(), selectedDie.die.getY()));
        selectedDie.setTranslationFromDie();
        redrawSelection();
        selectedDie.getTransforms().set(0, selectedDie.necessaryRotations());
    }

    private void handleBoardRotate(double xPosition) {
        root.setRotate(root.getRotate() + (mouseX - xPosition)/10);
        mouseX = xPosition;
    }

    private void handleDieRelease() {
        selectedDie = null;

        boolean moveExecuted = false;
        for (Game.Move m : game.getStepHistory()) {
            if (m.getType() == Game.MoveType.JUMP || m.getType() == Game.MoveType.TIP) {
                moveExecuted = true;
                break;
            }
        }

        if (moveExecuted) {
            game.endTurn();
            if (game.getWinner() == Game.GameResult.UNFINISHED) {
                turnLabel.setText(game.getCurrentPlayer().isWhite() ? "White" : "Black");
            } else {
                permitsMoveMaking = false;
                turnLabel.setText(game.getWinner().name());
            }
        }

        mouseDown = false;
        selectedTiles.clear();
        redrawSelection();
    }

    public void handleMouseOverTile(Position position) {
        mouseOverTile = position;
        redrawSelection();
    }

    public void handleMouseExitTile(Position position) {
        if (mouseOverTile != null && mouseOverTile.equals(position)) mouseOverTile = null;
        redrawSelection();
    }

    public void selectTile(Position p, GuiTile.SelectionType s) {
        try {
            boardTiles[p.x][p.y].setSelected(s);
        } catch (Exception ignored) { }
    }

    public void deselectEverything() {
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                boardTiles[y][x].setSelected(GuiTile.SelectionType.UNSELECTED);
            }
        }
    }

    public static PhongMaterial makePhongFromAsset(String path) {
        PhongMaterial result = new PhongMaterial();
        result.setDiffuseMap(new Image(new File(URI_BASE + path).toURI().toString()));
        return result;
    }

    boolean mouseDown = false;
    Position mouseOverTile;
    List<Position> selectedTiles = new ArrayList<>();

    private void redrawSelection() {
        if (permitsMoveMaking) {
            deselectEverything();
            if (mouseDown && selectedDie == null) return;
            for (Position p : selectedTiles) {
                selectTile(p, GuiTile.SelectionType.PREVIOUS);
            }
            if (mouseOverTile != null) selectTile(mouseOverTile, GuiTile.SelectionType.CURRENT);
        }
    }

    public static class InvalidSetupException extends Exception {
        public InvalidSetupException(String errorMessage) {
            super(errorMessage);
        }
    }
}
