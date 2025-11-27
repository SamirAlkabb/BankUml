public class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printCustomerInfo() {
        System.out.println("Customer info:");
        System.out.println("Name: " + name);
    }
}
