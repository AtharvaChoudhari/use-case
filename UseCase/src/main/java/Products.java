import lombok.Getter;
import lombok.Setter;

public class Products {
    @Getter
    @Setter
    private int product_id;
    @Getter
    @Setter
    private int product_category_id;
    @Getter
    @Setter
    private String product_name;
    @Getter
    @Setter
    private String product_description;
    @Getter
    @Setter
    private float product_price;
    @Getter
    @Setter
    private String product_image;

    public Products() {
    }

    public Products(int pid, int pci, String pn, String pd, float pp, String pim) {
        product_id = pid;
        product_category_id = pci;
        product_name = pn;
        product_description = pd;
        product_price = pp;
        product_image = pim;
    }

}