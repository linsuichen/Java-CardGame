package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class DiceTest {

    private static int BASE_ITERATIONS = 100;

    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

//check if every time the dice did not rolled three times
    @Test
    public void testFourDice() {
        for (int i = 0; i < BASE_ITERATIONS; i++) {
            String roll = RailroadInk.generateDiceRoll();
            int numRolls = roll.length() / 2;
            assertFalse("Expected a string of four die rolls, but you rolled: " + numRolls, numRolls != 4);
        }
    }
    //check if every time we roll the correct dice
    @Test
    public void testCorrectDice() {
        for (int i = 0; i < BASE_ITERATIONS; i++) {
            String roll = RailroadInk.generateDiceRoll();
            int a = 0;
            int b = 0;
            int x = 0;
            for (int j = 0; j < roll.length(); j += 2) {
                if (roll.charAt(j) == 'A') {
                    a++;
                } else if (roll.charAt(j) == 'B') {
                    b++;
                } else {
                    x++;
                }
            }
            assertFalse("Expected dice A to be rolled 3 times, but you rolled it " + a + " time/s " + roll, a != 3);
            assertFalse("Expected dice B to be rolled once, but you rolled it " + b + " time/s: " + roll, b != 1);
            assertFalse("Expected dice A and B but you rolled a different die" + x + " time/s " + roll, x != 0);
        }

    }
    //check whether the dice roll in the right way
    private char[][] validFace = {{'0', '1', '2', '3', '4', '5'}, {'0', '1', '2'}};

    @Test
    public void testCorrectFace() {
        for (int i = 0; i < BASE_ITERATIONS; i++) {
            String roll = RailroadInk.generateDiceRoll();
            for (int j = 0; j < roll.length(); j += 2) {
                char a = roll.charAt(j);
                char b = roll.charAt(j + 1);
                assertTrue("Invalid die: " + a, a - 'A' >= 0 && a - 'A' <= 1);
                assertTrue("Invalid face for die " + roll.charAt(j) + ": " + b, Arrays.binarySearch(validFace[a - 'A'], b) >= 0);
            }
        }
    }


}
