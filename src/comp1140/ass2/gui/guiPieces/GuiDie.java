package comp1140.ass2.gui.guiPieces;

import comp1140.ass2.Controller.Controller;
import comp1140.ass2.State.Die;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point3D;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

import java.util.ArrayList;
import java.util.List;

/**
 * A textured 3D mesh representing a die in any variant of Cublino
 *
 * @author Zane Gates
 */
public class GuiDie extends MeshView {

    /**
     * The set of the various textures available for players to assign to their dice
     */
    public enum Skin {
        NONE, PLAIN_WHITE, PLAIN_BLACK, GILDED, OAK, DEEP_SEA, STARRY, CLOUDY, MARSH_MELLOW, MINT, NEON, PUMPKIN
    }

    /**
     * Determines the path to the file containing the image data for a particular die skin
     * @param skin the skin to get the filename for
     * @return the path to the image file, relative to the assets/die folder
     */
    public static String filenameOfSkin(Skin skin) {
        return switch(skin) {
            case NONE -> "";
            case PLAIN_WHITE -> "whiteDie.png";
            case PLAIN_BLACK -> "blackDie.png";
            case GILDED ->  "gildedDie.png";
            case OAK -> "oakDie.png";
            case DEEP_SEA -> "deepSeaDie.png";
            case STARRY -> "starryDie.png";
            case CLOUDY -> "cloudyDie.png";
            case MARSH_MELLOW -> "marshmellowDie.png";
            case MINT -> "mintDie.png";
            case NEON -> "neonDie.png";
            case PUMPKIN -> "pumpkinDie.png";
        };
    }

    Die die;
    GuiBoard viewer;

    AnimationTarget currentAnimation = null;

    List<Position> animationQueue = new ArrayList<>();

    boolean isAnimating = false;

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

        // Construct the static mesh, if it has not already been constructed by another GuiDie
        if (!meshConstructed) createDieMesh();

        this.die = die;
        this.viewer = viewer;

        // Apply the appropriate die texture to the mesh
        if (controllers != null) {
            Skin appropriateSkin = controllers[die.isWhite() ? 0 : 1].getDiceSkin();
            if (appropriateSkin != Skin.NONE)
                setMaterial(GuiBoard.makePhongFromAsset("die/" + filenameOfSkin(appropriateSkin)));
        }


        while (getTransforms().size() < 2) getTransforms().add(zeroTransform());

        // Rotate the mesh to show the correct numbers
        // NB: Please see below generateAnimationTo() for a technical explanation of how GuiDice store animations
        getTransforms().set(1, necessaryRotations());

        // Position and scale the mesh
        setScaleX(DIE_SCALE);
        setScaleY(DIE_SCALE);
        setScaleZ(DIE_SCALE);
        setTranslateX(125 * (die.getX()-3));
        setTranslateZ(125 * (die.getY()-3));
        setTranslationFromDie();

        // GuiDies should ignore the mouse so you can select tiles below the mouse
        setMouseTransparent(true);

        // Animate the die to show moves being made to it in real time
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                // Check if no animation is currently playing
                if (currentAnimation == null || currentAnimation.hasFinished(l)) {
                    if (animationQueue.isEmpty()) {
                        // If no more animations need to be played, make the die comport to its expected transform
                        currentAnimation = null;
                        getTransforms().set(1, necessaryRotations());
                        getTransforms().set(0, zeroTransform());

                        // If it had previously been animating, play the sound effect for a die being dropped
                        if (isAnimating) {
                            if (!viewer.isDieSelected(die)) viewer.playMoveSfx();
                            isAnimating = false;
                        }
                    }
                    else {
                        // Otherwise, start playing the next part of the animation
                        currentAnimation = generateAnimationTo(animationQueue.remove(0));
                        if (currentAnimation != null) {
                            isAnimating = true;
                            viewer.playStepSfx();
                        }
                    }
                }

                // If the animation has not been started, start it
                // since .start() has no effect if it has already started
                if (currentAnimation != null) currentAnimation.start(l);

                // If a die has been destroyed (in Contra) and is not playing any animations, fly off the board
                if (die.isDeleted() && (currentAnimation == null || currentAnimation.hasFinished(l))) {
                    setTranslateY(getTranslateY()-10);
                    return;
                }

                // A die can be put down iff it is neither selected nor currently animating
                boolean canBePutDown = !viewer.isDieSelected(die) && (currentAnimation == null);
                // Smoothly move the die along the y-axis to either on top of the board, or above it
                setTranslateY(getTranslateY() + ((canBePutDown ? 0 : -50) - getTranslateY())*0.2);

