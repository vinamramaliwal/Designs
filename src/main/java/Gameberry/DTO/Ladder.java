package Gameberry.DTO;

import lombok.AllArgsConstructor;


public class Ladder extends BaseJumper{
    public Ladder(int top, int bottom) {
        super(top, bottom);
    }

    @Override
    public  int newPosition() {
        return top;
    }
}
