package comp1140.ass2.State;

public class Die {
    /** x and y represents the coordinate of the dice on a board */
    private int x;
    private int y;
    /** the value on each side of a dice
    * values will vary when the position/ orientation of the dice changes
     */
    private int top;
    private int down;
    private int front;
    private int back;
    private int left;
    private int right;
    private Players player;


    /** Given the coordinates, the values of the dice on each side, and the owner of the dice,
    * construct the corresponding dice.*/
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
            this.player = whitePlayer;
            orientation -= 97;
        } else {
            this.player = blackPlayer;
            orientation -= 65;
        }
        assert 0 <= orientation && orientation < 24;

        // All 24 orientations in the order {top}{front}{left},
        // manually determined by yours truly
        int[][] orientations = new int[][] {
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
    public int getTop() {return this.top;}

    /**
     *  Get the value of the die on a particular side
     * @param d The side of the die the value is on
     * @return The int value on the particular side of the die
     */
    public int getSide(Direction d){
        switch (d) {
            case UP: return back;
            case DOWN: return front;
            case LEFT: return left;
            case RIGHT: return right;
            default: return -1;
        }
    }
    /** Get the current player that is making moves
     * @return Player
     */
    public Players getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return player.toString() + " " + getPosition();
    }

    /** Obtain the direction of the next desired movement
     * @param d
     * @return Direction
     */
    public Direction get(Direction d){
        return d;
    }

    /**
     * Get the current position of the dice in the form of a string
     * @return string x+""+y
     */
    public String getPosition(){
        return x + "" + y;
    }

    public int getX() {return x;}
    public int getY() {return y;}

    public boolean isWhiteDieFinished() {
        return getY() == 0;
    }

    public boolean isBlackDieFinished() {
        return getY() == 6;
    }

    public boolean getColor() {
        return player.isWhite();
    }

    /**
     * Returns whether or not the current instance of dice is adjacent to another given dice
     * @param other
     * @return
     */
    public boolean isAdjacent(Die other){
        return false;

    }

    /**
     * Roll the dice in a given direction
     * the orientation and position of the dice will change accordingly
     * @param d
     * @param direction
     */
    public void roll(Die d, Direction direction){
    }

    /**
     * Let the dice jump in a given direction
     * Only the postion of the dice will change accordingly
     * The orientation of he dice is not changed in a Jump movement
     * @param d
     * @param direction The direction the dice will jump in
     */
    public void jump(Die d, Direction direction){
    }
}
