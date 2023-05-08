package SnakeLadderLLD.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
    int n;
    Cell[][] listOfCells;
    List<Snake> snakes= new ArrayList<>();
    List<Ladder> ladders= new ArrayList<>();
    Board(int n, int snakes, int ladders){
        this.n=n;
        listOfCells = new Cell[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                listOfCells[i][j]=new Cell(i,j);
            }
        }
        addSnake(snakes);
        addLadder(ladders);
    }

    Cell getCell(int position){
        int x=position/n;
        int y= position%n;
        return listOfCells[x][y];
    }

   void addSnake(int snake){
        while(snake>0){
            int head= ThreadLocalRandom.current().nextInt(1, n*n-1);
            int tail= ThreadLocalRandom.current().nextInt(1, n*n-1);
            if(tail>=head) {
                continue;
            }
            Snake s= new Snake(getCell(head),getCell(tail));
            snakes.add(s);
            s.getStart().setSnake(s);
            snake--;
        }

   }
   void addLadder(int ladder){
       while(ladder>0){
           int head= ThreadLocalRandom.current().nextInt(1, n*n-1);
           int tail= ThreadLocalRandom.current().nextInt(1, n*n-1);
           if(tail<=head) {
               continue;
           }
           Ladder s= new Ladder(getCell(head),getCell(tail));
           ladders.add(s);
           s.getStart().setLadder(s);
           ladder--;
       }
   }


}
