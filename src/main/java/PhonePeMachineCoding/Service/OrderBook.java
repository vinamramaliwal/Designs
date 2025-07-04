package PhonePeMachineCoding.Service;

import PhonePeMachineCoding.DTO.Order;
import PhonePeMachineCoding.DTO.OrderStatus;
import PhonePeMachineCoding.DTO.OrderType;
import PhonePeMachineCoding.DTO.Trade;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OrderBook {
        private final PriorityQueue<Order> buyOrders;
        private final PriorityQueue<Order> sellOrders;
        private final ScheduledExecutorService scheduler;
        private static final int TRADE_EXPIRY_TIME = 30; // seconds


        public OrderBook() {
            buyOrders = new PriorityQueue<>(Comparator.comparingDouble(Order::getPrice).reversed().thenComparing(Order::getTimestamp));
            sellOrders = new PriorityQueue<>(Comparator.comparingDouble(Order::getPrice).thenComparing(Order::getTimestamp));
            scheduler = Executors.newScheduledThreadPool(1);
            startTradeExpiryScheduler();
        }

        public synchronized void placeOrder(Order order) {
            if (order.getOrderType() == OrderType.BUY) {
                buyOrders.add(order);
            } else {
                sellOrders.add(order);
            }
            matchOrders();
        }

        public synchronized void cancelOrder(Order order) {
            if (order.getOrderType() == OrderType.BUY) {
                buyOrders.remove(order);
            } else {
                sellOrders.remove(order);
            }
            order.cancel();
        }

        private synchronized void matchOrders() {
            while (!buyOrders.isEmpty() && !sellOrders.isEmpty()) {
                Order buy = buyOrders.peek();
                Order sell = sellOrders.peek();

                if (buy.getPrice() >= sell.getPrice()) {
                    int tradeQuantity = Math.min(buy.getQuantity(), sell.getQuantity());
                    double tradePrice = sell.getPrice();

                    Trade trade = new Trade(buy.getOrderId(), sell.getOrderId(), buy.getStockSymbol(), tradeQuantity, tradePrice);
                    System.out.println("Trade Executed: " + trade);

                    buy.setQuantity(buy.getQuantity()- tradeQuantity);
                    sell.setQuantity(sell.getQuantity() -  tradeQuantity);

                    if (buy.getQuantity() == 0) {
                        buyOrders.poll();
                        buy.setStatus(OrderStatus.EXECUTED);
                    } else{
                        buy.setStatus(OrderStatus.PARTIALLY_EXECUTED);
                    }
                    if (sell.getQuantity() == 0) {
                        sellOrders.poll();
                        sell.setStatus(OrderStatus.EXECUTED);
                    } else{
                        buy.setStatus(OrderStatus.PARTIALLY_EXECUTED);
                    }
                } else {
                    break;
                }
            }
        }

    private void startTradeExpiryScheduler() {
        scheduler.scheduleAtFixedRate(this::removeExpiredOrders, 10, 10, TimeUnit.SECONDS);
    }

    private synchronized void removeExpiredOrders() {
        LocalDateTime now = LocalDateTime.now();

        Iterator<Order> buyIterator = buyOrders.iterator();
        while (buyIterator.hasNext()) {
            Order order = buyIterator.next();
            if (Duration.between(order.getTimestamp(), now).getSeconds() > TRADE_EXPIRY_TIME) {
                buyIterator.remove();
                order.setStatus(OrderStatus.CANCELED);
                System.out.println("Buy Order Expired: " + order.getOrderId());
            }
        }

        Iterator<Order> sellIterator = sellOrders.iterator();
        while (sellIterator.hasNext()) {
            Order order = sellIterator.next();
            if (Duration.between(order.getTimestamp(), now).getSeconds() > TRADE_EXPIRY_TIME) {
                sellIterator.remove();
                order.setStatus(OrderStatus.CANCELED);
                System.out.println("Sell Order Expired: " + order.getOrderId());
            }
        }
    }
    }
