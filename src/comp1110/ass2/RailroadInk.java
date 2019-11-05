package comp1110.ass2;

import com.sun.jdi.IntegerValue;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.lang.reflect.Array;
import java.util.*;

import static comp1110.ass2.Tile.*;

public class RailroadInk {
    /**
     * Authorship: Ren
     * Determine whether a tile placement string is well-formed:
     * - it consists of exactly 5 characters;
     * - the first character represents a die A or B, or a special tile S
     * - the second character indicates which tile or face of the die (0-5 for die A and special tiles, or 0-2 for die B)
     * - the third character represents the placement row A-G
     * - the fourth character represents the placement column 0-6
     * - the fifth character represents the orientation 0-7
     *
     * @param tilePlacementString a candidate tile placement string
     * @return true if the tile placement is well formed
     */
    public static boolean isTilePlacementWellFormed(String tilePlacementString) {
        char[] tilePlacement = tilePlacementString.toCharArray();
        if(tilePlacement.length == 5){
            if(isRightRow(tilePlacement[2]) && isRightColumn(tilePlacement[3])
            && isRightOrientation(tilePlacement[4])){
                switch (tilePlacement[0]) {
                    case 'A':
                        return isTileOfAorS(tilePlacement[1]);
                    case 'S':
                        return isTileOfAorS(tilePlacement[1]);
                    case 'B':
                        return isTileOfB(tilePlacement[1]);
                }
            }

        }
        // FIXME Task 2: determine whether a tile placement is well-formed
        return false;
    }

    /* the character represents the orientation 0-7 */
    private static boolean isRightOrientation(char c){
        return c <= '7' && c >= '0';
    }

    /* the character represents the row A-G */
    private static boolean isRightRow(char c){
        return c <= 'G' && c >= 'A';
    }

    /* the character represents the column 0-6 */
    private static boolean isRightColumn(char c){
        return c <= '6' && c >= '0';
    }

    /* the character indicates which tile or face of the die A or S */
    private static boolean isTileOfAorS(char c){
        return c >= '0' && c <= '5';
    }

    /* the character indicates which tile or face of the die B */
    private static boolean isTileOfB(char c){
        return c >= '0' && c <= '2';
    }

    /**
     * Authorship: Daniel
     * Determine whether a board string is well-formed:
     * - it consists of exactly N five-character tile placements (where N = 1 .. 31);
     * - each piece placement is well-formed
     * - no more than three special tiles are included
     *
     * @param boardString a board string describing the placement of one or more pieces
     * @return true if the board string is well-formed
     */


    public static boolean isBoardStringWellFormed(String boardString) {
        boolean tf = false;
        if (boardString == null || boardString.length() == 0 || boardString.length()%5 != 0 || boardString.length()/5 > 31 || boardString.chars().filter(num -> num == 'S').count() > 3) {
            return false;
        }
        for (int i = 0; i < boardString.length(); i=i+5) {
            if (isTilePlacementWellFormed(boardString.substring(i,i+5))) {
                tf = true;
            } else {
                tf = false;
                break;
            }

        }
        return tf;
        // FIXME Task 3: determine whether a board string is well-formed
    }

    // This method returns the board placement string as a 2-dimensional character array of tile placements
    public static char[][] boardStringToTilePlacement(String boardString) {
        int k = boardString.length() / 5;
        char[][] tilePlacementsOnBoard = new char[k][5];

        for (int i = 0; i < k; i++) {
            for (int j = 0; j <= 4; j++) {
                int l = 5 * i + j;
                tilePlacementsOnBoard[i][j] = boardString.charAt(l);
            }
        }
        return tilePlacementsOnBoard;
    }


    /**
     * Determine whether the provided placements are neighbours connected by at least one validly connecting edge.
     * For example,
     * - areConnectedNeighbours("A3C10", "A3C23") would return true as these tiles are connected by a highway edge;
     * - areConnectedNeighbours("A3C23", "B1B20") would return false as these neighbouring tiles are disconnected;
     * - areConnectedNeighbours("A0B30", "A3B23") would return false as these neighbouring tiles have an
     * invalid connection between highway and railway; and
     * areConnectedNeighbours("A0B30", "A3C23") would return false as these tiles are not neighbours.
     *
     * @return true if the placements are connected neighbours
     */

