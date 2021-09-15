package comp1140.ass2;

import comp1140.ass2.GameLogic.ContraCublino;
import comp1140.ass2.GameLogic.PurCublino;
import comp1140.ass2.State.Boards;
import comp1140.ass2.State.Die;

import java.util.Arrays;

public class Cublino {

    // Dice orientation
    // Applies for both black and white players
    // a - Top: 1 Forward: 2 Left: 3
    // b - Top: 1 Forward: 3 Left: 5
    // c - Top: 1 Forward: 4 Left: 2
    // d - Top: 1 Forward: 5 Left: 4
    // e - Top: 2 Forward: 1 Left: 4
    // f - Top: 2 Forward: 3 Left: 1
    // g - Top: 2 Forward: 4 Left: 6
    // h - Top: 2 Forward: 6 Left: 3
    // i - Top: 3 Forward: 1 Left: 2
    // j - Top: 3 Forward: 2 Left: 6
    // k - Top: 3 Forward: 5 Left: 1
    // l - Top: 3 Forward: 6 Left: 5
    // m - Top: 4 Forward: 1 Left: 5
    // n - Top: 4 Forward: 2 Left: 1
    // o - Top: 4 Forward: 5 Left: 6
    // p - Top: 4 Forward: 6 Left: 2
    // q - Top: 5 Forward: 1 Left: 3
    // r - Top: 5 Forward: 3 Left: 6
    // s - Top: 5 Forward: 4 Left: 1
    // t - Top: 5 Forward: 6 Left: 4
    // u - Top: 6 Forward: 2 Left: 4
    // v - Top: 6 Forward: 3 Left: 2
    // w - Top: 6 Forward: 4 Left: 5
    // x - Top: 6 Forward: 5 Left: 3

