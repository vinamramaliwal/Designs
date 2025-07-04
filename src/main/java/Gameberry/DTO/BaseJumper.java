package Gameberry.DTO;

public abstract class BaseJumper {
    int top;
    int bottom;
    BaseJumper(int top,int bottom){
        this.top = top;
        this.bottom = bottom;
    }
    public abstract int newPosition();
}
