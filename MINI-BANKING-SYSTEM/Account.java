import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNo;
    private String accountHolderName;
    private String pin;
    private double balance;
    List<Transaction> transactions = new ArrayList<>();

    public Account(String accountNo,String accountHolderName,String pin,double balance){
        this.accountNo = accountNo;
        this.accountHolderName = accountHolderName;
        this.pin = pin;
        this.deposit(balance);
    }
    public boolean deposit(double amount){
        if(amount>0){
            balance  += amount;
            Transaction t = new Transaction(PaymentType.DEPOSIT,amount);
            this.transactions.add(t);
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount){
        if(this.balance>=amount){
            this.balance -= amount;
            Transaction t = new Transaction(PaymentType.WITHDRAW,amount);
            this.transactions.add(t);
            return true;
        }
        return false;
    }

    public boolean verifyPin(String pin){
        String trim = pin.trim();
        if(trim.equals(this.pin)){
            return true;
        }
        return false;
    }

    public void getMiniStatement(){
        System.out.println("---Statement---");
        System.out.println("Account Number :"+this.accountNo);
        System.out.println("Account Holder Name :"+this.accountHolderName);
        System.out.println("Account balance :"+this.balance);
        System.out.println("Last 5 Transactions : ");
        for (int i = 0; i < transactions.size(); i++) {
            if(i==5){
                break;
            }
            System.out.print(transactions.get(transactions.size()-1-i).getTransId()+"\t");
            System.out.print(transactions.get(transactions.size()-1-i).getTimeStamp()+"\t");
            System.out.print(transactions.get(transactions.size()-1-i).getPaymentType()+"\t");
            System.out.print(transactions.get(transactions.size()-1-i).getAmount()+"\t\n");
        }
    }

}
