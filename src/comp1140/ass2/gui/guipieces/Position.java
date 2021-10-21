package comp1140.ass2.gui.guipieces;

/**
 * A simple helper class, representing a position in Board space.
 * Differs from the Position class used in State classes by having clamp checks to disallow off-board moves
 * but with less unnecessary functionality elsewhere.
 *
 * @author Zane Gates
 */
public class Position {
    public int x;
    public int y;

    /**
     * Constructs a position by clamping the x and y variables to the range (0,0) -- (6,6) inclusive
     * @param x the un-clamped x position
     * @param y the un-clamped y position
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        if (x < 0) this.x = 0;
        if (x >= 7) this.x = 6;
        if (y < 0) this.y = 0;
        if (y >= 7) this.y = 6;
    }

    @Override
    public String toString() {
        return "" + x + "" + y;
    }
}