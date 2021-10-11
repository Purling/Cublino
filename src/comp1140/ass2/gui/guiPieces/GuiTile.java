package comp1140.ass2.gui.guiPieces;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class GuiTile extends Box {

    final static PhongMaterial whiteTileMaterial = GuiBoard.makePhongFromAsset("whitetile.png");
    final static PhongMaterial blackTileMaterial = GuiBoard.makePhongFromAsset("blacktile.png");
    final static PhongMaterial darkRedTileMaterial = GuiBoard.makePhongFromAsset("previousselectedtile.png");
    final static PhongMaterial redTileMaterial = GuiBoard.makePhongFromAsset("selectedtile.png");

    Position position;
    GuiBoard viewer;

    public GuiTile(Position p, GuiBoard viewer) {
        super(125, 20, 125);
        setTranslateX(125 * (p.x - 3));
        setTranslateY(50);
        setTranslateZ(125 * (p.y - 3));

        this.position = new Position(p.x, p.y);
        this.viewer = viewer;

        setSelected(SelectionType.UNSELECTED);
        toBack();

        setOnMouseEntered(e -> viewer.handleMouseOverTile(position));
        setOnMouseDragEntered(e -> viewer.handleMouseOverTile(position));
        setOnMouseExited(e -> viewer.handleMouseExitTile(position));
        setOnMouseDragExited(e -> viewer.handleMouseExitTile(position));
    }

    public void setSelected(SelectionType s) {
        switch (s) {
            case UNSELECTED -> setMaterial((position.x + position.y) % 2 == 0
                    ? whiteTileMaterial : blackTileMaterial);
            case PREVIOUS -> setMaterial(darkRedTileMaterial);
            case CURRENT -> setMaterial(redTileMaterial);
        }
    }

    public enum SelectionType {UNSELECTED, PREVIOUS, CURRENT}
}