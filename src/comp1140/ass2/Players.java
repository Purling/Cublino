package comp1140.ass2;

public class Players {
    Die[] myDice; //Store the information of the player's dices

    public Die getDice(int i) {
        // get the information of a particular dice from a series of dices
        return myDice[i];
    }
}
