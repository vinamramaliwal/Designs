package PaymentLLD.services;

import PaymentLLD.Handlers.PaymentStrategy;
import PaymentLLD.model.Client;
import PaymentLLD.model.PaymentMethord;

import java.util.Arrays;
import java.util.List;

public interface PaymentGateway {

    public void addClient(Client client);

    public void removeClient(Client client);

    public  boolean hasClient(Client client);

    public List<PaymentMethord> supportedPayModes(Client client);

    public List<PaymentMethord> supportedPayModes();

    public void addSupportForPayModes(Client client, PaymentMethord paymentMethord);

    public void removePayModes(Client client, PaymentMethord paymentMethord);

    public void makePayment(Client client,PaymentStrategy paymentStrategy, Double price);
}
