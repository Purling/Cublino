package comp1140.ass2;


import comp1140.ass2.State.Die;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


import static comp1140.ass2.State.Die.dieToEncoding;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Timeout(value = 1000, unit = MILLISECONDS)
    public class dieToEncodingTest {

    @Test
    public void whitePlayerDice() {

        for (char zero = 'A'; zero < 'W'; zero++){
            for(char one = 'a'; one<'g'; one++){
                for(char two = '1'; two < '7'; two++){
                    String die = ""+zero+one+two;
                    Die dice = new Die(die);

                    assertEquals(die, dieToEncoding(dice), "Die with string \"" + die + " returns the string \"" + die + " which is valid.");

                    }
                }
            }
    }

    @Test
    public void blackPlayerDice() {

        for (char zero = 'a'; zero < 'w'; zero++){
            for(char one = 'a'; one<'g'; one++){
                for(char two = '1'; two < '7'; two++){
                    String die = ""+zero+one+two;
                    Die dice = new Die(die);

                    assertEquals(die, dieToEncoding(dice), "Die with string \"" + die + " returns the string \"" + die + " which is valid.");

                }
            }
        }
    }

    }

