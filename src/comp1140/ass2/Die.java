package comp1140.ass2;

public class Die {
    // x and y represents the coordinate of the dice on a board
    private int x;
    private int y;
    // the value on each side of a dice
    // values will vary when the position/ orientation of the dice changes
    private int top;
    private int down;
    private int front;
    private int back;
    private int left;
    private int right;
    private Player player;



    public Die(int top, int down, int front, int back, int left, int right, int x, int y, Player player){
        // Given the coordinates, the values of the dice on each side, and the owner of the dice,
        // construct the corresponding dice.
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

    public int getTop() {return this.top;}

    public Player getPlayer() {
        return player;
    }

    public Direction get(Direction d){
        // Obtain the direction of the next desired movement
        return d;
    }

    public String getPosition(int x, int y){
        // get the location of the dice in the form of "xy"
        return "";
    }

    public boolean isAdjacent(Die other){
        //Returns whether or not the current instance of dice is adjacent
        // to another given dice
        return false;

    }
    public Die roll(Die d, Direction direction){
        //roll the dice in a given direction
        return d;
    }
    public Die jump(Die d, Direction direction){
        //Let the die jump in a given direction
        return d;
    }
}
