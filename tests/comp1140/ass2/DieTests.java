package comp1140.ass2;


import comp1140.ass2.State.Die;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static comp1140.ass2.State.Die.dieToEncoding;
import static comp1140.ass2.State.Direction.*;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Timeout(value = 1000, unit = MILLISECONDS)
public class DieTests {

    /**
     * Tests the dieToEncoding method for white die
     */
    @Test
    public void whitePlayerDice() {
        for (char zero = 'A'; zero < 'W'; zero++) {
            for (char one = 'a'; one < 'g'; one++) {
                for (char two = '1'; two < '7'; two++) {
                    String die = "" + zero + one + two;
                    Die dice = new Die(die);

                    assertEquals(die, dieToEncoding(dice), "Die with string \"" + die + " returns the string \"" + die + " which is valid.");
                }
            }
        }
    }

    /**
     * Tests the dieToEncoding method for black die
     */
    @Test
    public void blackPlayerDice() {
        for (char zero = 'a'; zero < 'w'; zero++) {
            for (char one = 'a'; one < 'g'; one++) {
                for (char two = '1'; two < '7'; two++) {
                    String die = "" + zero + one + two;
                    Die dice = new Die(die);

                    assertEquals(die, dieToEncoding(dice), "Die with string \"" + die + " returns the string \"" + die + " which is valid.");
                }
            }
        }
    }

    /**
     * Tests if the right direction is returned when method getDirection is called
     */
    @Test
    public void getDirectionTest() {
        for (char zero = 'a'; zero < 'w'; zero++) {
            for (char one = 'a'; one < 'g'; one++) {
                for (char two = '1'; two < '7'; two++) {
                    String die = "" + zero + one + two;
                    Die dice = new Die(die);
                    String toRight = "" + (dice.getX() + 1) + dice.getY();
                    String toLeft = "" + (dice.getX() - 1) + dice.getY();
                    String toUp = "" + dice.getX() + (dice.getY() + 1);
                    String toDown = "" + dice.getX() + (dice.getY() - 1);

                    if (dice.getX() + 1 < 7) assertEquals(RIGHT, dice.getDirection(toRight), "Direction is wrong");
                    if (dice.getX() - 1 > -1) assertEquals(LEFT, dice.getDirection(toLeft), "Direction is wrong");
                    if ((dice.getY() + 1) < 7) assertEquals(UP, dice.getDirection(toUp), "Direction is wrong");
                    if (dice.getY() - 1 > -1) assertEquals(DOWN, dice.getDirection(toDown), "Direction is wrong");
                }
            }
        }

        for (char zero = 'A'; zero < 'W'; zero++) {
            for (char one = 'a'; one < 'g'; one++) {
                for (char two = '1'; two < '7'; two++) {
                    String die = "" + zero + one + two;
                    Die dice = new Die(die);
                    String toRight = "" + (dice.getX() + 1) + dice.getY();
                    String toLeft = "" + (dice.getX() - 1) + dice.getY();
                    String toUp = "" + dice.getX() + (dice.getY() + 1);
                    String toDown = "" + dice.getX() + (dice.getY() - 1);

                    if (dice.getX() + 1 < 7) assertEquals(RIGHT, dice.getDirection(toRight), "Direction is wrong");
                    if (dice.getX() - 1 > -1) assertEquals(LEFT, dice.getDirection(toLeft), "Direction is wrong");
                    if ((dice.getY() + 1) < 7) assertEquals(UP, dice.getDirection(toUp), "Direction is wrong");
                    if (dice.getY() - 1 > -1) assertEquals(DOWN, dice.getDirection(toDown), "Direction is wrong");
                }
            }
        }
    }
}

