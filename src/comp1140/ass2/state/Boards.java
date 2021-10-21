package comp1140.ass2.state;

import comp1140.ass2.helperclasses.DeepCloneable;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static comp1140.ass2.state.Die.dieToEncoding;

/**
 * Representation of the board including all the players and their die
 *
 * @author Whole group
 */
public class Boards implements Serializable, DeepCloneable<Boards> {

    /**
     * The length and width of the board
     */
    public static final int BOARD_DIMENSION = 7;

    /**
     * A 2d array to represent the current status of the board
     */
    private final Die[][] board = new Die[BOARD_DIMENSION][BOARD_DIMENSION];

    /**
     * The white player
     */
    private Players whitePlayer = new Players(true);

    /**
     * The black player
     */
    private Players blackPlayer = new Players(false);

    /**
     * Constructor for Boards
     */
    public Boards(String encodedState) {
        for (int i = 1; i < encodedState.length(); i += 3) {
            Die d = new Die(encodedState.substring(i, i + 3));
            if (d.isWhite() == whitePlayer.isWhite) whitePlayer.addToDice(d);
            else blackPlayer.addToDice(d);
            board[d.getY()][d.getX()] = d;
        }
    }

    /**
     * Empty constructor for Boards
     */
    public Boards() {
    }

    /**
     * Converts a move String into an array of String containing positions
     *
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

        for (int i = 0; i < encodedCh.length; i++) {
            if (i % even == 0) {
                sb.append((int) encodedCh[i] - xPositionLowerRange);
            } else {
                sb.append((int) encodedCh[i] - yPositionLowerRange);
            }
        }
        return Arrays.stream(sb.toString().split(twoSplit)).map(Positions::new).collect(Collectors.toList()).toArray(Positions[]::new);
    }

    /**
     * Helper function to get the first character in a 2 character String which represents coordinates
     */
    public static int getPositionX(String x) {
        return Integer.parseInt(x.substring(0, 1));
    }

    /**
     * Helper function to get the second character in a 2 character String which represents coordinates
     */
    public static int getPositionY(String x) {
        return Integer.parseInt(x.substring(1));
    }

    /**
     * Returns the manhattan distance between two points
     *
     * @param startPosition Point A
     * @param endPosition   Point B
     * @return The manhattan distance between A and B
     */
    public static int getManhattanDistance(String startPosition, String endPosition) {
        return Math.abs(getPositionX(startPosition) - getPositionX(endPosition)) + Math.abs(getPositionY(startPosition) - getPositionY(endPosition));
    }

    /**
     * Gets the position of the middle of two dice with a Manhattan distance of 2
     *
     * @param startPosition position of first die
     * @param endPosition   position of second die
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

    /**
     * Returns whether two positions are horizontal or vertical to each other
     *
     * @param position1 Position on board
     * @param position2 Position on board
     * @return True if positions are on the same axis(column/row), false otherwise
     */
    public static boolean sameAxis(String position1, String position2) {
        return getPositionX(position1) == getPositionX(position2) || getPositionY(position1) == getPositionY(position2);
    }

