package comp1140.ass2.State;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;

import static comp1140.ass2.State.Direction.*;

/**
 * @author Whole group
 */
public class Die implements Serializable {

    /**
     * Colour of Die. True if white, false if black
     */
    private final boolean isWhite;

    /**
     * x coordinate of Die
     */
    private int x;

    /**
     * y coordinate of Die
     */
    private int y;

    /**
     * Value displayed at the top of the die
     */
    private int top;

    /**
     * Value displayed at the bottom of the die
     */
    private int down;

    /**
     * Value displayed at the front of the die i.e., facing away from the white player
     */
    private int front;

    /**
     * Value displayed at the back of the die i.e., facing the white player
     */
    private int back;

    /**
     * Value displayed on the left of the die (relative to the white player)
     */
    private int left;

    /**
     * Value displayed on the right of the die (relative to the white player)
     */
    private int right;

    /**
     * Boolean which stores if the die has been deleted (for dieMesh reasons)
     */
    private boolean deleted = false;

    /**
     * Given the coordinates, the values of the dice on each side, and the owner of the dice,
     * construct the corresponding dice.
     */
    public Die(int top, int down, int front, int back, int left, int right, int x, int y, boolean isWhite) {
        this.top = top;
        this.down = down;
        this.front = front;
        this.back = back;
        this.left = left;
        this.right = right;
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
    }

    /**
     * Constructor for Die
     */
    public Die(String placement) {
        boolean added = false;
        int orientation = placement.charAt(0);
        isWhite = !Character.isLowerCase(orientation);
        orientation = Character.toLowerCase(orientation) - 'a';
        int frontTemp = (orientation % 4) + 1;
        this.top = (orientation / 4) + 1;
        this.down = 7 - this.top;

        if (frontTemp >= down) {
            frontTemp++;
            added = true;
        }
        if (frontTemp >= top) frontTemp++;
        if (frontTemp >= down && !added) frontTemp++;

        this.front = frontTemp;
        this.back = 7 - this.front;
        int polyEquation = (int) (3 * top * front * (Math.pow(top, 2) - Math.pow(front, 2)));
        BigInteger polynomial = new BigInteger(String.valueOf(polyEquation));
        this.left = polynomial.mod(BigInteger.valueOf(7)).intValue();
        this.right = 7 - this.left;
        this.x = placement.charAt(1) - 'a';
        this.y = placement.charAt(2) - '1';
    }

    /**
     * Converts a Die to string encoding
     *
     * @param die The die to be encoded
     * @return String encoding of die
     */
    public static String dieToEnc(Die die) { // FIXME If there is time, please also change this to not be hardcoded
        int[][] orientations = new int[][]{
                {1, 2, 3},
                {1, 3, 5},
                {1, 4, 2},
                {1, 5, 4},
                {2, 1, 4},
                {2, 3, 1},
                {2, 4, 6},
                {2, 6, 3},
                {3, 1, 2},
                {3, 2, 6},
                {3, 5, 1},
                {3, 6, 5},
                {4, 1, 5},
                {4, 2, 1},
                {4, 5, 6},
                {4, 6, 2},
                {5, 1, 3},
                {5, 3, 6},
                {5, 4, 1},
                {5, 6, 4},
                {6, 2, 4},
                {6, 3, 2},
                {6, 4, 5},
                {6, 5, 3}
        };
        char zero;
        char one;
        char two;
        int[] current = {die.getTop(), die.getFront(), die.getLeft()};
        int index = -1;
        for (int i = 0; i < orientations.length; i++) {
            if (Arrays.equals(orientations[i], current)) {
                index = i;
            }
        }
        if (die.isWhite()) {
            zero = (char) (index + 65);
        } else {
            zero = (char) (index + 97);
        }

        one = (char) (die.getX() + 'a');
        two = (char) (die.getY() + '1');

        return "" + zero + one + two;
    }

    /**
     * Set deleted flag to be true
     */
    public void delete() {
        deleted = true;
    }

    /**
     * Getter for deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Given a die, shallow clone the die
     */
    public void setDie(Die die) {
        this.top = die.top;
        this.down = die.down;
        this.front = die.front;
        this.back = die.back;
        this.left = die.left;
        this.right = die.right;
        this.x = die.x;
        this.y = die.y;
    }

    /**
     * Getter for top
     */
    public int getTop() {
        return this.top;
    }

    /**
     * Setter for top
     */
    public void setTop(int top) {
        this.top = top;
    }

    /**
     * Overriding equals() method
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Die die = (Die) o;
        return x == die.x && y == die.y && top == die.top && down == die.down && front == die.front && back == die.back
                && left == die.left && right == die.right && isWhite == die.isWhite;
    }

    /**
     * toString method
     */
    @Override
    public String toString() {
        return (isWhite ? "White" : "Black") + " (" + getX() + "," + getY() + ")";
    }

    /**
     * Getter for position
     */
    public String getPosition() {
        return x + "" + y;
    }

    /**
     * Setter for position
     */
    public void setPosition(String position) {
        this.x = Integer.parseInt(position.substring(0, 1));
        this.y = Integer.parseInt(position.substring(1));
    }

    /**
     * Setter for position
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a deep copy of the die
     *
     * @return A deep copy the die
     */
    public Die deepClone() {
        Die object = new Die(this.top, this.down, this.front, this.back, this.left, this.right, this.x, this.y, this.isWhite);
        object.deleted = this.deleted;
        return object;
    }

