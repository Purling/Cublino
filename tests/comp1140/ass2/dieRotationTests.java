package comp1140.ass2;

import comp1140.ass2.controller.Controller;
import comp1140.ass2.state.Die;
import comp1140.ass2.gui.guipieces.GuiDie;
import javafx.geometry.Point3D;
import javafx.scene.transform.Transform;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 1000, unit = MILLISECONDS)
class dieRotationTests {

    // Defines points at the six cardinal directions at the centre of each face of the unit cube
    static final Point3D up = new Point3D(0, 1, 0);
    static final Point3D down = new Point3D(0, -1, 0);
    static final Point3D left = new Point3D(1, 0, 0);
    static final Point3D right = new Point3D(-1, 0, 0);
    static final Point3D front = new Point3D(0, 0, -1);
    static final Point3D back = new Point3D(0, 0, 1);

    Transform t;
    String currentlyTesting;

    @Test
    public void testAll() {
        // Loop over every possible placement of a (white) die
        for (char o = 'A'; o <= 'X'; o++) {
            for (char x = 'a'; x <= 'g'; x++) {
                for (char y = '1'; y < '7'; y++) {
                    // Construct the die and its model
                    String placementString = "" + o + x + y;
                    Die d = new Die(placementString);
                    t = new GuiDie(d, null, new Controller[]{new Controller(Controller.ControllerType.HUMAN, "", GuiDie.Skin.NONE)}).necessaryRotations();

                    // Make sure every face on the model is positioned where the die class suggests it should be
                    makeRotationAssertion(d.getTop(), up, "top");
                    makeRotationAssertion(d.getDown(), down, "bottom");
                    makeRotationAssertion(d.getLeft(), left, "left");
                    makeRotationAssertion(d.getRight(), right, "right");
                    makeRotationAssertion(d.getFront(), front, "front");
                    makeRotationAssertion(d.getBack(), back, "back");
                }
            }
        }
    }

    public void makeRotationAssertion(int face, Point3D target, String faceString) {
        assertTrue(nearlyEquals(t.transform(startingPositionOfNumber(face)), target),
                "Dice with placement " + currentlyTesting + " has incorrect " + faceString + "face");
    }

    // The initial position of the die model before any rotation (e.g. the number 3 is on the front face)
    public Point3D startingPositionOfNumber(int n) {
        return switch (n) {
            case 1 -> right;
            case 2 -> down;
            case 3 -> front;
            case 4 -> back;
            case 5 -> up;
            default -> left;
        };
    }

    // Allows for the comparison of points despite floating-point errors
    public boolean nearlyEquals(Point3D a, Point3D b) {
        return a.subtract(b).magnitude() < 0.00001;
    }
}