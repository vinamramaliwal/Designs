package PollingSystem.Entities;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data
public class PollEntity {
    int pollId;
    String question;
    Date created_at;
}
