package PhonePeMachineCoding.DTO;

import lombok.ToString;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@ToString
public class Order {
    private static final AtomicInteger orderIdGenerator = new AtomicInteger(1);
    private final int orderId;
    private final int userId;
    private final OrderType orderType;
    private final String stockSymbol;
    private int quantity;
    private final double price;
    private final LocalDateTime timestamp;
    private OrderStatus status;

    public Order(int userId, OrderType orderType, String stockSymbol, int quantity, double price) {
        this.orderId = orderIdGenerator.getAndIncrement();
        this.userId = userId;
        this.orderType = orderType;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = LocalDateTime.now();
        this.status = OrderStatus.ACCEPTED;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public int getOrderId() { return orderId; }
    public int getUserId() { return userId; }
    public OrderType getOrderType() { return orderType; }
    public String getStockSymbol() { return stockSymbol; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public OrderStatus getStatus() { return status; }

    public void cancel() { this.status = OrderStatus.CANCELED; }
}
