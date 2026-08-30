import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();
        boolean running = true;

        while (running) {
            System.out.println("\n=== WELCOME TO MINI BANK ===");
            System.out.println("1. Open New Account");
            System.out.println("2. Login to Account");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter Account Holder Name: ");
                String name = sc.nextLine();
                System.out.print("Set a 4-digit PIN: ");
                String pin = sc.nextLine();
                System.out.print("Enter Initial Deposit: ");
                double deposit = sc.nextDouble();

                String accNo = bank.openAccount(name, pin, deposit);
                System.out.println("Account Created Successfully!");
                System.out.println("YOUR ACCOUNT NUMBER IS: " + accNo);
                System.out.println("Please save this to login.");

            } else if (choice == 2) {
                System.out.print("Enter Account Number: ");
                String accNo = sc.nextLine();
                Account currentAccount = bank.getAccount(accNo);

                if (currentAccount == null) {
                    System.out.println("Account not found!");
                    continue;
                }

                // PIN Authentication (3 Attempts)
                boolean authenticated = false;
                for (int attempts = 1; attempts <= 3; attempts++) {
                    System.out.print("Enter PIN (Attempt " + attempts + "/3): ");
                    String enteredPin = sc.nextLine();

                    if (currentAccount.verifyPin(enteredPin)) {
                        authenticated = true;
                        break;
                    } else {
                        System.out.println("Incorrect PIN.");
                    }
                }

                if (!authenticated) {
                    System.out.println("Account locked due to 3 failed attempts. Returning to main menu.");
                    continue;
                }

                boolean loggedIn = true;
                while (loggedIn) {
                    System.out.println("\n--- ACCOUNT DASHBOARD ---");
                    System.out.println("1. Deposit");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Fund Transfer");
                    System.out.println("4. Mini Statement");
                    System.out.println("5. Logout");
                    System.out.print("Choose an option: ");

                    int action = sc.nextInt();
                    sc.nextLine();

                    if (action == 1) {
                        System.out.print("Enter amount to deposit: ");
                        double amount = sc.nextDouble();
                        if (currentAccount.deposit(amount)) {
                            System.out.println("Deposit successful!");
                        } else {
                            System.out.println("Deposit failed. Invalid amount.");
                        }
                    }
                    else if (action == 2) {
                        System.out.print("Enter amount to withdraw: ");
                        double amount = sc.nextDouble();
                        if (currentAccount.withdraw(amount)) {
                            System.out.println("Withdrawal successful! Please collect your cash.");
                        } else {
                            System.out.println("Withdrawal failed. Insufficient funds.");
                        }
                    }
                    else if (action == 3) {
                        System.out.print("Enter Target Account Number: ");
                        String targetAcc = sc.nextLine();
                        System.out.print("Enter amount to transfer: ");
                        double amount = sc.nextDouble();
                        sc.nextLine(); // Consume newline
                        System.out.print("Enter your PIN to confirm: ");
                        String pin = sc.nextLine();

                        if (bank.transferFunds(accNo, targetAcc, amount, pin)) {
                            System.out.println("Transfer successful!");
                        } else {
                            System.out.println("Transfer failed. Check target account, balance, or PIN.");
                        }
                    }
                    else if (action == 4) {
                        currentAccount.getMiniStatement();
                    }
                    else if (action == 5) {
                        loggedIn = false;
                        System.out.println("Logged out successfully.");
                    }
                }

            } else if (choice == 3) {
                running = false;
                System.out.println("Thank you for using Mini Bank. Goodbye!");
            }
        }
        sc.close();
    }
}