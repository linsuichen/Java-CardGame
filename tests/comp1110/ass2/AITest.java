package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class AITest {

    private static int BASE_ITERATIONS = 10;

    @Rule
    // Authorship: Daniel
    // Generating AI moves takes time, and generating all the AI moves for multiple boards takes more time, so timeout
    // is set generously for this test
    public Timeout globalTimeout = Timeout.millis(30000);


    @Test
    // Test 10 games of the same dicerolls. Sum the advanced scores and simple scores and compare which has done better.
    public void testTenGames() {
        String boardStringSimple = "";
        String boardStringAdvanced = "";
        int simpleScore = 0;
        int advancedScore = 0;
        for (int i = 0; i < BASE_ITERATIONS; i++) {
            for (int j = 0; j < 7; j++) {
                String diceRoll = RailroadInk.generateDiceRoll();
                String simpleMove = AI.SimpleAIMove(boardStringSimple, diceRoll);
                boardStringSimple = boardStringSimple+simpleMove;
                String advancedMove = AI.AdvancedAIMove(boardStringAdvanced, diceRoll);
                boardStringAdvanced = boardStringAdvanced+advancedMove;
            }
            simpleScore = simpleScore + RailroadInk.getAdvancedScore(boardStringSimple);
            advancedScore = advancedScore + RailroadInk.getAdvancedScore(boardStringAdvanced);
        }
        assertTrue("Expected advanced AI to beat simple AI, but advanced AI lost by: " + (simpleScore - advancedScore), simpleScore < advancedScore);

    }


}
