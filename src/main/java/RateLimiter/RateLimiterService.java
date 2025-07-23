package RateLimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiterService {
    Map<String, BucketToken> userBucketMap = new ConcurrentHashMap<>();

    int refillRate = 5; // tokens per second

    boolean  allowRequest(String userId){
        userBucketMap.computeIfAbsent(userId, key-> new BucketToken(10, refillRate));
        return userBucketMap.get(userId).tokenPresent();
    }
}
