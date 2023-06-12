package PendencySystem.Services;


import java.util.List;

public interface TrackingServices {
    void startTracking(int id, List<String> tagList);
    void stopTracking(int id);
    int getCounts(List<String> tagList);

}
