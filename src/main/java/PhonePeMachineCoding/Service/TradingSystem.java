package PhonePeMachineCoding.Service;

import PhonePeMachineCoding.DTO.Order;
import PhonePeMachineCoding.DTO.OrderStatus;
import PhonePeMachineCoding.DTO.OrderType;
import PhonePeMachineCoding.DTO.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TradingSystem {
    private final Map<Integer, User> users = new HashMap<>();
    private final Map<String, OrderBook> orderBooks = new ConcurrentHashMap<>();

    private final Map<Integer, Order> allOrders = new ConcurrentHashMap<>();

    public TradingSystem() {
        users.put(1, new User(1, "vinamra", "9876565432", "vin@example.com"));
        users.put(2, new User(2, "Ayush", "0987676767", "ayu@example.com"));
        users.put(3, new User(2, "Abhishek", "0987676767", "ayu@example.com"));
        users.put(4, new User(2, "Avanish", "0987676767", "ayu@example.com"));
    }

    public synchronized void placeOrder(int userId, OrderType type, String stockSymbol, int quantity, double price) {
        if (!users.containsKey(userId)) {
            System.out.println("User not registered!");
            return;
        }

        orderBooks.putIfAbsent(stockSymbol, new OrderBook());
        Order order = new Order(userId, type, stockSymbol, quantity, price);
        orderBooks.get(stockSymbol).placeOrder(order);
        System.out.println("Order Placed: " + order.toString());
        allOrders.put(order.getOrderId(), order);
    }

    public synchronized void cancelOrder(String stockSymbol, Order order) {
        if (orderBooks.containsKey(stockSymbol) && order.getStatus()!= OrderStatus.EXECUTED && order.getStatus()!=OrderStatus.CANCELED) {
            orderBooks.get(stockSymbol).cancelOrder(order);
            System.out.println("Order Canceled: " + order.getOrderId());
        }
    }

    public Order checkOrderStatus(int orderId){
        return allOrders.getOrDefault(orderId, null);
    }
}
