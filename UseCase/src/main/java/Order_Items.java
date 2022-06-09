import lombok.Getter;
import lombok.Setter;

public class Order_Items {
    @Getter
    @Setter
    private int order_item_id;
    @Getter
    @Setter
    private int order_item_order_id;
    @Getter
    @Setter
    private int order_item_product_id;
    @Getter
    @Setter
    private int order_item_quantity;
    @Getter
    @Setter
    private float order_item_subtotal;
    @Getter
    @Setter
    private float order_item_product_price;

}