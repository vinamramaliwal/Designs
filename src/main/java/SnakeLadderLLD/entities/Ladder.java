package SnakeLadderLLD.entities;

import lombok.Data;

@Data
public class Ladder {
    Cell start;
    Cell end;
    Ladder(Cell start, Cell end){
        this.start=start;
        this.end=end;
    }
}
