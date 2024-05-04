import java.util.Scanner;
import java.util.List;

public class ATM {
    private User currentUser;
    private Scanner scanner;

    public ATM(){
        scanner = new Scanner(System.in);
    }

    public void start(){
        System.out.println("Welcome to the ATM.");

        System.out.println("Please enter your user-id: ");
        String userID = scanner.nextLine();

        System.out.println("Please enter your PIN: ");
        String pin = scanner.nextLine();

        User user = authenticateUser(userID, pin);
        if(user != null) {
            currentUser = user;
            System.out.println("Authentication successful. Welcome, " + currentUser.getUserId() + "!");
            boolean running = true;
            while (running) {
                displayMenu();
                System.out.print("\nDo you want to continue? (y/n): ");
                String choice = scanner.nextLine();
                if (!choice.equalsIgnoreCase("y")) {
                    running = false;
                }
            }
            System.out.println("Thank you for using the ATM system. Goodbye!");
        } else {
            System.out.println("Authentication failed. Please try again.");
        }
    }

    private User authenticateUser(String userId, String pin) {
        if (userId.equals("user123") && pin.equals("1234")) {
            return new User("user123", "John Doe", 1000.0);
        } else {
            return null;
        }
    }

    private void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. View Transaction History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");

        System.out.print("\nEnter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
                displayTransactionHistory();
                break;
                case 2:
                System.out.print("Enter amount to withdraw: ");
                double withdrawAmount = scanner.nextDouble();
                scanner.nextLine();
                withdraw(withdrawAmount);
                break;
            case 3:
                System.out.print("Enter amount to deposit: ");
                double depositAmount = scanner.nextDouble();
                scanner.nextLine();
                deposit(depositAmount);
                break;
            case 4:
                System.out.print("Enter amount to transfer: ");
                double transferAmount = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Enter recipient's account number: ");
                String recipientAccountNumber = scanner.nextLine();
                transfer(transferAmount, recipientAccountNumber);
                break;
            case 5:
                quit();
                break;
            default:
                System.out.println("Invalid choice. Please enter a number from 1 to 5.");
                break;
        }
    }

    public void withdraw(double amount) {
        if (currentUser != null) {
            if (amount > 0 && currentUser.getBalance() >= amount) {
                currentUser.setBalance(currentUser.getBalance() - amount);
                System.out.println("Withdrawal of $" + amount + " successful. Remaining balance: $" + currentUser.getBalance());
                Withdraw withdrawTransaction = new Withdraw(amount, "Amount withdrawn successfully.");
                currentUser.addTransaction(withdrawTransaction);
            } else {
                System.out.println("Invalid withdrawal amount or insufficient funds.");
            }
        } else {
            System.out.println("User not authenticated.");
        }
    }

    public void deposit(double amount) {
        if (currentUser != null) {
            if (amount > 0) {
                currentUser.setBalance(currentUser.getBalance() + amount);
                System.out.println("Deposit of $" + amount + " successful. New balance: $" + currentUser.getBalance());
                Deposit depositTransaction = new Deposit(amount, "Amount deposited successfully.");
                currentUser.addTransaction(depositTransaction);
            } else {
                System.out.println("Invalid deposit amount.");
            }
        } else {
            System.out.println("User not authenticated.");
        }
    }

    public void transfer(double amount, String recipientAccountNumber) {
        if (currentUser != null) {
            if (amount > 0 && currentUser.getBalance() >= amount) {
                currentUser.setBalance(currentUser.getBalance() - amount);
                System.out.println("Transfer of $" + amount + " to account number " + recipientAccountNumber + " successful. Remaining balance: $" + currentUser.getBalance());
                Transfer transferTransactionSender = new Transfer(amount, "Transfer to " + recipientAccountNumber, recipientAccountNumber);
                currentUser.addTransaction(transferTransactionSender);
            } else {
                System.out.println("Invalid transfer amount or insufficient funds.");
            }
        } else {
            System.out.println("User not authenticated.");
        }
    }

    public void displayTransactionHistory() {
        if (currentUser != null) {
            List<Transaction> transactions = currentUser.getTransactions();
            if (!transactions.isEmpty()) {
                System.out.println("Transaction History:");
                for (Transaction transaction : transactions) {
                    System.out.println(formatTransaction(transaction));
                }
            } else {
                System.out.println("No transactions found.");
            }
        } else {
            System.out.println("User not authenticated.");
        }
    }

    private String formatTransaction(Transaction transaction) {
        StringBuilder sb = new StringBuilder();
        sb.append("Type: ").append(transaction.getType()).append(", ");
        sb.append("Amount: ").append(transaction.getAmount()).append(", ");
        sb.append("Timestamp: ").append(transaction.getTimestamp()).append(", ");
        sb.append("Description: ").append(transaction.getDescription());
        return sb.toString();
    }

    public void quit() {
        System.out.println("Thank you for using the ATM system. Goodbye!");
        System.exit(0);
    }

    public static void main(String[] args) {
        // Create an instance of the ATM class
        ATM atm = new ATM();
        // Start the ATM system
        atm.start();
    }
}