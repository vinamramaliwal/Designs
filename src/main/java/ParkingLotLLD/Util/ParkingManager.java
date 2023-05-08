package ParkingLotLLD.Util;

import ParkingLotLLD.Entities.ParkingSpot;
import ParkingLotLLD.Entities.Vehicle;
import lombok.Data;

import java.util.List;
@Data
public class ParkingManager {

    List<ParkingSpot> parkingSpotList;
    CostComputationStrategy costComputationStrategy;
    ParkingStrategy parkingStrategy;
    public ParkingManager(List<ParkingSpot> parkingSpotList){
        this.parkingSpotList=parkingSpotList;
    }
    public ParkingManager(){}

    public void addParkingSpot(ParkingSpot parkingSpot){}
    public void removeParkingSpot(ParkingSpot parkingSpot){}
    public void parkVehicle(Vehicle v){};
    public void removeVehicle(Vehicle v){};
    public ParkingSpot findParking(ParkingStrategy parkingStrategy)
    {
        return parkingStrategy.findParking(parkingSpotList);
    }

}
