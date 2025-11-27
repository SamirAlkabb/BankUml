import java.util.List;

public class MyBankUMLApp {

    public static void main(String[] args) {

        DataStore store = new DataStore();

        // --- Permissions for each role ---
        Permissions custPerms   = new Permissions("alice",   "pass123", 0);
        Permissions tellerPerms = new Permissions("teller1", "pass123", 1);
        Permissions adminPerms  = new Permissions("admin",   "root",    2);

        // --- Users ---
        User customerUser = new User("C001", "alice",   "pass123",
                "Montreal", "2000-01-01", custPerms);
        User tellerUser   = new User("T001", "teller1", "pass123",
                "Montreal", "1995-05-05", tellerPerms);
        User adminUser    = new User("A001", "admin",   "root",
                "Montreal", "1990-03-03", adminPerms);

        // --- Customer + Card account ---
        Customer customer = new Customer("Alice Customer");
        Card aliceCard = new Card(customer, "ACC-1001", 500.00);

        customerUser.addAccount(aliceCard);

        // Save to datastore
        store.saveUser(customerUser);
        store.saveUser(tellerUser);
        store.saveUser(adminUser);
        store.saveAccount(aliceCard);

        // --- Customer UI: list + details ---
        CustomerUI customerUI = new CustomerUI(customerUser);
        customerUI.displayAccountList();
        customerUI.displayAccountDetails(aliceCard);

        // --- Transactions with validation ---
        try {
            System.out.println("\nDepositing 150.257 (rounded to 150.25)...");
            Transactions.deposit(aliceCard, 150.257, "Paycheck", store);
            System.out.println("New balance: " + aliceCard.getBalance());

            System.out.println("\nAttempting to withdraw 1000.00 (should fail)...");
            Transactions.withdraw(aliceCard, 1000.00, "Large withdrawal", store);
        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Validation error: " + e.getMessage());
        }

        System.out.println("Balance after operations: " + aliceCard.getBalance());

        // --- Teller search ---
        Search search = new Search();
        TellerUI tellerUI = new TellerUI(tellerPerms, search, store);

        System.out.println("\nTeller searching by account number ACC-1001:");
        List<Account> tellerResults =
                tellerUI.searchCustomerAccounts("ACC-1001", null, null, null);
        tellerUI.displayResults(tellerResults);

        // --- Admin global view ---
        AdminUI adminUI = new AdminUI(adminPerms, search, store);
        System.out.println("\nAdmin viewing all accounts:");
        adminUI.printAllAccounts();
    }
}