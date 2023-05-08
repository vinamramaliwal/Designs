package SnakeLadderLLD.entities;

import lombok.Data;

@Data
public class Player {
    String name;
    int position;
    Player(String name){
        this.name=name;
        position=-1;
    }
}
