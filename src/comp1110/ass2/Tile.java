package comp1110.ass2;
import java.lang.reflect.Array;
import java.util.*;

import static comp1110.ass2.RailroadInk.*;

public class Tile {
    /**
     * Authorship: Written by Isaac
     * Enumerate the unrotated connection configurations of each type of tile, letters representing the type of
     * route emanating from each face of a given tile. The tile that it represents is commented next to it.
     *
     * From the the perspective of the viewer of the screen the positions are represented as follows:
     * The 0th entry of each element corresponds to the face of the tile pointing in the 'up' position
     * The 1st entry of each element corresponds to the face of the tile pointing in the 'right' position
     * The 2nd entry of each element corresponds to the face of the tile pointing in the 'down' position
     * The 3rd entry of each element corresponds to the face of the tile pointing in the 'left' position
     *
     * 'R' corresponds to a railway exit from the position of that face of the tile
     * H' corresponds to a highway exit from that face of the tile
     * '0' corresponds to that face of the tile having no exit
     *
     * @return a list of configurations as a multi-dimensional character array of tile configurations
     **/
    static char[][] PlacementString = {
            {'R', '0', '0', 'R'},// A0[0]
            {'R', '0', 'R', '0'},// A1[1]
            {'R', 'R', 'R', '0'},// A2[2]
            {'H', 'H', 'H', '0'},// A3[3]
            {'H', '0', 'H', '0'},// A4[4]
            {'H', '0', '0', 'H'},// A5[5]
            {'H', '0', 'R', '0'},// B0[6]
            {'H', 'R', '0', '0'},// B1[7]
            {'H', 'R', 'H', 'R'},// B2[8]
            {'H', 'H', 'R', 'H'},// S0[9]
            {'H', 'R', 'R', 'R'},// S1[10]
            {'H', 'H', 'H', 'H'},// S2[11]
            {'R', 'R', 'R', 'R'},// S3[12]
            {'H', 'R', 'R', 'H'},// S4[13]
            {'H', 'R', 'H', 'R'},// S5[14]
    };

    static char[] ExitType = {
            'H', 'R', 'H', 'R', 'R', 'H', 'H', 'R', 'R', 'H', 'R', 'H'
    };

    /**
     * Authorship: Written by Ren
     * Determine whether two given tiles, tileA and tileB, are placed adjacent to one another
     * @param tileA the placement string of the first tile to be checked
     * @param tileB the placement string of the second tile to be checked
     * @return true if the tiles are placed adjacent to each other and false if not
     */
    public static boolean isAdjacent(String tileA, String tileB) {
        char columnA, columnB, rowA, rowB;
        rowA = tileA.charAt(2);
        rowB = tileB.charAt(2);
        columnA = tileA.charAt(3);
        columnB = tileB.charAt(3);
        boolean neibours = false;
        if (rowA == rowB) {
            if (columnA == columnB + 1 || columnA == columnB - 1) {
                neibours = true;
            }
        }
        if (columnA == columnB) {
            if (rowA == rowB + 1 || rowA == rowB - 1) {
                neibours = true;
            }
        }
        return neibours;
    }

    /**
     * Authorship: Written by Ren
     * Determine whether the provided placement can connect to Exit
     * @param tile the string of the tile placement being tested
     * @return true if the placements can connect to exit
     */
    public static boolean actualConnectToExit(String tile) {
        int representnumberA = 0;
        representnumberA = getRepresentnumber2(tile, representnumberA);
        char[] trans = arraytrans(representnumberA, PlacementString);
        trans = getChars(tile, trans);
        if (tile.charAt(2) == 'A') {
            if (tile.charAt(3) == '1' || tile.charAt(3) == '5') {
                return trans[0] == 'H';
            }
            if (tile.charAt(3) == '3') {
                return trans[0] == 'R';
            }
        }
        if (tile.charAt(2) == 'B') {
            if (tile.charAt(3) == '0') {
                return trans[3] == 'R';
            }
            if (tile.charAt(3) == '6') {
                return trans[1] == 'R';
            }
        }
        if (tile.charAt(2) == 'D') {
            if (tile.charAt(3) == '0') {
                return trans[3] == 'H';
            }
            if (tile.charAt(3) == '6') {
                return trans[1] == 'H';
            }
        }
        if (tile.charAt(2) == 'F') {
            if (tile.charAt(3) == '0') {
                return trans[3] == 'R';
            }
            if (tile.charAt(3) == '6') {
                return trans[1] == 'R';
            }
        }
        if (tile.charAt(2) == 'G') {
            if (tile.charAt(3) == '1' || tile.charAt(3) == '5') {
                return trans[2] == 'H';
            }
            if (tile.charAt(3) == '3') {
                return trans[2] == 'R';
            }
        }
        return false;
    }

    /**
     * Authorship: Written by Ren
     * Determine whether a tile placement has a valid placement next to a board exit (either as an exit or unconnected but not mismatched)
     * @param tile the string of the tile placement being tested
     * @return boolean value of true if the tile has a valid placement next to an exit and false if not
     **/
    public static boolean validNextToExit(String tile) {
        int representnumberA = 0;
        representnumberA = getRepresentnumber2(tile, representnumberA);
        char[] trans = arraytrans(representnumberA, PlacementString);
        trans = getChars(tile, trans);
        if (tile.charAt(2) == 'A') {
            if (tile.charAt(3) == '1' || tile.charAt(3) == '5') {
                return trans[0] == 'H' || trans[0] == '0';
            }
            if (tile.charAt(3) == '3') {
                return trans[0] == 'R' || trans[0] == '0';
            }
        }
        if (tile.charAt(2) == 'B') {
            if (tile.charAt(3) == '0') {
                return trans[3] == 'R' || trans[3] == '0';
            }
            if (tile.charAt(3) == '6') {
                return trans[1] == 'R' || trans[1] == '0';
            }
        }
        if (tile.charAt(2) == 'D') {
            if (tile.charAt(3) == '0') {
                return trans[3] == 'H' || trans[3] == '0';
            }
            if (tile.charAt(3) == '6') {
                return (trans[1] == 'H' || trans[1] == '0');
            }
        }
        if (tile.charAt(2) == 'F') {
            if (tile.charAt(3) == '0') {
                return trans[3] == 'R' || trans[3] == '0';
            }
            if (tile.charAt(3) == '6') {
                return trans[1] == 'R' || trans[1] == '0';
            }
        }
        if (tile.charAt(2) == 'G') {
            if (tile.charAt(3) == '1' || tile.charAt(3) == '5') {
                return trans[2] == 'H' || trans[2] == '0';
            }
            if (tile.charAt(3) == '3') {
                return trans[0] == 'R' || trans[0] == '0';
            }
        }
        return false;
    }

