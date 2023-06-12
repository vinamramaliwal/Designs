package PaymentLLD.Handlers;

import PaymentLLD.model.PaymentMethord;

public class UPIPayment extends PaymentStrategy {

    public UPIPayment(){
        this.paymentMethord = PaymentMethord.UPI;
    }

    public  void getUPIDetails(){}
}
