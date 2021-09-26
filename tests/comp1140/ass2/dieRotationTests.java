package comp1140.ass2;

import comp1140.ass2.State.Die;
import comp1140.ass2.gui.Viewer;
import javafx.geometry.Point3D;
import javafx.scene.transform.Transform;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 1000, unit = MILLISECONDS)
class dieRotationTests {

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
        for (char o = 'A'; o <= 'X'; o++) {
            for (char x = 'a'; x <= 'g'; x++) {
                for (char y = '1'; y < '7'; y++) {
                    String placementString = "" + o + x + y;
                    Die d = new Die(placementString, null, null);
                    t = new Viewer.DieModel(d).necessaryRotations();

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

    public Point3D startingPositionOfNumber(int n) {
        switch(n) {
            case 1: return right;
            case 2: return down;
            case 3: return front;
            case 4: return back;
            case 5: return up;
            default: return left;
        }
    }

    // Allows for the comparison of points despite floating-point errors
    public boolean nearlyEquals(Point3D a, Point3D b) {
        return a.subtract(b).magnitude() < 0.00001;
    }
}