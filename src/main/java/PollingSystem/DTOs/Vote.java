package PollingSystem.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vote {
    String voteId;
    String pollId;
    String userId;
    String optionId;
    Date date;
}
