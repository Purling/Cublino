package comp1140.ass2.gui;

import comp1140.ass2.GameLogic.PurCublino;
import comp1140.ass2.State.Die;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

import java.io.File;

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

    // When the mouse is moved less than this amount, the die will be left in place
    private static final double MOUSE_NULLSPOT = 40;
    private static final double MOUSE_SMALLMOVESPOT = 120;

    private final Group root = new Group();

    double mouseX = 0;

    public DieModel selectedDie = null;

    BoardTile[][] boardTiles;

    PurCublino game;

    boolean permitsMoveMaking;

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */
    public BoardConstructor(String placement, boolean permitsMoveMaking) {
        super(new Group(), VIEWER_WIDTH, VIEWER_HEIGHT, true, SceneAntialiasing.BALANCED);
        createDieMesh();
        createMaterials();

        this.permitsMoveMaking = permitsMoveMaking;

        this.setRoot(root);
        boardTiles = new BoardConstructor.BoardTile[7][7];

        try {
            game = new PurCublino(true, new Boards(placement));
        } catch (Exception e) {System.out.println(e); return;} // If the user inputs an invalid board state, do not update the display

        // Iterate over every tile in the board
        for (int y = 0; y < 7; y++) {
            for (int x = 0; x < 7; x++) {
                // Construct the checkerboard tile
                boardTiles[x][y] = new BoardTile(x, y);
                root.getChildren().add(boardTiles[x][y]);

                // If the game state contains a die at the current position, construct it as well
                Die die = game.getBoard().getAt(x, y);
                if (die != null) root.getChildren().add(new DieModel(die, this));
            }
        }

        // ALlow the board group to be rotated as the mouse is dragged
        root.setRotationAxis(new Point3D(0, 1, 0));
        root.setOnMousePressed(e -> {
            mouseX = e.getSceneX();
        });
        root.setOnMouseDragged(e -> {
            if (selectedDie == null) root.setRotate(root.getRotate() + (mouseX - e.getSceneX())/10);
            mouseX = e.getSceneX();
        });

        // Establish, position and rotate camera
        PerspectiveCamera camera = new PerspectiveCamera();
        camera.setRotationAxis(new Point3D(1, 0, 0));
        camera.setTranslateY(100-VIEWER_HEIGHT*3/4);
        camera.setTranslateZ(-250);
        camera.setTranslateX(-VIEWER_WIDTH/2);
        camera.setRotate(-30);
        setCamera(camera);

        // Establish soft white lighting to remove shading and shadows
        AmbientLight light = new AmbientLight(Color.WHITE);
        root.getChildren().add(light);
    }

    public double getBoardRotation() {
        double result = root.getRotate();
        while (result < 0) result += 360;
        return result % 360;
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

    public void selectTile(int x, int y) {
        try {
            boardTiles[x][y].setSelected();
        } catch (Exception e) {
            return;
        }
    }

    public void deselectEverything() {
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                boardTiles[y][x].setUnselected();
            }
        }
    }

    public void tryToMakeMove(Die movingDie, String move) {
        game.applyStep(movingDie, move);
    }

    public static class DieModel extends MeshView {

        Die die;
        BoardConstructor viewer;
        DieModel me;

        double mouseDownX;
        double mouseDownY;
        double mouseCurrentX;
        double mouseCurrentY;

        int indicatorDirection;
        int indicatorDistance;

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

            this.me = this;

            // Apply the die texture to the mesh
            setMaterial(die.isWhite() ? whiteMaterial : blackMaterial);

            // Rotate the mesh to show the correct numbers
            getTransforms().add(necessaryRotations());

            // Position and scale the mesh
            setScaleX(dieScale);
            setScaleY(dieScale);
            setScaleZ(dieScale);
            setTranslateX(125 * (die.getX()-3));
            setTranslateZ(125 * (die.getY()-3));

            if (!viewer.permitsMoveMaking) return;

            this.setOnMousePressed(event -> {
                viewer.selectedDie = this;
                mouseDownX = event.getScreenX();
                mouseDownY = event.getScreenY();
                mouseCurrentX = mouseDownX;
                mouseCurrentY = mouseDownY;
                viewer.selectedDie = this;
                indicatorDirection = -1;
                viewer.selectTile(die.getX(), die.getY());
            });

            this.setOnMouseDragged(event -> {
                mouseCurrentX = event.getScreenX();
                mouseCurrentY = event.getScreenY();

                viewer.deselectEverything();
                viewer.selectTile(die.getX(), die.getY());

                if (inNullSpot()) {
                    indicatorDirection = -1;
                    indicatorDistance = 0;
                    return;
                }

                double direction = Math.atan2(mouseCurrentY - mouseDownY, mouseCurrentX - mouseDownX)*180/Math.PI;
                direction -= viewer.getBoardRotation();
                indicatorDirection = (((int) Math.round(direction/90)) + 8)%4;

                indicatorDistance = inSmallMoveSpot() ? 1 : 2;

                Position positionToHighlight = new Position(die.getX(), die.getY()).positionIn(indicatorDirection, indicatorDistance);
                viewer.selectTile(positionToHighlight.x, positionToHighlight.y);
            });

            this.setOnMouseReleased(event -> {
                if (indicatorDirection != -1) {
                    viewer.tryToMakeMove(this.die, "" + new Position(die.getX(), die.getY())
                         .positionIn(indicatorDirection, indicatorDistance));
                    setTranslateX(125 * (die.getX()-3));
                    setTranslateZ(125 * (die.getY()-3));
                    getTransforms().set(0, necessaryRotations());
                }
                viewer.deselectEverything();
                viewer.selectedDie = null;
            });

            AnimationTimer animation = new AnimationTimer() {
                @Override
                public void handle(long l) {
                    setTranslateY(lerp(getTranslateY(), viewer.selectedDie == me ? -50 : 0, 0.2));
                }
            };

            animation.start();
        }

        class Position {
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

            public Position positionIn(int direction, int distance) {
                switch(direction) {
                    case 0: return new Position(x+distance, y);
                    case 1: return new Position(x, y-distance);
                    case 2: return new Position(x-distance, y);
                    case 3: return new Position(x, y+distance);
                    default: return new Position(x, y);
                }
            }
        }


        double lerp(double start, double end, double amount) {
            return start + (end-start)*amount;
        }

        /**
         * Computes a rotation transform along the various axes
         * @param degrees the side of the rotation, in degrees
         * @returns the required rotation
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
         * @returns the required rotation
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
         * @returns the required rotation
         */
        Rotate backRotation(int relativeBackFace) {
            switch(relativeBackFace) {
                case 1: return spinTransform( 90);
                case 2: return spinTransform(180);
                case 4: return spinTransform(-90);
                default: return new Rotate(); // 4 (which has a relative index of 3) is the default back face
            }
        }

        /**
         * Computes the rotation to show a particular
         * absolute number on the top face of the mesh
         * @returns the required rotation
         */
        Rotate topRotation(int topFace) {
            switch(topFace) {
                case 1: return pitchTransform(-90);
                case 2: return pitchTransform(180);
                case 3: return rollTransform(  90);
                case 4: return rollTransform( -90);
                case 6: return pitchTransform( 90);
                default: return new Rotate(); // 5 is the default top face
            }
        }

        public double magnitude(double x, double y) {
            return Math.sqrt(x*x+y*y);
        }

        boolean inNullSpot() {
            return mouseMagnitude() < MOUSE_NULLSPOT;
        }

        boolean inSmallMoveSpot() {
            return mouseMagnitude() < MOUSE_SMALLMOVESPOT;
        }

        double mouseMagnitude() {
            return magnitude(mouseCurrentY - mouseDownY, mouseCurrentX - mouseDownX);
        }
    }

    final static PhongMaterial whiteMaterial = new PhongMaterial();
    final static PhongMaterial blackMaterial = new PhongMaterial();
    final static PhongMaterial whiteTileMaterial = new PhongMaterial();
    final static PhongMaterial blackTileMaterial = new PhongMaterial();
    final static PhongMaterial selectedTileMaterial = new PhongMaterial();

    static void createMaterials() {
        whiteMaterial.setDiffuseMap(makeTextureFromAsset("whitedie.png"));
        blackMaterial.setDiffuseMap(makeTextureFromAsset("blackdie.png"));
        whiteTileMaterial.setDiffuseMap(makeTextureFromAsset("whitetile.png"));
        blackTileMaterial.setDiffuseMap(makeTextureFromAsset("blacktile.png"));
        selectedTileMaterial.setDiffuseMap(makeTextureFromAsset("selectedtile.png"));
    }

    static Image makeTextureFromAsset(String path) {
        return new Image(new File(URI_BASE + path).toURI().toString());
    }

    class BoardTile extends Box {
        int x;
        int y;

        public BoardTile(int x, int y) {
            super(125, 20, 125);
            setTranslateX(125*(x-3));
            setTranslateY(50);
            setTranslateZ(125*(y-3));

            this.x = x;
            this.y = y;

            setUnselected();
            toBack();
        }

        public void setSelected() {
            setMaterial(selectedTileMaterial);
        }

        public void setUnselected() {
            setMaterial((x+y)%2 == 0 ? whiteTileMaterial : blackTileMaterial);
        }
    }
}
