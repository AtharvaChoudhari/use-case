import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class PlusCustomers extends Customers {
    @Getter
    private Privilege privilege;

    public PlusCustomers(int cid, String cf, String cl, String ce, String cp, String cstr, String cc, String csta, String cz, String t, Privilege privilege) {
        super(cid, cf, cl, ce, cp, cstr, cc, csta, cz, t);
        this.privilege = privilege;
    }

    public PlusCustomers() {

    }

    public String toString() {
        return getClass().getName() + "[Plus Customer ID =" + getCustomer_id() + ", Plus Customer First Name =" + getCustomer_fname() + ", Plus Customer Last Name =" + getCustomer_lname() + "]";
    }

    public List<Customers> showDetails(List<Customers> allCustomers) {

        List<Customers> plusCustomers = new ArrayList<>();
        for (Customers customers : allCustomers) {
            if ("Plus".equals(customers.getType())) {
                plusCustomers.add(customers);
            }
        }

        return plusCustomers;
    }

    public void showDetails() {
        System.out.println("The Plus Customer ID is " + getCustomer_id() + ".");
    }

    public void showPrivileges(List<Customers> allCustomers, int customerId) throws PlusCustomersException {
        String matchedValue = null;
        for (Customers plusCustomers : allCustomers) {
            if (plusCustomers.getCustomer_id() == customerId) {
                System.out.println("The Plus Customer has the privilege of : " + ((PlusCustomers) plusCustomers).getPrivilege().getPrivilegeName());
                matchedValue = "Matched";
                break;
            }
        }
        if (!"Matched".equals(matchedValue)) {
            throw new PlusCustomersException("The customer is not a PlusCustomer. Please provide correct Customer ID.");
        }
    }
}