    /**
     * Authorship: Written by Ren
     * Determine whether a given tile has been placed at an exit to the board
     * @param tile the string of the tile placement being tested
     * @return boolean value of true if the tile has a been placed next to an exit and false if not
     **/
    public static boolean isEdge(String tile) {
        if (tile.charAt(2) == 'A') {
            if (tile.charAt(3) == '1' || tile.charAt(3) == '5' || tile.charAt(3) == '3') {
                return true;
            }
        }
        if (tile.charAt(2) == 'B') {
            if (tile.charAt(3) == '0' || tile.charAt(3) == '6') {
                return true;
            }
        }
        if (tile.charAt(2) == 'D') {
            if (tile.charAt(3) == '0' || tile.charAt(3) == '6') {
                return true;
            }
        }
        if (tile.charAt(2) == 'F') {
            if (tile.charAt(3) == '0' || tile.charAt(3) == '6') {
                return true;
            }
        }
        if (tile.charAt(2) == 'G') {
            if (tile.charAt(3) == '1' || tile.charAt(3) == '5' || tile.charAt(3) == '3') {
                return true;
            }
        }
        return false;
    }

    /**
     * Authorship: Written by Ren
     * Find the tiles that are adjacent to a given tile within a given board string
     * @param tile the string of the tile for which adjacent placements are sought
     * @param boardString the given board string being searched for adjacent tiles
     * @return a string array of tile placements from the given board string that are adjacent to the given tile
     **/
    public static String[] findAdjacent(String tile, String boardString) {
        String[] tilePlacements = transToTileArray(boardString);
        ArrayList<String> adjacentTiles = new ArrayList<>();
        for (String val : tilePlacements) {
            if (isAdjacent(tile, val)) {
                adjacentTiles.add(val);
            }
        }
        String[] adjacents = new String[adjacentTiles.size()];
        for (int i = 0; i < adjacentTiles.size(); i++) {
            adjacents[i] = adjacentTiles.get(i);
        }


        return adjacents;
    }

    /**
     * Authorship: Written by Ren
     * Break up a given board string into an array of tile placements
     * @param boardString the given board string to be decomposed into constituent tile placements
     * @return a string array of tile placements that comprise the board string
     **/
    public static String[] transToTileArray(String boardString) {
        int bLen = boardString.length();
        int placemenLen = bLen / 5;
        String[] tilePlacement = new String[placemenLen];
        int index = 0;
        for (int i = 0; i < bLen; i += 5) {
            tilePlacement[index] = boardString.substring(i, i + 5);
            index++;
        }
        return tilePlacement;
    }

    /**
     * Authorship: Written by Ren
     * Transfer a string of generated dice rolls to an ArrayList of dice rolls
     * @param diceRoll is the given dice roll to be decomposed into constituent dice rolls
     * @return a string array of dice rolls that comprise its parent string
     **/
    public static ArrayList<String> transToDiceArray(String diceRoll) {
        int bLen = diceRoll.length();
        ArrayList<String> tilePlacement = new ArrayList<>();
        for (int i = 0; i < bLen; i += 2) {
            tilePlacement.add(diceRoll.substring(i, i + 2));
        }
        return tilePlacement;
    }

    /**
     * Rotates a tile according to the rotation described in its tile placement strings and
     * obtains its transposed connection configuration
     * @param tilePlacementString is the placement string for the tile to be rotated
     * @param trans is the unrotated connection configuration of the tile
     * @return a string array of dice rolls that comprise its parent string
     **/
    public static char[] getChars(String tilePlacementString, char[] trans) {
        switch (tilePlacementString.charAt(4)) {
            case '0':
                trans = trans;
                break;
            case '1':
                trans = rotate90(trans);
                break;
            case '2':
                trans = rotate180(trans);
                break;
            case '3':
                trans = rotate270(trans);
                break;
            case '4':
                trans = reflectarray(trans);
                break;
            case '5':
                trans = reflectarray(trans);
                trans = rotate90(trans);
                break;
            case '6':
                trans = reflectarray(trans);
                trans = rotate180(trans);
                break;
            case '7':
                trans = reflectarray(trans);
                trans = rotate270(trans);
                break;
        }
        return trans;
    }

    /* Authorship: Written by Isaac
     * Rotate tile placement and return corresponding tile placement array
     */
    public static char[] arraytrans(int representnumber, char[][] Placement) {
        char[] transarray = new char[4];
        for (int i = 0; i < 4; i++) {
            transarray[i] = Placement[representnumber][i];
        }
        return transarray;
    }

    /* Authorship: Written by Isaac
     * Rotate tile placement and return corresponding tile placement array
     */
    public static char[] rotate90(char[] array) {
        char[] r90 = {array[3], array[0], array[1], array[2]};
        return r90;
    }

    /* Authorship: Written by Isaac
     * Rotate tile placement and return corresponding tile placement array
     */
    public static char[] rotate180(char[] array) {
        char[] r180 = {array[2], array[3], array[0], array[1]};
        return r180;
    }

    /* Authorship: Written by Isaac
     * Rotate tile placement and return corresponding tile placement array
     */
    public static char[] rotate270(char[] array) {
        char[] r270 = {array[1], array[2], array[3], array[0]};
        return r270;
    }

    /* Authorship: Written by Isaac
     * Given tile transarray and return corresponding symmetry tile transarray
     */
    public static char[] reflectarray(char[] transarray) {
        char[] reflect = {transarray[0], transarray[3], transarray[2], transarray[1]};
        return reflect;
    }