    /**
     * Returns the String representation of a board
     *
     * @param board The board being played on
     * @return The String representation of said parameter board
     */
    public static String boardToString(Boards board) {
        List<String> b = new ArrayList<>();
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                Die die;
                if (board.getAt(x, y) != null) {
                    die = board.getAt(x, y);
                    b.add(dieToEncoding(die));
                }
            }
        }
        b.sort((o1, o2) -> {
            int a = o1.charAt(2) - o2.charAt(2);
            if (a != 0) {
                return a;
            }
            int b1 = o1.charAt(1) - o2.charAt(1);
            if (b1 != 0) {
                return b1;
            }
            return o1.charAt(0) - o2.charAt(0);
        });
        StringBuilder str = new StringBuilder();
        for (String r : b) {
            str.append(r);
        }
        return str.toString();
    }

    /**
     * Implements the deepClone method from DeepCloneable interface
     */
    public Boards deepClone() {
        Boards object = new Boards();
        object.whitePlayer = this.whitePlayer != null ? this.whitePlayer.deepClone() : null;
        object.blackPlayer = this.blackPlayer != null ? this.blackPlayer.deepClone() : null;
        for (int i = 0; i < BOARD_DIMENSION; i++) {
            for (int j = 0; j < BOARD_DIMENSION; j++) {
                if (this.board[i][j] != null) {
                    object.board[i][j] = this.board[i][j].deepClone();
                }
            }
        }
        return object;
    }

    /**
     * Overriding equals method
     */
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
     * Evaluates if a coordinate is adjacent to another coordinate
     *
     * @param startPosition First coordinate
     * @param endPosition   Other coordinate
     * @return True if adjacent coordinates, false otherwise
     */
    public boolean isAdjacent(String startPosition, String endPosition) {
        return getManhattanDistance(startPosition, endPosition) == 1;
    }

    /**
     * Returns any adjacent die
     *
     * @param die The die from which adjacency is determined
     * @return Any die which are adjacent to the specified die
     */
    public Die[] getAdjacentDie(Die die) {
        ArrayList<Die> allDice = (ArrayList<Die>) Stream.concat(whitePlayer.getDice().stream(), blackPlayer.getDice().stream()).collect(Collectors.toList());
        ArrayList<Die> adjacentDice = (ArrayList<Die>) allDice.stream().filter(die::isAdjacent).collect(Collectors.toList());
        return adjacentDice.toArray(Die[]::new);
    }

    /**
     * Overloaded getter for the die at a certain position
     */
    public Die getAt(int x, int y) {
        return board[y][x];
    }

    /**
     * Getter for the die at a certain position
     */
    public Die getAt(String position) {
        int x = Integer.parseInt(position.substring(0, 1));
        int y = Integer.parseInt(position.substring(1));
        return board[y][x];
    }

    /**
     * Setter for the die at a certain position
     */
    public void setAt(String position, Die die) {
        int x = Integer.parseInt(position.substring(0, 1));
        int y = Integer.parseInt(position.substring(1));
        board[y][x] = die;
    }

    /**
     * Returns if there are multiple die stored on the same position
     *
     * @return True if multiple die have the same position, false otherwise
     */
    public boolean containsOverlappingPieces() {
        List<String> white = whitePlayer.getDice().stream().map(Die::getPosition).collect(Collectors.toList());
        List<String> black = blackPlayer.getDice().stream().map(Die::getPosition).collect(Collectors.toList());
        Set<String> allPieces = new HashSet<>();
        allPieces.addAll(white);
        allPieces.addAll(black);
        return allPieces.size() != (white.size() + black.size());
    }

    /**
     * Getter for whitePlayer
     */
    public Players getWhitePlayer() {
        return whitePlayer;
    }

    /**
     * Getter for blackPlayer
     */
    public Players getBlackPlayer() {
        return blackPlayer;
    }

    /**
     * Akin to a toString method for Boards
     */
    public String getStringRepresentation() {
        String boardRepresentation = Arrays.deepToString(board);
        String formattedCorrectly = boardRepresentation.replaceAll("(?<=[]])", "\n");
        String noCommas = formattedCorrectly.replaceAll("\n, ", "\n");
        String noFirstBracket = noCommas.substring(1);
        return noFirstBracket.substring(0, noFirstBracket.length() - 2); // Gets rid of the last bracket
    }

    /**
     * toString method for Boards
     */
    @Override
    public String toString() {
        return whitePlayer.getDice().toString() + "\n" + blackPlayer.getDice();
    }

    /**
     * The representation of a coordinate
     */
    public static class Positions {

        /**
         * The coordinate which this class represents
         */
        private final String coordinate;

        /**
         * Constructor for Positions
         */
        public Positions(String coordinate) {
            this.coordinate = coordinate;
        }

        /**
         * Getter for x
         */
        public int getX() {
            return Integer.parseInt(coordinate.substring(0, 1));
        }

        /**
         * Getter for y
         */
        public int getY() {
            return Integer.parseInt(coordinate.substring(1, 2));
        }

        /**
         * Overriding equals method
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Positions positions = (Positions) o;
            return Objects.equals(coordinate, positions.coordinate);
        }

        /**
         * Overriding toString method
         */
        @Override
        public String toString() {
            return coordinate;
        }
    }
}
