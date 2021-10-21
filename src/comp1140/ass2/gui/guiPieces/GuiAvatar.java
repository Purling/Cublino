package comp1140.ass2.gui.guiPieces;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;

public class GuiAvatar extends Group {

    GuiBoard viewer;
    double angle;

    public GuiAvatar(GuiBoard viewer, double angle, String name) {
        this.viewer = viewer;
        this.angle = angle;

        Group subGroup = new Group();
        Text text = new Text();
        text.setFont(new Font(40));
        text.setWrappingWidth(600);
        text.setLayoutX(-300);
        text.setText(name);
        text.setTextAlignment(TextAlignment.CENTER);
        subGroup.getChildren().add(text);
        subGroup.setTranslateZ(125 * 3.5);
        subGroup.setTranslateY(-100);

        getTransforms().add(new Rotate(angle, new Point3D(0, 1, 0)));
        getChildren().add(subGroup);

        new AnimationTimer() {
            @Override
            public void handle(long l) {
                double difference = viewer.getCameraYaw() - angle;

                // Find the smallest angle difference
                while (difference <= 0) difference += 360;
                while (difference >= 360) difference -= 360;
                if (difference > 180) difference = 360 - difference;

                double opacity = (2/(Math.pow(difference / 90, 2)+1))-1;
                if (opacity < 0) opacity = 0;
                setOpacity(opacity);
            }
        }.start();
    }
}