    // Authorship: Written by Isaac
    public static int getRepresentnumber(String tilePlacementString, int representnumber) {
        switch (tilePlacementString.charAt(0)) {
            case 'A':
                representnumber = 0;
                break;
            case 'B':
                representnumber = 6;
                break;
            case 'S':
                representnumber = 9;
                break;
        }
        return representnumber;
    }

    // Authorship: Written by Isaac
    public static int getRepresentnumber2(String tilePlacementString, int representnumber) {
        representnumber = getRepresentnumber(tilePlacementString, representnumber);
        switch (tilePlacementString.charAt(1)) {
            case '0':
                representnumber = representnumber;
                break;
            case '1':
                representnumber = representnumber + 1;
                break;
            case '2':
                representnumber = representnumber + 2;
                break;
            case '3':
                representnumber = representnumber + 3;
                break;
            case '4':
                representnumber = representnumber + 4;
                break;
            case '5':
                representnumber = representnumber + 5;
                break;
        }
        return representnumber;
    }


    /**
     * Determine whether the provided placements are neighbours connected by at least one validly connecting edge
     * Including empty orientation (which can also be assumed to be valid).
     *
     * Authorship: Written by Ren and Isaac
     * @return true if the placements can be neighbours
     */
    public static boolean canBeNeighbours(String tilePlacementStringA, String tilePlacementStringB) {
        boolean neighbours = false;
        neighbours = Tile.isAdjacent(tilePlacementStringA, tilePlacementStringB);

        int representnumberA = 0;
        int representnumberB = 0;
        representnumberA = getRepresentnumber2(tilePlacementStringA, representnumberA);
        representnumberB = getRepresentnumber2(tilePlacementStringB, representnumberB);
        char[] transA = arraytrans(representnumberA, PlacementString);
        char[] transB = arraytrans(representnumberB, PlacementString);

        transA = getChars(tilePlacementStringA, transA);
        transB = getChars(tilePlacementStringB, transB);

        boolean areConnected = false;
        if (tilePlacementStringA.charAt(2) == tilePlacementStringB.charAt(2)) {
            int b = tilePlacementStringA.charAt(3) - tilePlacementStringB.charAt(3);
            if (b < 0) {
                if (transA[1] == transB[3]) {
                    areConnected = true;
                }
                if (transA[1] == '0' || transB[3] == '0') {
                    areConnected = true;
                }
            } else {
                if (transA[3] == transB[1]) {
                    areConnected = true;
                }
                if (transA[3] == '0' || transB[1] == '0') {
                    areConnected = true;
                }
            }
        }
        if (tilePlacementStringA.charAt(3) == tilePlacementStringB.charAt(3)) {
            int b = tilePlacementStringA.charAt(2) - tilePlacementStringB.charAt(2);
            if (b < 0) {
                if (transA[2] == transB[0]) {
                    areConnected = true;
                }
                if (transA[2] == '0' || transB[0] == '0') {
                    areConnected = true;
                }
            } else {
                if (transA[0] == transB[2]) {
                    areConnected = true;
                }
                if (transA[0] == '0' || transB[2] == '0') {
                    areConnected = true;
                }
                ;
            }
        }
        boolean bothok = false;
        if (areConnected && neighbours) {
            bothok = true;
        }
        return bothok;
    }

    /**
     * Authorship: Daniel
     * Takes a tile placement string and finds out what ID the tile corresponds to when looked up in the
     * character list at the top of the Tile.java file
     * @param tilePlacementString is the placement string for the tile in question
     * @return an integer corresponding to the ID of the tile
     **/
    public static int getTileID(String tilePlacementString) {
        switch (tilePlacementString.charAt(0)) {
            case 'A':
                return (int) tilePlacementString.charAt(1) - 48;
            case 'B':
                return 6 + (int) tilePlacementString.charAt(1) - 48;
            case 'S':
                return 9 + (int) tilePlacementString.charAt(1) - 48;
        }
        return -1;
    }

    /**
     * Authorship: Daniel
     * Takes a tile placement string and finds out where its connections are geographically after rotating
     * according to the 5th character of the placement string
     * @param tilePlacementString is the placement string for the tile in question
     * @return an array of characters corresponding to the tile's connection (e.g. 'R' for railway) at the
     * 0th, 1st, 2nd and 3rd positions as enumerated in the char array at the top of Tile.java
     **/
    public static char[] getTranspose(String tilePlacementString) {
        int tileID = getTileID(tilePlacementString);
        char[] trans = PlacementString[tileID];
        switch (tilePlacementString.charAt(4)) {
            case '0':
                break;
            case '1':
                trans = rotate90(trans);
                break;
            case '2':
                trans = rotate180(trans);
                break;
            case '3':
                trans = rotate270(trans);
                break;
            case '4':
                trans = reflectarray(trans);
                break;
            case '5':
                trans = reflectarray(trans);
                trans = rotate90(trans);
                break;
            case '6':
                trans = reflectarray(trans);
                trans = rotate180(trans);
                break;
            case '7':
                trans = reflectarray(trans);
                trans = rotate270(trans);
                break;
        }
        return trans;
    }

