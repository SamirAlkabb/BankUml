public class Check extends Account {

    public Check(Customer customer) {
        super(customer);
    }

    public Check(Customer customer,
                 String accountNumber,
                 double openingBalance) {
        super(customer, accountNumber, "CHECK", openingBalance);
    }

    private void title() {
        System.out.println("**Check Payment**");
    }

    @Override
    public void pay() {
        title();
        System.out.println("Check payment for customer: " + customer.getName());
    }

    @Override
    public void receipt() {
        System.out.println("Check receipt for customer: " + customer.getName());
    }
}