    /**
     * Getter for x
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for y
     */
    public int getY() {
        return y;
    }

    /**
     * Find out if the die is in white's "goal" area
     *
     * @return True if the die is in white's "goal", false otherwise
     */
    public boolean isWhiteDieFinished() {
        return getY() == 6;
    }

    /**
     * Find out if the die is in black's "goal" area
     *
     * @return True if the die is in black's "goal", false otherwise
     */
    public boolean isBlackDieFinished() {
        return getY() == 0;
    }

    /**
     * Getter for isWhite
     */
    public boolean isWhite() {
        return isWhite;
    }

    /**
     * Returns whether the current instance of dice is adjacent to another given dice
     *
     * @param other A die
     * @return True if dice are adjacent, false otherwise
     */
    public boolean isAdjacent(Die other) {
        return Boards.getManhattanDistance(other.getPosition(), getPosition()) == 1;
    }

    /**
     * Tip the dice in a given direction the orientation and position of the dice will change accordingly
     *
     * @param direction The direction the die will roll in
     */
    public void tip(Direction direction) {
        switch (direction) {
            case UP -> {
                int temp = getTop();
                setTop(getBack());
                setBack(getDown());
                setDown(getFront());
                setFront(temp);
                setPosition(getX(), getY() + 1);
            }
            case DOWN -> {
                int temp = getTop();
                setTop(getFront());
                setFront(getDown());
                setDown(getBack());
                setBack(temp);
                setPosition(getX(), getY() - 1);
            }
            case LEFT -> {
                int temp = getLeft();
                setLeft(getTop());
                setTop(getRight());
                setRight(getDown());
                setDown(temp);
                setPosition(getX() - 1, getY());
            }
            case RIGHT -> {
                int temp = getLeft();
                setLeft(getDown());
                setDown(getRight());
                setRight(getTop());
                setTop(temp);
                setPosition(getX() + 1, getY());
            }
        }
    }

    /**
     * Moves the die two units in the direction that is specified
     *
     * @param direction The direction which is specified
     */
    public void jump(Direction direction) {
        switch (direction) {
            case UP -> setPosition(getX(), getY() + 2);
            case DOWN -> setPosition(getX(), getY() - 2);
            case LEFT -> setPosition(getX() - 2, getY());
            case RIGHT -> setPosition(getX() + 2, getY());
        }
    }

    /**
     * Returns the position that is a given steps over in whichever direction specified
     *
     * @param direction The direction specified
     * @param n         The amount of positions over
     * @return The position which is n positions over
     */
    public String getPositionOver(Direction direction, int n) {
        return switch (direction) {
            case RIGHT -> Integer.toString(getX() + n) + getY();
            case LEFT -> Integer.toString(getX() - n) + getY();
            case UP -> getX() + Integer.toString(getY() + n);
            case DOWN -> getX() + Integer.toString(getY() - n);
        };
    }

    /**
     * Gets the direction that a position is relative to the die
     *
     * @param endPosition The position whose direction the die will calculate
     * @return The direction of the position relative to the die
     */
    public Direction getDirection(String endPosition) {
        Boards.Positions end = new Boards.Positions(endPosition);
        if (getX() > end.getX()) {
            return LEFT;
        } else if (getX() < end.getX()) {
            return RIGHT;
        } else if (getY() < end.getY()) {
            return UP;
        } else if (getY() > end.getY()) {
            return DOWN;
        }
        return null;
    }

    /**
     * Evaluate the position of a die when it is approaching the edge
     *
     * @return
     */
    public int evaluateApproachingDie() {
        if (this.isWhite()) {
            return this.getY() == 6 ? this.getBack() - 3 : 0;
        } else {
            return this.getY() == 1 ? this.getFront() - 3 : 0;
        }

    }

    /**
     * Getter for down
     */
    public int getDown() {
        return down;
    }

    /**
     * Setter for down
     */
    public void setDown(int down) {
        this.down = down;
    }

    /**
     * Getter for front
     */
    public int getFront() {
        return front;
    }

    /**
     * Setter for front
     */
    public void setFront(int front) {
        this.front = front;
    }

    /**
     * Getter for back
     */
    public int getBack() {
        return back;
    }

    /**
     * Setter for back
     */
    public void setBack(int back) {
        this.back = back;
    }

    /**
     * Getter for left
     */
    public int getLeft() {
        return left;
    }

    /**
     * Setter for left
     */
    public void setLeft(int left) {
        this.left = left;
    }

    /**
     * Getter for right
     */
    public int getRight() {
        return right;
    }

    /**
     * Setter for right
     */
    public void setRight(int right) {
        this.right = right;
    }

    /**
     * Class which implements a comparator for Die
     *
     * @author Ziling Ouyang
     */
    public static class SortByColour implements Comparator<Die> {

        /**
         * Compares its two arguments for order. Returns a negative integer, zero, or a positive integer as the first argument
         * is less than, equal to, or greater than the second.
         */
        @Override
        public int compare(Die dieA, Die dieB) {
            int a = (dieA.getX() + dieA.getY()) * Boards.BOARD_DIMENSION;
            int b = (dieB.getX() + dieB.getY()) * Boards.BOARD_DIMENSION;

            if (a == b) {
                return Integer.signum(dieA.getX() - dieB.getX());
            } else {
                return (a > b) ? 1 : -1;
            }
        }
    }
}