    /**
     * Authorship: Daniel
     * Determines whether a tile has any valid exit connections to the edge of the board that aren't official
     * exits as marked on the board.
     * @param tile is a tile placement string
     * @return the integer number of exits a tile has to the edge of the board that aren't marked exits
     **/
    public static int tileEdgeExits(String tile) {
        int exits = 0;
        char[] transTile = getTranspose(tile);
        if (tile.charAt(2) == 'A') {
            if (tile.charAt(3) == '0') {
                if (tile.charAt(0) == 'S') {
                    exits = exits + 2;
                } else {
                    if (transTile[0] != '0') {
                        exits++;
                    } if (transTile[3] != '0') {
                        exits++;
                    }
                }
            } else if (tile.charAt(3) == '2') {
                if (transTile[0] != '0') {
                    exits++;
                }
            } else if (tile.charAt(3) == '4') {
                if (transTile[0] != '0') {
                    exits++;
                }
            } else if (tile.charAt(3) == '6') {
                if (tile.charAt(0) == 'S') {
                    exits = exits + 2;
                } else {
                    if (transTile[0] != '0') {
                        exits++;
                    } if (transTile[1] != '0') {
                        exits++;
                    }
                }
            }

        } else if (tile.charAt(2) == 'C') {
            if (tile.charAt(3) == '0') {
                if (transTile[3] != '0') {
                    exits++;
                }
            } else if (tile.charAt(3) == '6') {
                if (transTile[1] != '0') {
                    exits++;
                }

            }
        } else if (tile.charAt(2) == 'E') {
            if (tile.charAt(3) == '0') {
                if (transTile[3] != '0') {
                    exits++;
                }
            } else if (tile.charAt(3) == '6') {
                if (transTile[1] != '0') {
                    exits++;
                }
            }
        } else if (tile.charAt(2) == 'G') {
            if (tile.charAt(3) == '0') {
                if (tile.charAt(0) == 'S') {
                    exits = exits + 2;
                } else {
                    if (transTile[2] != '0') {
                        exits++;
                    } if (transTile[3] != '0') {
                        exits++;
                    }
                }
            } else if (tile.charAt(3) == '2') {
                if (transTile[2] != '0') {
                    exits++;
                }
            } else if (tile.charAt(3) == '4') {
                if (transTile[2] != '0') {
                    exits++;
                }
            } else if (tile.charAt(3) == '6') {
                if (tile.charAt(0) == 'S') {
                    exits = exits + 2;
                } else {
                    if (transTile[1] != '0') {
                        exits++;
                    } if (transTile[2] != '0') {
                        exits++;
                    }
                }
            }

        }
        return exits;
    }

    // Get the position that there is already a tile.
    public static ArrayList<String> getPositions(String boardString){
        String[] tilePlacements = transToTileArray(boardString);
        ArrayList<String> positions = new ArrayList<>();
        for (String tile : tilePlacements) {
            String position = tile.substring(2,4);
            positions.add(position);
        }
        return positions;
    }

