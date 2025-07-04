package PollingSystem.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "poll_options") // Define table name
public class OptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    int optionId;

    String pollId;
    String option;
}