    /**
     * Task 3:
     * Determine whether the input state is well formed or not.
     * Note: you don't have to consider whether the state is valid for this task.
     * A state is well formed if it has the following format:
     * [variant][dice]*
     * where [variant] and [dice] are replaced by the corresponding substrings below. Note that [dice]* means zero or
     * more repetitions of the [dice] substring.
     *
     * 1. [variant] The variant substring is a single character which is either an upper or lower case 'p' or 'c' - The
     * letter encodes which variant the of the rules the game is using and the case represents the turn of the current
     * player.
     *
     * 2. [dice] The dice substring contains three characters. The first character can be either an upper or lower case
     * letter in the range 'a' to 'x'. The letter encodes the orientation of the dice and the case encodes which player
     * the dice belongs two. The next two characters encode the position of the dice in the format [column][row] where
     * the column is one character in the range 'a' to 'g' and the row is one character in the range '1' to '7'.
     *
     * See the "Encoding Game State" section in the README for more details.
     *
     * @param state a string representing a game state
     * @return true if the input state is well formed, otherwise false
     */
    public static Boolean isStateWellFormed(String state) {
        if (state != null && state.length() % 3 == 1) {
            if (state.charAt(0) == 'p' || state.charAt(0) == 'c'
                    || state.charAt(0) == 'P' || state.charAt(0) == 'C') {
                int l = (state.length() - 1) / 3;
                if (l != 0) {
                    int i = 1;
                    while (i < state.length()){
                        String x;
                        if (i+3 == state.length()) {
                            x = state.substring(i);
                        }
                        else {
                            x = state.substring(i, i + 3);
                        }

                        if (x.charAt(0) < 'A' || (x.charAt(0) > 'X' && x.charAt(0) < 'a')
                                || x.charAt(0) > 'x') {
                            return false;
                        }
                        else if (x.charAt(1) > 'g' || x.charAt(1) < 'a') {
                            return false;
                        }
                        else if (x.charAt(2) > '7' || x.charAt(2) < '1') {
                            return false;
                        }
                        i+=3;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Task 4:
     * Determine whether the input state is valid.
     * A game state is valid if it satisfies the conditions below. Note that there are some shared conditions and
     * some conditions which are specific to each variant of the game. For this task you are expected to check states
     * from both variants.
     *
     * [Both Variants]
     * 1. The game state is well formed.
     * 2. No two dice occupy the same position on the board.
     *
     * [Pur]
     * 1. Each player has exactly seven dice.
     * 2. Both players do not have all seven of their dice on the opponent's end of the board (as the game would have
     * already finished before this)
     *
     * [Contra]
     * 1. Each player has no more than seven dice.
     * 2. No more than one player has a dice on the opponent's end of the board.
     *
     * @param state a string representing a game state
     * @return true if the input state is valid, otherwise false
     */
    public static Boolean isStateValid(String state) {

        if(!isStateWellFormed(state) || state.length() == 1) return false;

        Boards board = new Boards();
        board.setWhiteAndBlackPlayer(state);
        PurCublino purCublino = new PurCublino();
        ContraCublino contraCublino = new ContraCublino();

        if(board.containsOverlappingPieces()) return false;

        if(state.toLowerCase().charAt(0) == 'p'){
            return purCublino.isGameValid(board);
        } else {
            return contraCublino.isGameValid(board);
        }
    }

    /**
     * Task 6:
     * Determine whether a state represents a finished Pur game, and if so who the winner is.
     *
     * A game of Cublino Pur is finished once one player has reached the opponent's end of the board with all seven of
     * their dice. Each player then adds the numbers facing upwards on their dice which have reached the opponent's end
     * of the board. The player with the highest total wins.
     *
     * You may assume that all states input into this method will be of the Pur variant and that the state will be
     * valid.
     *
     * @param state a Pur game state
     * @return 1 if player one has won, 2 if player two has won, 3 if the result is a draw, otherwise 0.
     */
    public static int isGameOverPur(String state) {
        if (state.charAt(0) != 'p' && state.charAt(0)!= 'P') return 0;
        else {
            int p1Top = 0;
            int p2Top = 0;
            int p1Bottom = 0;
            int p2Bottom = 0;
            int p1s = 0;
            int p2s = 0;
            int i = 0;
            while (i < 14){
                String s = state.substring(3*i+1, 3*i+4);
                if (s.charAt(0) >= 'A' && s.charAt(0) <= 'Z' && s.charAt(2) == '7'){
                    p1Top++;
                    p1s += (s.charAt(0) - 'A')/4 + 1;
                    i++;
                }
                else if (s.charAt(0) >= 'a' && s.charAt(0) <= 'z' && s.charAt(2) == '1'){
                    p2Top++;
                    p2s += (s.charAt(0) - 'a')/4 + 1;
                    i++;
                }
                else i++;
            }
                if (p1Top == 7 || p2Top == 7 || p1Bottom == 7 || p2Bottom == 7) {
                    if (p1s == p2s) return 3;
                    else if (p1s < p2s) return 2;
                    else if (p1s > p2s) return 1;
                }
                else return 0;
        }
        return 0;
    }

    /**
     * Task 7:
     * Determine whether a single step of a move is valid for a given Pur game.
     * A step is encoded as follows: [position][position]. The [position] substring is a two character string encoding a
     * position on the board. The first position represents the starting position of the dice making the step and the
     * second position represents the ending position of the dice making the step. You may assume that the step strings
     * input into this method are well formed according to the above specification.
     *
     * A step is valid if it satisfies the following conditions:
     * 1. It represents either a tilt or a jump of a dice.
     * 2. The ending position of the step is not occupied.
     * 3. The step moves towards the opponent's end of the board or horizontally (along its current row).
     * 3. If it is a jump step, there is a dice in the position which is jumped over.
     *
     * You may assume that all states input into this method will be of the Pur variant and that the state will be
     * valid.
     *
     * @param state a Pur game state
     * @param step a string representing a single step of a move
     * @return true if the step is valid for the given state, otherwise false
     */
    public static Boolean isValidStepPur(String state, String step) {
        return null; // FIXME Task 7 (D)
    }

    /**
     * Task 8:
     * Determine whether a move (sequence of steps) is valid for a given game.
     * A move is encoded as follows [position]*. The [position] substring encodes a position on the board. This encoding
     * lists a sequence of positions that a dice will have as it makes a move. Note that [position]* means zero or more
     * repetitions of the [position] substring. You may assume that the move strings input into this method are well
     * formed according to the above specification.
     *
     * A move is valid if it satisfies the following conditions:
     * 1. The starting position of the move contains a dice belonging to the player who's turn it is.
     * 2. All steps in the move are valid.
     * 3. The move contains at least one step.
     * 4. Only the first step may be a tipping step.
     * 5. The starting and ending positions of the moved dice are different.
     *
     * You may assume that all states input into this method will be of the Pur variant and that the state will be
     * valid.
     *
     * @param state a Pur game state
     * @param move a string representing a move
     * @return true if the move is valid for the given state, otherwise false
     */
    public static Boolean isValidMovePur(String state, String move) {
        return null; // FIXME Task 8 (P)
    }

    /**
     * Task 9:
     * Given a Pur game state and a move to play, determine the state that results from that move being played.
     * If the move ends the game, the turn should be the player who would have played next had the game not ended. If
     * the move is invalid the game state should remain unchanged.
     *
     * You may assume that all states input into this method will be of the Pur variant and that the state will be
     * valid.
     *
     * @param state a Pur game state
     * @param move a move being played
     * @return the resulting state after the move has been applied
     */
    public static String applyMovePur(String state, String move) {
        if (!isValidMovePur(state, move)) return state;
        else{


        }
        return null; // FIXME Task 9 (P)
    }

    /**
     * Task 11:
     * Given a valid Pur game state, return a valid move.
     * This task imposes an additional constraint that moves returned must not revisit positions previously occupied as
     * part of the move (ie. a move may not contain a jumping move followed by another jumping move back to the previous
     * position).
     *
     * You may assume that all states input into this method will be of the Pur variant and that the state will be
     * valid.
     *
     * @param state a Pur game state
     * @return a valid move for the current game state.
     */
    public static String generateMovePur(String state) {
        return null; // FIXME Task 11 (D)
        // FIXME Task 13 (HD): Implement a "smart" generateMove()
    }

    /**
     * Task 14a:
     * Determine whether a state represents a finished Contra game, and if so who the winner is.
     *
     * A player wins a game of Cublino Contra by reaching the opponent's end of the board with one of their dice.
     *
     * You may assume that all states input into this method will be of the Contra variant and that the state will be
     * valid.
     *
     * @param state a Contra game state
     * @return 1 if player one has won, 2 if player two has won, otherwise 0.
     */
    public static int isGameOverContra(String state) {
        return -1; // FIXME Task 14a (HD)
    }

    /**
     * Task 14b:
     * Given a Contra game state and a move to play, determine the state that results from that move being played.
     * If the move ends the game, the turn should be the player who would have played next had the game not ended. If
     * the move is invalid the game state should remain unchanged. See the README for what constitutes a valid Contra
     * move and the rules for updating the game state.
     *
     * You may assume that all states input into this method will be of the Contra variant and that the state will be
     * valid.
     *
     * @param state a Contra game state
     * @param move a move being played
     * @return the resulting state after the move has been applied
     */
    public static String applyMoveContra(String state, String move) {
        return null; // FIXME Task 14b (HD)
    }

    /**
     * Task 14c:
     * Given a valid Contra game state, return a valid move.
     *
     * You may assume that all states input into this method will be of the Contra variant and that the state will be
     * valid.
     *
     * @param state the Pur game state
     * @return a move for the current game state.
     */
    public static String generateMoveContra(String state) {
        return null; // FIXME Task 14c (HD)
    }

}
