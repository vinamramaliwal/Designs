package PhonePeMachineCoding.DTO;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Trade {
    private static final AtomicInteger tradeIdGenerator = new AtomicInteger(1);
    private final int tradeId;
    private final int buyerOrderId;
    private final int sellerOrderId;
    private final String stockSymbol;
    private final int quantity;
    private final double price;
    private final LocalDateTime tradeTimestamp;

    public Trade(int buyerOrderId, int sellerOrderId, String stockSymbol, int quantity, double price) {
        this.tradeId = tradeIdGenerator.getAndIncrement();
        this.buyerOrderId = buyerOrderId;
        this.sellerOrderId = sellerOrderId;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.tradeTimestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Trade{" +
                "tradeId=" + tradeId +
                ", buyerOrderId=" + buyerOrderId +
                ", sellerOrderId=" + sellerOrderId +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", tradeTimestamp=" + tradeTimestamp +
                '}';
    }
}
