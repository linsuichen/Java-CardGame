package comp1110.ass2;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertTrue;

public class CanBeNeighboursTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(100);


    /* Test if two tile placements are perfectly connected
     * e.g. highway and highway, railway and railway.
     */
    @Test
    public void perfectConnectedTest() {
        testExpected("A3C10", "A3C23", true);
        testExpected("A4A10", "A3B10",true);
        testExpected("A4A10", "A3B13",true);
        testExpected("B0C32", "A4D34",true);
    }

    /* Test if two tile placements are empty connected
     * e.g. Empty can connect with everything
     */
    @Test
    public void emptyConnectedTest(){
        testExpected("A3C23", "B1B20", true);
        testExpected("A2B30", "A0A34",true);
    }


    /* Test if two tile placements cannot be neighbours
     * e.g. highway connects with railway.
     */
    @Test
    public void wrongConnectedTest(){
        testExpected("A0B30", "A3B23", false);
        testExpected("A2G40","S2F40",false);
        testExpected("A2E36", "B0E47",false);
    }

    /* Test if two tile placements cannot be neighbours
     * since they are not adjacent.
     */
    @Test
    public void notAdjacentTest(){
        testExpected("A0B30", "A3C23", false);
        testExpected("A3C10", "A3C43", false);
        testExpected("B2C10", "A3B23", false);
    }

    public void testExpected(String placementA, String placementB, boolean expected) {
        boolean result = Tile.canBeNeighbours(placementA,placementB);
        assertTrue("Tile.canBeNeighbours( " + placementA + " , " + placementB + " ) expected " + expected + " but got " + result, expected==result);
    }


}
