public class Purchase {
    static int transId = 1;
    private int transactionNo;
    private double purchaseAmount;
    private GiftCard card;
    Purchase(GiftCard card,double amount) {
        this.transactionNo = transId++;
        this.card = card;
        this.purchaseAmount = amount;
    }

    public int getTransactionNo() { return transactionNo; }
    public double getPurchaseAmount() { return purchaseAmount; }
}