package RentalSystem.Services;

import RentalSystem.Models.Branch;
import RentalSystem.Models.Slot;
import RentalSystem.Models.Vehicle;
import RentalSystem.Models.VehicleType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class RentalService {

    List<Branch> branchList = new ArrayList<>();
    List<Vehicle> vehicleList = new ArrayList<>();

    public  Branch addBranch(int id, String name){
        Branch b = new Branch(id, name);
        branchList.add(b);
        return  b;
    }

    public void allocatePrice(String branchName, VehicleType vehicleType, int price) {
        Branch station = getBranchByName(branchName);
        station.getPriceOfVehicle().put(vehicleType,price);
    }

    public void addVehicle(int vehicleId, VehicleType vehicleType, String branchName){
        Branch branch = getBranchByName(branchName);
        branch.getVehicleBookedSlot().put(vehicleId, new ArrayList<>());
    }

    public Branch getBranchByName(String name){
        Branch station=null;
        for(Branch branch:branchList){
            if(branch.getName().equals(branch.getName())){
                station=branch;
                break;
            }
        }
        if(station==null){
            throw new RuntimeException("Invalid Branch Name");
        }
        return station;
    }

    public Vehicle getVehicleById(int vehicleId){
        Vehicle vehicle = null;
        for(Vehicle v:vehicleList){
            if(v.getId()== vehicleId){
                vehicle=v;
                break;
            }
        }
        if(vehicle==null){
            throw new RuntimeException("Invalid Branch Name");
        }
        return vehicle;
    }

    public String bookVehicle(VehicleType vehicleType, int startTime, int endTime) {

        int currentPrice =  Integer.MAX_VALUE;
        Branch bookedBranch = null;
        Vehicle bookedVehicle= null;
        for(Branch branch: branchList) {
            Map<Integer,List<Slot>> vehicle= branch.getVehicleBookedSlot();
            for (Map.Entry<Integer,List<Slot>> entry : vehicle.entrySet()) {
                Vehicle vehicle1 = getVehicleById(entry.getKey());
                if(vehicle1.getVehicleType().equals(vehicleType)){
                    if(isSlotAvailable(entry.getValue(), startTime, endTime)){
                        if(branch.getPriceOfVehicle().get(vehicleType)<currentPrice){
                            currentPrice = branch.getPriceOfVehicle().get(vehicleType);
                            bookedBranch =  branch;
                            bookedVehicle = vehicle1;

                        }
                    }
                }
            }
        }
        Slot slot = new Slot(startTime, endTime);
        bookedBranch.getVehicleBookedSlot().get(bookedVehicle.getId()).add(slot);
        return bookedBranch.getName();
    }

    public boolean isSlotAvailable(List<Slot> list, int startTime, int endTime){

        for(Slot s:list){
            if((startTime>=s.getStartTime()&&startTime<=s.getEndTime())||(endTime>=s.getStartTime()&&endTime<=s.getStartTime()))
                return false;
        }
        return true;
    }
    public int getPriceOfVehicle(VehicleType vehicleType, String branchName, int startTime, int endTime){
        Branch station = getBranchByName(branchName);
        return (station.getPriceOfVehicle().get(vehicleType))*(endTime-startTime);
    }
}
