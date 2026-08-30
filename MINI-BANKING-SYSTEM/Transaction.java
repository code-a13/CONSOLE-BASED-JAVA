import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private String transId;
    private String timeStamp;
    private PaymentType paymentType;
    private double amount;

    public Transaction(PaymentType paymentType, double amount) {
        this.transId = UUID.randomUUID().toString().substring(0, 8);
        this.timeStamp = LocalDateTime.now().toString();
        this.paymentType = paymentType;
        this.amount = amount;
    }

    public String getTransId() {
        return transId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }
}
