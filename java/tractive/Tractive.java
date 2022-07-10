package java.tractive;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Tractive {

    public static void main(String[] args) {
        List<ProductQuantity> purchasedProductsQuantity = new Tractive().getPurchasedProductsQuantity(
                List.of("CVCD", "SDFD", "DDDF", "SDFD"),
                Map.of("CVCD", new Product(1, "X"),
                        "SDFD", new Product(2, "Z"),
                        "DDDF", new Product(1, null))
        );

        System.out.println(purchasedProductsQuantity);
    }

    public List<ProductQuantity> getPurchasedProductsQuantity(List<String> purchasedProducts,
                                                              Map<String, Product> mappings) {
        Map<Product, Integer> purchasedProductsQuantity = new HashMap<>();
        purchasedProducts.forEach(p -> purchasedProductsQuantity
                .compute(mappings.get(p), (k, v) -> (v == null) ? 1 : ++v));

        return purchasedProductsQuantity.entrySet()
                .stream()
                .map(entry -> new ProductQuantity(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

    }
}

class Product {
    int version;
    String edition;

    public Product(int version, String edition) {
        this.version = version;
        this.edition = edition;
    }
}

class ProductQuantity extends Product {

    int quantity;

    public ProductQuantity(Product product, int quantity) {
        super(product.version, product.edition);
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductQuantity{" +
                "quantity=" + quantity +
                ", version=" + version +
                ", edition='" + edition + '\'' +
                '}';
    }
}


