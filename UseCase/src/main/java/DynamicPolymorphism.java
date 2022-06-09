import java.util.ArrayList;
import java.util.List;

class DynamicPolymorphism {
    private static List<Customers> allCustomers;

    public static void main(String[] args) throws VIPCustomersException, PlusCustomersException {
        initialize();
        Customers customers1 = new VIPCustomers();
        List<Customers> filteredVIPCustomers = customers1.showDetails(allCustomers);
        for (Customers VIPCustomers : filteredVIPCustomers) {
            System.out.println(VIPCustomers);
        }

        Customers customers2 = new PlusCustomers();
        List<Customers> filteredPlusCustomers = customers2.showDetails(allCustomers);
        for (Customers PlusCustomers : filteredPlusCustomers) {
            System.out.println(PlusCustomers);
        }

        Customers customers3 = new Customers();
        List<Customers> unfilteredCustomers = customers3.showDetails((allCustomers));
        for (Customers Cust : unfilteredCustomers) {
            System.out.println(Cust);
        }

        VIPCustomers customers = new VIPCustomers();
        customers.showDiscountDetails(allCustomers, 12);

        PlusCustomers plusCustomers = new PlusCustomers();
        plusCustomers.showPrivileges(allCustomers, 17);

    }

    public static void initialize() {
        allCustomers = new ArrayList<>();
        Customers c1 = new Customers(1, "A", "B", "AB@gmail.com", "AB", "Shanwara", "Burhanpur", "MP", "AB1100", null);
        Customers c2 = new Customers(2, "C", "D", "CD@gmail.com", "CD", "Rajkamal", "Amravati", "MH", "CD1100", null);
        Customers c3 = new Customers(3, "E", "F", "EF@gmail.com", "EF", "Shaniwarwada", "Pune", "MH", "EF1100", null);
        allCustomers.add(c1);
        allCustomers.add(c2);
        allCustomers.add(c3);

        VIPCustomers v1 = new VIPCustomers(4, "G", "H", "GH@gmail.com", "GH", "Shanwara", "Burhanpur", "MP", "AB1100", "VIP", 20);
        VIPCustomers v2 = new VIPCustomers(5, "I", "J", "IJ@gmail.com", "IJ", "Rajkamal", "Amravati", "MH", "CD1100", "VIP", 30);
        VIPCustomers v3 = new VIPCustomers(6, "K", "L", "KL@gmail.com", "KL", "Shaniwarwada", "Pune", "MH", "EF1100", "VIP", 25);
        allCustomers.add(v1);
        allCustomers.add(v2);
        allCustomers.add(v3);

        Privilege privilege = new Privilege(1, "Music");
        Privilege privilege1 = new Privilege(2, "Video");

        PlusCustomers p1 = new PlusCustomers(7, "M", "N", "MN@gmail.com", "MN", "Shanwara", "Burhanpur", "MP", "AB1100", "Plus", privilege1);
        PlusCustomers p2 = new PlusCustomers(8, "O", "P", "OP@gmail.com", "OP", "Rajkamal", "Amravati", "MH", "CD1100", "Plus", privilege);
        PlusCustomers p3 = new PlusCustomers(9, "Q", "R", "QR@gmail.com", "QR", "Shaniwarwada", "Pune", "MH", "EF1100", "Plus", privilege);
        allCustomers.add(p1);
        allCustomers.add(p2);
        allCustomers.add(p3);
    }
}