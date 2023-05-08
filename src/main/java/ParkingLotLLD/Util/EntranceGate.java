package ParkingLotLLD.Util;

import ParkingLotLLD.Entities.ParkingSpot;
import ParkingLotLLD.Entities.Vehicle;
import ParkingLotLLD.Entities.VehicleType;

import java.util.ArrayList;

public class EntranceGate {

    ParkingManagerFactory parkingManagerFactory;
    EntranceGate(){
        parkingManagerFactory = new ParkingManagerFactory();
    }

    ParkingSpot findParking(Vehicle v){
        ParkingManager parkingManager = parkingManagerFactory.getParkingManager(v.getVehicleType());
        return parkingManager.findParking(parkingManager.getParkingStrategy());
    }

    void bookSpot(Vehicle v){
        ParkingManager parkingManager = parkingManagerFactory.getParkingManager(v.getVehicleType());
        parkingManager.parkVehicle(v);

    }

    void generateTicket(Vehicle v, ParkingSpot p ){

    }



}
