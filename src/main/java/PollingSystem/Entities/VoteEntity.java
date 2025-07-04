package PollingSystem.Entities;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class VoteEntity {
    int voteId;
    String pollId;
    String userId;
    String optionId;
    Date date;
}
