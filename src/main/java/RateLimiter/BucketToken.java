package RateLimiter;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BucketToken {
    int maxCapacity;
    int currToken;

    long lastTimeStamp;

    int refillRate;
    BucketToken(int maxCapacity, int refillRate){
        this.currToken= maxCapacity;
        this.maxCapacity = maxCapacity;
        lastTimeStamp = System.nanoTime();
        this.refillRate = refillRate;
    }

     synchronized boolean tokenPresent(){
        refill();
        if(currToken > 0){
            currToken--;

            return true;
        }
        return false;
    }

    void refill(){
         long currentTime = System.nanoTime();
         int tokensToAdd = (int) (((currentTime - lastTimeStamp)/ 1e9)*refillRate);
         if(tokensToAdd > 0) {
             currToken = Math.min(tokensToAdd + currToken, maxCapacity);
             lastTimeStamp = currentTime;
         }

    }
}
