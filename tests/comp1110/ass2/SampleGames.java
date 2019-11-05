package comp1110.ass2;

import java.util.Random;

public class SampleGames {
    static final String exampleInBook = "A4A12B2B16A1B01A1B23S1B32A1A32B1B44B2A44A4C16A3D15A4D01A5D23A4E20B1F24A2F17A1F01B0G16A5C34A4C43A5C53A3D50A4D61S4E50A0F51A1F67S2E46B1E31A1F30A2G36A1G41B1G52";
    /**
     * Score for book example string above:
     * Route 1: 4 exits -> 12
     * Route 2: 6 exits -> 20
     * Centre: 6
     * Errors: -5
     * Highway: 8
     * Railway: 5
     * Basic Total: 34
     * Bonus Total: 47
     */

    static final int[] scoreExample = {33, 46, 6, 5, 5, 8, 6, 4};

    static final String[] routesExample = {"A0F51A1A32A1B01A1B23A1F30A1F67A1G41A2G36A3D50A4C43A4D61A5C34A5C53B1B44B1E31B1G52B2B16S1B32S2E46S4E50", "A1F01A2F17A3D15A4A12A4C16A4D01A4E20A5D23B0G16B1F24B2B16"};

    static final char[] pieceDice = {'A', 'B', 'S'};

    static final String[] COMPLETED_GAMES = {
            "A3D61A3D53B0C52A0B52A2B63A4D41B0E60A0F61A3D31A3D23A2G30B0F34A3E32A1B01B2B10A1B21A0A63A4D01A1G41B0G12S2D10A4C10B2A10A2B33A1A30S4E11A4E21A3C21A3C31S5F11",
            "A3A10A3A52A3G10B2F10S1B50A2B61A0C60A1B41B1A35A4A41A2B31A1C30B0D32A2C50A4E10A3D12B2B10A2F01A0G00A4D01B1A27S3B20A4C10A1D50A0F23B2G25A3E30A4E41",
            "A2A30A0A43A3A50B2B50A4C50A3D50A2B43B0G12B0A14A2B33A0B11A4E50A3D61A2B21A3G52B1G45A3F52B2F41A3F33A1E40A1D40A3E32A3E27B0F10S0E12B1D17A4D01A1B61A0C43",
            "A4A50A1F61A0B61S5F50B1F46A1F01S1F12A2F23A1E20B2D21A3D03A1C20A0B22B1A61A4D11A4G10B1G44A2G30A3C01A3C12B0B31A1B01A4B50B0C50A2F32A0E32A0E40A4D31B1D47A1B11",
            "A4A50A1A30B2B31A0C34A3B41B2C40A3B52A2B60A2C62S5C50B1D65A4B21A2A60A3B10A4A10A4C10B2G10B2F10A4E10A3D12A1F01S2D00A4C00B1B02A0F23A0G20A2F61B2F50A3G52A0G02",
            "A4G10B2F10A4E10A0F20A3D17A0E22A2E31B1E44S0D42A3D23A4D31A2F30B1F42A1G30A0C42A0C57B0C22A2F03A1E02S5D01A0B22B0A50A4D51A3D61B2B53A0B30B2A31A4E60A3A41A0B03"
    };

