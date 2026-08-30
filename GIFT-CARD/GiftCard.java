import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.List;

public class GiftCard {
    private static int CardCounter = 1;
    private int cardNo;
    private int pin;
    private double cardBalance;
    private int points;
    //Relations
    private Customer owner;
    private Level currentLevel;
    private CardStatus status;
    private List<Purchase> transactionHistory = new ArrayList<>();

    public int getCardNo() {
        return cardNo;
    }

    public int getPin() {
        return pin;
    }
    public void blockCard(int enteredPin) {
        if (this.pin != enteredPin) {
            System.out.println("Incorrect PIN!");
            return;
        }
        this.status = CardStatus.BLOCKED;
        System.out.println("Card Blocked");
    }
    public void unblockCard(int enteredPin) {
        if (this.pin != enteredPin) {
            System.out.println("Incorrect PIN!");
            return;
        }
        this.status = CardStatus.OPENED;
        System.out.println("Card Unblocked");
    }
    public void closeCard(int enteredPin){
        if(this.pin != enteredPin){
            System.out.println("Incorrect Pin ");
            return;
        }
        if(this.cardBalance>0){
            owner.addBalance(this.cardBalance);
        }
        this.cardBalance = 0;
        this.status = CardStatus.CLOSED;
        System.out.println("Card Closed");
    }

    public void purchasewithCard(double amount, int enteredPin) {
        if (pin != enteredPin) {
            System.out.println("Incorrect PIN!");
            return;
        }
        if (status != CardStatus.OPENED) {
            System.out.println("Card is not active!");
            return;
        }
        if (amount > cardBalance) {
            System.out.println("Insufficient Gift Card Balance!");
            return;
        }
        reduceCardBalance(amount);
        Purchase p = new Purchase(this, amount);
        addTransaction(p);
        System.out.println("Purchase successful! Transaction ID: " + p.getTransactionNo());
        System.out.println("Remaining Balance: " + this.cardBalance);
        int score = (int) (amount/500)*50;
        setPoints(score);
        if(score>1000){
            currentLevel = Level.PLATINUM;
        } else if (score >501 && score <=1000) {
            currentLevel = Level.GOLD;
        } else if (score>201 && score <=500) {
            currentLevel = Level.SILVER;
        }
        else{
            currentLevel = Level.BRONZE;
        }
    }
    public void addTransaction(Purchase purchase){
        transactionHistory.add(purchase);
    }
    public double getCardBalance() {
        return cardBalance;
    }

    public Customer getOwner() {
        return owner;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public int getPoints() {
        return points;
    }

    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public void setPoints(int points) {
        this.points += points;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public void setCardBalance(double cardBalance) {
        this.cardBalance += cardBalance;
    }
    public void reduceCardBalance(double amount){
        this.cardBalance -= amount;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }
    public void getTransactionHistory() {
        for(Purchase p : transactionHistory){
            System.out.println("Transaction Id : "+p.getTransactionNo());
            System.out.println("Purchase Amount : "+p.getPurchaseAmount());
        }
    }
    GiftCard(Customer cus , int pin , double balance){
        this.cardNo = CardCounter++;
        this.owner = cus;
        this.pin = pin;
        if(balance>0 && balance <= cus.getAccountBalance()){
            this.cardBalance = balance;
            cus.setWithdrawBalance(balance);
            System.out.println("Amount Added to Gift Card ");
        }
        else{
            System.out.println("Insufficient Funds");
            System.out.println("Current Balance :"+owner.getAccountBalance());
        }
        this.points = 0;
        this.currentLevel = Level.BEGINNER;
        this.status = CardStatus.OPENED;
    }

}
