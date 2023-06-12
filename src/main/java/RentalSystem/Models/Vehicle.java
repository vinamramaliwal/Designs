package RentalSystem.Models;

import lombok.Data;

@Data
public class Vehicle {

    int id;
    String number;
    VehicleType vehicleType;
    int branchId;

    Vehicle(int id, String number, VehicleType type, int branchId){
        this.id = id;
        this.number= number;
        this.vehicleType = type;
        this.branchId = branchId;
    }

}