    public static boolean areConnectedNeighbours(String tilePlacementStringA, String tilePlacementStringB) {
        // FIXME Task 5: determine whether neighbouring placements are connected
        boolean neighbours=false;
        neighbours=Tile.isAdjacent(tilePlacementStringA,tilePlacementStringB);

        int representnumberA = 0;
        int representnumberB = 0;
        representnumberA = Tile.getRepresentnumber2(tilePlacementStringA, representnumberA);
        representnumberB = Tile.getRepresentnumber2(tilePlacementStringB, representnumberB);
        char[] transA=Tile.arraytrans(representnumberA,Tile.PlacementString);
        char[] transB=Tile.arraytrans(representnumberB,Tile.PlacementString);

        transA = Tile.getChars(tilePlacementStringA, transA);
        transB = Tile.getChars(tilePlacementStringB, transB);

        boolean areConnected=false;
        if (tilePlacementStringA.charAt(2) == tilePlacementStringB.charAt(2)){
            int b=tilePlacementStringA.charAt(3)-tilePlacementStringB.charAt(3);
            if (b<0){
                if(transA[1]==transB[3]){
                    areConnected=true;
                    if (transA[1]=='0'){
                        areConnected=false;
                    }
                }
            }else {
                if (transA[3]==transB[1]){
                    areConnected=true;
                    if (transA[3]=='0'){
                        areConnected=false;
                    }
                }
            }
        }
        if (tilePlacementStringA.charAt(3)==tilePlacementStringB.charAt(3)){
            int b=tilePlacementStringA.charAt(2)-tilePlacementStringB.charAt(2);
            if (b<0){
                if (transA[2]==transB[0]){
                    areConnected=true;
                    if (transA[2]=='0'){
                        areConnected=false;
                    }
                }
            }else {
                if (transA[0]==transB[2]){
                    areConnected=true;
                    if (transA[0]=='0'){
                        areConnected=false;
                    }
                }
            }
        }
        boolean bothok=false;
        if (areConnected&&neighbours){
            bothok=true;
        }
        return bothok;
    }



    /**
     * Given a well-formed board string representing an ordered list of placements,
     * determine whether the board string is valid.
     * A board string is valid if each tile placement is legal with respect to all previous tile
     * placements in the string, according to the rules for legal placements:
     * - A tile must be placed such that at least one edge connects to either an exit or a pre-existing route.
     *   Such a connection is called a valid connection.
     * - Tiles may not be placed such that a highway edge connects to a railway edge;
     *   this is referred to as an invalid connection.
     *   Highways and railways may only join at station tiles.
     * - A tile may have one or more edges touching a blank edge of another tile;
     *   this is referred to as disconnected, but the placement is still legal.
     *
     * @param boardString a board string representing some placement sequence
     * @return true if placement sequence is valid
     */

    public static boolean isValidPlacementSequence(String boardString) {
        // FIXME Task 6: determine whether the given placement sequence is valid

        // Transfer the board string into tile placement string array.
        boolean valid = true;
        String[] tilePlacements = transToTileArray(boardString);
        List<String> list = new ArrayList<>();
        for(int i=0;i<tilePlacements.length;i++){
            for(int j=i+1;j<tilePlacements.length;j++){
                if((tilePlacements[i].substring(2,4)).equals(tilePlacements[j].substring(2,4))){
                    return false;
                }
            }
        }
        if(!isBoardStringWellFormed(boardString)){
            return false;
        }
        if (!actualConnectToExit(tilePlacements[0])) {
            return false;
        }

        for ( int i = 0; i < tilePlacements.length; i++ ) {
            String[] adjacentTiles = Tile.findAdjacent(tilePlacements[i],boardString);
            if (adjacentTiles.length == 0) {
                if (Tile.isEdge(tilePlacements[i])) {
                    if(!actualConnectToExit(tilePlacements[i])) {
                        return false;
                    }
                }else{
                    return false;
                }

            }else {
                if(Tile.isEdge(tilePlacements[i])){
                    if(!Tile.validNextToExit(tilePlacements[i])){
                       valid = false;
                }
                    if(Tile.actualConnectToExit(tilePlacements[i])){
                        continue;
                    }

            }
                int connectors = 0;
                for(String val:adjacentTiles) {

                    if (!Tile.canBeNeighbours(tilePlacements[i], val)) {
                        valid = false;
                    }
                    if(areConnectedNeighbours(tilePlacements[i],val)){
                        connectors ++;
                    }
                }
                if(connectors < 1){
                    return false;
                }
            }

        }

        return valid;

    }


    /**
     * Generate a random dice roll as a string of eight characters.
     * Dice A should be rolled three times, dice B should be rolled once.
     * Die A has faces numbered 0-5.
     * Die B has faces numbered 0-2.
     * Each die roll is composed of a character 'A' or 'B' representing the dice,
     * followed by a digit character representing the face.
     *
     * @return a String representing the die roll e.g. A0A4A3B2
     */
    public static String generateDiceRoll() {
        // FIXME Task 7: generate a dice roll
        return "A"+(int)(Math.random()*6)+"A"+(int)(Math.random()*6)+"A"+(int)(Math.random()*6)+"B"+(int)(Math.random()*3);
    }

