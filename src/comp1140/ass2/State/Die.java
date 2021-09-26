package comp1140.ass2.State;

import java.util.Arrays;

public class Die {
    /**
     * x and y represents the coordinate of the dice on a board
     */
    private int x;
    private int y;
    /**
     * the value on each side of a dice
     * values will vary when the position/ orientation of the dice changes
     */
    private int top;
    private int down; // Shouldn't this be called bottom?
    private int front;
    private int back;
    private int left;
    private int right;
    private Players player;


    /**
     * Given the coordinates, the values of the dice on each side, and the owner of the dice,
     * construct the corresponding dice.
     */
    public Die(int top, int down, int front, int back, int left, int right, int x, int y, Players player) {
        this.top = top;
        this.down = down;
        this.front = front;
        this.back = back;
        this.left = left;
        this.right = right;
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public Die(String placement, Players whitePlayer, Players blackPlayer) {
        assert placement.length() == 3;
        int orientation = placement.charAt(0);
        if (Character.isLowerCase(orientation)) {
            this.player = blackPlayer;
            orientation -= 97;
        } else {
            this.player = whitePlayer;
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

    /**
     * Obtain the value on the top of the dice
     * The value will vary when the orientation of dice changes
     */
    public int getTop() {
        return this.top;
    }

    /**
     * Get the value of the die on a particular side
     *
     * @param d The side of the die the value is on
     * @return The int value on the particular side of the die or -1 if not valid
     */
    public int getSide(Direction d) {
        switch (d) {
            case UP:
                return back;
            case DOWN:
                return front;
            case LEFT:
                return left;
            case RIGHT:
                return right;
            default:
                return -1;
        }
    }

    /**
     * Get the current player that is making moves
     *
     * @return Player
     */
    public Players getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return player.toString() + " (" + getX() + "," + getY() + ")";
    }

    /** Obtain the direction of the next desired movement
     * @param move
     * @return Direction
     */


    /**
     * Get the current position of the dice in the form of a string
     *
     * @return string x+""+y
     */
    public String getPosition() {
        return x + "" + y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setPosition(String position) {
        this.x = Integer.parseInt(position.substring(0, 1));
        this.y = Integer.parseInt(position.substring(1));
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

    public boolean isDieWhite() {
        return player.isWhite();
    }

    /**
     * Returns whether the current instance of dice is adjacent to another given dice
     *
     * @param other A die
     * @return True if dice are adjacent, false otherwise
     */
    public boolean isAdjacent(Die other) {
        return false;
    }

    /**
     * Tip the dice in a given direction the orientation and position of the dice will change accordingly
     *
     * @param direction The direction the die will roll in
     */
    public void tip(Direction direction) {

        switch (direction) {
            case UP -> {
                int top = getTop();
                setTop(getBack());
                setBack(getDown());
                setDown(getFront());
                setFront(top);
                setPosition(getX(), getY() - 1);
            }
            case DOWN -> {
                int top1 = getTop();
                setTop(getFront());
                setFront(getDown());
                setDown(getBack());
                setBack(top1);
                setPosition(getX(), getY() + 1);
            }
            case LEFT -> {
                int left = getLeft();
                setLeft(getTop());
                setTop(getRight());
                setRight(getDown());
                setDown(left);
                setPosition(getX() - 1, getY());
            }
            case RIGHT -> {
                int left1 = getLeft();
                setLeft(getDown());
                setDown(getRight());
                setRight(getTop());
                setDown(left1);
                setPosition(getX() + 1, getY());
            }
        }
    }

    public void setTop(int top) {
        this.top = top;
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

    /**
     * Lets the dice jump in a given direction. Only the position of the dice will change.
     *
     * @param direction The direction the dice will jump in
     */
    public void jump(Direction direction) {
        switch (direction) {
            case UP -> {
                setPosition(getX(), getY() - 2);
            }
            case DOWN -> {
                setPosition(getX(), getY() + 2);
            }
            case LEFT -> {
                setPosition(getX() - 2, getY());
            }
            case RIGHT -> {
                setPosition(getX() + 2, getY());
            }
        }
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
            if (die.isDieWhite()) {
                zero = (char) (index + 65);
            } else {
                zero = (char) (index + 97);
            }
            //String z = current.toString();

            one = (char) (die.getX() + 'a');
            two = (char) (die.getY() + '1');

            return "" + zero + one + two;
        }
    }

