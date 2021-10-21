package comp1140.ass2.gui.guipieces;

import comp1140.ass2.Controller.Controller;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;

/**
 * Displays an avatar for a player, indicating where their baseline
 * is, what their name is, and other relevant information.
 *
 * @author Zane Gates
 */
public class GuiAvatar extends Group {

    GuiBoard viewer;
    double angle;

    /**
     * Generate an avatar for a player
     * @param viewer the viewer on which this avatar lies
     * @param angle the angle around the board anticlockwise from row 7 to place this avatar at
     * @param controller the controller of this player, containing a name and type
     */
    public GuiAvatar(GuiBoard viewer, double angle, Controller controller) {
        this.viewer = viewer;
        this.angle = angle;
        Group subGroup = new Group();

        // Display a text object containing this player's in-game name
        Text text = new Text();
        text.setFont(new Font(40));
        text.setWrappingWidth(600);
        text.setLayoutX(-300);
        text.setText(controller.getName());
        text.setTextAlignment(TextAlignment.CENTER);
        text.setMouseTransparent(true);
        subGroup.getChildren().add(text);

        // Also create an image containing an icon showing if a given player is a human or AI
        ImageView view = new ImageView();
        view.setScaleX(0.25);
        view.setScaleY(0.25);
        view.setLayoutX(-260);
        view.setLayoutY(-400);
        view.setImage(GuiBoard.imageFromAsset("avatar/" + imageSourceFromControllerType(controller.getType()) + ".png"));
        view.setMouseTransparent(true);
        subGroup.getChildren().add(view);


        // Move this tag to the edge of the board, and rotate it to the appropriate edge
        subGroup.setTranslateZ(125 * 3.5);
        subGroup.setTranslateY(-100);
        getTransforms().add(new Rotate(angle, new Point3D(0, 1, 0)));
        getChildren().add(subGroup);

        // Animate the tag so it fades in and out so as to not obstruct the user's view
        // When the user is facing (i.e. the camera is opposite) this avatar, it should be full opacity
        // which will gradually become transparent as the angle between the two approaches a right angle
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                double difference = viewer.getCameraYaw() - angle;

                // Find the smallest angle difference
                while (difference <= 0) difference += 360;
                while (difference >= 360) difference -= 360;
                if (difference > 180) difference = 360 - difference;

                // Use a smooth curve to fade the opacity
                double opacity = (2/(Math.pow(difference / 90, 2)+1))-1;
                if (opacity < 0) opacity = 0;

                text.setOpacity(opacity);
                view.setOpacity(opacity);
            }
        }.start();
    }

    /**
     * Finds the relevant icon image for any given controller type
     * @param type the controller type
     * @return the correct image relative to avatar/*.png
     */
    private String imageSourceFromControllerType(Controller.ControllerType type) {
        return switch(type) {
            case HUMAN -> "humanLogo";
            case EASY_AI -> "robot1Logo";
            case DIFFICULT_AI -> "robot2Logo";
        };
    }
}