                // Update the die to be in the position it should at the current point in its animation
                // in terms of both position in both the x and z axes, and rotation
                if (!(currentAnimation == null) && !currentAnimation.hasFinished(l)) {
                    setTranslateX(currentAnimation.xAtTime(l));
                    setTranslateZ(currentAnimation.zAtTime(l));
                    getTransforms().set(0, currentAnimation.rotateAtTime(l));
                }
            }
        }.start();
    }

    /**
     * Computes a rotation transform along the vertical axis, that is, spinning in the horizontal plane
     * @param degrees the side of the rotation, in degrees
     * @return the required rotation
     */
    private Rotate spinTransform(double degrees) {
        return new Rotate(degrees, new Point3D(0, 1, 0));
    }

    /**
     * Computes a rotation transform along the z-axis, that is, spinning in plane parallel to the viewer
     * @param degrees the side of the rotation, in degrees
     * @return the required rotation
     */
    private Rotate pitchTransform(double degrees) {
        return new Rotate(degrees, new Point3D(0, 0, 1));
    }

    /**
     * Computes a rotation transform along the x-axis, that is, spinning away from or towards the viewer
     * @param degrees the side of the rotation, in degrees
     * @return the required rotation
     */
    private Rotate rollTransform(double degrees) {
        return new Rotate(degrees, new Point3D(1, 0, 0));
    }

    /**
     * Computes an rotation that does nothing -- necessary for when
     * no rotation is necessary but a function expects a transform
     * @return a zero rotation
     */
    private Rotate zeroTransform() {
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

    /**
     * Informs the dice that its position may have changed, and animate it into its new position
     */
    void setTranslationFromDie() {
        animationQueue.add(new Position(die.getX(), die.getY()));
    }

    /**
     * Generates an animation from the die's current position to the given location
     * @param pos the position, in board space
     * @return an animation, consisting of a horizontal movement, and
     * possibly a rotation for a tip move, or null if an animation is not necessary
     */
    AnimationTarget generateAnimationTo(Position pos) {
        double rotateAngle = 0;
        Point3D rotateAxis = null;

        // Convert the given board space into world space
        double tx = 125 * (pos.x - 3);
        double tz = 125 * (pos.y - 3);


        // If it is only moving one tile (so the distance will be approximately 150),
        // then a rotation will be necessary. Calculate this rotation depending on the die's direction
        double distance = Math.sqrt(Math.pow(tx-getTranslateX(), 2) + Math.pow(tz-getTranslateZ(), 2));
        if (distance > 100 && distance < 150) {
            // Check to see which axis the die is moving along
            if (tz - getTranslateZ() > 10) { // Forward (player 1 only)
                rotateAngle = -90;
                rotateAxis = new Point3D(1, 0, 0);
            } else if (tz - getTranslateZ() < -10) { // Backward (player 2 only)
                rotateAngle = 90;
                rotateAxis = new Point3D(1, 0, 0);
            } else if (tx - getTranslateX() < -10) { // To the left
                rotateAngle = -90;
                rotateAxis = new Point3D(0, 0, 1);
            } else if (tx - getTranslateX() > 10) { // To the right
                rotateAngle = 90;
                rotateAxis = new Point3D(0, 0, 1);
            }
        }

        // If the die is not moving in either direction, return null (corresponding to 'no animation is necessary')
        if (!(Math.abs(tx-getTranslateX())>10 || Math.abs(tz-getTranslateZ())>10))
            return null;
        // Otherwise, if the die is only moving, not rotating, return an appropriate animation with zero rotation
        else if (rotateAxis == null)
            return new AnimationTarget(this, tx, tz, 0, new Point3D(0, 1, 0));
        // Otherwise, the die is both moving and rotating, so return that
        else
            return new AnimationTarget(this, tx, tz, rotateAngle, rotateAxis);
    }
    /*      TECHNICAL NOTE REGARDING HOW ROTATIONS ARE STORED IN THIS CLASS
            JavaFX supports one animation applied directly through the model through .setRotate() and .setRotationAxis()
            However, we require three animations, two when the die is stationary to force the correct numbers to be visible on every face,
            and a third to change as the die is moving. We will therefore utilise the .getTransforms() list, with the first thing
            in this list being the partial variable rotation, and the second being a concatenation of the two stationary animations.
            This is because the two stationary animations will be always recalculated together (and a concatenation of
            these rotations exists by Euler's theorem.) Also note that the animations will be stored in the reverse order --
            that is, the partial animation, which should be applied after the static one, will be stored first in the list.
            This is because JavaFx calculates transformation concatenations by multiplying matrices, and transforming a point
            by two matrices (like ABp for a point p) corresponds to A(Bp) rather than B(Ap).
     */

    // A class representing an animation the die will have to execute, consisting of possibly a translation and a rotation
    private static class AnimationTarget {
        // The timestamp when this animation began
        long startTime;

        // The initial and final positions of this step of the animation, measured in world space
        double startX;
        double startZ;
        double targetX;
        double targetZ;

        // The rotation stored in this animation
        double targetAngle;
        Point3D axis;

        // The duration of the animation, measured in nanoseconds
        final double duration = 0.5e9;

        /**
         * Generate an animation moving a particular die to a position with a particular rotation
         * @param model the model to move
         * @param targetX the final x position, in world space
         * @param targetZ the final z position, in world space
         * @param targetAngle the angle to rotate by (either positive or negative)
         * @param axis the axis along which to rotate
         */
        public AnimationTarget(GuiDie model, double targetX, double targetZ, double targetAngle, Point3D axis) {
            this.targetX = targetX;
            this.targetZ = targetZ;
            this.startX = model.getTranslateX();
            this.startZ = model.getTranslateZ();
            this.targetAngle = targetAngle;
            this.axis = axis;
        }

        /**
         * Dequeue and start the current animation if it has not yet started
         * @param t the current timestamp
         */
        public void start(long t) {
            if (startTime == 0) startTime = t;
        }

        /**
         * Calculates the rotation at a point along this animation
         * @param t the current timestamp
         * @return the current rotation
         */
        public Rotate rotateAtTime(long t) {
            return new Rotate(targetAngle*normTime(t), axis);
        }

        /**
         * Calculates the x position at a point along this animation
         * @param t the current timestamp
         * @return the current x position, in world space
         */
        public double xAtTime(long t) {
            return startX + (targetX-startX)*normTime(t);
        }

        /**
         * Calculates the z position at a point along this animation
         * @param t the current timestamp
         * @return the current z position, in world space
         */
        public double zAtTime(long t) {
            return startZ + (targetZ-startZ)*normTime(t);
        }

        /**
         * Determines if the animation has yet finished
         * @param t the current timestamp
         * @return whether or not this animation has finished
         */
        public boolean hasFinished(long t) {
            return (startTime > 0) && normTime(t) >= 1;
        }

        /**
         * The normed time along this animation, which is zero at the starting time, one after
         * the duration of the animation, and some linearly interpolated fraction in-between
         * @param t the current timestamp
         * @return the normed time
         */
        private double normTime(long t) {
            return (t-startTime)/duration;
        }
    }

    /* The standard 3D box only allows one texture that will be applied to every side
       Obviously, this is not particular useful for making a die with different numbers on each side
       Accordingly, we will use a custom mesh shared between every die, which must be calculated live
     */

    public final static TriangleMesh dieMesh = new TriangleMesh();
    final static double DIE_SCALE = 40;
    static boolean meshConstructed = false;

    /*      y   Vertices          UV Map
          G-^-----H                 I---J
       z  |\|     |\                | 5 |
        ^ | C-------D           K---L---M---N---O
         \| |     | |           | 6 | 3 | 1 | 4 |
          E-|-----F |           P---Q---R---S---T
           \|      \|               | 2 |
            A-------B-->x           U---V                   */

    /**
     * Constructs the dice mesh
     */
    public static void createDieMesh() {
        // Sets the vertices of the mesh, in accordance with the above diagram
        // Each triple of coordinates represent a point in 3D space at a vertex of the mesh
        dieMesh.getPoints().addAll(
                -1,-1,-1, // A 0
                1,-1,-1,         // B 1
                -1, 1,-1,        // C 2
                1, 1,-1,         // D 3
                -1, -1, 1,       // E 4
                1,-1, 1,         // F 5
                -1, 1, 1,        // G 6
                1, 1, 1);        // H 7
        // Sets the UV coordinates of the mesh, in accordance with the above diagram
        // Each pair of coordinates represent a point in 2D space on the UV image
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
        /* Constructs the triangular faces for the dice
         * Each sextuplet of coordinates is of the form (v1,t1,v2,t2,v3,t3)
         * where (v1,v2,v3) is the indices of the vertices in the vertex array
         * and (t1,t2,t3) are the indices of the corresponding vertices in the UV array
         JavaFX only supports triangular faces, so we combine two triangles to make each square face */
        // On each line, the comments list the vertices of that tri, and the number on a die texture it displays
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

        // Flag that the mesh has been constructed, so this function needn't be called again
        meshConstructed = true;
    }
}