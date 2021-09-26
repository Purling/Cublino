package comp1140.ass2.gui;

import comp1140.ass2.State.Die;
import comp1140.ass2.State.Direction;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;

import java.io.File;

import comp1140.ass2.State.Boards;

import javafx.scene.*;

/**
 * A very simple viewer for piece placements in the Cublino game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 */
public class Viewer extends Application {

    /* board layout */
    private static final int VIEWER_WIDTH = 933;
    private static final int VIEWER_HEIGHT = 700;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group controls = new Group();
    private TextField textField;

    private SubScene boardSubscene = new SubScene(new Group(), VIEWER_WIDTH, VIEWER_HEIGHT);

    double mouseX = 0;

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */
    void makePlacement(String placement) {
        createDieMesh();
        createMaterials();

        Group subRoot = new Group();
        Group boardGroup = new Group();

        root.getChildren().remove(boardSubscene);

        // Generic JavaFX window setup
        boardSubscene = new SubScene(subRoot, VIEWER_WIDTH, VIEWER_HEIGHT, true, SceneAntialiasing.BALANCED);

        Boards boards = null;
        try {
            boards = new Boards(placement);
        } catch (Exception e) {} // If the user inputs an invalid board state, do not update the display

        if (boards == null) return;

        // Iterate over every tile in the board
        for (int y = 0; y < 7; y++) {
            for (int x = 0; x < 7; x++) {
                // Construct the checkerboard tile
                boardGroup.getChildren().add(makeTile(x, y));

                // If the game state contains a die at the current position, construct it as well
                Die die = boards.getAt(x, y);
                if (die != null) boardGroup.getChildren().add(new DieModel(die));
            }
        }

        // ALlow the board group to be rotated as the mouse is dragged
        boardGroup.setRotationAxis(new Point3D(0, 1, 0));
        boardGroup.setOnMousePressed(e -> {
            mouseX = e.getSceneX();
        });
        boardGroup.setOnMouseDragged(e -> {
            boardGroup.setRotate(boardGroup.getRotate() + (mouseX - e.getSceneX())/10);
            mouseX = e.getSceneX();
        });

        // Establish, position and rotate camera
        PerspectiveCamera camera = new PerspectiveCamera();
        camera.setRotationAxis(new Point3D(1, 0, 0));
        camera.setTranslateY(100-VIEWER_HEIGHT*3/4);
        camera.setTranslateZ(-250);
        camera.setTranslateX(-VIEWER_WIDTH/2);
        camera.setRotate(-30);
        boardSubscene.setCamera(camera);

        // Establish soft white lighting to remove shading and shadows
        AmbientLight light = new AmbientLight(Color.WHITE);
        boardGroup.getChildren().add(light);

        subRoot.getChildren().add(boardGroup);
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
                makePlacement(textField.getText());
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
        primaryStage.setTitle("Cublino Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        root.getChildren().add(controls);

        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public final static TriangleMesh dieMesh = new TriangleMesh();
    public final static double dieScale = 40;

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

    public static class DieModel extends MeshView {

        Die die;

        public DieModel(Die die) {
            super(dieMesh);
            this.die = die;

            // Apply the die texture to the mesh
            setMaterial(die.isDieWhite() ? whiteMaterial : blackMaterial);

            // Rotate the mesh to show the correct numbers
            getTransforms().add(necessaryRotations());

            // Position and scale the mesh
            setScaleX(dieScale);
            setScaleY(dieScale);
            setScaleZ(dieScale);
            setTranslateX(125 * (die.getX()-3));
            setTranslateZ(125 * (die.getY()-3));
        }

        public void spin(double degrees) {
            getTransforms().add(spinTransform(degrees));
        }

        public void pitch(double degrees) {
            getTransforms().add(pitchTransform(degrees));
        }

        public void roll(double degrees) {
            getTransforms().add(rollTransform(degrees));
        }

        public Rotate spinTransform(double degrees) {
            return new Rotate(degrees, new Point3D(0, 1, 0));
        }

        public Rotate pitchTransform(double degrees) {
            return new Rotate(degrees, new Point3D(0, 0, 1));
        }

        public Rotate rollTransform(double degrees) {
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

        public Rotate backRotation(int relativeBackFace) {
            switch(relativeBackFace) {
                case 1: return spinTransform( 90);
                case 2: return spinTransform(180);
                case 4: return spinTransform(-90);
                default: return new Rotate(); // 4 (which has a relative index of 3) is the default back face
            }
        }

        public Rotate topRotation(int topFace) {
            switch(topFace) {
                case 1: return pitchTransform(-90);
                case 2: return pitchTransform(180);
                case 3: return rollTransform(  90);
                case 4: return rollTransform( -90);
                case 6: return pitchTransform( 90);
                default: return new Rotate(); // 5 is the default top face
            }
        }
    }

    final static PhongMaterial whiteMaterial = new PhongMaterial();
    final static PhongMaterial blackMaterial = new PhongMaterial();
    final static PhongMaterial whiteTileMaterial = new PhongMaterial();
    final static PhongMaterial blackTileMaterial = new PhongMaterial();

    public static void createMaterials() {
        whiteMaterial.setDiffuseMap(makeTextureFromAsset("whitedie.png"));
        blackMaterial.setDiffuseMap(makeTextureFromAsset("blackdie.png"));
        whiteTileMaterial.setDiffuseMap(makeTextureFromAsset("whitetile.png"));
        blackTileMaterial.setDiffuseMap(makeTextureFromAsset("blacktile.png"));
    }

    public static Image makeTextureFromAsset(String path) {
        return new Image(new File(URI_BASE + path).toURI().toString());
    }

    public Box makeTile(int x, int y) {
        Box box = new Box(125, 20, 125);
        box.setTranslateX(125*(x-3));
        box.setTranslateY(50);
        box.setTranslateZ(125*(y-3));
        box.setMaterial((x+y)%2 == 0 ? whiteTileMaterial : blackTileMaterial);
        box.toBack();
        return box;
    }
}
