package comp1140.ass2.State;

import java.util.ArrayList;

public class Boards {
    Players whitePlayer;
    Players blackPlayer;

    /**
     * A 2d array to represent the current status of the board
     */
    Die[][] board;

    public Boards(String encodedState) {
        //Assumes the encoded state is valid and well-formed
        board = new Die[7][7];

        whitePlayer = new Players(true);
        blackPlayer = new Players(false);
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

    public Die getAt(int x, int y) {
        return board[y][x];
    }

    /**
     * Check whether or not a given location locates on the board
     * Return true if the given coordinates locate on the board
     * False otherwise
     * @param x
     * @param y
     */
    public boolean isOnBoard(int x, int y){
        return false;
    }
}
