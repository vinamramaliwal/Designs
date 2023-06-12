package PaymentLLD.Handlers;

import PaymentLLD.model.Banks;
import PaymentLLD.model.PaymentMethord;

public class PaymentStrategy {

    PaymentMethord paymentMethord;

    public Banks getBank() {
        return BankRouter.getInstance().getBankForPaymentMethod(paymentMethord);
    }

}
