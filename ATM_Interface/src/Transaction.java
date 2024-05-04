import java.time.LocalDateTime;

public class Transaction {
    private String type;
    private double amount;
    private LocalDateTime timestamp;
    private String description;

    public Transaction(String type, double amount, String description) {
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.description = description;
    }

    // Getters and setters
    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }
}

class Deposit extends Transaction {
    public Deposit(double amount, String description) {
        super("Deposit", amount, description);
    }
}

class Withdraw extends Transaction {
    public Withdraw(double amount, String description) {
        super("Withdraw", amount, description);
    }
}

class Transfer extends Transaction {
    private String recipientAccountNumber;

    public Transfer(double amount, String description, String recipientAccountNumber) {
        super("Transfer", amount, description);
        this.recipientAccountNumber = recipientAccountNumber;
    }

    // Getter for recipientAccountNumber
    public String getRecipientAccountNumber() {
        return recipientAccountNumber;
    }
}
