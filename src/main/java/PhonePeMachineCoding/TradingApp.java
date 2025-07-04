package PhonePeMachineCoding;

import PhonePeMachineCoding.DTO.Order;
import PhonePeMachineCoding.DTO.OrderStatus;
import PhonePeMachineCoding.DTO.OrderType;
import PhonePeMachineCoding.Service.TradingSystem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TradingApp {
    public static void main(String[] args) {
        TradingSystem tradingSystem = new TradingSystem();

        // Users placing orders
        System.out.println("\n--- Initial Orders ---");
        tradingSystem.placeOrder(1, OrderType.BUY, "RELIANCE", 100, 2500);
        tradingSystem.placeOrder(2, OrderType.SELL, "RELIANCE", 100, 2500);
        tradingSystem.placeOrder(1, OrderType.BUY, "TCS", 50, 3500);
        tradingSystem.placeOrder(2, OrderType.SELL, "TCS", 25, 3500);

        // Concurrent operations
        ExecutorService executor = Executors.newFixedThreadPool(5);

        executor.submit(() -> tradingSystem.placeOrder(3, OrderType.BUY, "INFY", 30, 1600));
        executor.submit(() -> tradingSystem.placeOrder(4, OrderType.SELL, "INFY", 30, 1600));
        executor.submit(() -> tradingSystem.placeOrder(3, OrderType.BUY, "INFY", 20, 1650));
        executor.submit(() -> tradingSystem.placeOrder(4, OrderType.SELL, "INFY", 20, 1650));

        // Sleep to let trades process
        sleep(2);

        System.out.println("\n--- Testing Order Status ---");
        Order order1 = tradingSystem.checkOrderStatus(1);
        Order order2 = tradingSystem.checkOrderStatus(2);
        Order order3 = tradingSystem.checkOrderStatus(3);
        Order order4 = tradingSystem.checkOrderStatus(4);
        if (order1 != null) System.out.println("Order 1 Status: " + order1.getStatus());
        if (order2 != null) System.out.println("Order 2 Status: " + order2.getStatus());
        if (order3 != null) System.out.println("Order 3 Status: " + order3.getStatus());
        if (order4 != null) System.out.println("Order 4 Status: " + order4.getStatus());

        // Testing Order Cancellation
        System.out.println("\n--- Cancelling an Order ---");
        Order cancelOrder = tradingSystem.checkOrderStatus(3);
        if (cancelOrder != null) {
            tradingSystem.cancelOrder(cancelOrder.getStockSymbol(), cancelOrder);
        }

        sleep(2);

        // Trade Expiry Test
        System.out.println("\n--- Waiting for Expiry Test ---");
        sleep(65); // Ensure orders expire (TRADE_EXPIRY_TIME is assumed 60 seconds)

        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- Final Order Status ---");
        System.out.println("Order 1: " + tradingSystem.checkOrderStatus(1).toString());
        System.out.println("Order 3: " + tradingSystem.checkOrderStatus(3).toString());
    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
