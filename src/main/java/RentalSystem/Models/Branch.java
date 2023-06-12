package RentalSystem.Models;

import lombok.Data;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Branch {

    int id;
    String name;
    Map<VehicleType, Integer> priceOfVehicle;
    Map<Integer,List<Slot>> vehicleBookedSlot;

    public Branch(int id, String name){
        this.id = id;
        this.name =  name;
        priceOfVehicle =  new HashMap<>();
        vehicleBookedSlot = new HashMap<>();
    }


}
