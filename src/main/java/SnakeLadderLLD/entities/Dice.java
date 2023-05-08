package SnakeLadderLLD.entities;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    int diceCount;
    final int min=1;
    final int max=6;
    Dice(int count){
        diceCount=count;
    }
    int rollDice(){
        int sum=0;
        int k=0;
        while(k<diceCount){
            sum= sum+ ThreadLocalRandom.current().nextInt(min, max+1);
            k++;
        }
        return sum;
    }
}
