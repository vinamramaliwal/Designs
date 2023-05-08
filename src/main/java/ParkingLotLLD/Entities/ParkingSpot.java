package ParkingLotLLD.Entities;

import lombok.Data;

@Data
public class ParkingSpot {

    int id;
    boolean isEmpty=true;
    VehicleType vehicleType;
    Vehicle vehicle;
    int price;

    void parkVehicle(Vehicle v){

    }

    void removeVehicle(Vehicle v){

    }

}
