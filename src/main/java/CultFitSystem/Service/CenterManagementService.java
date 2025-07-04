package CultFitSystem.Service;

import CultFitSystem.DTOs.Activitiy;
import CultFitSystem.DTOs.ActivityType;
import CultFitSystem.DTOs.CultCenter;
import CultFitSystem.DTOs.LocationDTO;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@Data
public class CenterManagementService {

    private Map<String, CultCenter> cultCenterMap = new ConcurrentHashMap<>();
    private Map<String, Activitiy> activities = new ConcurrentHashMap<>();

    public String addNewCenter(String centerName, LocationDTO locationDTO){

        CultCenter cultCenter =  new CultCenter();
        cultCenter.setCenterId(UUID.randomUUID().toString());
        cultCenter.setCenterName(centerName);
        cultCenter.setLocation(locationDTO);
        cultCenterMap.put(cultCenter.getCenterId(), cultCenter);
        return cultCenter.toString();
    }

    public String addNewActivity(String cultCenterId, ActivityType activityType, int capacity, Long startTime,Long endTime){
        if(!cultCenterMap.containsKey(cultCenterId)){
            return "center not found";
        }
        CultCenter center = cultCenterMap.get(cultCenterId);
        if(validateOverLappingActivities(startTime,endTime,center.getActivityList())){
            return "Could not create activity . found overlapping activity";
        }
        Activitiy activity = new Activitiy();
        activity.setActivityId(UUID.randomUUID().toString());
        activity.setActivityName(activityType);
        activity.setCultCenterId(cultCenterId);
        activity.setCapacity(capacity);
        activity.setStartTime(startTime);
        activity.setEndTime(endTime);
        center.getActivityList().put(activity.getActivityId(),activity);
        activities.put(activity.getActivityId(), activity);
        return activity.toString();

    }

    private boolean validateOverLappingActivities(Long startTime, Long endTime, Map<String, Activitiy> activityList) {
        for(Map.Entry<String,Activitiy> entry: activityList.entrySet()){
            Long start = entry.getValue().getStartTime();
            Long end = entry.getValue().getEndTime();
            if(startTime<=start && endTime>=start || startTime<=end && endTime>=end){
                return true;
            }
        }

        return false;

    }

    public String updateActivity(String cultCenterId, String activityId, ActivityType activityType, int capacity, Long startTime,Long endTime){
        if(!cultCenterMap.containsKey(cultCenterId)){
            return "center not found";
        }
        CultCenter center = cultCenterMap.get(cultCenterId);
        if(validateOverLappingActivities(startTime,endTime,center.getActivityList())){
            return "Could not update activity . found overlapping activity";
        }
        Activitiy activity = center.getActivityList().get(activityId);
        if(activity == null){
            return "could not find activity";
        }
        activity.setActivityName(activityType);
        activity.setCapacity(capacity);
        activity.setStartTime(startTime);
        activity.setEndTime(endTime);
        return activity.toString();


    }

    public String deleteActivity(String cultCenterId, String activityId){
        if(!cultCenterMap.containsKey(cultCenterId)){
            return "center not found";
        }
        CultCenter center = cultCenterMap.get(cultCenterId);
        center.getActivityList().remove(activityId);
        activities.remove(activityId); // Remove globally
        return "activity deleted successfully";

    }

    public List<CultCenter> viewCenters(LocationDTO userLocation, ActivityType type, Long startTime, Long endTime) {
        return cultCenterMap.values().stream()
                .filter(center -> {
                    double distance = calculateDistance(userLocation, center.getLocation());
                    return distance <= 10; // Only return centers within 10 km
                })
                .filter(center -> center.getActivityList().values().stream()
                        .anyMatch(activity ->
                                (type == null || activity.getActivityName() == type) &&
                                        (startTime < activity.getEndTime() && endTime > activity.getStartTime()) // Overlap check
                        ))
                .collect(Collectors.toList());
    }

    private double calculateDistance(LocationDTO loc1, LocationDTO loc2) {
        double lat1 = loc1.getLatitude();
        double lon1 = loc1.getLongitude();
        double lat2 = loc2.getLatitude();
        double lon2 = loc2.getLongitude();

        double R = 6371; // Earth’s radius in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; // Distance in km
    }
}
