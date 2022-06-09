import java.util.*;


public class StaticPolymorphism {
    private static List<Products> allProducts;

    public static void main(String[] args) {

        initialize1();

        List<Products> originalProductsResult = originalProducts("Britannia");
        for (Products originalproduct : originalProductsResult) {
            System.out.println(originalproduct.getProduct_category_id() + " " + originalproduct.getProduct_id() + " " + originalproduct.getProduct_price() + " " + originalproduct.getProduct_name() + " " + originalproduct.getProduct_image() + " " + originalproduct.getProduct_description() + ".");
        }

        List<Products> filteredProductsResult = filterProducts("Parle", 20f);
        for (Products filteredproduct : filteredProductsResult) {
            System.out.println(filteredproduct.getProduct_category_id() + " " + filteredproduct.getProduct_id() + " " + filteredproduct.getProduct_price() + " " + filteredproduct.getProduct_name() + " " + filteredproduct.getProduct_image() + " " + filteredproduct.getProduct_description() + ".");
        }
    }

    public static void initialize1() {
        allProducts = new ArrayList<>();
        Products p1 = new Products(1, 12, "Parle", "Biscuit", 10, "Image1");
        Products p2 = new Products(2, 23, "Britannia", "Biscuit", 20, "Image2");
        Products p3 = new Products(3, 34, "Skate", "Roller", 30, "image3");
        allProducts.add(p1);
        allProducts.add(p2);
        allProducts.add(p3);
    }



    // returns list of products with input product name
    // product can match with complete input or a part of it.
    public static List<Products> originalProducts(String productName) {
        List<Products> someOriginalProducts = new ArrayList<>();
        for (Products oProduct : allProducts) {
            if (oProduct.getProduct_name().equals(productName)) {
                someOriginalProducts.add(oProduct);
            }
        }
        return someOriginalProducts;
    }

    // returns list of products with input product name and price less than input price name
    public static List<Products> filterProducts(String productName, float productPrice) {
        List<Products> filteredProducts = new ArrayList<>();
        //List<Products> originalProducts = allProducts;
        for (Products product : allProducts) {
            if (product.getProduct_name().equals(productName) & (product.getProduct_price() < productPrice)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }
}



