package Meesho;

import Meesho.Service.InventoryManagementService;

public class MainClass {

    public static void main(String args[]) {
        try{
            InventoryManagementService inventoryManagementService = new InventoryManagementService();
            inventoryManagementService.startScheduler();

            inventoryManagementService.createProduct("p1", "p1", 3);
            inventoryManagementService.createProduct("p2","p2",5);

            int inventoryCountP1 = inventoryManagementService.getInventory("p1");
            System.out.println("inventoryCountP1-"+ inventoryCountP1);

            int inventoryCountP2 = inventoryManagementService.getInventory("p2");
            System.out.println("inventoryCountP2-"+ inventoryCountP2);

            if(inventoryManagementService.blockInventory("p1",1,"order1")){
                System.out.println("BlockInventory p1 success-");
            } else{
                System.out.println("BlockInventory p1 failure-");
            }

            if(inventoryManagementService.blockInventory("p2",1,"order2")){
                System.out.println("BlockInventory p2 success-");
            } else{
                System.out.println("BlockInventory p2 failure-");
            }

            inventoryManagementService.confirmOrder("order1");
            System.out.println("inventoryCountP1-"+ inventoryManagementService.getInventory("p1"));


        } catch (Exception e){
            System.out.println("Exception in main method -"+ e.getMessage());
        }

    }
}
