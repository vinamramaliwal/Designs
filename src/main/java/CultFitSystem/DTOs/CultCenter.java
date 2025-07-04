package CultFitSystem.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CultCenter {
    String centerId;
    String centerName;
    Map<String,Activitiy> activityList = new HashMap<>();
    LocationDTO location;
}
