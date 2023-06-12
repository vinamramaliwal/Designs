package PaymentLLD.Handlers;

import PaymentLLD.model.Banks;
import PaymentLLD.model.Client;
import PaymentLLD.model.PaymentStatus;

import java.util.UUID;

public class Payment {

    Client client;
    String id;
    PaymentStatus status;
    Banks bank;
    Double price;
    PaymentStrategy paymentStrategy;

    public Payment(PaymentStrategy paymentStrategy, Client client, Double price) {
        id= UUID.randomUUID().toString();
        this.paymentStrategy = paymentStrategy;
        this.client =client;
        this.price = price;
        this.bank = paymentStrategy.getBank();
    }

    public void  processPayment() {
            this.bank.processPayment();
    }
}
