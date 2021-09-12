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

    public Boards() {
    }

    public String stateToPosition(String encodedPosition){
        char[] encodedCh = encodedPosition.toCharArray();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < encodedCh.length; i++){
            if (i % 2 == 0) {
                sb.append((int) encodedCh[i] - 'a');
            } else {
                sb.append((int) encodedCh[i] - '1');
            }
        }
        return sb.toString();
    }

    public int getManhattanDistance(String startPosition, String endPosition){
        return getDistance(Integer.parseInt(startPosition.substring(0,1)) , Integer.parseInt(endPosition.substring(0,1)))
                + getDistance(Integer.parseInt(startPosition.substring(1)) , Integer.parseInt(endPosition.substring(1)));
    }

    /**
     * Gets the position of the middle of two dice with a Manhattan distance of 2
     * @param startPosition position of first die
     * @param endPosition position of second die
     * @return The position which is between the two dice
     */
    public String getMiddlePosition(String startPosition, String endPosition){
        if (startPosition.charAt(0) == endPosition.charAt(0)) {
            return startPosition.charAt(0) + ((startPosition.charAt(1) > endPosition.charAt(1))
                    ? Character.toString(startPosition.charAt(1) - 1)
                    : Character.toString(endPosition.charAt(1) - 1));
        } else {
            return (startPosition.charAt(0) > endPosition.charAt(0))
                    ? Character.toString(startPosition.charAt(0) - 1) + startPosition.charAt(1)
                    : Character.toString(endPosition.charAt(0) - 1) + startPosition.charAt(1);
        }
    }

    private int getDistance(int point1, int point2) {
        return Math.abs(point1-point2);
    }

    public boolean isAdjacent(String startPosition, String endPosition){
        return getManhattanDistance(startPosition, endPosition) == 1;
    }

    public boolean sameAxis(String startPosition, String endPosition) {
        return startPosition.charAt(0) == endPosition.charAt(0)
                || startPosition.charAt(1) == endPosition.charAt(1);
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

    public void setWhiteAndBlackPlayer(String encodedState){

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
