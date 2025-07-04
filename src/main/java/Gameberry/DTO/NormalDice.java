package Gameberry.DTO;

import java.util.UUID;

public class NormalDice implements BaseDice{
    @Override
    public int roll() {
        return 3;
    }
}
