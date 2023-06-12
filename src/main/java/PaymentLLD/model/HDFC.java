package PaymentLLD.model;

import java.util.List;

public class HDFC extends Banks{

    public HDFC(List<PaymentMethord> paymentMethodList) {
        super(paymentMethodList);
    }

    public double getSuccessRate(PaymentMethord paymentMethod) {
        return 0.6;
    }

}
