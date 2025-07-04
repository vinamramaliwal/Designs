package Gameberry.Service;

import Gameberry.DTO.BaseDice;
import Gameberry.DTO.BaseJumper;
import Gameberry.DTO.Board;
import Gameberry.DTO.User;
import Gameberry.Util.ThrowStrategy;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class Game {
    List<User> UserList = new ArrayList<>();
    Board board;
    Map<Integer, Integer> position = new ConcurrentHashMap<>();
    User winnerUser;

    ThrowStrategy throwStrategy;

    public Game(List<User> UserList, Board board, ThrowStrategy throwStrategy) {
        this.UserList = UserList;
        this.board = board;
        for(User user: UserList){
            position.put(user.getUserId(), 0);
        }
        this.throwStrategy = throwStrategy;

    }

    public User play(){

        while(winnerUser == null) {

            for (User user : UserList) {
                int userPosition = position.get(user.getUserId());
                System.out.println("userPosition-" + userPosition);
                int dicNumber = throwStrategy.roll();
                System.out.println("dicNumber-" + dicNumber);
                int newPosition = userPosition + dicNumber;
                if(board.getMove().get(newPosition) != null){
                    BaseJumper baseJumper = board.getMove().get(newPosition);
                    System.out.println("Found a jumper at -" + newPosition);
                    newPosition = baseJumper.newPosition();
                }
                position.put(user.getUserId(), newPosition);
                if (newPosition>=board.getSize()){
                    winnerUser = user;
                    return winnerUser;
                }

            }

        }

        return winnerUser;

    }

}
