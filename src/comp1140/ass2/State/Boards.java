package comp1140.ass2.State;

import java.util.*;
import java.util.stream.Collectors;

import static comp1140.ass2.State.Direction.*;

public class Boards {
    private Players whitePlayer = new Players(true);
    private Players blackPlayer = new Players(false);
    public static final int BOARD_DIMENSION = 7;

    public static class Positions {
        private String coordinate;

        public Positions(String coordinate) {
            this.coordinate = coordinate;
        }

        public int getX() {
            return Integer.parseInt(coordinate.substring(0,1));
        }

        public int getY() {
            return Integer.parseInt(coordinate.substring(1,2));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Positions positions = (Positions) o;
            return Objects.equals(coordinate, positions.coordinate);
        }

        @Override
        public String toString() {
            return coordinate;
        }
    }

    /**
     * A 2d array to represent the current status of the board
     */
    private Die[][] board = new Die[BOARD_DIMENSION][BOARD_DIMENSION];

    public Boards(String encodedState) {
        //Assumes the encoded state is valid and well-formed

        assert encodedState.length() % 3 == 1;
        for (int i = 1; i < encodedState.length(); i += 3) {
            Die d = new Die(encodedState.substring(i, i+3), whitePlayer, blackPlayer);
            if (d.getPlayer().equals(whitePlayer)) whitePlayer.myDice.add(d);
            else blackPlayer.myDice.add(d);
            board[d.getY()][d.getX()] = d;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boards boards = (Boards) o;

        whitePlayer.getDice().sort(new Die.SortByColour());
        blackPlayer.getDice().sort(new Die.SortByColour());
        boards.whitePlayer.getDice().sort(new Die.SortByColour());
        boards.blackPlayer.getDice().sort(new Die.SortByColour());

        return whitePlayer.getDice().equals(boards.whitePlayer.getDice()) && blackPlayer.getDice().equals(boards.blackPlayer.getDice())
                && Arrays.deepEquals(board, boards.board);
    }

    /**
     * Empty constructor for Boards
     */
    public Boards() {
    }

    /**
     * Converts a move String into an array of String containing positions
     * @param move Encoded String of positions
     * @return String array of board positions indicating steps
     */
    public static Positions[] moveToPositions(String move) {

        int even = 2;
        char xPositionLowerRange = 'a';
        char yPositionLowerRange = '1';
        String twoSplit = "(?<=\\G.{2})";
        char[] encodedCh = move.toCharArray();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < encodedCh.length; i++){
            if (i % even == 0) {
                sb.append((int) encodedCh[i] - xPositionLowerRange);
            } else {
                sb.append((int) encodedCh[i] - yPositionLowerRange);
            }
        }
        return Arrays.stream(sb.toString().split(twoSplit)).map(Positions::new).collect(Collectors.toList()).toArray(Positions[]::new);
    }

    public void applyTip(Die initial, String endPosition) {

        String start = initial.getPosition();

        if(getAt(endPosition) == null) {
            initial.tip(getDirection(initial, endPosition));
            initial.setPosition(endPosition);
            setAt(endPosition, initial);
            setAt(start,null);
        }
    }

    public void applyJump(Die initial, String endPosition) {

        String start = initial.getPosition();

        if(getAt(endPosition) == null) {
            initial.setPosition(endPosition);
            setAt(endPosition, initial);
            setAt(start,null);
        }
    }

    public Direction getDirection(Die initial, String endPosition) {

        Positions end = new Positions(endPosition);

        if(initial.getX() > end.getX()){
            return LEFT;
        } else if(initial.getX() < end.getX()) {
            return RIGHT;
        } else if(initial.getY() < end.getY()) {
            return UP;
        } else if(initial.getY() > end.getY()) {
            return DOWN;
        }
        return null;
    }

    public static int getPositionX(String x) {
        return Integer.parseInt(x.substring(0,1));
    }

    public static int getPositionY(String x) {
        return Integer.parseInt(x.substring(1));
    }

    public static int getManhattanDistance(String startPosition, String endPosition) {
        return Math.abs(getPositionX(startPosition) - getPositionX(endPosition)) + Math.abs(getPositionY(startPosition) - getPositionY(endPosition));
    }

    /**
     * Gets the position of the middle of two dice with a Manhattan distance of 2
     * @param startPosition position of first die
     * @param endPosition position of second die
     * @return The position which is between the two dice
     */
    public static String getMiddlePosition(String startPosition, String endPosition) {
        if (getPositionX(startPosition) == getPositionX(endPosition)) {
            return "" + getPositionX(startPosition)
                    + (getPositionY(startPosition) > getPositionY(endPosition)
                        ? getPositionY(startPosition) - 1
                        : getPositionY(endPosition) - 1);
        } else {
            return (getPositionX(startPosition) > getPositionX(endPosition))
                    ? "" + (getPositionX(startPosition) - 1) + getPositionY(startPosition)
                    : "" + (getPositionX(endPosition) - 1) + getPositionY(startPosition);
        }
    }

    public boolean isAdjacent(String startPosition, String endPosition) {
        return getManhattanDistance(startPosition, endPosition) == 1;
    }

    /**
     * Returns whether two positions are horizontal or vertical to each other
     * @param position1 Position on board
     * @param position2 Position on board
     * @return True if positions are on the same axis(column/row), false otherwise
     */
    public static boolean sameAxis(String position1, String position2) {
        return getPositionX(position1) == getPositionX(position2) || getPositionY(position1) == getPositionY(position2);
    }

    /**
     * Gets the die at the specified location on the board
     * @param x x-coordinate on board
     * @param y y-coordinate on board
     * @return A die or null if there is no die at particular location
     */
    public Die getAt(int x, int y) {
        return board[y][x];
    }

    public Die getAt(String position) {
        int x = Integer.parseInt(position.substring(0,1));
        int y = Integer.parseInt(position.substring(1));
        return board[y][x];
    }

    public void setAt(String position, Die die) {
        int x = Integer.parseInt(position.substring(0,1));
        int y = Integer.parseInt(position.substring(1));
        board[y][x] = die;
    }

    public Die getAtPosition(String position) {
        return getAt(Boards.getPositionX(position), Boards.getPositionY(position));
    }

    public boolean containsOverlappingPieces() {
        List<String> white = whitePlayer.getDice().stream().map(Die::getPosition).collect(Collectors.toList());
        List<String> black = blackPlayer.getDice().stream().map(Die::getPosition).collect(Collectors.toList());
        Set<String> allPieces = new HashSet<>();
        allPieces.addAll(white);
        allPieces.addAll(black);
        return allPieces.size() != (white.size() + black.size());
    }

    @Deprecated
    public void setWhiteAndBlackPlayer(String encodedState) {

        String tripleSplit = "(?<=\\G.{3})";
        encodedState = encodedState.substring(1);
        String[] diceList = encodedState.split(tripleSplit);

        for (String dieStr : diceList) {
            Die die = new Die(dieStr, whitePlayer, blackPlayer);
            if (die.getPlayer().isWhite()){
               whitePlayer.myDice.add(die);
            }
            else{
                blackPlayer.myDice.add(die);
            }
        }
    }

    public Players getWhitePlayer() {
        return whitePlayer;
    }

    public Players getBlackPlayer() {
        return blackPlayer;
    }

    @Override
    public String toString() {
        return whitePlayer.getDice().toString() + "\n" + blackPlayer.getDice();
    }
}
