package comp1140.ass2.gui.guiPieces;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

/**
 * A class representing one of the tiles on the 7x7 Cublino board
 *
 * Author: Zane Gates
 */
public class GuiTile extends Box {

    final static PhongMaterial whiteTileMaterial = GuiBoard.makePhongFromAsset("tile/whiteTile.png");
    final static PhongMaterial blackTileMaterial = GuiBoard.makePhongFromAsset("tile/blackTile.png");
    final static PhongMaterial darkRedTileMaterial = GuiBoard.makePhongFromAsset("tile/previousSelectedTile.png");
    final static PhongMaterial redTileMaterial = GuiBoard.makePhongFromAsset("tile/selectedTile.png");

    Position position;

    /**
     * Constructs a tile on the GUI like a JavaFx box
     * @param position the position to construct it at, in board space
     * @param board the GUI to which this component belongs
     */
    public GuiTile(Position position, GuiBoard board) {
        // Create a 125x20x125 box at put it at the correct position
        super(125, 20, 125);
        setTranslateX(125 * (position.x - 3));
        setTranslateY(50);
        setTranslateZ(125 * (position.y - 3));
        this.position = new Position(position.x, position.y);

        // Tiles should start deselected
        setSelected(SelectionType.UNSELECTED);
        toBack();

        // Mouse-overs are all handled by the viewer, so just connect it to the relevant functions
        setOnMouseEntered(e -> board.handleMouseOverTile(position));
        setOnMouseDragEntered(e -> board.handleMouseOverTile(position));
        setOnMouseExited(e -> board.handleMouseExitTile(position));
        setOnMouseDragExited(e -> board.handleMouseExitTile(position));
    }

    /**
     * Update the material of the box to the correct material, depending on its selection state
     * @param s the selection state
     */
    public void setSelected(SelectionType s) {
        switch (s) {
            // Draw a checkerboard pattern by the parity of the sum of the tile's coordinates
            case UNSELECTED -> setMaterial((position.x + position.y) % 2 == 0
                    ? whiteTileMaterial : blackTileMaterial);
            case PREVIOUS -> setMaterial(darkRedTileMaterial);
            case CURRENT -> setMaterial(redTileMaterial);
        }
    }

    public enum SelectionType {UNSELECTED, PREVIOUS, CURRENT}
}