    static final String[][] COMPLETED_GAMES_ROUTE_STRINGS = {
            {"A0A63A0B52A0F61A1G41A2B63A2G30A3C21A3C31A3D23A3D31A3D53A3D61A3E32A4C10A4D01A4D41A4E21B0C52B0E60B0F34B0G12B2A10B2B10S2D10S4E11S5F11", "A1A30A1B01A1B21A2B33B2B10"},
            {"A0C60A1B41A1C30A1D50A2B31A2B61A2C50A3A10A3A52A3D12A3E30A3G10A4A41A4C10A4D01A4E10A4E41B0D32B1A27B1A35B2B10B2F10B2G25S1B50S3B20", "A0F23A0G00A2F01B2F10"},
            {"A0C43A1D40A1E40A3A50A3D50A3D61A3E27A3E32A3F33A3F52A3G52A4C50A4D01A4E50B0F10B0G12B1D17B1G45B2B50B2F41S0E12", "A0A43A0B11A1B61A2A30A2B21A2B33A2B43B0A14B2B50"},
            {"A0B22A0E32A0E40A1C20A1E20A1F01A1F61A2F23A2F32A2G30A3C01A3C12A3D03A4D11A4D31A4G10B0B31B1D47B1F46B1G44B2D21S1F12S5F50", "A4A50A4B50B0C50", "A0B61B1A61", "A1B01A1B11"},
            {"A0C34A1A30A2A60A2B60A2C62A3B10A3B41A3B52A3D12A4A10A4A50A4B21A4C00A4C10A4E10B1B02B1D65B2B31B2C40B2F10B2G10S2D00S5C50", "A0F23A0G02A0G20A1F01B2F10B2G10", "A2F61B2F50", "A3G52B2F50"},
            {"A0B22A0B30A0C42A0C57A0E22A0F20A1E02A1G30A2E31A2F03A2F30A3D17A3D23A3D61A4D31A4D51A4E10A4E60A4G10B0A50B0C22B1E44B1F42B2A31B2B53B2F10S0D42S5D01", "A3A41B2A31", "A0B03"}
    };


    /**
     * Format:
     * Basic Score
     * Advanced Score
     * Centres
     * Errors
     * Longest Rail
     * Longest Highway
     * Exits mapped by each network (as many as there are networks)
     */
    static final int[][] SCORES = {
            {24, 41, 7, 11, 5, 12, 7, 2},
            {18, 36, 4, 6, 9, 9, 6, 1},
            {21, 42, 5, 8, 9, 12, 5, 3},
            {15, 32, 7, 8, 10, 7, 5, 1, 1, 1},
            {23, 42, 2, 7, 8, 11, 8, 1, 1},
            {23, 42, 8, 9, 9, 10, 7, 1}
    };

    static String randomPiece(Random r) {
        return "" + pieceDice[r.nextInt(3)] + (char) (r.nextInt(6) + '0');
    }

    static String randomLocation(Random r) {
        return "" + (char) (r.nextInt(7) + 'A') + (char) (r.nextInt(7) + '0');
    }

    static int randomOrientation(Random r) {
        return r.nextInt(8);
    }

    static String badlyFormedPiecePlacement(Random r) {
        String piece = randomPiece(r);
        char a = piece.charAt(0);
        char b = piece.charAt(1);
        char bada = (char) ('C' + r.nextInt(26));
        if (bada == 'S') bada = '!';
        char badb = (char) (r.nextInt(5) + 8 + '0');
        char lowp = (char) (r.nextInt(15) + '0');

        String loc = randomLocation(r);
        char c = loc.charAt(0);
        char d = loc.charAt(1);
        char badc = (char) (r.nextInt(10) + 7 + 'A');
        char badd = (char) (r.nextInt(10) + 7 + '0');
        int e = randomOrientation(r);
        char bade = (char) ('A' + 8 + r.nextInt(10));

        String test = "";
        switch (r.nextInt(9)) {
            case 0:
                test += "" + bada + b + c + d + e;
                break;
            case 1:
                test += "" + a + badb + c + d + e;
                break;
            case 2:
                test += "" + a + b + badc + d + e;
                break;
            case 3:
                test += "" + a + b + badc + d + e;
                break;
            case 4:
                test += "" + a + b + c + badd + e;
                break;
            case 5:
                test += "" + a + b + c + d + bade;
                break;
            case 6:
                test += "" + bada + b + badc + d + e;
                break;
            case 7:
                test += "" + lowp + b + c + d + e;
                break;
            case 8:
                test += "" + a + b + lowp + d + e;
                break;
            default:
                test += "" + a + badb + c + badd + bade;
        }
        return test;
    }
}