public class Customer {

    private String name;
 private Permissions permissions;

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
       public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

}