    /**
     * Authorship: Daniel
     * Determines whether an overpass tile in a board string has a valid connection to an exit from a given route
     * @param tile is the tile placement string of the overpass
     * @param routeString is the placement string of the route the overpass tile is connected to
     * @return a boolean value whether the overpass is legitimately connected to the exit from the given route
     **/
    public static boolean doesOverpassConnectToExit (String tile, String routeString) {
        String[] overpassAdjacents = findAdjacent(tile, routeString);
        char[] overpassConnections = getTranspose(tile);
        char overpassConnectionType='0';
        int yes = 0;
        int no = 0;

        switch (tile.charAt(2)) {
            case 'A':
                if (tile.charAt(3) == '1' || tile.charAt(3) == '5') {
                    overpassConnectionType = 'H';
                } else {
                    overpassConnectionType = 'R';
                }
                break;
            case 'B':
                overpassConnectionType = 'R';
                break;
            case 'D':
                overpassConnectionType = 'H';
                break;
            case 'E':
                overpassConnectionType = 'R';
                break;
            case 'G':
                if (tile.charAt(3) == '1' || tile.charAt(3) == '5') {
                    overpassConnectionType = 'H';
                } else {
                    overpassConnectionType = 'R';
                }

        }
        for (String i : overpassAdjacents) {
            if (connectionType(tile, i) == overpassConnectionType) {
                yes++;
            } else {
                no++;
            }
        }

        if (yes > no) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Authorship: Daniel
     * Determines how many unconnected paths (errors) a tile has in a given boardString
     * @param tile is the tile placement string of the overpass
     * @param boardString is the placement string of the entire board
     * @return an integer value of the number of unconnected paths a tile has from its placement
     **/

    public static int tileUnconnectedPaths(String tile, String boardString) {
        int unconnectedPaths = 0;
        switch (getTileID(tile)) {
            case 0:
                unconnectedPaths = 2;
                break;
            case 1:
                unconnectedPaths = 2;
                break;
            case 2:
                unconnectedPaths = 3;
                break;
            case 3:
                unconnectedPaths = 3;
                break;
            case 4:
                unconnectedPaths = 2;
                break;
            case 5:
                unconnectedPaths = 2;
                break;
            case 6:
                unconnectedPaths = 2;
                break;
            case 7:
                unconnectedPaths = 2;
                break;
            case 8:
                unconnectedPaths = 4;
                break;
            case 9:
                unconnectedPaths = 4;
                break;
            case 10:
                unconnectedPaths = 4;
                break;
            case 11:
                unconnectedPaths = 4;
                break;
            case 12:
                unconnectedPaths = 4;
                break;
            case 13:
                unconnectedPaths = 4;
                break;
            case 14:
                unconnectedPaths = 4;
                break;
        }

        unconnectedPaths = unconnectedPaths - tileEdgeExits(tile);

        String[] adjacents = findAdjacent(tile, boardString);

        for (String i : adjacents) {
            if (areConnectedNeighbours(i, tile)) {
                unconnectedPaths--;
            }
        }

        if (actualConnectToExit(tile)) {
            unconnectedPaths--;
        }
        return unconnectedPaths;
    }

    /**
     * Authorship: Daniel
     * Determines whether a given tile is placed in the centre 9 squares of the board
     * @param tile is the tile placement string
     * @return a boolean value indicating whether the tile is in the centre or not
     **/
    public static boolean isTileInCentre(String tile) {
        return (tile.charAt(2) >= 'C' &&  tile.charAt(2) <= 'E') && (tile.charAt(3) >= '2' && tile.charAt(3) <= '4');
    }

    /**
     * Authorship: Daniel
     * Determines whether a given tile is an overpass or not
     * @param tile is the tile placement string
     * @return a boolean value indicating whether the tile is an overpass or not
     **/
    public static boolean isTileAnOverpass(String tile) {
        if ((tile.charAt(0) == 'B' && tile.charAt(1) == '2')) {
            return true;
        }
        return false;
    }

    /**
     * Authorship: Daniel
     * Determines the type of connection (railway or highway) between two given tiles
     * @param tileA is the first tile placement string to be tested
     * @param tileB is the second tile placement string to be tested
     * @return a character value denoting the type of connection
     **/
    public static char connectionType(String tileA, String tileB) {
        char[] tile1 = getTranspose(tileA);

        if (areConnectedNeighbours(tileA, tileB)) {
            if (tileA.charAt(2) == tileB.charAt(2)) {
                if (tileA.charAt(3) < tileB.charAt(3)) {
                    return tile1[1];
                } else {
                    return tile1[3];
                }
            } else {
                if (tileA.charAt(2) < tileB.charAt(2)) {
                    return tile1[2];
                } else {
                    return tile1[0];
                }
            }

        }
        return 0;
    }

    /**
     * Authorship: Daniel
     * Determines whether a tile connects to a route at an overpass
     * @param tileA is the tile placement string to be tested
     * @param overpass is the overpass tile placement to which tileA connects
     * @param boardString is the route string to which the overpass belongs and tileA wishes to connect to
     * @return a boolean value denoting whether tileA connects to the route
     **/
    public static boolean doesOverpassConnectRoute(String tileA, String overpass, String boardString) {
        String[] overpassAdjacents = findAdjacent(overpass, boardString);


        if (overpassAdjacents.length == 0) {
            return true;
        } else if (tileA.charAt(2) == overpass.charAt(2)) {
            for (String i : overpassAdjacents) {
                if (tileA.charAt(2) == i.charAt(2)) {
                    return true;
                }
            }
        } else if (tileA.charAt(3) == overpass.charAt(3)) {
            for (String i : overpassAdjacents) {
                if (tileA.charAt(3) == i.charAt(3)) {
                    return true;
                }
            }
        } else {
            return false;

        }
        return false;
    }


    /**
     * Authorship: Daniel
     * Decompose a given board string into constituent route strings.]
     * @param boardString is the string of tile placements comprising the board
     * @return an array of route strings comprising the full board
     */
    public static String[] boardStringToRouteArray(String boardString) {

        // initialise variables that will at different point hold various tile placements
        // for different purposes that will be enumerated as they are called.
        String[] tilePlacements = transToTileArray(boardString);
        ArrayList<String> remainingTilePlacements = new ArrayList<>();
        ArrayList<String> overpassTilesArrayList = new ArrayList<>();
        ArrayList<String> unallocatedOverpassTiles = new ArrayList<>();
        ArrayList<String> lastCheckOverPassTiles = new ArrayList<>();
        ArrayList<String> routeTransferArray =  new ArrayList<>();
        String overpassBoardString = "";

        // Determine which tiles in the board string are overpasses and assign them to an array list,
        // and put the non-overpass tiles in a separate list
        for (int i = 0; i < tilePlacements.length; i++) {
            if (isTileAnOverpass(tilePlacements[i])) {
                overpassTilesArrayList.add(tilePlacements[i]);
            } else {
                remainingTilePlacements.add(tilePlacements[i]);
            }
        }

        // initialise a list storing overpass tiles that aren't yet allocated to a given route
        String[] overpassTiles = new String[overpassTilesArrayList.size()];
        for (int i = 0; i< overpassTilesArrayList.size(); i++) {
            overpassTiles[i] = overpassTilesArrayList.get(i);
        }
        Collections.addAll(unallocatedOverpassTiles, overpassTiles);


        // Initialise a variable which keeps track of the current route being iterated
        int n = 0;

        // Intialise the route array, to which tiles are written as they are confirmed
        // to belong to a given route
        ArrayList<ArrayList<String>> routeArray = new ArrayList<>();
        ArrayList<String> Init = new ArrayList<>();
        Init.add(remainingTilePlacements.get(0));
        routeArray.add(Init);
        remainingTilePlacements.remove(0);


        // remainingTilePlacements is a list of tile placements that have not yet been allocated to a route.
        // Once this field is empty all tiles have been allocated to their correct routes and the task is
        // more or less complete.
        while (!remainingTilePlacements.isEmpty()) {
            // Break statement to which the loop exits once the array being searched finds a hit and modifies itself
            ArrayListModification:

            // This 'for' loop searches each tile placement in the current element of the route array for connected tile placements.
            for (int i = 0; i < routeArray.get(n).size(); i++) {
                for (String b : remainingTilePlacements) {
                    overpassBoardString = "";
                    if (areConnectedNeighbours(routeArray.get(n).get(i), b)) {
                        // Regular tile placement connections to overpass tiles mustt be handled differently.
                        // Even though the overpass may be connected to the tile being tested, it is not necessarily
                        // a part of the given route. This if loop tests whether it is connected
                        if (isTileAnOverpass(routeArray.get(n).get(i))) {
                            for (String x : routeArray.get(n)) {
                                overpassBoardString = overpassBoardString+x;
                            }

                            if (doesOverpassConnectRoute(b, routeArray.get(n).get(i), overpassBoardString)) {
                                routeArray.get(n).add(b);
                                remainingTilePlacements.remove(b);
                                routeTransferArray.add(b);
                                break ArrayListModification;
                            }
                            // If the tile is not an overpass, no further checking is necessary and the tile should be added to the route array
                            // The route transfer array has the tile added to it to signify that a tile has been found
                        } else {
                            routeArray.get(n).add(b);
                            remainingTilePlacements.remove(b);
                            routeTransferArray.add(b);
                            break ArrayListModification;
                        }

                    }
                }
            }

            // If the route transfer array is empty then that means no more regular tiles are able to be allocated
            // to the current route. If this is the case, we will now check if any unallocated overpasss tiles can
            // be added to the route.

            if (routeTransferArray.isEmpty()) {
                if (!unallocatedOverpassTiles.isEmpty()) {
                    // Break point if a tile is found.
                    OneOverpassTile:

                    //Initialise a grid of unallocated overpass tiles and route string array elements
                    for (int j = 0; j < unallocatedOverpassTiles.size(); j++) {
                        for (int i = 0; i < routeArray.get(n).size(); i++) {
                            // check if the given tile and given overpass are connected
                            if (areConnectedNeighbours(routeArray.get(n).get(i), unallocatedOverpassTiles.get(j))) {
                                // check if the -other- tile is an overpass (i.e. an overpass connecting to another overpass)
                                if (isTileAnOverpass(routeArray.get(n).get(i))) {
                                    // Exactly as above, test if the overpass tiles form a route with each other
                                    for (String x : routeArray.get(n)) {
                                        overpassBoardString = overpassBoardString + x;
                                    }

                                    if (doesOverpassConnectRoute(routeArray.get(n).get(i), unallocatedOverpassTiles.get(j), overpassBoardString)) {
                                        routeArray.get(n).add(unallocatedOverpassTiles.get(j));
                                        unallocatedOverpassTiles.remove(unallocatedOverpassTiles.get(j));
                                        routeTransferArray.add("a");
                                        break OneOverpassTile;
                                    }
                                    // check if a regular tile and an unallocated overpass tile are connected to the same route
                                } else {
                                    routeArray.get(n).add(unallocatedOverpassTiles.get(j));
                                    routeTransferArray.add("a");
                                    unallocatedOverpassTiles.remove(unallocatedOverpassTiles.get(j));
                                    break OneOverpassTile;
                                }

                            }
                        }

                    }
                }

                // If we get to this point there are no more tiles to be allocated; either regular type or overpass type
                // We now increment our route counter to the next element in the route array and populate it with one tile
                // from the remaining tile placements (each tile remaining in the array must be part of a new route).
                if (routeTransferArray.isEmpty()) {
                    n++;
                    ArrayList<String> Init2 = new ArrayList<>();
                    Init2.add(remainingTilePlacements.get(0));
                    remainingTilePlacements.remove(0);
                    routeArray.add(n, Init2);
                    unallocatedOverpassTiles.clear();
                    Collections.addAll(unallocatedOverpassTiles, overpassTiles);

                }
                // If we get to this point, all regular tiles have been allocated but there still might be overpass tiles
                // to allocate to the final route, and this loop adds those tiles.
                if (remainingTilePlacements.isEmpty()) {
                    for (String i : unallocatedOverpassTiles) {
                        for (String j : routeArray.get(n)) {
                            overpassBoardString = "";
                            for (String x : routeArray.get(n)) {
                                overpassBoardString = overpassBoardString+x;
                            }
                            if (areConnectedNeighbours(i, j) && doesOverpassConnectRoute(j, i, overpassBoardString)) {
                                lastCheckOverPassTiles.add(i);
                            }
                        }
                    }
                    // Add any found overpass board tiles to the route
                    if (!lastCheckOverPassTiles.isEmpty()) {
                        for (String i : lastCheckOverPassTiles) {
                            routeArray.get(n).add(i);
                        }
                    }
                }
            }

            // Clear the route transfer array for this pass of the while loop
            routeTransferArray.clear();
        }

        // Transfer the routes from an ArrayList to a string array
        String[] routes = new String[routeArray.size()];
        for (int m = 0; m < routeArray.size(); m++) {
            routes[m] = "";
            for (int j = 0; j < routeArray.get(m).size(); j++) {
                routes[m] = routes[m]+routeArray.get(m).get(j);
            }
        }

        return routes;
    }

    /**
     * Authorship: Daniel
     * Decompose a given board string into a string array of all its unique paths
     * @param boardString is the string of tile placements comprising the board
     * @return an array of path strings which are all the longest possible unique paths
     * in a board string. Uses a depth-first search algorithm with some qualifying conditions
     * based on the connection rules of RailroadInk
     */
  public static String[] boardStringToPathArray(String boardString) {
        // Transfer the board string to an array of route strings for easier analysis
        String[] routes = boardStringToRouteArray(boardString);

        ArrayList<String> finishedPaths = new ArrayList<>();


        // Spool up a grid to populate the path array with every highway and railway path in each route.
        for (int i = 0; i < routes.length; i++) {
            ArrayList<ArrayList<String>> iteratedIncompletePaths = new ArrayList<>();
            ArrayList<String> freeEnds = new ArrayList<>();
            String[] tilePlacements = transToTileArray(routes[i]);
            // Make a list of all the free ends where the algorithm can begin its search for paths
            for (String j : tilePlacements) {
                if (actualConnectToExit(j) || tileUnconnectedPaths(j, routes[i]) > 0) {
                    if (!isTileAnOverpass(j)) {
                        freeEnds.add(j);
                    } else {
                        if (tileUnconnectedPaths(j, routes[i])%2 == 1) {
                            freeEnds.add(j);
                        }
                    }
                }
            }

            // Search every free end for paths
            for (String p : freeEnds) {

                // Spool an ArrayList to keep track of the incompletePaths that have not been searched
                // as yet for their next node. Automatically populate the first item in this list with
                // the next 'free end' as a 1-tile path.
                ArrayList<ArrayList<String>> incompletePaths = new ArrayList<>();
                ArrayList<String> Init = new ArrayList<>();
                Init.add(p);
                incompletePaths.add(Init);

                // Keep iterating all of the paths associated with this free end only while there remain
                // incomplete paths to search
                while (!incompletePaths.isEmpty()) {

                    // Find the next nodes in the latest incomplete path for the algorithm to inspect
                    String[] tileAdjacents = findAdjacent(incompletePaths.get(0).get(0), routes[i]);

                    // Check if the current incomplete path is 1 tile in size only, as special connection rules apply
                    if (incompletePaths.get(0).size() <= 1) {
                        int y = 0;
                        String currentIncompletePath = incompletePaths.get(0).get(0);

                        // Count the number of adjacent tiles which are actually connected to the 1-tile route
                        for (String j : tileAdjacents) {
                            if (areConnectedNeighbours(j, currentIncompletePath)) {
                                y++;
                            }
                        }

                        // If there are no connected, adjacent tiles, then it is a 1 tile route and shall be added to the finished paths array
                        if (y == 0) {
                            iteratedIncompletePaths.add(incompletePaths.get(0));
                            finishedPaths.add(incompletePaths.get(0).get(0));
                            incompletePaths.remove(0);

                        // Otherwise, these adjacent tiles are connected as the next nodes in the depth first search
                        } else  {
                            y = 0;

                            for (String j : tileAdjacents) {
                                if (areConnectedNeighbours(j, currentIncompletePath)) {
                                    if (y == 0) {
                                        incompletePaths.get(0).add(0, j);
                                        y++;
                                    } else {
                                        ArrayList<String> transferArray = new ArrayList<>();
                                        transferArray.add(currentIncompletePath);
                                        transferArray.add(0, j);
                                        if (!iteratedIncompletePaths.contains(transferArray) && !incompletePaths.contains(transferArray)) {
                                            incompletePaths.add(transferArray);
                                        }
                                    }
                                }
                            }
                        }
                        // Begin handling of paths greater than 1 tile in size
                    } else {
                        // Determine the connected adjacent tiles
                        int size = tileAdjacents.length - 1;

                        String[] oldBranchAdjacents = new String[size];

                        int y = 0;
                        for (int j = 0; j < tileAdjacents.length; j++) {
                            if (!tileAdjacents[j].equals(incompletePaths.get(0).get(1))) {
                                oldBranchAdjacents[y] = tileAdjacents[j];
                                y++;
                            }
                        }

                        for (String j : oldBranchAdjacents) {
                            if (!areConnectedNeighbours(j, incompletePaths.get(0).get(0))) {
                                size--;
                            }
                        }

                        String[] branchAdjacents = new String[size];
                        y = 0;
                        for (int j = 0; j < oldBranchAdjacents.length; j++) {
                            if (areConnectedNeighbours(oldBranchAdjacents[j], incompletePaths.get(0).get(0))) {
                                branchAdjacents[y] = oldBranchAdjacents[j];
                                y++;
                            }
                        }

                        // If there are no adjacent connected tiles then we have reached a finished path
                        if (branchAdjacents.length == 0) {
                            ArrayList<String> transferArray = incompletePaths.get(0);
                            ArrayList<String> incompleteTransferArray = new ArrayList<>();
                            Collections.sort(transferArray, String.CASE_INSENSITIVE_ORDER);
                            String transferString = "";
                            for (String j : transferArray) {
                                transferString = transferString + j;
                                incompleteTransferArray.add(j);
                            }
                            // Check that this is a unqiue path. If it is, add it to finished paths, if not, put it in the bin.
                            if (!finishedPaths.contains(transferString)) {
                                finishedPaths.add(transferString);
                            }

                            // Add the path to a list of checked incomplete paths
                            iteratedIncompletePaths.add(incompleteTransferArray);

                            // Remove the path from the depth-first search so that the next path cna be checked.
                            incompletePaths.remove(0);
                        // If there are adjacent connected tiles, we must add them to the depth-first search list
                        } else {
                            ArrayList<String> currentIncompletePath = new ArrayList<>();

                            String currentIncompletePathString = "";
                            // Make a string of the current incomplete path being searched
                            for (String j : incompletePaths.get(0)) {
                                currentIncompletePath.add(j);
                                currentIncompletePathString = currentIncompletePathString+j;
                            }

                            String currentBranchTile = incompletePaths.get(0).get(0);
                            String previousBranchTile = incompletePaths.get(0).get(1);

                            iteratedIncompletePaths.add(currentIncompletePath);
                            incompletePaths.remove(0);

                            // Check all the adjacent connected tiles for connection types
                            for (String j : branchAdjacents) {
                                // If the connection type of the adjacent tile is equal to the previous connection type
                                // that was last recorded for the path, then we can just add the tile to the current path
                                // and keep searching for the next node.
                                if (connectionType(j, currentBranchTile) == connectionType(currentBranchTile, previousBranchTile)) {
                                    ArrayList<String> transferArray = new ArrayList<>();
                                    for (String z : currentIncompletePath) {
                                        transferArray.add(z);
                                    }

                                    // We have to check that the path isn't simply connecting back to itself which would
                                    // no longer be a unique path. If it does, we just move on and this path is discarded.
                                    if (!transferArray.contains(j)) {
                                        transferArray.add(0, j);
                                        currentIncompletePathString = currentIncompletePathString+j;
                                            if (!iteratedIncompletePaths.contains(transferArray) && !incompletePaths.contains(transferArray)) {
                                                incompletePaths.add(0, transferArray);
                                            }
                                    }
                                    // If the connection type of the adjacent connected tile is different to the previous tile
                                    // in the path, then we must treat it very differently
                                } else {
                                    ArrayList<String> pathTransferArray = new ArrayList<>();
                                    for (String z : currentIncompletePath) {
                                        pathTransferArray.add(z);
                                    }

                                    Collections.sort(pathTransferArray, String.CASE_INSENSITIVE_ORDER);
                                    String transferString = "";
                                    for (String z : pathTransferArray) {
                                        transferString = transferString + z;
                                    }
                                    // Check that the previous path is a unique path. If so, it's reached a deadend
                                    // (a new connection type means the path could have reached the end of the line)
                                    // this path is then added as a finished path. This can give some false finished paths
                                    // which are taken care of later.
                                    if (!finishedPaths.contains(transferString)) {
                                        finishedPaths.add(transferString);
                                    }

                                    // Now add the new tile to the incomplete path list as its own first tile to be searched.
                                    String z = currentIncompletePath.get(0);
                                    ArrayList<String> transferArray = new ArrayList<>();
                                    transferArray.add(z);


                                    ArrayList<String> transferArray2 = new ArrayList<>();
                                    transferArray2.add(currentBranchTile);
                                    // Make sure this tile is not already in the list currently being checked and that it
                                    // hasn't already been checked
                                        if (!iteratedIncompletePaths.contains(transferArray2) && !incompletePaths.contains(transferArray2)) {
                                            incompletePaths.add(0, transferArray2);
                                        }
                                }
                            }

                        }
                    }
                }
            }
        }


        // Some paths are ended early above where a different connection type is found. That path could still have been
        // connected to a different adjacent tile that had the same connection. This loop checks whether any smaller unique paths
        // are wholly contained within larger unique paths. If they are, then the smaller ones are found with this loop.
        ArrayList<String> garbageRemoval = new ArrayList<>();
        for (int i = 0; i < finishedPaths.size(); i++) {
            String[] pathTiles = transToTileArray(finishedPaths.get(i));
            for (int j = 0; j < finishedPaths.size(); j++) {
                if (i != j) {
                    int l = 0;
                    for (String k : pathTiles) {
                        if (finishedPaths.get(j).contains(k)) {
                            l++;
                        }
                    }
                    if (l == pathTiles.length) {
                        garbageRemoval.add(finishedPaths.get(i));
                    }
                }
            }
        }

        // Remove all the extraneous, non-unique smaller paths
        for (String i : garbageRemoval) {
            finishedPaths.remove(i);
        }

        // Populate a string array with all the unique paths of the board string
        String[] uniquePaths = new String[finishedPaths.size()];
        for (int i = 0; i < finishedPaths.size(); i++) {
            uniquePaths[i] = finishedPaths.get(i);
        }

        return uniquePaths;
    }

    public static char pathType (String pathString) {
        if (pathString.length() > 5) {
            String b = "";
            String[] tilePlacements = transToTileArray(pathString);
            for (String i : tilePlacements) {
                if (areConnectedNeighbours(i,tilePlacements[0])) {
                    b = i;
                }
            }
            return connectionType(tilePlacements[0], b);
        } else if (pathString.length() == 5) {
            String[] tilePlacements = transToTileArray(pathString);
            switch(getTileID(tilePlacements[0])) {
                case 0:
                    return 'R';
                case 1:
                    return 'R';
                case 2:
                    return 'R';
                case 3:
                    return 'H';
                case 4:
                    return 'H';
                case 5:
                    return 'H';
                case 6:
                    return '0';
                case 7:
                    return '0';
                case 8:
                    return '0';
                case 9:
                    return '0';
                case 10:
                    return '0';
                case 11:
                    return 'H';
                case 12:
                    return 'R';
                case 13:
                    return '0';
                case 14:
                    return '0';
            }
        }
        return '0';
    }

    /**
     * Authorship: Daniel
     * NOTE: This method is not used but was intended to be used as part of an advanced AI that searched
     * for the longest path that could accept placements and added to it. Time ran out, but it is included
     * here as evidence of work done towards a more advajnced AI than that found in the game.
     * Find all of the different paths that currently exist in the board string and order them by size
     * @param boardString is the current board string
     * @return an ArrayList with all the paths in the board string ordered from largest to smallest
     */
    public static ArrayList<ArrayList<String>> sizeOrderedPaths (String boardString) {
        String[] Paths = boardStringToPathArray(boardString);
        ArrayList<String> Highways = new ArrayList<>();
        ArrayList<String> Railways = new ArrayList<>();

        for (String i : Paths) {
            if (pathType(i) == 'H') {
                Highways.add(i);
            } else if (pathType(i) == 'R') {
                Railways.add(i);
            } else if (pathType(i) == '0') {
                Highways.add(i);
                Railways.add(i);
            }
        }

        int a = Highways.size();

        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {
                if (Highways.get(i).length() >= Highways.get(j).length()) {
                    String s = Highways.get(i);
                    Highways.remove(i);
                    Highways.add(j, s);
                }
            }
        }

        a = Railways.size();

        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {
                if (Railways.get(i).length() >= Railways.get(j).length()) {
                    String s = Railways.get(i);
                    Railways.remove(i);
                    Railways.add(j, s);
                }
            }
        }

