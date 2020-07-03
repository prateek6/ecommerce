package domain.product;

import java.util.List;

public interface ProductService {
    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean updateProductId(Product product,String oldProductId);
    boolean deleteProduct(Product product);
    List<Product> getAllProducts();

    Product getProduct(Product product);

    boolean isProductExist(Product product);
}
