package domain.product.impl;

import domain.product.Product;
import domain.product.ProductService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {
    public static Map<String,Product> productDbInMemory = new HashMap<>();

    @Override
    public boolean addProduct(Product product) {
        productDbInMemory.put(product.getProductId(),product);
        return true;
    }

    @Override
    public boolean updateProduct(Product product) {
        Product productInDb = productDbInMemory.get(product.getProductId());
        productDbInMemory.put(productInDb.getProductId(),product);
        return true;
    }

    @Override
    public boolean deleteProduct(Product product) {
        productDbInMemory.remove(product.getProductId());
        return true;
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(productDbInMemory.values());
    }

    @Override
    public Product getProduct(Product product) {
        return productDbInMemory.get(product.getProductId());
    }

    @Override
    public boolean isProductExist(Product product) {
        return productDbInMemory.containsKey(product.getProductId());
    }

    @Override
    public boolean updateProductId(Product product,String oldProductId) {
        productDbInMemory.remove(oldProductId);
        productDbInMemory.put(product.getProductId(),product);
        return true;
    }
}
