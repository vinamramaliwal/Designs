package PaymentLLD;

import PaymentLLD.Handlers.PaymentStrategy;
import PaymentLLD.Handlers.PaymentStrategyFactory;
import PaymentLLD.Handlers.UPIPayment;
import PaymentLLD.model.Client;
import PaymentLLD.model.PaymentMethord;
import PaymentLLD.services.PaymentGateway;
import PaymentLLD.services.PaymentGatewayImpl;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainClass {

    public  static  void main(String args[]){

        Client client = new Client("FLIPKART", new ArrayList(Arrays.asList(PaymentMethord.UPI, PaymentMethord.Card)));
        PaymentGateway paymentGateway = new PaymentGatewayImpl(new ArrayList(Arrays.asList(client)));
        Client client2 = new Client("AMAZON", (Arrays.asList(PaymentMethord.NetBanking)));
        paymentGateway.addClient(client2);

        PaymentStrategyFactory factory = new PaymentStrategyFactory();
        PaymentStrategy upi = factory.getPaymentStrategy("UPI");

        paymentGateway.makePayment(client, upi, 100.0);

        System.out.println("supported method for the client 1: " + paymentGateway.supportedPayModes(client));
    }

    // Singleton pattern for BankRouter;
    // Strategy pattern to get banks based on paymentMethod
    // Factory pattern to getPaymentStrategy

}
