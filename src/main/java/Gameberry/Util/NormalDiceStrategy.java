package Gameberry.Util;

import java.util.Random;

public class NormalDiceStrategy implements ThrowStrategy{
    @Override
    public int roll() {
         Random rand = new Random();
         return  rand.nextInt(6)+1;
    }
}
