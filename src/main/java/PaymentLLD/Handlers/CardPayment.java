package PaymentLLD.Handlers;

import PaymentLLD.model.PaymentMethord;

public class CardPayment extends  PaymentStrategy{

    public CardPayment(){
        this.paymentMethord = PaymentMethord.Card;
    }

    void getCardDetails(){};

}
