package comp1140.ass2.gui.guiPieces;

public class Position {
    public int x;
    public int y;

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