package CircuitBreaker;

public class Main {

    CircuitBreaker circuitBreaker = new CircuitBreaker(2,2);

    void callExternalService(){

        if(circuitBreaker.allowRequest()){

            if(callUserService()){
                circuitBreaker.recordSuccess();
            } else{
                circuitBreaker.recordFailure();
            }
        }
    }

    Boolean callUserService(){
        return true;
    }
}
