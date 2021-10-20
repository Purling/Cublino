package comp1140.ass2.gui.guiPieces;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class GuiSkybox extends Group {

    // Top, Bottom, Back, Left, Front, Right
    private final ImageView[] faces = new ImageView[6];

    private final static double SIZE = 5000;

    public GuiSkybox(Image image) {
        super();

        for (int i = 0; i < 6; i++) {
            faces[i] = new ImageView();
            faces[i].setFitWidth(2*SIZE);
            faces[i].setFitHeight(2*SIZE);
            getChildren().add(faces[i]);
        }

        // Position all six faces
        faces[0].setTranslateX(-SIZE);
        faces[0].setTranslateY(-2*SIZE);

        faces[1].setTranslateX(-SIZE);

        faces[2].setTranslateX(-2*SIZE);
        faces[2].setTranslateY(-SIZE);

        faces[3].setTranslateX(-SIZE);
        faces[3].setTranslateY(-SIZE);
        faces[3].setTranslateZ(-SIZE);

        faces[4].setTranslateY(-SIZE);

        faces[5].setTranslateX(-SIZE);
        faces[5].setTranslateY(-SIZE);
        faces[5].setTranslateZ(SIZE);
        faces[5].setScaleX(-1);

        // Ensure top/bottom is flat
        faces[0].setRotationAxis(Rotate.X_AXIS);
        faces[0].setRotate(-90);

        faces[1].setRotationAxis(Rotate.X_AXIS);
        faces[1].setRotate(90);

        // Ensure back/front are vertical but perpendicular to the left/right faces
        faces[2].setRotationAxis(Rotate.Y_AXIS);
        faces[2].setRotate(90);

        faces[4].setRotationAxis(Rotate.Y_AXIS);
        faces[4].setRotate(-90);

        updateImage(image);
    }

    void updateImage(Image image) {
        for (ImageView view : faces) {
            view.setImage(image);
        }

        double s = image.getWidth()/4;

        faces[0].setViewport(new Rectangle2D(s, 0,   s, s));
        faces[1].setViewport(new Rectangle2D(s, s*2, s, s));
        faces[2].setViewport(new Rectangle2D(0, s,   s, s));
        faces[3].setViewport(new Rectangle2D(s, s,   s, s));
        faces[4].setViewport(new Rectangle2D(s*2, s, s, s));
        faces[5].setViewport(new Rectangle2D(s*3, s, s, s));
    }
}