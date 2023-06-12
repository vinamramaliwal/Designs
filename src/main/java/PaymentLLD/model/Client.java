package PaymentLLD.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Client {

    private final String name;
    private final List<PaymentMethord> paymentMethordList;

}
