package comp1140.ass2.gui.guiPieces;

import comp1140.ass2.Controller.Controller;
import comp1140.ass2.State.Die;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.geometry.Point3D;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class GuiDie extends MeshView {

    public enum Skin {
        NONE, PLAIN_WHITE, PLAIN_BLACK, GILDED
    }

    public static String filenameOfSkin(Skin skin) {
        return switch(skin) {
            case NONE -> "";
            case PLAIN_WHITE -> "whitedie.png";
            case PLAIN_BLACK -> "blackdie.png";
            case GILDED ->  "gildedDie.png";
        };
    }

    Die die;
    GuiBoard viewer;

    double animationVelocityX = 0;
    double animationVelocityZ = 0;

    double animationDegrees = 0;
    double animationDegreesTarget = 0;
    Point3D animationAxis = new Point3D(0, 0, 0);

    /**
     * Constructs and transforms a die mesh to provide an
     * accurate visual model of any die.
     *
     * @param die the die to show
     * @param viewer the parent GuiBoard
     * @param controllers the controllers associated with each player in the game
     */
    public GuiDie(Die die, GuiBoard viewer, Controller[] controllers) {
        super(dieMesh);

        if (!meshConstructed) createDieMesh();

        this.die = die;
        this.viewer = viewer;

        // Apply the appropriate die texture to the mesh
        if (controllers != null) {
            Skin appropriateSkin = controllers[die.isWhite() ? 0 : 1].getDiceSkin();
            if (appropriateSkin != Skin.NONE)
                setMaterial(GuiBoard.makePhongFromAsset(filenameOfSkin(appropriateSkin)));
        }


        while (getTransforms().size() < 2) getTransforms().add(zeroTransform());

        // Rotate the mesh to show the correct numbers
        getTransforms().set(1, necessaryRotations());

        // Position and scale the mesh
        setScaleX(dieScale);
        setScaleY(dieScale);
        setScaleZ(dieScale);
        setTranslateX(125 * (die.getX()-3));
        setTranslateZ(125 * (die.getY()-3));
        setTranslationFromDie();

        setMouseTransparent(true);

        new AnimationTimer() {
            @Override
            public void handle(long l) {
                boolean canBePutDown = !viewer.isDieSelected(die) && animationVelocityX == 0 && animationVelocityZ == 0;
                setTranslateY(getTranslateY() + ((canBePutDown ? 0 : -50) - getTranslateY())*0.2);
                double targetX = 125 * (die.getX()-3);
                double targetZ = 125 * (die.getY()-3);
                setTranslateX(getTranslateX()+animationVelocityX/30);
                setTranslateZ(getTranslateZ()+animationVelocityZ/30);
                if (Math.abs(getTranslateX()-targetX) < Math.abs(animationVelocityX/10)) {
                    animationVelocityX = 0;
                    setTranslateX(targetX);
                }
                if (Math.abs(getTranslateZ()-targetZ) < Math.abs(animationVelocityZ/10)) {
                    animationVelocityZ = 0;
                    setTranslateZ(targetZ);
                }
                animationDegrees += (animationDegreesTarget > animationDegrees ? 3 : -3);
                if (Math.abs(animationDegrees-animationDegreesTarget) < 4) {
                    animationDegrees = animationDegreesTarget;
                    getTransforms().set(1, necessaryRotations());
                    getTransforms().set(0, zeroTransform());
                } else {
                    getTransforms().set(0, new Rotate(animationDegrees, animationAxis));
                }
            }
        }.start();
    }

    /**
     * Computes a rotation transform along the various axes
     * @param degrees the side of the rotation, in degrees
     * @return the required rotation
     */
    Rotate spinTransform(double degrees) {
        return new Rotate(degrees, new Point3D(0, 1, 0));
    }

    Rotate pitchTransform(double degrees) {
        return new Rotate(degrees, new Point3D(0, 0, 1));
    }

    Rotate rollTransform(double degrees) {
        return new Rotate(degrees, new Point3D(1, 0, 0));
    }

    Rotate zeroTransform() {
        return new Rotate(0, new Point3D(0, 1, 0));
    }

    /**
     * Computes the necessary rotation that,
     * when applied to the default die, orients
     * it in alignment with the source die
     *
     * @return the required rotation
     */
    public Transform necessaryRotations() {
        // Convert from the number on the back face of the die back to the relative number. For example,
        // if the top face shows a 5 and the back face shows a 4, this is the third possible back face
        // since 2 is impossible as it must be on the bottom. Hence the relativeBackNumber would be 3.
        int relativeBackNumber = die.getBack();
        // Decrement the relative index for each number that it cannot be - namely the top and bottom faces
        if (die.getBack() > die.getTop()) relativeBackNumber--;
        if (die.getBack() > 7 - die.getTop()) relativeBackNumber--;

        // When the top number is 2 or 6, the numbers are reversed, due to chirality
        if (die.getTop() == 2 || die.getTop() == 6) relativeBackNumber = 5 - relativeBackNumber;

        // When the top of the die is an even number, the front and back faces will be swapped
        if (die.getTop() % 2 == 0) {
            if (relativeBackNumber == 2) relativeBackNumber = 3;
            else if (relativeBackNumber == 3) relativeBackNumber = 2;
        }

        // Apply the rotations one after another to correctly position both faces
        // If the top and back faces are positioned correctly, the other four must be as well
        return backRotation(relativeBackNumber).createConcatenation(topRotation(die.getTop()));
    }

    /**
     * Computes the rotation to show a particular
     * relative number on the back face of the mesh
     * @return the required rotation
     */
    Rotate backRotation(int relativeBackFace) {
        return switch (relativeBackFace) {
            case 1 -> spinTransform(90);
            case 2 -> spinTransform(180);
            case 4 -> spinTransform(-90);
            default -> new Rotate(); // 4 (which has a relative index of 3) is the default back face
        };
    }

    /**
     * Computes the rotation to show a particular
     * absolute number on the top face of the mesh
     * @return the required rotation
     */
    Rotate topRotation(int topFace) {
        return switch (topFace) {
            case 1 -> pitchTransform(-90);
            case 2 -> pitchTransform(180);
            case 3 -> rollTransform(90);
            case 4 -> rollTransform(-90);
            case 6 -> pitchTransform(90);
            default -> new Rotate(); // 5 is the default top face
        };
    }

    void setTranslationFromDie() {
        animationVelocityX = (125 * (die.getX()-3))-getTranslateX();
        animationVelocityZ = (125 * (die.getY()-3))-getTranslateZ();

        animationDegrees = 0;
        if (animationVelocityZ > 0) {
            animationDegreesTarget = -90;
            animationAxis = new Point3D(1, 0, 0);
            return;
        } else if (animationVelocityZ < 0) {
            //impossible
        } else if (animationVelocityX < 0) {
            animationDegreesTarget = -90;
            animationAxis = new Point3D(0, 0, 1);
            return;
        } else if (animationVelocityX > 0) {
            animationDegreesTarget = 90;
            animationAxis = new Point3D(0, 0, 1);
            return;
        } else {
            animationDegreesTarget = 0;
            return;
        }
    }

    public final static TriangleMesh dieMesh = new TriangleMesh();
    final static double dieScale = 40;
    static boolean meshConstructed = false;

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
        meshConstructed = true;
    }
}