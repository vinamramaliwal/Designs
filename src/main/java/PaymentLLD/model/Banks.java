package PaymentLLD.model;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public abstract class Banks {

    private List<PaymentMethord> paymentMethodList;

    public  void processPayment(){

    }

    public boolean supports(PaymentMethord paymentMethod) {
        return paymentMethodList.contains(paymentMethod);
    }

    public abstract double getSuccessRate(PaymentMethord paymentMethod);

    public void addPaymentMethod(PaymentMethord paymentMethord){
        paymentMethodList.add(paymentMethord);
    }

}