        ArrayList<ArrayList<String>> sizeOrderedPaths = new ArrayList<>();
        sizeOrderedPaths.add(Highways);
        sizeOrderedPaths.add(Railways);

        return sizeOrderedPaths;
    }


    /**
     * Authorship: Written by Ren
     * Find the boardString that will get the highest score
     * @param possibleBoards is the array of all possible current boardString
     * @return a boardString that will get the highest score
     */
    public static String bestBoard(ArrayList<String> possibleBoards){
        int currentScore = 0;
        int index = 0;
        for(String board: possibleBoards){
            if(currentScore <= getBasicScore(board)){
                currentScore = getBasicScore(board);
                index = possibleBoards.indexOf(board);
            }
        }
        return possibleBoards.get(index);

    }



    /*
     * Authorship: Written by Ren
     * Remove the corresponding substring from a given initial string
     */
    public static String remove(String initial,String sub){
        int index = initial.indexOf(sub);
        if (initial.length()==sub.length() || initial.length()==0){
            return "";
        }
        if(index == 0){
            return initial.substring(sub.length());
        }
        if (index == initial.length()-sub.length()){
            return initial.substring(0,index);
        }
        if (index == -1){
            return initial;
        }
        return initial.substring(0,index)+initial.substring(index+sub.length());

    }



    /*
     * Authorship: Written by Ren
     * Find a valid board according to the given diceRoll using recursive call.
     */
    public static String findValidBoard(String boardString, String diceRoll){
        if(diceRoll.equals("")){
            return boardString;
        }
        ArrayList<String> diceArray = transToDiceArray(diceRoll);
        ArrayList<String> validTiles = new ArrayList<>();
        ArrayList<String> position = blankSpace(boardString);
        for(String dice: diceArray){
            for(String pos:position){
                for(char c = '0' ; c <= '7'; ++c){
                    String temp = dice + pos + c;
                    if(isValidPlacementSequence(boardString+temp)){
                        validTiles.add(temp+boardString);
                        boardString = boardString+temp;
                        return findValidBoard(boardString,remove(diceRoll,dice));
                    }
                }
            }
        }
        return boardString;
    }


    public static ArrayList<String> generateOneMove(String boardString,String dice){
        ArrayList<String> validTiles = new ArrayList<>();
        ArrayList<String> position = blankSpace(boardString);
        if(dice.equals("")){
            return validTiles;
        }
        for(String pos:position){
            for(char c = '0' ; c <= '7'; ++c){
                String temp = dice + pos + c;
                if(isValidPlacementSequence(boardString+temp)){
                    validTiles.add(temp);
                }
            }
        }


        return validTiles;


    }


    /*
     * Authorship: Written by Ren
     * Find the blank space on the board.
     */
    public static ArrayList<String> blankSpace(String boardString){
        ArrayList<String> blackSpace = new ArrayList<>();
        for(String pos:Board.BoardPos){
            blackSpace.add(pos);
        }
        ArrayList<String> placedPos = getPositions(boardString);
        for(String pos:placedPos){
            blackSpace.remove(pos);
        }
        return blackSpace;

    }
}