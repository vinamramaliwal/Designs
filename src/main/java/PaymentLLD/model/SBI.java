package PaymentLLD.model;

import java.util.List;

public class SBI extends Banks{

    public SBI(List<PaymentMethord> paymentMethodList) {
        super(paymentMethodList);
    }

    public double getSuccessRate(PaymentMethord paymentMethod) {
        return 0.7;
    }
}
