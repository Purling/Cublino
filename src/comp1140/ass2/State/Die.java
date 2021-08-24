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
    public Die(int top, int down, int front, int back, int left, int right, int x, int y, Players player){

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

    /**
     * Obtain the value on the top of the dice
     * The value will vary when the orientation of dice changes
     */
    public int getTop() {return this.top;}

    /**
     *  Get the value of the dice on a particular side
     * @param d
     * @return
     */
    public int getSide(Direction d){
        return -1;
    }
    /** Get the current player that is making moves
     * @return Player
     */
    public Players getPlayer() {
        return player;
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
     * @param x
     * @param y
     * @return string x+""+y
     */
    public String getPosition(int x, int y){
        return "";
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
     * @return
     */
    public void roll(Die d, Direction direction){
    }

    /**
     * Let the dice jump in a given direction
     * Only the postion of the dice will change accordingly
     * The orientation of he dice is not changed in a Jump movement
     * @param d
     * @param direction
     * @return
     */
    public void jump(Die d, Direction direction){
    }
}
