package PaymentLLD.services;

import PaymentLLD.Handlers.Payment;
import PaymentLLD.Handlers.PaymentStrategy;
import PaymentLLD.model.Client;
import PaymentLLD.model.PaymentMethord;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class PaymentGatewayImpl implements PaymentGateway {

    List<Client> clientList;

    public void addClient(Client client){
        clientList.add(client);
    }

    public void removeClient(Client client){
        clientList.remove(client);
    }

    public  boolean hasClient(Client client){
        return clientList.contains(client);
    }

    public List<PaymentMethord> supportedPayModes(Client client){
        return client.getPaymentMethordList();
    }

    public List<PaymentMethord> supportedPayModes(){
        return Arrays.asList(PaymentMethord.values());
    }

    public void addSupportForPayModes(Client client, PaymentMethord paymentMethord){
        client.getPaymentMethordList().add(paymentMethord);
    }

    public void removePayModes(Client client, PaymentMethord paymentMethord){
        client.getPaymentMethordList().remove(paymentMethord);
    }

    @Override
    public void makePayment(Client client, PaymentStrategy paymentStrategy, Double price) {
         Payment payment= new Payment(paymentStrategy, client, price);
         payment.processPayment();

    }


}
