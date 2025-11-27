import java.util.*;

public class DataStore {

    private final Map<String, User> usersById = new HashMap<>();
    private final Map<String, Account> accountsByNumber = new HashMap<>();
    private final List<Transaction> transactions = new ArrayList<>();

    public void saveUser(User user) {
        usersById.put(user.getId(), user);
        for (Account a : user.getAccounts()) {
            if (a.getAccountNumber() != null)
                accountsByNumber.put(a.getAccountNumber(), a);
        }
    }

    public void saveAccount(Account account) {
        if (account.getAccountNumber() != null)
            accountsByNumber.put(account.getAccountNumber(), account);
    }

    public void saveTransaction(Transaction tx) {
        transactions.add(tx);
    }

    public Optional<User> findUserById(String id) {
        return Optional.ofNullable(usersById.get(id));
    }

    public Collection<User> getAllUsers() {
        return Collections.unmodifiableCollection(usersById.values());
    }

    public Optional<Account> findAccountByNumber(String accNumber) {
        return Optional.ofNullable(accountsByNumber.get(accNumber));
    }

    public Collection<Account> getAllAccounts() {
        return Collections.unmodifiableCollection(accountsByNumber.values());
    }

    public List<Transaction> getAllTransactions() {
        return Collections.unmodifiableList(transactions);
    }
}
