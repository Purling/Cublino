package comp1140.ass2.gui.guipieces;

import javafx.scene.Group;
import javafx.scene.PointLight;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

/**
 * A class representing one of the tiles on the 7x7 Cublino board
 *
 * Author: Zane Gates
 */
public class GuiTile extends Group {

    final static PhongMaterial whiteTileMaterial = GuiBoard.makePhongFromAsset("tile/whiteTile.png");
    final static PhongMaterial blackTileMaterial = GuiBoard.makePhongFromAsset("tile/blackTile.png");
    final static PhongMaterial darkRedTileMaterial = GuiBoard.makePhongFromAsset("tile/previousSelectedTile.png");
    final static PhongMaterial redTileMaterial = GuiBoard.makePhongFromAsset("tile/selectedTile.png");

    Position position;

    Box box;

    PointLight selectionLight;

    /**
     * Constructs a tile on the GUI like a JavaFx box
     * @param position the position to construct it at, in board space
     * @param board the GUI to which this component belongs
     */
    public GuiTile(Position position, GuiBoard board) {
        // Create a 125x20x125 box at put it at the correct position
        box = new Box(125, 20, 125);
        box.setTranslateX(125 * (position.x - 3));
        box.setTranslateY(50);
        box.setTranslateZ(125 * (position.y - 3));
        this.position = new Position(position.x, position.y);

        // Tiles should start deselected, using a material according to a checkerboard pattern
        box.setMaterial((position.x + position.y) % 2 == 0 ? whiteTileMaterial : blackTileMaterial);
        toBack();

        // Mouse-overs are all handled by the viewer, so just connect it to the relevant functions
        box.setOnMouseEntered(e -> board.handleMouseOverTile(position));
        box.setOnMouseDragEntered(e -> board.handleMouseOverTile(position));
        box.setOnMouseExited(e -> board.handleMouseExitTile(position));
        box.setOnMouseDragExited(e -> board.handleMouseExitTile(position));

        selectionLight = new PointLight();
        selectionLight.setTranslateX(125 * (position.x - 3));
        selectionLight.setTranslateY(25);
        selectionLight.setTranslateZ(125 * (position.y - 3));

        getChildren().addAll(box, selectionLight);
        setSelected(SelectionType.UNSELECTED);
    }

    /**
     * Update the material of the box to the correct material, depending on its selection state
     * @param s the selection state
     */
    public void setSelected(SelectionType s) {
        switch (s) {
            case UNSELECTED -> selectionLight.setColor(Color.rgb(255, 255, 255));
            case PREVIOUS -> selectionLight.setColor(Color.rgb(225, 225, 150));
            case CURRENT -> selectionLight.setColor(Color.rgb(255, 255, 255));
        }
        selectionLight.setLightOn(s != SelectionType.UNSELECTED);
    }

    public enum SelectionType {UNSELECTED, PREVIOUS, CURRENT}
}