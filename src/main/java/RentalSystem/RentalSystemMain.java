package RentalSystem;

import RentalSystem.Services.RentalService;

public class RentalSystemMain {

    static  final RentalService rentalService = new RentalService();
    public  static void main(String args[]){
        rentalService.addBranch(1,"Amroha");
    }
}
