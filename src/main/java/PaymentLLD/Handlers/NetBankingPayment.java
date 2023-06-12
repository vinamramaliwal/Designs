package PaymentLLD.Handlers;

import PaymentLLD.model.PaymentMethord;

public class NetBankingPayment extends  PaymentStrategy{

    public NetBankingPayment(){
        this.paymentMethord = PaymentMethord.NetBanking;
    }

    void setNetBankingDetails(){};

}
