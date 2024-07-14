
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


    // Transaction class to hold transaction details
    class Transaction {
        private String type;
        private double amount;

        public Transaction(String type, double amount) {
            this.type = type;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return type + ": $" + amount;
        }
    }

    // ATM class to simulate ATM functionalities
    public class ATM {
        private double balance;
        private String accountHolderName;
        private int pin;
        private List<Transaction> transactions;

        // Constructor to initialize ATM with account details
        public ATM(String accountHolderName, int pin) {
            this.accountHolderName = accountHolderName;
            this.pin = pin;
            this.balance = 0; // Starting balance is zero
            this.transactions = new ArrayList<>();
        }

        // Method to display menu and handle user actions
        public void run() {
            Scanner scanner = new Scanner(System.in);
            boolean sessionActive = true;

            while (sessionActive) {
                System.out.println("\nATM Menu:");
                System.out.println("1. Account Balance Inquiry");
                System.out.println("2. Cash Withdrawal");
                System.out.println("3. Cash Deposit");
                System.out.println("4. PIN Change");
                System.out.println("5. Transaction History");
                System.out.println("6. Exit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        withdraw(scanner);
                        break;
                    case 3:
                        deposit(scanner);
                        break;
                    case 4:
                        changePIN(scanner);
                        break;
                    case 5:
                        showTransactionHistory();
                        break;
                    case 6:
                        sessionActive = false;
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
            }
            scanner.close();
        }

        // Method to check account balance
        private void checkBalance() {
            System.out.println("Account Balance: $" + balance);
        }

        // Method to withdraw cash
        private void withdraw(Scanner scanner) {
            System.out.print("Enter amount to withdraw: $");
            double amount = scanner.nextDouble();
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.println("$" + amount + " withdrawn successfully.");
                System.out.println("Remaining Balance: $" + balance);
                // Record transaction
                recordTransaction("Withdrawal", amount);
            } else {
                System.out.println("Invalid amount or insufficient balance.");
            }
        }

        // Method to deposit cash
        private void deposit(Scanner scanner) {
            System.out.print("Enter amount to deposit: $");
            double amount = scanner.nextDouble();
            if (amount > 0) {
                balance += amount;
                System.out.println("$" + amount + " deposited successfully.");
                System.out.println("Current Balance: $" + balance);
                // Record transaction
                recordTransaction("Deposit", amount);
            } else {
                System.out.println("Invalid amount.");
            }
        }

        // Method to change PIN
        private void changePIN(Scanner scanner) {
            System.out.print("Enter current PIN: ");
            int currentPIN = scanner.nextInt();
            if (currentPIN == pin) {
                System.out.print("Enter new PIN: ");
                int newPIN = scanner.nextInt();
                pin = newPIN;
                System.out.println("PIN changed successfully.");
            } else {
                System.out.println("Incorrect PIN. PIN change failed.");
            }
        }

        // Method to show transaction history
        private void showTransactionHistory() {
            System.out.println("Transaction History:");
            if (transactions.isEmpty()) {
                System.out.println("No transactions yet.");
            } else {
                for (Transaction transaction : transactions) {
                    System.out.println(transaction);
                }
            }
        }

        // Method to record transactions
        private void recordTransaction(String type, double amount) {
            Transaction transaction = new Transaction(type, amount);
            transactions.add(transaction);
        }

        public static void main(String[] args) {
            // Example usage
            ATM atm = new ATM("Sakshi Akotkar", 1234);
            atm.run();
        }
    }


