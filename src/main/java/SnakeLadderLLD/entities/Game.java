package SnakeLadderLLD.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

@Data
public class Game {
    Board board;
    Deque<Player> players= new LinkedList<>();
    Player winner;
    Dice dice;
    public Game(){
        this.board= new Board(10,5,5);
        this.dice = new Dice(1);
        winner=null;
        addPlayers();
    }
    void addPlayers(){
        Player p1= new Player("Vinamra");
        Player p2= new Player("Ayush");
        players.add(p1);
        players.add(p2);
    }

    public void startGame(){
        while(winner==null){
            Player playerturn= findTurn();
            System.out.println("Player turn"+ playerturn.getName()+". Current position is "+playerturn.getPosition());
            int diceNumber= dice.rollDice();
            int newPosition= diceNumber+ playerturn.getPosition();
            if(newPosition>=board.n*board.n){
                System.out.println("Player turn"+ playerturn.getName()+". New position is "+playerturn.getPosition());
                winner = playerturn;
                break;
            }
            newPosition= check(newPosition);
            playerturn.setPosition(newPosition);
            System.out.println("Player turn"+ playerturn.getName()+". New position is "+playerturn.getPosition());



        }
        System.out.println("Winner is "+ winner.getName());
    }

    Player findTurn(){
        Player p= players.removeFirst();
        players.addLast(p);
        return p;
    }
    int check(int position){
        Cell c=board.getCell(position);
        if(c.Ladder!=null){
            System.out.println("Found Ladder");
            Cell newCell= c.getLadder().getEnd();
            return newCell.x*board.n + newCell.y;
        } else if(c.Snake!=null){
            System.out.println("Found Snake");
            Cell newCell = c.getSnake().getEnd();
            return newCell.x*board.n + newCell.y;
        }
        return position;
    }
}
