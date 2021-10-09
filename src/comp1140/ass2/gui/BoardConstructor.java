package comp1140.ass2.gui;

import comp1140.ass2.GameLogic.Game;
import comp1140.ass2.GameLogic.PurCublino;
import comp1140.ass2.State.Die;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import comp1140.ass2.State.Boards;

import javafx.scene.*;

/**
 * A very simple viewer for piece placements in the Cublino game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 *
 * @author Zane Gates
 */
public class BoardConstructor extends SubScene {

    /* board layout */
    private static final int VIEWER_WIDTH = 933;
    private static final int VIEWER_HEIGHT = 700;

    private static final String URI_BASE = "assets/";
    private final Group root = new Group();

    double mouseX = 0;

    public DieModel selectedDie = null;

    BoardTile[][] boardTiles;

    PurCublino game;

    boolean permitsMoveMaking;

    Label turnLabel;

    List<DieModel> dieModels = new ArrayList<>();

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */
    public BoardConstructor(String placement, boolean permitsMoveMaking, Label turnLabel) {
        super(new Group(), VIEWER_WIDTH, VIEWER_HEIGHT, true, SceneAntialiasing.BALANCED);
        createDieMesh();

        this.permitsMoveMaking = permitsMoveMaking;

        this.setRoot(root);
        boardTiles = new BoardConstructor.BoardTile[7][7];

        this.turnLabel = turnLabel;

        try {
            game = new PurCublino(true, new Boards(placement));
        } catch (Exception e) {return;} // If the user inputs an invalid board state, do not update the display

        // Iterate over every tile in the board
        for (int y = 0; y < 7; y++) {
            for (int x = 0; x < 7; x++) {
                // Construct the checkerboard tile
                boardTiles[x][y] = new BoardTile(new Position(x, y), this);
                root.getChildren().add(boardTiles[x][y]);

                // If the game state contains a die at the current position, construct it as well
                Die die = game.getBoard().getAt(x, y);
                if (die != null) {
                    DieModel m = new DieModel(die, this);
                    root.getChildren().add(m);
                    dieModels.add(m);
                }
            }
        }

        // Allow the board group to be rotated as the mouse is dragged
        root.setRotationAxis(new Point3D(0, 1, 0));

        setEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            mouseX = e.getSceneX();
            if (permitsMoveMaking) {
                if (e.isPrimaryButtonDown() && e.isSecondaryButtonDown()) {
                    game.applyStep(selectedDie.die, mouseOverTile.toString());
                    selectedTiles.add(new Position(selectedDie.die.getX(), selectedDie.die.getY()));
                    selectedDie.setTranslationFromDie();
                    newTileSelected();
                    selectedDie.getTransforms().set(0, selectedDie.necessaryRotations());
                } else if (e.isPrimaryButtonDown()) {
                    selectedDie = null;
                    selectedTiles.clear();
                    selectedTiles.add(mouseOverTile);
                    mouseDown = true;
                    for (DieModel m : dieModels) {
                        if (m.die.getPosition().equals(mouseOverTile.toString())
                                && m.die.isWhite() == game.getCurrentPlayer().isWhite()) {
                            selectedDie = m;
                            break;
                        }
                    }
                }

                newTileSelected();
            }
        });

        setEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            if (selectedDie == null) root.setRotate(root.getRotate() + (mouseX - e.getSceneX())/10);
            mouseX = e.getSceneX();
        });

        setEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            if (permitsMoveMaking) {
                if (!e.isPrimaryButtonDown()) {
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
                        if (turnLabel != null) {
                            turnLabel.setText(game.getCurrentPlayer().isWhite() ? "White" : "Black");
                        }
                    }

                    mouseDown = false;
                    selectedTiles.clear();
                    newTileSelected();
                }
            }
        });

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

    public final static TriangleMesh dieMesh = new TriangleMesh();
    final static double dieScale = 40;

    /*      y   Vertices          UV Map
          G-^-----H                 I---J
       z  |\|     |\                | 5 |
        ^ | C-------D           K---L---M---N---O
         \| |     | |           | 6 | 3 | 1 | 4 |
          E-|-----F |           P---Q---R---S---T
           \|      \|               | 2 |
            A-------B-->x           U---V                   */

    public static void createDieMesh() {
        dieMesh.getPoints().addAll(
                -1,-1,-1, // A 0
                1,-1,-1,         // B 1
                -1, 1,-1,        // C 2
                1, 1,-1,         // D 3
                -1, -1, 1,       // E 4
                1,-1, 1,         // F 5
                -1, 1, 1,        // G 6
                1, 1, 1);        // H 7
        dieMesh.getTexCoords().addAll(
                0.25f, 0, // I 0
                0.5f, 0,         // J 1
                0, 0.25f,        // K 2
                0.25f, 0.25f,    // L 3
                0.5f, 0.25f,     // M 4
                0.75f, 0.25f,    // N 5
                1, 0.25f,        // O 6
                0, 0.5f,         // P 7
                0.25f, 0.5f,     // Q 8
                0.5f, 0.5f,      // R 9
                0.75f, 0.5f,     // S 10
                1, 0.5f,         // T 11
                0.25f, 0.75f,    // U 12
                0.5f, 0.75f);    // V 13
        dieMesh.getFaces().addAll(
                1,1, 4,3, 0,0, // BEA 5
                1,1, 5,4, 4,3,      // BFE 5
                4,3, 2,7, 0,2,      // ECA 6
                4,3, 6,8, 2,7,      // EGC 6
                5,4, 6,8, 4,3,      // FGE 3
                5,4, 7,9, 6,8,      // FHG 3
                1,5, 7,9, 5,4,      // BHF 1
                1,5, 3,10,7,9,      // BDH 1
                0,6, 3,10,1,5,      // ADB 4
                0,6, 2,11,3,10,     // ACD 4
                7,9, 2,12,6,8,      // HCG 2
                7,9, 3,13,2,12);    // HDC 2
    }

    public void selectTile(Position p, SelectionType s) {
        try {
            boardTiles[p.x][p.y].setSelected(s);
        } catch (Exception ignored) { }
    }

    public void deselectEverything() {
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                boardTiles[y][x].setSelected(SelectionType.UNSELECTED);
            }
        }
    }

    public static class DieModel extends MeshView {

        Die die;
        BoardConstructor viewer;

        /**
         * Constructs and transforms a die mesh to provide an
         * accurate visual model of any die.
         *
         * @param die the die to show
         */
        public DieModel(Die die, BoardConstructor viewer) {
            super(dieMesh);
            this.die = die;
            this.viewer = viewer;

            // Apply the die texture to the mesh
            setMaterial(die.isWhite() ? whiteMaterial : blackMaterial);

            // Rotate the mesh to show the correct numbers
            getTransforms().add(necessaryRotations());

            // Position and scale the mesh
            setScaleX(dieScale);
            setScaleY(dieScale);
            setScaleZ(dieScale);
            setTranslationFromDie();

            setMouseTransparent(true);

            DieModel thisModel = this;
            AnimationTimer animation = new AnimationTimer() {
                @Override
                public void handle(long l) {
                    setTranslateY(getTranslateY() + ((viewer.selectedDie == thisModel ? -50 : 0) - getTranslateY())*0.2);
                }
            };

            animation.start();
        }

        /**
         * Computes a rotation transform along the various axes
         * @param degrees the side of the rotation, in degrees
         * @return the required rotation
         */
        Rotate spinTransform(double degrees) {
            return new Rotate(degrees, new Point3D(0, 1, 0));
        }

        Rotate pitchTransform(double degrees) {
            return new Rotate(degrees, new Point3D(0, 0, 1));
        }

        Rotate rollTransform(double degrees) {
            return new Rotate(degrees, new Point3D(1, 0, 0));
        }

        /**
         * Computes the necessary rotation that,
         * when applied to the default die, orients
         * it in alignment with the source die
         *
         * @return the required rotation
         */
        public Transform necessaryRotations() {
            // Convert from the number on the back face of the die back to the relative number. For example,
            // if the top face shows a 5 and the back face shows a 4, this is the third possible back face
            // since 2 is impossible as it must be on the bottom. Hence the relativeBackNumber would be 3.
            int relativeBackNumber = die.getBack();
            // Decrement the relative index for each number that it cannot be - namely the top and bottom faces
            if (die.getBack() > die.getTop()) relativeBackNumber--;
            if (die.getBack() > 7 - die.getTop()) relativeBackNumber--;

            // When the top number is 2 or 6, the numbers are reversed, due to chirality
            if (die.getTop() == 2 || die.getTop() == 6) relativeBackNumber = 5 - relativeBackNumber;

            // When the top of the die is an even number, the front and back faces will be swapped
            if (die.getTop() % 2 == 0) {
                if (relativeBackNumber == 2) relativeBackNumber = 3;
                else if (relativeBackNumber == 3) relativeBackNumber = 2;
            }

            // Apply the rotations one after another to correctly position both faces
            // If the top and back faces are positioned correctly, the other four must be as well
            return backRotation(relativeBackNumber).createConcatenation(topRotation(die.getTop()));
        }

        /**
         * Computes the rotation to show a particular
         * relative number on the back face of the mesh
         * @return the required rotation
         */
        Rotate backRotation(int relativeBackFace) {
            return switch (relativeBackFace) {
                case 1 -> spinTransform(90);
                case 2 -> spinTransform(180);
                case 4 -> spinTransform(-90);
                default -> new Rotate(); // 4 (which has a relative index of 3) is the default back face
            };
        }

        /**
         * Computes the rotation to show a particular
         * absolute number on the top face of the mesh
         * @return the required rotation
         */
        Rotate topRotation(int topFace) {
            return switch (topFace) {
                case 1 -> pitchTransform(-90);
                case 2 -> pitchTransform(180);
                case 3 -> rollTransform(90);
                case 4 -> rollTransform(-90);
                case 6 -> pitchTransform(90);
                default -> new Rotate(); // 5 is the default top face
            };
        }

        void setTranslationFromDie() {
            setTranslateX(125 * (die.getX()-3));
            setTranslateZ(125 * (die.getY()-3));
        }
    }

    final static PhongMaterial whiteMaterial = makePhongFromAsset("whitedie.png");
    final static PhongMaterial blackMaterial = makePhongFromAsset("blackdie.png");
    final static PhongMaterial whiteTileMaterial = makePhongFromAsset("whitetile.png");
    final static PhongMaterial blackTileMaterial = makePhongFromAsset("blacktile.png");
    final static PhongMaterial darkRedTileMaterial = makePhongFromAsset("previousselectedtile.png");
    final static PhongMaterial redTileMaterial = makePhongFromAsset("selectedtile.png");

    static PhongMaterial makePhongFromAsset(String path) {
        PhongMaterial result = new PhongMaterial();
        result.setDiffuseMap(new Image(new File(URI_BASE + path).toURI().toString()));
        return result;
    }

    boolean mouseDown = false;
    Position mouseOverTile;
    List<Position> selectedTiles = new ArrayList<>();

    void newTileSelected() {
        if (permitsMoveMaking) {
            deselectEverything();
            if (mouseDown && selectedDie == null) return;
            for (Position p : selectedTiles) {
                selectTile(p, SelectionType.PREVIOUS);
            }
            if (mouseOverTile != null) selectTile(mouseOverTile, SelectionType.CURRENT);
        }
    }

    static class BoardTile extends Box {
        Position position;
        BoardConstructor viewer;

        public BoardTile(Position p, BoardConstructor viewer) {
            super(125, 20, 125);
            setTranslateX(125*(p.x-3));
            setTranslateY(50);
            setTranslateZ(125*(p.y-3));

            this.position = new Position(p.x, p.y);

            this.viewer = viewer;

            setSelected(SelectionType.UNSELECTED);
            toBack();

            setOnMouseEntered(event -> {
                viewer.mouseOverTile = position;
                viewer.newTileSelected();
            });

            setOnMouseDragEntered(event -> {
                viewer.mouseOverTile = position;
                viewer.newTileSelected();
            });

            setOnMouseExited(event -> {
                if (viewer.mouseOverTile.equals(position)) viewer.mouseOverTile = null;
                viewer.newTileSelected();
            });

            setOnDragExited(event -> {
                if (viewer.mouseOverTile.equals(position)) viewer.mouseOverTile = null;
                viewer.newTileSelected();
            });
        }

        public void setSelected(SelectionType s) {
            switch (s) {
                case UNSELECTED -> setMaterial((position.x + position.y) % 2 == 0 ? whiteTileMaterial : blackTileMaterial);
                case PREVIOUS -> setMaterial(darkRedTileMaterial);
                case CURRENT -> setMaterial(redTileMaterial);
            }
        }
    }

    public enum SelectionType {UNSELECTED, PREVIOUS, CURRENT}

    static class Position {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
            if (x < 0) this.x = 0;
            if (x >= 7) this.x = 6;
            if (y < 0) this.y = 0;
            if (y >= 7) this.y = 6;
        }

        @Override
        public String toString() {
            return "" + x + "" + y;
        }
    }
}
