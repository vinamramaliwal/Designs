package Gameberry.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
@Data
public class Board {
    int size;
    List<Integer> cells;
    Map<Integer,BaseJumper> move = new ConcurrentHashMap<>();
    public Board(int size, Map<Integer,BaseJumper> move){
        this.size =size;
        this.move = move;

    }

    void addJumper(){

    }
}
