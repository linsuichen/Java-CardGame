package comp1110.ass2;

// Author: Ren

import java.util.ArrayList;

public class DiceRoll {
    public String diceRoll;

    DiceRoll(String diceRoll){
        this.diceRoll = diceRoll;
    }

    public ArrayList<String> getDifferentOrderOfDice(){
        ArrayList<String> dice = Tile.transToDiceArray(diceRoll);
        ArrayList<String> orders = new ArrayList<>();
        String Dice1,Dice2,Dice3,Dice4;
        String[] random = new String[24];
        Dice1 = dice.get(0);
        Dice2 = dice.get(1);
        Dice3 = dice.get(2);
        Dice4 = dice.get(3);
        random[0] = Dice1+Dice2+Dice3+Dice4;
        random[1] = Dice1+Dice2+Dice4+Dice3;
        random[2] = Dice1+Dice3+Dice2+Dice4;
        random[3] = Dice1+Dice3+Dice4+Dice2;
        random[4] = Dice1+Dice4+Dice2+Dice3;
        random[5] = Dice1+Dice4+Dice3+Dice2;
        random[6] = Dice2+Dice1+Dice3+Dice4;
        random[7] = Dice2+Dice1+Dice4+Dice3;
        random[8] = Dice2+Dice3+Dice1+Dice4;
        random[9] = Dice2+Dice3+Dice4+Dice1;
        random[10] = Dice2+Dice4+Dice1+Dice3;
        random[11] = Dice2+Dice4+Dice3+Dice1;
        random[12] = Dice3+Dice1+Dice2+Dice4;
        random[13] = Dice3+Dice1+Dice4+Dice2;
        random[14] = Dice3+Dice2+Dice1+Dice4;
        random[15] = Dice3+Dice2+Dice4+Dice1;
        random[16] = Dice3+Dice4+Dice1+Dice2;
        random[17] = Dice3+Dice4+Dice2+Dice1;
        random[18] = Dice4+Dice1+Dice2+Dice3;
        random[19] = Dice4+Dice1+Dice3+Dice2;
        random[20] = Dice4+Dice2+Dice1+Dice3;
        random[21] = Dice4+Dice2+Dice3+Dice1;
        random[22] = Dice4+Dice3+Dice1+Dice2;
        random[23] = Dice4+Dice3+Dice2+Dice1;

        for (int i = 0; i < 24; i++) {
            orders.add(random[i]);
        }
        return orders;
    }

    public ArrayList<String> getAdvancedDifferentOrderOfDice() {
        ArrayList<String> dice = Tile.transToDiceArray(diceRoll);
        ArrayList<String> orders = new ArrayList<>();
        String Dice1,Dice2,Dice3,Dice4;
        String[] random = new String[24];
        int j = (int)(Math.random()*6);
        Dice1 = dice.get(0);
        Dice2 = dice.get(1);
        Dice3 = dice.get(2);
        Dice4 = dice.get(3);

        String Dice5 = "";
        if ((int)(Math.random()*7) < 4) {
            Dice5 = Dice5+"S" + j;
        }
        random[0] = Dice5+Dice1+Dice2+Dice3+Dice4;
        random[1] = Dice1+Dice5+Dice2+Dice4+Dice3;
        random[2] = Dice1+Dice3+Dice2+Dice5+Dice4;
        random[3] = Dice5+Dice1+Dice3+Dice4+Dice2;
        random[4] = Dice1+Dice4+Dice2+Dice3+Dice5;
        random[5] = Dice1+Dice4+Dice3+Dice2+Dice5;
        random[6] = Dice2+Dice1+Dice5+Dice3+Dice4;
        random[7] = Dice2+Dice5+Dice1+Dice4+Dice3;
        random[8] = Dice2+Dice3+Dice5+Dice1+Dice4;
        random[9] = Dice5+Dice2+Dice3+Dice4+Dice1;
        random[10] = Dice5+Dice2+Dice4+Dice1+Dice3;
        random[11] = Dice2+Dice5+Dice4+Dice3+Dice1;
        random[12] = Dice3+Dice1+Dice2+Dice5+Dice4;
        random[13] = Dice3+Dice1+Dice4+Dice2+Dice5;
        random[14] = Dice3+Dice2+Dice5+Dice1+Dice4;
        random[15] = Dice3+Dice2+Dice4+Dice1+Dice5;
        random[16] = Dice3+Dice4+Dice1+Dice5+Dice2;
        random[17] = Dice3+Dice5+Dice4+Dice2+Dice1;
        random[18] = Dice5+Dice4+Dice1+Dice2+Dice3;
        random[19] = Dice5+Dice4+Dice1+Dice3+Dice2;
        random[20] = Dice4+Dice2+Dice5+Dice1+Dice3;
        random[21] = Dice4+Dice2+Dice5+Dice3+Dice1;
        random[22] = Dice4+Dice3+Dice1+Dice5+Dice2;
        random[23] = Dice4+Dice5+Dice3+Dice2+Dice1;

        for (int i = 0; i < 24; i++) {
            orders.add(random[i]);
        }
        return orders;
    }

}
