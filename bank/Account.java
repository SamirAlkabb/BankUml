import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Account entity as defined in MyBankUML report.
 * Holds financial data only: balance, account number,
 * account type, owners, permissions, and transaction history.
 */
public abstract class Account {

    // Required by Component Level Design (Page 27)
    protected Permissions perms;
    protected List<User> users;

    // Core financial attributes
    protected String accountNumber;
    protected String accountType;
    protected double balance;

    protected List<Transaction> transactions;

    public Account(List<User> users,
                   String accountNumber,
                   String accountType,
                   double openingBalance,
                   Permissions perms) {

        this.users = (users != null) ? users : new ArrayList<>();
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = openingBalance;
        this.perms = perms;

        this.transactions = new ArrayList<>();
    }

    // --- Getters ---

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public Permissions getPermissions() {
        return perms;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    // --- Setters ---

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setPermissions(Permissions perms) {
        this.perms = perms;
    }

    // --- Internal balance update (used by Transaction service only) ---
    protected void applyAmount(double delta) {
        this.balance += delta;
    }

    public void addTransaction(Transaction t) {
        if (t != null) transactions.add(t);
    }
}
