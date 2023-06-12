package PaymentLLD.Handlers;

import FactoryPattern.Circle;
import FactoryPattern.Rectangle;

public class PaymentStrategyFactory {

    public PaymentStrategy getPaymentStrategy(String input){
        switch (input){
            case "UPI":
                return new UPIPayment();
            case "NB":
                return new NetBankingPayment();
            case "CARD":
                return new CardPayment();
            default:
                return null;
        }
    }
}
