package ParkingLotLLD.Util;

import ParkingLotLLD.Entities.Ticket;
import ParkingLotLLD.Entities.Vehicle;
import ParkingLotLLD.Entities.VehicleType;

import java.time.Instant;
import java.time.LocalDateTime;

public class ExitGate {

    ParkingManagerFactory parkingManagerFactory;
    ExitGate(){
        parkingManagerFactory = new ParkingManagerFactory();
    }

    void removeVehicle(Vehicle v){
        ParkingManager parkingManager = parkingManagerFactory.getParkingManager(v.getVehicleType());
        parkingManager.removeVehicle(v);
    }

    int findCost(Ticket t){
        ParkingManager parkingManager = parkingManagerFactory.getParkingManager(t.getVehicle().getVehicleType());
       return  parkingManager.getCostComputationStrategy().findCost(t);
    }

    void getPayment(){

    }



}
