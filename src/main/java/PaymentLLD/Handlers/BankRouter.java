package PaymentLLD.Handlers;

import PaymentLLD.model.Banks;
import PaymentLLD.model.HDFC;
import PaymentLLD.model.PaymentMethord;
import PaymentLLD.model.SBI;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BankRouter {

        List<Banks> banks;

        static BankRouter instance;

        public static BankRouter getInstance() {
            if (instance == null) {
                instance = new BankRouter();
            }
            return instance;
        }

        public BankRouter() {
            HDFC hdfc = new HDFC((Arrays.asList(PaymentMethord.Card, PaymentMethord.NetBanking)));
            SBI sbi = new SBI((Arrays.asList(PaymentMethord.UPI, PaymentMethord.Card)));
            this.banks = Arrays.asList(hdfc, sbi);
        }

        public Banks getBankForPaymentMethod(PaymentMethord paymentMethod) {
            double successRate = 0;
            Banks selectedBank = banks.get(0);
            List<Banks> eligibleBanks = banks.stream().filter(b -> b.supports(paymentMethod)).collect(Collectors.toList());
            for (Banks bank : eligibleBanks) {
                if (successRate < bank.getSuccessRate(paymentMethod)) {
                    successRate = bank.getSuccessRate(paymentMethod);
                    selectedBank = bank;
                }
            }
            return selectedBank;
        }

}
