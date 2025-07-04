package Gameberry.DTO;

import lombok.AllArgsConstructor;

public class Snake extends BaseJumper{
    public Snake(int top, int bottom) {
        super(top,bottom);
    }

    @Override
    public int newPosition() {
        return bottom;
    }
}
