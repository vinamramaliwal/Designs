package CircuitBreaker;

import org.springframework.stereotype.Component;

@Component
public class CircuitBreaker {
    int failureThreshold;
    int successThreshold;
    CircuitBreakerState state;

    private int failureCount = 0;
    private int successCount = 0;
    private int retryTimePeriod;
    private long lastFailureTime;


    public CircuitBreaker(int failureThreshold, int successThreshold){
        this.failureThreshold = failureThreshold;
        this.successThreshold = successThreshold;
        state = CircuitBreakerState.CLOSED;
        retryTimePeriod  =  10000;
    }

    synchronized boolean allowRequest(){
        long currentTime = System.currentTimeMillis();
        switch (state){

            case OPEN:
                if(currentTime - lastFailureTime > retryTimePeriod ){
                    failureCount = 0;
                    successCount = 0;
                    state = CircuitBreakerState.HALF_OPEN;
                    return true;
                } else{
                    return false;
                }

            case CLOSED:
            default:
                return  true;

        }

    }

    public synchronized void recordSuccess(){
        successCount++;
        if(state == CircuitBreakerState.HALF_OPEN){
            if(successCount >= successThreshold){
                state=CircuitBreakerState.CLOSED;
                successCount = 0 ;
                failureCount =0;
            }
        }
        else if (state == CircuitBreakerState.CLOSED) {
            failureCount = 0;
        }
    }

    public synchronized void recordFailure(){
        failureCount++;
        lastFailureTime = System.currentTimeMillis();
        if(state==CircuitBreakerState.CLOSED){
            if(failureCount > failureThreshold){
                state = CircuitBreakerState.OPEN;
                successCount =0;
                failureCount = 0;
            }
        } else if (state== CircuitBreakerState.HALF_OPEN){
            state = CircuitBreakerState.OPEN;
        }
    }


}
