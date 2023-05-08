package ParkingLotLLD.Util;

import ParkingLotLLD.Entities.ParkingSpot;
import ParkingLotLLD.Entities.VehicleType;

import java.util.List;

public class ParkingManagerFactory {

    TwoWheelerParkingManager twoWheelerParkingManager;
    FourWheelerParkingManager fourWheelerParkingManager;

    ParkingManagerFactory(List<ParkingSpot> parkingSpotList1, List<ParkingSpot> parkingSpotList2){
        twoWheelerParkingManager= new TwoWheelerParkingManager(parkingSpotList1);
        fourWheelerParkingManager = new FourWheelerParkingManager(parkingSpotList2);
    }

    ParkingManagerFactory(){}

    ParkingManager getParkingManager(VehicleType vehicleType){
        if( vehicleType.equals(VehicleType.TwoWheeler))
            return twoWheelerParkingManager;
        else
            return fourWheelerParkingManager;

    }
}
