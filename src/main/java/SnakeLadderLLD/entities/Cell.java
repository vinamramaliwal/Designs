package SnakeLadderLLD.entities;

import lombok.Data;

@Data
public class Cell {
    int x;
    int y;
    Snake Snake;
    Ladder Ladder;
    Cell(int x,int y){
        this.x=x;
        this.y=y;
    }
}
