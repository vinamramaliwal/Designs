package RentalSystem.Models;

import lombok.Data;

@Data
public class Slot {
    int startTime;
    int endTime;
     public Slot(int a, int b){
         startTime =a;
         endTime = b;
     }
}
