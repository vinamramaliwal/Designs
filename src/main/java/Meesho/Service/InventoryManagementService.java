package Meesho.Service;

import Meesho.DTOs.BlockedEntry;
import Meesho.DTOs.Product;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class InventoryManagementService {

    Map<String, Product> inventoryList= new ConcurrentHashMap<>();
    Map<String, BlockedEntry> blockedInventory = new ConcurrentHashMap<>(); // orderId->productId


    private Lock lock = new ReentrantLock();

    public void createProduct(String productId, String name, int count){
        Product product = new Product(productId,name, count);
        inventoryList.put(productId,product);
    }

    public int getInventory(String productId){
        if(inventoryList.get(productId)==null){
            return  -1;
        }
        return inventoryList.get(productId).getCount();
    }

    public boolean blockInventory(String productId, int count, String orderId) {
        if(inventoryList.get(productId)==null || inventoryList.get(productId).getCount()-count < 0){
            return false;
        }

        Product product = inventoryList.get(productId);
        product.setCount(product.getCount()-count);
        BlockedEntry blockedEntry = new BlockedEntry();
        blockedEntry.setBlockedDateTime(LocalDateTime.now());
        blockedEntry.setBlockedCount(count);
        blockedEntry.setOrderId(orderId);
        blockedEntry.setProductId(productId);
        blockedInventory.put(orderId, blockedEntry);
        System.out.println("Blocked inventory::" + blockedInventory.get(orderId).toString());
        return true;

    }

    public boolean confirmOrder(String orderId){

        if(blockedInventory.get(orderId)==null)
            return false;

        blockedInventory.remove(orderId);
        System.out.println("OrderConfirmed:: " + orderId);
        return true;
    }

    public void releaseExpiredEntries(){
            LocalDateTime now = LocalDateTime.now();
            lock.lock();
            try {
                for (Map.Entry<String, BlockedEntry> entry : blockedInventory.entrySet()) {
                    BlockedEntry blockedEntry = entry.getValue();
                    Duration duration = Duration.between(blockedEntry.getBlockedDateTime(), now);
                    if (duration.toMinutes() >= 1) {
                        Product product = inventoryList.get(blockedEntry.getProductId());
                        product.setCount(product.getCount() + blockedEntry.getBlockedCount());
                        blockedInventory.remove(entry.getKey());
                        System.out.println("Released expired entry " + entry.getKey() + " Time- " + now);
                        System.out.println("Inventory Count " + product.getProductId() + " == " + product.getCount());
                    }
                }
            } finally {
                lock.unlock();
            }

    }

    public void startScheduler(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(this::releaseExpiredEntries,0,5, TimeUnit.SECONDS);
    }

}
