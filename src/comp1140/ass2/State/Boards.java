package comp1140.ass2.State;

import java.util.*;
import java.util.stream.Collectors;

public class Boards {
    private Players whitePlayer = new Players(true);
    private Players blackPlayer = new Players(false);
    public static final int BOARD_DIMENSION = 7;

    /**
     * A 2d array to represent the current status of the board
     */
    private Die[][] board = new Die[BOARD_DIMENSION][BOARD_DIMENSION];

    public Boards(String encodedState) {
        //Assumes the encoded state is valid and well-formed

        ArrayList<Die> dice = new ArrayList<Die>();
        ArrayList<Die> whiteDice = new ArrayList<Die>();
        ArrayList<Die> blackDice = new ArrayList<Die>();
        assert encodedState.length() % 3 == 1;
        for (int i = 1; i < encodedState.length(); i += 3) {
            Die d = new Die(encodedState.substring(i, i+3), whitePlayer, blackPlayer);
            if (d.getPlayer().equals(whitePlayer)) whiteDice.add(d);
            else blackDice.add(d);
            board[d.getY()][d.getX()] = d;
        }

        whitePlayer.setDice(new Die[whiteDice.size()]);
        for (int i = 0; i < whiteDice.size(); i++) {
            whitePlayer.setOneDie(i, whiteDice.get(i));
        }
        blackPlayer.setDice(new Die[blackDice.size()]);
        for (int i = 0; i < blackDice.size(); i++) {
            blackPlayer.setOneDie(i, blackDice.get(i));
        }
    }

    /**
     * Empty constructor for Boards
     */
    public Boards() {
    }

    public Positions statesToPositions(String encodedPosition) {
        char[] encodedCh = encodedPosition.toCharArray();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < encodedCh.length; i++){
            if (i % 2 == 0) {
                sb.append((int) encodedCh[i] - 'a');
            } else {
                sb.append((int) encodedCh[i] - '1');
            }
        }
        return new Positions(sb.substring(0,2), sb.substring(2));
    }

    public static class Positions {
        private String start;
        private String end;

        public Positions(String start, String end) {
            this.start = start;
            this.end = end;
        }

        public String getStart() {
            return start;
        }

        public String getEnd() {
            return end;
        }
    }

    public static int getPositionX(String x) {
        return Integer.parseInt(x.substring(0,1));
    }

    public static int getPositionY(String x) {
        return Integer.parseInt(x.substring(1));
    }

    public int getManhattanDistance(String startPosition, String endPosition) {
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
     * Returns whether two positions are horinzontal or vertical to each other
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

    public Die getAtPosition(String position) {
        return getAt(Boards.getPositionX(position), Boards.getPositionY(position));
    }


    /**
     * Check whether or not a given location locates on the board
     * Return true if the given coordinates locate on the board
     * False otherwise
     * @param x x-coordinate on board
     * @param y y-coordinate on board
     */
    public boolean isOnBoard(int x, int y){
        return false;
    }

    public boolean containsOverlappingPieces() {
        List<String> white = Arrays.stream(whitePlayer.getDice()).map(Die::getPosition).collect(Collectors.toList());
        List<String> black = Arrays.stream(blackPlayer.getDice()).map(Die::getPosition).collect(Collectors.toList());
        Set<String> allPieces = new HashSet<>();
        allPieces.addAll(white);
        allPieces.addAll(black);
        return allPieces.size() != (white.size() + black.size());
    }

    public void setWhiteAndBlackPlayer(String encodedState) {

        List<Die> whiteDice = new ArrayList<>();
        List<Die> blackDice = new ArrayList<>();
        encodedState = encodedState.substring(1);
        String[] diceList = encodedState.split("(?<=\\G.{3})");

        for (String dieStr : diceList) {
            Die die = new Die(dieStr, whitePlayer, blackPlayer);
            if (die.getPlayer().isWhite()){
               whiteDice.add(die);
            }
            else{
                blackDice.add(die);
            }
        }
        whitePlayer.setDice(whiteDice.toArray(new Die[]{}));
        blackPlayer.setDice(blackDice.toArray(new Die[]{}));
    }

    public Players getWhitePlayer() {
        return whitePlayer;
    }

    public Players getBlackPlayer() {
        return blackPlayer;
    }

    public Die[][] getBoard() {
        return board;
    }

    public void setBoard(Die[][] board) {
        this.board = board;
    }

    private String[] getPositionArray(Die[] player){
        return null;
    }
}
