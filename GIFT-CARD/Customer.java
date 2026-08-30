import java.util.ArrayList;
import java.util.List;

public class Customer {
    private static int IdCounter = 1;
    private int custId;
    private String name;
    private double accountBalance;
    private List<GiftCard> mygiftCards = new ArrayList<>(); // one to many

    //create Customer
    Customer(){

    }
    public GiftCard getGiftCardById(int giftId) {
        for (GiftCard g : mygiftCards) {
            if (g.getCardNo() == giftId) {
                return g;
            }
        }
        return null;
    }

    public void topUpGiftCard(int giftId, double amount) {
        GiftCard g = getGiftCardById(giftId);
        if (g == null) {
            System.out.println("Gift Card not found!");
            return;
        }
        if (amount >= 0 && accountBalance >= amount) {
            g.setCardBalance(amount);
            accountBalance -= amount;
            System.out.println("Top up successful!");
        } else {
            System.out.println("Insufficient Funds in Customer Account.");
        }
    }
    public void addGiftCard(GiftCard gc){
        mygiftCards.add(gc);
    }
    public void displayGiftCard(){
        for(GiftCard g : mygiftCards){
            System.out.println("GIFT CARD ID :"+g.getCardNo());
            System.out.println("GIFT CARD OWNER : "+g.getOwner().getName());
            System.out.println("GIFT CARD STATUS : "+g.getStatus());
            System.out.println("GIFT CARD LEVEL : "+g.getCurrentLevel());
            System.out.println("GIFT CARD BALANCE : "+g.getCardBalance());
        }
    }

    public int getCustId(){
        return custId;
    }
    public String getName(){
        return name;
    }
    public void setWithdrawBalance(double amount){
        if(amount<=this.accountBalance){
            this.accountBalance -= amount;
        }
        else{
            System.out.println("Low Balance");
        }
    }
    public double getAccountBalance(){
        return accountBalance;
    }

    public void displayAllTransactions() {
        for(GiftCard g : mygiftCards) {
            System.out.println("--- Transactions for Gift Card ID: " + g.getCardNo() + " ---");
            g.getTransactionHistory(); // This calls the awesome print method you already wrote!
        }
    }
    Customer(String name, double accountBalance){
        this.custId = IdCounter++;
        this.name = name;
        this.accountBalance = accountBalance;
    }

    void addBalance(double balance){
        if(balance>0){
            accountBalance+=balance;
        }
        else{
            System.out.println("Cant add Negative Values");
        }
    }

}
