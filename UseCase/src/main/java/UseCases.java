import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.*;

public class UseCases {
    private static final String CUSTOMER_ID = "customer_id";
    private static final String ORDER_CUSTOMER_ID = "order_customer_id";
    private static final String CUSTOMER_FIRST_NAME = "customer_first_name";
    private static final String CUSTOMER_LAST_NAME = "customer_last_name";
    private static final String ORDER_ITEM_SUBTOTAL = "order_item_subtotal";
    private static final String CUSTOMER_REVENUE = "order_revenue";
    private static final String PRODUCT_ID = "product_id";
    private static final String CATEGORY_ID = "category_id";

    public static void main(String[] args) {
        SparkSession spark = SparkSession
                .builder()
                .master("local")
                .appName("Java Spark SQL basic example")
                .getOrCreate();

        String customers = "src/main/resources/retail_db/customers/part-00000";

        Dataset<Row> customersDf = spark.read().option("header", "true").option("inferSchema", "true").csv(customers);
        //customersDf.show();

        String orders = "src/main/resources/retail_db/orders/part-00000";

        Dataset<Row> ordersDf = spark.read().option("header", "true").option("inferSchema", "true").csv(orders);
        //ordersDf.show();

        Dataset<Row> ordersDf1 = ordersDf.withColumn("order_date", to_date(col("order_date"),"yyyy-MM-dd"));
        //ordersDf1.filter(col("order_date").lt(lit("2014-01-31")).and(gt(lit("2014-01-01")));
        Dataset<Row> ordersDf2 = ordersDf1.filter(col("order_date").gt(lit("2014-01-01")).and((col("order_date").lt(lit("2014-01-31")))));
        //ordersDf2.show();
        //System.out.println(ordersDf2.count());
        Dataset<Row> joinDf = customersDf.join(ordersDf2, customersDf.col("customer_id").equalTo(ordersDf2.col("order_customer_id")));
        //joinDf.show();

        String orderItems = "src/main/resources/retail_db/order_items/part-00000";

        Dataset<Row> orderItemsDf = spark.read().option("header", "true").option("inferSchema", "true").csv(orderItems);
        //orderItemsDf.show();

        String categories = "src/main/resources/retail_db/categories/part-00000";

        Dataset<Row> categoriesDf = spark.read().option("header", "true").option("inferSchema", "true").csv(categories);
        // categoriesDf.show();
        //System.out.println(categoriesDf.count());

        String departments = "src/main/resources/retail_db/departments/part-00000";

        Dataset<Row> departmentsDf = spark.read().option("header", "true").option("inferSchema", "true").csv(departments);
        //departmentsDf.show();
        //System.out.println(departmentsDf.count());

        String products = "src/main/resources/retail_db/products/part-00000";

        Dataset<Row> productsDf = spark.read().option("header", "true").option("inferSchema", "true").csv(products);
        //productsDf.show();
        //System.out.println(productsDf.count());

        // Usecase 1 - Customer order count

        Dataset<Row> customerOrderCount = ordersDf.
                                    //filter("order_date <= '2014-01-31%' & order_date >= '2014-01-01%'").
                                    filter(col("order_date").gt(lit("2013-12-31")).and((col("order_date").lt(lit("2014-02-01"))))).
                                    join(customersDf, customersDf.col(CUSTOMER_ID).equalTo(ordersDf.col(ORDER_CUSTOMER_ID))).
                                    select(customersDf.col(CUSTOMER_ID), customersDf.col("customer_fname").alias(CUSTOMER_FIRST_NAME), customersDf.col("customer_lname").alias(CUSTOMER_LAST_NAME), ordersDf.col("order_status")).
                                    groupBy(CUSTOMER_ID, CUSTOMER_FIRST_NAME, CUSTOMER_LAST_NAME)
                                    .agg(count("order_status").alias("customers_order_count")).
                                    orderBy(col("customers_order_count").desc(), (col(CUSTOMER_ID).asc()));
        customerOrderCount.show();

        // Usecase 2 - Dormant Customers

        Dataset<Row> dCDf = ordersDf.
                                        //filter("order_date <= '2014-01-31%' & order_date >= '2014-01-01'").
                                        filter(col("order_date").gt(lit("2014-01-01")).and((col("order_date").lt(lit("2014-01-31"))))).
                                        join(customersDf, customersDf.col(CUSTOMER_ID).
                                        equalTo(ordersDf.col(ORDER_CUSTOMER_ID)));
                                        //select(customersDf.col("*")).
                                        //orderBy(col(CUSTOMER_ID).asc());
        //dCDf.show();

        Dataset<Row> dormantCustomers = dCDf.
                                        select("customer_id", "customer_fname", "customer_lname", "customer_email", "customer_password", "customer_street", "customer_city", "customer_state", "customer_zipcode").
                                        orderBy(col(CUSTOMER_ID).asc());
       // dormantCustomers.show();



        // Usecase 3 - Revenue Per Customer

        Dataset<Row> revenuePerCustomer = ordersDf.
                                            join(customersDf, customersDf.col(CUSTOMER_ID).equalTo(ordersDf.col(ORDER_CUSTOMER_ID))).
                                            join(orderItemsDf, ordersDf.col("order_id").equalTo(orderItemsDf.col("order_item_id"))).
                                            select(customersDf.col(CUSTOMER_ID),
                                            customersDf.col("customer_fname").alias(CUSTOMER_FIRST_NAME),
                                                    customersDf.col("customer_lname").alias(CUSTOMER_LAST_NAME),
                                                    orderItemsDf.col(ORDER_ITEM_SUBTOTAL)).
                                            groupBy(CUSTOMER_ID, CUSTOMER_FIRST_NAME, CUSTOMER_LAST_NAME).
                                            agg(round(sum(orderItemsDf.col(ORDER_ITEM_SUBTOTAL)), 2)
                                                    .alias(CUSTOMER_REVENUE)).
                                                    orderBy(col(CUSTOMER_REVENUE).desc());
        //revenuePerCustomer.show();

        // Usecase 4 - Revenue Per Category

        Dataset<Row> revenuePerCategory = ordersDf.
                                            join(orderItemsDf, ordersDf.col("order_id").equalTo(orderItemsDf.col("order_item_id"))).
                                            join(productsDf, productsDf.col(PRODUCT_ID).equalTo(orderItemsDf.col("order_item_product_id"))).
                                            join(categoriesDf, categoriesDf.col(CATEGORY_ID).equalTo(productsDf.col("product_category_id"))).
                                            select(categoriesDf.col("*"), orderItemsDf.col("ORDER_ITEM_SUBTOTAL")).
                                            groupBy(CATEGORY_ID, "category_department_id", "category_name").
                                            agg(round(sum(orderItemsDf.col(ORDER_ITEM_SUBTOTAL))).alias(CUSTOMER_REVENUE)).
                                            orderBy((CATEGORY_ID));
        //revenuePerCategory.show();

        // Usecase 5 - Product Count Per Department

        Dataset<Row> productCountPerDepartment = departmentsDf.
                                                    join(categoriesDf, categoriesDf.col("category_department_id").
                                                    equalTo(departmentsDf.col("department_id"))).
                                                    join(productsDf, productsDf.col("product_category_id").
                                                    equalTo(categoriesDf.col(CATEGORY_ID))).
                                                    select(departmentsDf.col("*"),
                                                    productsDf.col(PRODUCT_ID)).
                                                    groupBy("department_id", "department_name").
                                                    agg(count(productsDf.col(PRODUCT_ID)).alias("product_count")).
                                                    orderBy(col("department_id").asc());
        //productCountPerDepartment.show();



    }

}
