package CultFitSystem.Service;

import CultFitSystem.DTOs.Activitiy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingService {


    @Autowired
    CenterManagementService centerManagementService;

    Map<String, Set<String>> userActivityBooking = new HashMap<>();
    Map<String, List<String>> waitingList = new HashMap<>();

    public String bookActivity(String userId, String activityId){

        Activitiy activitiy = centerManagementService.getActivities().get(activityId);
        //validate for overlapping activity
        if (activitiy == null) return "Activity not found";

        // Validate for overlapping activities
        if (hasOverlappingBooking(userId, activitiy)) {
            return "Booking failed: Overlapping activity already booked.";
        }

        if(activitiy.getCurrentBooking() == activitiy.getCapacity()){
           return addUserToWaitingList(userId,activitiy.getActivityId());
        }
        userActivityBooking.putIfAbsent(userId, new HashSet<>());
        activitiy.setCurrentBooking(activitiy.getCurrentBooking() + 1);
        userActivityBooking.get(userId).add(activityId);
        return "activity Booked successFully";

    }

    private boolean hasOverlappingBooking(String userId, Activitiy newActivity) {
        Set<String> bookedActivities = userActivityBooking.getOrDefault(userId, Collections.emptySet());

        for (String bookedActivityId : bookedActivities) {
            Activitiy bookedActivity = centerManagementService.getActivities().get(bookedActivityId);
            if (bookedActivity != null && isTimeOverlapping(bookedActivity, newActivity)) {
                return true; // Overlapping found
            }
        }
        return false;
    }

    private boolean isTimeOverlapping(Activitiy activity1, Activitiy activity2) {
        return activity1.getStartTime() < activity2.getEndTime() &&
                activity2.getStartTime() < activity1.getEndTime();
    }

    private String addUserToWaitingList(String userId, String activityId) {
        waitingList.putIfAbsent(activityId, new ArrayList<>());
        waitingList.get(activityId).add(userId);
        return "Activity is full. You have been added to the waiting list.";
    }

    public String cancelBooking(String userId, String activityId) {
        if (!userActivityBooking.containsKey(userId) || !userActivityBooking.get(userId).contains(activityId)) {
            return "No booking found for the user.";
        }

        // Remove the booking
        userActivityBooking.get(userId).remove(activityId);
        if (userActivityBooking.get(userId).isEmpty()) {
            userActivityBooking.remove(userId);
        }

        Activitiy activity = centerManagementService.getActivities().get(activityId);
        if (activity != null) {
            activity.setCurrentBooking(activity.getCurrentBooking() - 1);
        }

        // If there are users on the waiting list, move the first user to the booked list
        if (waitingList.containsKey(activityId) && !waitingList.get(activityId).isEmpty()) {
            String nextUser = waitingList.get(activityId).remove(0);
            if (waitingList.get(activityId).isEmpty()) {
                waitingList.remove(activityId);
            }
            bookActivity(nextUser, activityId); // Auto-book the next user
            return "Booking cancelled. User " + nextUser + " moved from waiting list to booked list.";
        }

        return "Booking cancelled successfully.";
    }
}
