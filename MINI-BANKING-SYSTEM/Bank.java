import java.util.HashMap;
import java.util.UUID;

public class Bank {
    private HashMap<String, Account> accounts = new HashMap<>();

    public String openAccount(String name, String pin, double initialDeposit){
        String bankId = UUID.randomUUID().toString().substring(0, 6);
        Account a = new Account(bankId, name, pin, initialDeposit);
        accounts.put(bankId, a);
        return bankId;
    }

    public Account getAccount(String accountNo) {
        return accounts.get(accountNo);
    }

    public boolean transferFunds(String fromAccountNo, String toAccountNo, double amount, String pin) {
        Account from = accounts.get(fromAccountNo);
        Account to = accounts.get(toAccountNo);
        if(from != null && to != null){
            if(!from.verifyPin(pin)){
                return false;
            }
            if(from.withdraw(amount)){
                to.deposit(amount);
                return true;
            }
            else{
                return false;
            }
        }
        return false;

    }
}
