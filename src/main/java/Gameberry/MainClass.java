package Gameberry;

import Gameberry.DTO.*;
import Gameberry.Service.Game;
import Gameberry.Util.NormalDiceStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

	play()




GameManager
	createGame()
	joinGame()

 */
public class MainClass {
    public static void main(String args[]){
        User user1 = new User(1,"Alice");
        User user2 = new User(2,"Bob");
        Map<Integer,BaseJumper> move = new HashMap<>();
        BaseJumper snake1 = new Snake(25,2);
        BaseJumper snake2 = new Snake(56, 23);
        BaseJumper ladder1 = new Ladder(67, 12);
        BaseJumper ladder2 = new Ladder(89, 23);
        move.put(25,snake1);
        move.put(56,snake2);
        move.put(12,ladder1);
        move.put(23,ladder2);
        Board board = new Board(100, move);
        List<User> userList= new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        Game game = new Game(userList,board, new NormalDiceStrategy());
        User WinnerUser  = game.play();
        System.out.println("Winner User Is " + WinnerUser.getUsername());


    }
}
