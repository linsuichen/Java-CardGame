package comp1110.ass2;

import java.util.ArrayList;

import static comp1110.ass2.RailroadInk.getAdvancedScore;
import static comp1110.ass2.RailroadInk.getBasicScore;
import static comp1110.ass2.Tile.*;

public class AI {


    public static String SimpleAIMove(String boardString,String diceRoll) {
        String AImove=RailroadInk.generateMove(boardString,diceRoll);
        return AImove;
    }



    public static String AdvancedAIMove(String boardString, String diceRoll) {
        DiceRoll d = new DiceRoll(diceRoll);
        ArrayList<String> orders = d.getAdvancedDifferentOrderOfDice();
        ArrayList<String> boards = new ArrayList<>();
        for(String order:orders){
            String newBoard = findValidBoard(boardString,order);
            boards.add(newBoard);
        }
        int currentScore = 0;
        int index = 0;
        for(String board: boards){
            if(currentScore <= getAdvancedScore(board)){
                currentScore = getAdvancedScore(board);
                index = boards.indexOf(board);
            }
        }
        String bestBoard = boards.get(index);
        String result = remove(bestBoard,boardString);
        return result;
    }
}
