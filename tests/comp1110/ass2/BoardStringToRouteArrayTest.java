package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.Collections;

import static comp1110.ass2.Tile.boardStringToRouteArray;
import static comp1110.ass2.Tile.transToTileArray;
import static org.junit.Assert.assertTrue;

public class BoardStringToRouteArrayTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

// Test the full game example in the GitLab intro.

    @Test
    public void testFullGameExample() {

        // Run the function being tested on the sample board string
        String routes[] = Tile.boardStringToRouteArray(SampleGames.exampleInBook);

        // Initialise useful variables to complete the test
        ArrayList<String> givenRoute = new ArrayList<>();
        String[] routesToTest = new String[routes.length];
        String givenRouteString = "";
        int check = 0;

        // For loop to iterate through the decomposed board string and sort it alphabetically for easy comparison with
        // the sample route strings in SampleGames.java
        for (int n = 0; n < routes.length; n++) {
            String[] tilePlacements = transToTileArray(routes[n]);
            Collections.addAll(givenRoute, tilePlacements);
            Collections.sort(givenRoute, String.CASE_INSENSITIVE_ORDER);

            for (String j : givenRoute) {
                givenRouteString = givenRouteString+j;
            }

            routesToTest[n] = givenRouteString;

            givenRouteString = "";
            givenRoute.clear();
        }

        // Iterate through a grid of route strings to confirm whether the contents match and increment the "check"
        // quantity for each match found
        for (String i: SampleGames.routesExample) {
            CheckForEquivalent:
            for (String j : routesToTest) {
                if (i.equals(j)) {
                    check++;
                    break CheckForEquivalent;
                }

            }
        }
        // Test that the method produced the correct number of matching route strings from the given board string and
        // either confirm success with a tick or print error message
        assertTrue(errorMessage(SampleGames.exampleInBook, SampleGames.routesExample, Tile.boardStringToRouteArray(SampleGames.exampleInBook)), check == SampleGames.routesExample.length);
}


    @Test
    public void testSampleGameRoutes() {

        // As distinct from above, previous array variables must now be initialised as multi-dimensional arrays and
        // simple variables are now initialised as arrays
        String[][] routes = new String[6][];
        String[][] routesToTest = new String[6][];
        ArrayList<String> givenRoute = new ArrayList<>();
        int[] check = new int[6];

        // Iterate the tested method for the various sample games being tested
        for (int i = 0; i < 6; i++) {
            routes[i] = Tile.boardStringToRouteArray(SampleGames.COMPLETED_GAMES[i]);
        }

        // Create a grid of all route arrays for all converted board strings and iterate
        // through to sort each route string alphabetically.
        for (int j = 0; j < 6; j++) {
            String[] Temp = new String[routes[j].length];
            for (int n = 0; n < routes[j].length; n++) {
                String givenRouteString = "";
                String[] tilePlacements = transToTileArray(routes[j][n]);
                Collections.addAll(givenRoute, tilePlacements);
                Collections.sort(givenRoute, String.CASE_INSENSITIVE_ORDER);

                for (String k : givenRoute) {
                    givenRouteString = givenRouteString + k;
                }

                Temp[n] = givenRouteString;
                givenRoute.clear();
            }
            routesToTest[j] = Temp;
        }

        // Check the array of route strings generated by Tile.boardStringToRouteArray for content that matches with the sample game route strings.
        for (int k = 0; k < 6; k++) {
        for (String i: SampleGames.COMPLETED_GAMES_ROUTE_STRINGS[k]) {
            CheckForEquivalent:
            for (String m : routesToTest[k]) {
                if (i.equals(m)) {
                    check[k]++;
                    break CheckForEquivalent;
                }

            }
        }

        }
        // Iterate for each tested board string that routes were correctly formed and confirm success or print
        // an error message
        for (int i = 0; i < SampleGames.COMPLETED_GAMES.length; i++) {
            assertTrue(errorMessage(SampleGames.COMPLETED_GAMES[i], SampleGames.COMPLETED_GAMES_ROUTE_STRINGS[i], Tile.boardStringToRouteArray(SampleGames.COMPLETED_GAMES[i])), (check[i] == SampleGames.COMPLETED_GAMES_ROUTE_STRINGS[i].length));
        }
    }


    /**
     * Construct an error message to communicate whether board strings were correctly decomposed into their constituent routes:
     * @param boardString the string of tile placements comprising the board
     * @param routes the correct decomposition of boardString into its constituent routes
     * @param providedRoutes the routes provided to the test by the method under scrutiny
     * @return an error message detailing whether the routes matched and, if not, what they were supposed to be vs what was rendered
     **/
    private String errorMessage(String boardString, String[] routes, String[] providedRoutes) {
        StringBuilder sb = new StringBuilder();
        sb.append("Sample game with board string "+boardString+"\n is expected to have the following "+routes.length+" routes: ");
        for (int i = 0; i < routes.length; i++) {
            sb.append("\n"+(i+1)+". "+routes[i]);
        }

        sb.append("\nBut Tile.boardStringToRouteArray returned the following "+providedRoutes.length+" routes instead: \n ");

        for (int i = 0; i < providedRoutes.length; i++) {
            sb.append("\n"+(i+1)+". "+providedRoutes[i]);
        }

        sb.append(".");
        return sb.toString();
    }
}
