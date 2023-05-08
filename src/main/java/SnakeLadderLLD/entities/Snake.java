package SnakeLadderLLD.entities;

import lombok.Data;

@Data
public class Snake {
    Cell start;
    Cell end;
    Snake(Cell start, Cell end){
        this.start=start;
        this.end=end;
    }
}
