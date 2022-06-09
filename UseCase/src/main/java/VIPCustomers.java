import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class VIPCustomers extends Customers {
    @Getter
    private float discount;

    public VIPCustomers(int cid, String cf, String cl, String ce, String cp, String cstr, String cc, String csta, String cz, String t, float discount) {
        super(cid, cf, cl, ce, cp, cstr, cc, csta, cz, t);
        this.discount = discount;
    }

    public VIPCustomers() {

    }

    public String toString() {
        return getClass().getName() + "[VIP Customer ID =" + getCustomer_id() + ", VIP Customer First Name =" + getCustomer_fname() + ", VIP Customer Last Name =" + getCustomer_lname() + "]";
    }

    public List<Customers> showDetails(List<Customers> allCustomers) {
        List<Customers> vipCustomers = new ArrayList<>();
        for (Customers customers : allCustomers) {
            if ("VIP".equals(customers.getType())) {
                vipCustomers.add(customers);
            }
        }
        return vipCustomers;
    }

    public void showDiscountDetails(List<Customers> allCustomers, int customerId) throws VIPCustomersException {
        String matchedValue = null;
        for (Customers vipCustomers : allCustomers) {
            if (vipCustomers.getCustomer_id() == customerId) {
                System.out.println("The VIP Customer will get % of discount : " + ((VIPCustomers) vipCustomers).getDiscount());
                matchedValue = "Matched";
                break;
            }
        }
        if (!"Matched".equals(matchedValue)) {
            throw new VIPCustomersException("The customer is not a VIPCustomer. Please provide correct Customer ID.");
        }
    }
}