    /**
     * Given the current state of a game board, output an integer
     * representing the sum of all the following factors
     * that contribute to the player's final score.
     * <p>
     * * Number of exits mapped
     * * Number of centre tiles used
     * * Number of dead ends in the network
     *
     * @param boardString a board string representing a completed game
     * @return integer (positive or negative) for score *not* considering longest rail/highway
     */
    public static int getBasicScore(String boardString) {
        if(boardString.length() == 0){
            return 0;
        }
        String[] routes = boardStringToRouteArray(boardString);

        int basicScore = 0;

        int[] routeExits = new int[routes.length];
        int[] routeScore = new int[routes.length];
        int[] routeErrors = new int[routes.length];
        int[] routeCentres = new int[routes.length];

        for (int i = 0; i < routes.length; i++) {
            routeExits[i] = 0;
            routeScore[i] = 0;
            routeErrors[i] = 0;
            routeCentres[i] = 0;

            String[] tileArray = transToTileArray(routes[i]);
            for (String tile : tileArray) {
                if (actualConnectToExit(tile)) {
                    if (isTileAnOverpass(tile) && !doesOverpassConnectToExit(tile, routes[i])) {
                    } else {
                        routeExits[i]++;
                    }
                }
                if (isTileInCentre(tile)) {
                    routeScore[i]++;
                    routeCentres[i]++;
                }
            }


            if (routeExits[i] >= 2 && routeExits[i] <= 11) {
                routeScore[i] = routeScore[i] + (routeExits[i] - 1)*4;
            } else if (routeExits[i] == 12) {
                routeScore[i] = routeScore[i] + 45;
            }
        }

        for (String tile : transToTileArray(boardString)) {
            basicScore = basicScore - tileUnconnectedPaths(tile, boardString);
            routeErrors[0] = routeErrors[0] + tileUnconnectedPaths(tile, boardString);
        }

        for (int i = 0; i < routes.length; i++) {
            basicScore = basicScore + routeScore[i];
        }



        //FIXME Task 8: compute the basic score
        return basicScore;

    }

    /**
     * Authorship: Ren
     * Given a valid boardString and a dice roll for the round,
     * return a String representing an ordered sequence of valid piece placements for the round.
     * @param boardString a board string representing the current state of the game as at the start of the round
     * @param diceRoll a String representing a dice roll for the round
     * @return a String representing an ordered sequence of valid piece placements for the current round
     * @see RailroadInk#generateDiceRoll()
     */
    public static String generateMove(String boardString, String diceRoll) {
        DiceRoll d = new DiceRoll(diceRoll);
        ArrayList<String> orders = d.getDifferentOrderOfDice();
        ArrayList<String> boards = new ArrayList<>();
        for(String order:orders){
            String newBoard = findValidBoard(boardString,order);
            boards.add(newBoard);
        }
        String bestBoard = bestBoard(boards);
        String result = remove(bestBoard,boardString);
        return result;
        // FIXME Task 10: generate a valid move
    }




    /**
     * Given the current state of a game board, output an integer representing the sum of all the factors contributing
     * to `getBasicScore`, as well as those attributed to:
     * <p>
     * * Longest railroad
     * * Longest highway
     *
     * @param boardString a board string representing a completed game
     * @return integer (positive or negative) for final score (not counting expansion packs)
     */


    public static int getAdvancedScore(String boardString) {
        // Get the basic score of the game
        String[] Paths = boardStringToPathArray(boardString);



       int longestRailway = 0;
        int longestHighway = 0;

        for (String i : Paths) {
           /* System.out.println(longestHighway+"sanitycheck3");
            System.out.println(pathType(i));
            System.out.println(i);
            System.out.println("huh");*/
            if (pathType(i) == 'H') {
                // System.out.println(longestHighway+"sanitycheck4");
                if (i.length()/5 > longestHighway) {
                    // System.out.println(longestHighway+"sanitycheck");
                    longestHighway = i.length()/5;
                }
            } else if (pathType(i) == 'R') {
                if (i.length()/5 > longestRailway) {
                    // System.out.println(longestRailway+"sanitycheck2");
                    longestRailway = i.length()/5;
                }
            } else if (pathType(i) == '0') {
              if (longestHighway == 0) {
                  longestHighway = 1;
                }
              if (longestRailway == 0) {
                  longestRailway = 1;
              }
            }
          //  System.out.println(longestHighway+"Sanitycheck5");
        }

        // System.out.println(longestHighway+"hsize");
        // System.out.println(longestRailway+"rsize");



        // FIXME Task 12: compute the total score including bonus points
        return getBasicScore(boardString)+longestHighway+longestRailway;
    }

    public static void main(String[] args) {
        String boardStringSimple = "";
        String boardStringAdvanced = "";
        int simpleScore = 0;
        int advancedScore = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                String diceRoll = RailroadInk.generateDiceRoll();
                String simpleMove = AI.SimpleAIMove(boardStringSimple, diceRoll);
                boardStringSimple = boardStringSimple+simpleMove;
                String advancedMove = AI.AdvancedAIMove(boardStringAdvanced, diceRoll);
                boardStringAdvanced = boardStringAdvanced+advancedMove;
            }
            simpleScore = simpleScore + getAdvancedScore(boardStringSimple);
            advancedScore = advancedScore + getAdvancedScore(boardStringAdvanced);
        }
        System.out.println(advancedScore+"a");
        System.out.println(simpleScore+"s");
    }

}

