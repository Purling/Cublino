package comp1140.ass2.State;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

import static comp1140.ass2.State.Direction.*;

/**
 * @author Whole group
 */
public class Die implements Serializable {
    private final boolean isWhite;
    /**
     * x and y represents the coordinate of the dice on a board
     */
    private int x;
    private int y;
    /**
     * The value on each side of a die values will vary when the position/ orientation of the dice changes
     */
    private int top;
    private int down;
    private int front;
    private int back;
    private int left;
    private int right;
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

    public Die(String placement, Players whitePlayer, Players blackPlayer) {
        assert placement.length() == 3;
        int orientation = placement.charAt(0);
        if (Character.isLowerCase(orientation)) {
            isWhite = false;
            orientation -= 97;
        } else {
            isWhite = true;
            orientation -= 65;
        }
        assert 0 <= orientation && orientation < 24;

        // All 24 orientations in the order {top}{front}{left},
        // manually determined by yours truly
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

        this.top = orientations[orientation][0];
        this.down = 7 - this.top;
        this.front = orientations[orientation][1];
        this.back = 7 - this.front;
        this.left = orientations[orientation][2];
        this.right = 7 - this.left;

        this.x = placement.charAt(1) - 'a';
        this.y = placement.charAt(2) - '1';
    }

    public static String dieToEnc(Die die) {
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
        //String z = current.toString();

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
     * Given a die, create the same die
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
     * Obtain the value on the top of the dice
     * The value will vary when the orientation of dice changes
     */
    public int getTop() {
        return this.top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Die die = (Die) o;
        return x == die.x && y == die.y && top == die.top && down == die.down && front == die.front && back == die.back
                && left == die.left && right == die.right && isWhite == die.isWhite;
    }

    /**
     * Get the value of the die on a particular side
     *
     * @param d The side of the die the value is on
     * @return The int value on the particular side of the die or -1 if not valid
     */
    public int getSide(Direction d) {
        return switch (d) {
            case UP -> back;
            case DOWN -> front;
            case LEFT -> left;
            case RIGHT -> right;
        };
    }

    @Override
    public String toString() {
        return (isWhite ? "White" : "Black") + " (" + getX() + "," + getY() + ")";
    }

    /**
     * Get the current position of the dice in the form of a string
     *
     * @return string x+""+y
     */
    public String getPosition() {
        return x + "" + y;
    }

    public void setPosition(String position) {
        this.x = Integer.parseInt(position.substring(0, 1));
        this.y = Integer.parseInt(position.substring(1));
    }

    public String getFaces() {
        return "Top: " + top +
                " Down: " + down +
                " Front: " + front +
                " Back: " + back +
                " Left: " + left +
                " Right: " + right;
    }

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
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Die) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isWhiteDieFinished() {
        return getY() == 6;
    }

    public boolean isBlackDieFinished() {
        return getY() == 0;
    }

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

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public int getFront() {
        return front;
    }

    public void setFront(int front) {
        this.front = front;
    }

    public int getBack() {
        return back;
    }

    public void setBack(int back) {
        this.back = back;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public static class SortByColour implements Comparator<Die> {

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
