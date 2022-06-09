import lombok.Getter;
import lombok.Setter;

public class Orders {
    @Getter
    @Setter
    private int order_id;
    @Getter
    @Setter
    private String order_date;
    @Getter
    @Setter
    private int order_customer_id;
    @Getter
    @Setter
    private String order_status;

}