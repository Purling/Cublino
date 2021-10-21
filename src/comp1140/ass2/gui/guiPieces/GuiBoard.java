package comp1140.ass2.gui.guiPieces;

import comp1140.ass2.Controller.Controller;
import comp1140.ass2.GameLogic.ContraCublino;
import comp1140.ass2.GameLogic.Game;
import comp1140.ass2.GameLogic.PurCublino;
import comp1140.ass2.State.Die;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import comp1140.ass2.State.Boards;

import javafx.scene.*;
import javafx.scene.transform.Rotate;

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

    Game game;
    Label turnLabel;
    private boolean permitsMoveMaking;

    private double mouseX = 0;
    private double mouseY = 0;
    private double cameraYaw = 10; public double getCameraYaw() {return cameraYaw;};
    private double cameraPitch = -30;
    private GuiDie selectedDie = null;

    private final GuiTile[][] boardTiles;
    private final List<GuiDie> guiDice = new ArrayList<>();

    private boolean mouseDown = false;
    private Position mouseOverTile;
    private final List<Position> selectedTiles = new ArrayList<>();

    private final Controller[] controllers;

    PerspectiveCamera camera;

    private final Group boardRoot = new Group();

    private MediaPlayer moveSfx;
    private MediaPlayer stepSfx;

    private final ArrayList<Game> stateHistory = new ArrayList<>();

    /**
     * Constructs a board and all reliant 3D elements to represent a game position
     * @param placement The initial position of the board to be displayed
     * @param playable whether or not moves can be made in the GUI. The board can be rotated regardless
     * @param turnLabel the text label in the HUD to be updated with info about the game
     * @throws Exception if moves can be made but null HUD label is provided
     */
    public GuiBoard(String placement, GuiSkybox.Locale locale, Controller[] controllers, boolean isPur, boolean playable, Label turnLabel) throws Exception {
        super(new Group(), VIEWER_WIDTH, VIEWER_HEIGHT, true, SceneAntialiasing.BALANCED);

        this.controllers = controllers;

        this.permitsMoveMaking = playable;
        if (permitsMoveMaking && turnLabel == null)
            throw new InvalidSetupException("Any playable board must have an associated HUD");
        this.turnLabel = turnLabel;

        Group root = new Group();
        this.setRoot(root);
        boardTiles = new GuiTile[7][7];

        try {
            if (isPur) game = new PurCublino(true, new Boards(placement));
            else game = new ContraCublino(true, new Boards(placement));
        } catch (Exception e) {return;} // If the user inputs an invalid board state, do not update the display

        boardRoot.setRotationAxis(Rotate.Y_AXIS);
        root.getChildren().add(boardRoot);

        // Iterate over every tile in the board
        for (int y = 0; y < 7; y++) {
            for (int x = 0; x < 7; x++) {
                // Construct the checkerboard tile
                boardTiles[x][y] = new GuiTile(new Position(x, y), this);
                boardRoot.getChildren().add(boardTiles[x][y]);

                // If the game state contains a die at the current position, construct it as well
                Die die = game.getBoard().getAt(x, y);
                if (die != null) {
                    GuiDie m = new GuiDie(die, this, controllers);
                    boardRoot.getChildren().add(m);
                    guiDice.add(m);
                }
            }
        }

        // These next four handlers connect mouse controls to their corresponding actions
        setEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            if (e.isPrimaryButtonDown()) {
                // If M2 is pressed while M1 is held, try to make the indicated move
                if (permitsMoveMaking && e.isSecondaryButtonDown()) handleApplyStep();
                // If M1 is pressed (and held), try to pick up the currently selected die
                else handleSelectDie(e.getSceneX(), e.getSceneY());
                redrawSelection();
            }
        });
        // If the mouse is dragged across the screen without die selected, rotate the board
        setEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            if (selectedDie == null) handleBoardRotate(e.getSceneX(), e.getSceneY());
        });
        // Tells the tiles that moving the mouse while holding M1 is not dragging the board,
        // so they should still be highlighted as the mouse moves over them
        setOnDragDetected(e -> startFullDrag());
        // When M1 is released, drop the currently selected die
        setEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            if (permitsMoveMaking && !e.isPrimaryButtonDown()) handleDieRelease();
        });

        // Establish, position and rotate camera
        camera = new PerspectiveCamera();
        camera.setRotationAxis(new Point3D(1, 0, 0));
        setCamera(camera);


        // Establish soft white lighting to remove shading and shadows
        root.getChildren().add(new AmbientLight(Color.rgb(175, 175, 175)));
        // Use a stronger light to draw attention towards the board
        PointLight pointLight = new PointLight(Color.rgb(200, 200, 225));
        pointLight.setTranslateY(-125);
        root.getChildren().add(pointLight);

        boardRoot.getChildren().add(new GuiSkybox(locale));

        boardRoot.getChildren().add(new GuiAvatar(this, 180, controllers[0].getName()));
        boardRoot.getChildren().add(new GuiAvatar(this, 0, controllers[1].getName()));

        moveSfx = playableFromAsset("sfx/woodenThump.mp3");
        stepSfx = playableFromAsset("sfx/woodenHit.mp3");
        moveSfx.setVolume(0.2);
        stepSfx.setVolume(0.2);

        boardRoot.setTranslateX(450);

        handleBoardRotate(0, 0);

        stateHistory.add(game.deepClone());

        if (permitsMoveMaking) haveCurrentPlayerMakeMove();
    }

    /**
     * Try to pick up a die if there is a die on the currently selected tile.
     * If no such die exists, prepare to rotate the viewer as the mouse is dragged.
     * @param xPosition the x-coordinate of the mouse on the screen
     */
    private void handleSelectDie(double xPosition, double yPosition) {
        mouseDown = true;
        selectedTiles.clear();
        selectedTiles.add(mouseOverTile);

        for (GuiDie m : guiDice) {
            if (mouseOverTile != null && m.die.getPosition().equals(mouseOverTile.toString())
                    && m.die.isWhite() == game.getCurrentPlayer().isWhite() && !m.die.isDeleted()
                    && controllers[m.die.isWhite() ? 0 : 1].getType() == Controller.ControllerType.HUMAN) {
                selectedDie = m;
                return;
            }
        }

        selectedDie = null;
        mouseX = xPosition;
        mouseY = yPosition;
    }

    /**
     * Try to move the currently picked-up die into the currently indicated position
     */
    private void handleApplyStep() {
        if (mouseOverTile == null) return;
        game.applyStep(selectedDie.die, mouseOverTile.toString());
        selectedTiles.add(new Position(selectedDie.die.getX(), selectedDie.die.getY()));
        selectedDie.setTranslationFromDie();
        redrawSelection();
    }

    /**
     * Rotate the board relative to how far the mouse has been dragged
     * @param xPosition the x-coordinate of the mouse on the screen
     */
    private void handleBoardRotate(double xPosition, double yPosition) {
        cameraYaw += (mouseX-xPosition)/10;
        cameraPitch += (mouseY-yPosition)/10;
        if (cameraPitch > -2) cameraPitch = -2;
        if (cameraPitch < -89) cameraPitch = -89;
        mouseX = xPosition;
        mouseY = yPosition;

        boardRoot.setRotate(cameraYaw);
        camera.setRotate(cameraPitch);
        camera.setTranslateZ(-700*Math.cos(Math.toRadians(cameraPitch))+362);
        camera.setTranslateY(250*Math.sin(Math.toRadians(cameraPitch)) - 400);
    }

    public void playMoveSfx() {
        moveSfx.stop();
        moveSfx.play();
    }

    public void playStepSfx() {
        stepSfx.stop();
        stepSfx.play();
    }

    /**
     * Drop the selected die in its current place after making any moves.
     * If any moves have been made, complete the current player's turn.
     */
    private void handleDieRelease() {
        selectedDie = null;

        for (Game.Move m : game.getStepHistory()) {
            if (m.getType() == Game.MoveType.JUMP || m.getType() == Game.MoveType.TIP) {
                moveComplete();
                break;
            }
        }

        mouseDown = false;
        selectedTiles.clear();
        redrawSelection();
    }

    /**
     * Remember that the mouse is currently over a particular tile.
     * @param position the tile's position
     */
    public void handleMouseOverTile(Position position) {
        mouseOverTile = position;
        redrawSelection();
    }

    /**
     * Remember that the mouse is no longer over a particular tile.
     * @param position the tile's position
     */
    public void handleMouseExitTile(Position position) {
        if (mouseOverTile != null && mouseOverTile.equals(position)) mouseOverTile = null;
        redrawSelection();
    }

    /**
     * Visibly highlights the tile under the mouse, and in a
     * darker shade, any tiles previously accessed in this turn.
     *
     * When the currently or previously selected tiles change
     * as stored in this class, this function should be called.
     */
    private void redrawSelection() {
        if (!permitsMoveMaking) return;

        // Deselect any currently selected tiles: tiles that remain selected will be re-highlighted later
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                boardTiles[y][x].setSelected(GuiTile.SelectionType.UNSELECTED);
            }
        }

        if (mouseDown && selectedDie == null) return;
        // Use a maroon to highlight previously selected tiles
        for (Position position : selectedTiles)
            boardTiles[position.x][position.y].setSelected(GuiTile.SelectionType.PREVIOUS);
        // Use a bright red to highlight the currently selected tile, if there is one
        if (mouseOverTile != null)
            boardTiles[mouseOverTile.x][mouseOverTile.y].setSelected(GuiTile.SelectionType.CURRENT);
    }

    private void haveCurrentPlayerMakeMove() {
        Game.GameResult result = game.getWinner();
        if (result == Game.GameResult.UNFINISHED) {
            turnLabel.setText(controllers[game.getCurrentPlayer().isWhite() ? 0 : 1].getName() + "'s turn.");
            new Thread(() -> {
                controllers[game.getCurrentPlayer().isWhite() ? 0 : 1].requestMove(game,this);
            }).start();
        } else {
            permitsMoveMaking = false;
            switch(result) {
                case TIE: turnLabel.setText("Tie!"); return;
                case WHITE_WINS: turnLabel.setText(controllers[0].getName() + " wins!"); return;
                case BLACK_WINS: turnLabel.setText(controllers[1].getName() + " wins!");
                //TODO: when the game is over, allow the user to return to the menu
            }
        }
    }

    public void moveComplete() {
        for (GuiDie d : guiDice) {
            d.setTranslationFromDie();
        }
        game.endTurn();
        stateHistory.add(game.deepClone());
        haveCurrentPlayerMakeMove();
    }

    public void takeBack() {
        if (controllers[game.getCurrentPlayer().isWhite() ? 0 : 1].getType() != Controller.ControllerType.HUMAN) return;
        for (GuiDie d : guiDice) {
            boardRoot.getChildren().remove(d);
        }
        guiDice.clear();

        if (stateHistory.size() > 2) {
            stateHistory.remove(stateHistory.size()-1);
            stateHistory.remove(stateHistory.size()-1);
            game = stateHistory.get(stateHistory.size()-1);
        } else {
            game = stateHistory.get(0);
            stateHistory.clear();
            stateHistory.add(game.deepClone());
        }

        for (int y = 0; y < 7; y++) {
            for (int x = 0; x < 7; x++) {
                Die die = game.getBoard().getAt(x, y);
                if (die != null) {
                    GuiDie m = new GuiDie(die, this, controllers);
                    boardRoot.getChildren().add(m);
                    guiDice.add(m);
                }
            }
        }
    }

    /**
     * Checks whether or not a particular die is selected by the user.
     * (NB: this is essentially a get method for selectedDie)
     * @param die the die to check
     * @return whether the die is selected
     */
    public boolean isDieSelected(Die die) {
        return selectedDie != null && selectedDie.die == die;
    }

    /**
     * Construct a diffused material from an image stored in a particular folder
     * @param path the path to the image
     * @return the Phong material
     */
    public static PhongMaterial makePhongFromAsset(String path) {
        PhongMaterial result = new PhongMaterial();
        result.setDiffuseMap(imageFromAsset(path));
        return result;
    }

    public static Image imageFromAsset(String path) {
        return new Image(new File(URI_BASE + path).toURI().toString());
    }

    public static MediaPlayer playableFromAsset(String path) {
        return new MediaPlayer(new Media(new File(URI_BASE + path).toURI().toString()));
    }

    private static class InvalidSetupException extends Exception {
        public InvalidSetupException(String errorMessage) {
            super(errorMessage);
        }
    }
}
