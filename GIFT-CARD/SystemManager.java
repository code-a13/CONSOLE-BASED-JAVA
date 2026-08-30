import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SystemManager {
    List<Customer> allCustomers = new ArrayList<>();

    void displayallCustomers() {
        if (allCustomers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        System.out.println("Customers :");
        for (Customer cus : allCustomers) {
            System.out.println("ID : " + cus.getCustId());
            System.out.println("NAME : " + cus.getName());
            System.out.println("BALANCE : " + cus.getAccountBalance());
            System.out.println("----------------------");
        }
    }

    public Customer getCustomerSafely(int id) {
        for (Customer c : allCustomers) {
            if (c.getCustId() == id) {
                return c;
            }
        }
        return null;
    }

    public void addMoney(Customer cus, double amount) {
        if (amount > 0) {
            cus.addBalance(amount);
        } else {
            System.out.println("Amount must be greater than zero.");
        }
    }

    public void handleCreateCustomer(Scanner sc) {
        System.out.println("Enter Customer Name : ");
        String name = sc.nextLine();
        System.out.println("Enter Account Balance : ");
        double balance = sc.nextDouble();

        if (balance < 0) {
            System.out.println("Initial balance cannot be negative.");
            return;
        }

        Customer c = new Customer(name, balance);
        allCustomers.add(c);
        System.out.println("Customer Added Successfully !");
    }

    public void handleTopUp(Scanner sc) {
        System.out.println("Enter Customer Id :");
        int cusId = sc.nextInt();
        Customer topUpCus = getCustomerSafely(cusId);

        if (topUpCus != null) {
            System.out.println("Enter Gift Card No :");
            int gift = sc.nextInt();
            System.out.println("Enter Amount to Top Up : ");
            double amountTopUp = sc.nextDouble();
            if (amountTopUp > 0) {
                topUpCus.topUpGiftCard(gift, amountTopUp);
            } else {
                System.out.println("Top up amount must be greater than zero.");
            }
        } else {
            System.out.println("Customer not found!");
        }
    }

    public void handlePurchase(Scanner sc) {
        System.out.println("Enter Customer Id :");
        int cusId = sc.nextInt();
        Customer purchaseCus = getCustomerSafely(cusId);

        if (purchaseCus != null) {
            System.out.println("Enter Gift Card No : ");
            int giftNo = sc.nextInt();
            GiftCard purchaseCard = purchaseCus.getGiftCardById(giftNo);

            if (purchaseCard != null) {
                System.out.println("Enter PIN: ");
                int pin = sc.nextInt();
                System.out.println("Enter Purchase Amount : ");
                double amount = sc.nextDouble();
                if (amount > 0) {
                    purchaseCard.purchasewithCard(amount, pin);
                } else {
                    System.out.println("Purchase amount must be positive.");
                }
            } else {
                System.out.println("Gift card not found!");
            }
        } else {
            System.out.println("Customer not found!");
        }
    }

    public void handleAddMoney(Scanner sc) {
        System.out.println("Enter Customer Id :");
        int cusId = sc.nextInt();
        Customer cus = getCustomerSafely(cusId);

        if (cus != null) {
            System.out.println("Enter amount to add : ");
            double amount = sc.nextDouble();
            if (amount > 0) {
                cus.addBalance(amount);
                System.out.println("Money added successfully!");
            } else {
                System.out.println("Amount must be greater than zero.");
            }
        } else {
            System.out.println("Customer not found!");
        }
    }

    public void handleBlockCard(Scanner sc) {
        System.out.println("Enter Customer Id :");
        int cusId = sc.nextInt();
        Customer cus = getCustomerSafely(cusId);

        if (cus != null) {
            System.out.println("Enter Gift Id :");
            int giftId = sc.nextInt();
            GiftCard gc = cus.getGiftCardById(giftId);

            if (gc != null) {
                System.out.println("Enter Pin : ");
                int pin = sc.nextInt();
                gc.blockCard(pin);
            } else {
                System.out.println("Gift card not found!");
            }
        } else {
            System.out.println("Customer not found!");
        }
    }

    public void handleUnblockCard(Scanner sc) {
        System.out.println("Enter Customer Id :");
        int cusId = sc.nextInt();
        Customer cus = getCustomerSafely(cusId);

        if (cus != null) {
            System.out.println("Enter Gift Id :");
            int giftId = sc.nextInt();
            GiftCard gc = cus.getGiftCardById(giftId);

            if (gc != null) {
                System.out.println("Enter Pin : ");
                int pin = sc.nextInt();
                gc.unblockCard(pin);
            } else {
                System.out.println("Gift card not found!");
            }
        } else {
            System.out.println("Customer not found!");
        }
    }

    public void handleCloseCard(Scanner sc) {
        System.out.println("Enter Customer Id :");
        int cusId = sc.nextInt();
        Customer cus = getCustomerSafely(cusId);

        if (cus != null) {
            System.out.println("Enter Gift Id :");
            int giftId = sc.nextInt();
            GiftCard gc = cus.getGiftCardById(giftId);

            if (gc != null) {
                System.out.println("Enter Pin : ");
                int pin = sc.nextInt();
                gc.closeCard(pin);
            } else {
                System.out.println("Gift card not found!");
            }
        } else {
            System.out.println("Customer not found!");
        }
    }

    public void handleGiftCard(Scanner sc) {
        System.out.println("Gift Cards :");
        System.out.println("Enter Customer Id : ");
        int cusId = sc.nextInt();
        Customer cus = getCustomerSafely(cusId);

        if (cus != null) {
            cus.displayGiftCard();
        } else {
            System.out.println("Customer not found!");
        }
    }

    public void handleAddGiftCard(Scanner sc) {
        System.out.println("Gift Card :");
        displayallCustomers();
        System.out.println("Enter the Customer ID : ");
        int cusId = sc.nextInt();

        Customer cus = getCustomerSafely(cusId);

        if (cus != null) {
            System.out.println("Enter a Pin :");
            int pin = sc.nextInt();
            System.out.println("Enter Amount to add : ");
            double amount = sc.nextDouble();

            if (amount > 0) {
                GiftCard gc = new GiftCard(cus, pin, amount);
                cus.addGiftCard(gc);
                System.out.println("Gift Card successfully added!");
            } else {
                System.out.println("Gift card amount must be greater than zero.");
            }
        } else {
            System.out.println("Customer not found!");
        }
    }

    public void handleDisplay(Scanner sc) {
        System.out.println("All Customers : ");
        displayallCustomers();

        System.out.println("\nAll Gift Cards of Each Customer :");
        for (Customer cust : allCustomers) {
            cust.displayGiftCard();
        }

        System.out.println("\nTransactions History : ");
        for (Customer cust : allCustomers) {
            System.out.println("Customer: " + cust.getName());
            cust.displayAllTransactions();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SystemManager sm = new SystemManager();
        boolean isMenu = true;

        while (isMenu) {
            try {
                System.out.println("\n------MENU-------");
                System.out.println("1.CREATE CUSTOMER      2.DISPLAY CUSTOMER          3.ADD GIFT CARD           4.GIFT CARD DETAILS");
                System.out.println("5.GIFT CARD TOP UP     6.PURCHASE AN ITEM          7.CLOSE GIFT CARD         8.BLOCK GIFT CARD");
                System.out.println("9.UNBLOCK GIFT CARD    10.ADD MONEY TO CUSTOMER    11.DISPLAY INFORMATION    12.EXIT");
                System.out.print("Enter your Choice : ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1: sm.handleCreateCustomer(sc); break;
                    case 2: sm.displayallCustomers(); break;
                    case 3: sm.handleAddGiftCard(sc); break;
                    case 4: sm.handleGiftCard(sc); break;
                    case 5: sm.handleTopUp(sc); break;
                    case 6: sm.handlePurchase(sc); break;
                    case 7: sm.handleCloseCard(sc); break;
                    case 8: sm.handleBlockCard(sc); break;
                    case 9: sm.handleUnblockCard(sc); break;
                    case 10: sm.handleAddMoney(sc); break;
                    case 11: sm.handleDisplay(sc); break;
                    case 12:
                        isMenu = false;
                        System.out.println("Exiting Application");
                        break;
                    default:
                        System.out.println("Invalid Choice. Please enter a number between 1 and 12.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input format. Please enter numbers where expected.");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                sc.nextLine();
            }
        }
        sc.close();
    }
}