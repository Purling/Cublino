package comp1140.ass2.gui.guiPieces;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class GuiTile extends Box {

    final static PhongMaterial whiteTileMaterial = GuiBoard.makePhongFromAsset("tile/whiteTile.png");
    final static PhongMaterial blackTileMaterial = GuiBoard.makePhongFromAsset("tile/blackTile.png");
    final static PhongMaterial darkRedTileMaterial = GuiBoard.makePhongFromAsset("tile/previousSelectedTile.png");
    final static PhongMaterial redTileMaterial = GuiBoard.makePhongFromAsset("tile/selectedTile.png");

    Position position;

    public GuiTile(Position position, GuiBoard board) {
        super(125, 20, 125);
        setTranslateX(125 * (position.x - 3));
        setTranslateY(50);
        setTranslateZ(125 * (position.y - 3));

        this.position = new Position(position.x, position.y);

        setSelected(SelectionType.UNSELECTED);
        toBack();

        setOnMouseEntered(e -> board.handleMouseOverTile(position));
        setOnMouseDragEntered(e -> board.handleMouseOverTile(position));
        setOnMouseExited(e -> board.handleMouseExitTile(position));
        setOnMouseDragExited(e -> board.handleMouseExitTile(position));
    }

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