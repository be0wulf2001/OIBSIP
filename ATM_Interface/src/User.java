import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String name;
    private double balance;
    private List<Transaction> transactions;

    public User(String userId, String name, double balance) {
        this.userId = userId;
        this.name = name;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Transaction management
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}