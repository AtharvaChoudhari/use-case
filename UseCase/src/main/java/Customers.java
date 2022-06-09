import lombok.Getter;

import java.util.List;

public class Customers {
    @Getter
    private int customer_id;
    @Getter
    private String customer_fname;
    @Getter
    private String customer_lname;
    @Getter
    private String customer_email;
    @Getter
    private String customer_password;
    @Getter
    private String customer_street;
    @Getter
    private String customer_city;
    @Getter
    private String customer_state;
    @Getter
    private String customer_zipcode;
    @Getter
    private String type;

    public Customers(int cid, String cf, String cl, String ce, String cp, String cstr, String cc, String csta, String cz, String t) {
        customer_id = cid;
        customer_fname = cf;
        customer_lname = cl;
        customer_email = ce;
        customer_password = cp;
        customer_street = cstr;
        customer_city = cc;
        customer_state = csta;
        customer_zipcode = cz;
        type = t;
    }

    public Customers() {

    }

    public String toString() {
        return getClass().getName() + "[Customer ID =" + getCustomer_id() + ", Customer First Name =" + getCustomer_fname() + ", VCustomer Last Name =" + getCustomer_lname() + "]";
    }

    public List<Customers> showDetails(List<Customers> allCustomers) {
        return allCustomers;
    }
}
