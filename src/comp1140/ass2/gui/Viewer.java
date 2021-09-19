package comp1140.ass2.gui;

import comp1140.ass2.State.Die;
import comp1140.ass2.State.Direction;
import javafx.animation.RotateTransition;
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
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
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

        // Generic JavaFX window setup
        boardSubscene = new SubScene(subRoot, VIEWER_WIDTH, VIEWER_HEIGHT, true, SceneAntialiasing.BALANCED);

        // Stores the particular state the board should show
        Boards boards = new Boards(placement);

        // Iterate over every tile in the board
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                // Construct the checkerboard tile
                boardGroup.getChildren().add(makeTile(x, y));

                // If the game state contains a die at the current position, construct it as well
                Die die = boards.getAt(x, y);
                if (die == null) continue;
                Point3D position = new Point3D(125 * (x-3), 0, 125 * (y-3));
                boardGroup.getChildren().add(makeDie(position, die));
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

    final static TriangleMesh dieMesh = new TriangleMesh();
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

    public MeshView makeDie(Point3D position, Die die) {
        // Construct the MeshView from the original mesh
        MeshView dieMeshView = new MeshView(dieMesh);

        // Apply the die texture to the mesh
        dieMeshView.setMaterial(die.getColor() ? whiteMaterial : blackMaterial);

        // Reposition and scale the mesh
        dieMeshView.setTranslateX(position.getX());
        dieMeshView.setTranslateY(position.getY());
        dieMeshView.setTranslateZ(position.getZ());
        dieMeshView.setScaleX(dieScale);
        dieMeshView.setScaleY(dieScale);
        dieMeshView.setScaleZ(dieScale);

        // Rotate the mesh to show the correct numbers
        rotateToLookLike(dieMeshView, die);

        return dieMeshView;
    }

    public void rotateToLookLike(MeshView meshView, Die die) {
        Rotate rollLeft = new Rotate(90, new Point3D(0, 0, 1));
        Rotate rollRight = new Rotate(-90, new Point3D(0, 0, 1));
        Rotate rollUp = new Rotate(90, new Point3D(1, 0, 0));
        Rotate rollDown = new Rotate(-90, new Point3D(1, 0, 0));
        Rotate roll180 = new Rotate(180, new Point3D(0, 0, 1));
        Rotate spinLeft = new Rotate(90, new Point3D(0, 1, 0));
        Rotate spinRight = new Rotate(-90, new Point3D(0, 1, 0));
        Rotate spin180 = new Rotate(180, new Point3D(0, 1, 0));
        switch (die.getTop()) {
            case 1: switch(die.getSide(Direction.DOWN)) {
                case 2: meshView.getTransforms().add(spinRight); break;
                case 3: meshView.getTransforms().add(spin180); break;
                case 5: meshView.getTransforms().add(spinLeft); break;
            } meshView.getTransforms().add(rollLeft); return;
            case 2: switch(die.getSide(Direction.DOWN)) {
                case 1: meshView.getTransforms().add(spinLeft); break;
                case 3: meshView.getTransforms().add(spin180); break;
                case 6: meshView.getTransforms().add(spinRight); break;
            } meshView.getTransforms().add(roll180); return;
            case 3: switch(die.getSide(Direction.DOWN)) {
                case 1: meshView.getTransforms().add(spinRight); break;
                case 2: meshView.getTransforms().add(spin180); break;
                case 6: meshView.getTransforms().add(spinLeft); break;
            } meshView.getTransforms().add(rollUp); return;
            case 4: switch(die.getSide(Direction.DOWN)) {
                case 1: meshView.getTransforms().add(spinRight); break;
                case 5: meshView.getTransforms().add(spin180); break;
                case 6: meshView.getTransforms().add(spinLeft); break;
            } meshView.getTransforms().add(rollDown); return;
            case 5: switch(die.getSide(Direction.DOWN)) {
                case 1: meshView.getTransforms().add(spinRight); break;
                case 3: meshView.getTransforms().add(spin180); break;
                case 6: meshView.getTransforms().add(spinLeft); break;
            } return;
            case 6: switch(die.getSide(Direction.DOWN)) {
                case 2: meshView.getTransforms().add(spinLeft); break;
                case 3: meshView.getTransforms().add(spin180); break;
                case 5: meshView.getTransforms().add(spinRight); break;
            } meshView.getTransforms().add(rollRight); return;
        }
    }

    final static PhongMaterial whiteMaterial = new PhongMaterial();
    final static PhongMaterial blackMaterial = new PhongMaterial();
    final static PhongMaterial whiteTileMaterial = new PhongMaterial();
    final static PhongMaterial blackTileMaterial = new PhongMaterial();

    public static void createMaterials() {
        Image whiteTexture = new Image(new File(URI_BASE + "whitedie.png").toURI().toString());
        Image blackTexture = new Image(new File(URI_BASE + "blackdie.png").toURI().toString());
        whiteMaterial.setDiffuseMap(whiteTexture);
        blackMaterial.setDiffuseMap(blackTexture);

        Image whiteTileTexture = new Image(new File(URI_BASE + "whitetile.png").toURI().toString());
        Image blackTileTexture = new Image(new File(URI_BASE + "blacktile.png").toURI().toString());
        whiteTileMaterial.setDiffuseMap(whiteTileTexture);
        blackTileMaterial.setDiffuseMap(blackTileTexture);
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
