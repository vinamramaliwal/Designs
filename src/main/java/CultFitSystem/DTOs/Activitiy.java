package CultFitSystem.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activitiy {
    String activityId;
    String cultCenterId;
    ActivityType activityName;
    int capacity;
    int currentBooking = 0;
    Long startTime;
    Long endTime;
}
