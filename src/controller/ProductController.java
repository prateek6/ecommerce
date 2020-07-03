package controller;

import domain.product.Product;
import domain.product.impl.ProductServiceImpl;
import domain.user.User;

import java.util.List;
import java.util.Objects;

public class ProductController {
    ProductServiceImpl productService = new ProductServiceImpl();

    public boolean addProduct(Product product, User user){
        if (user.isAdmin()) {
            return productService.addProduct(product);
        }else {
            throw new IllegalArgumentException("You are not eligible to add the product");
        }

    }

    public boolean updateProduct(Product product, User user){
        if (user.isAdmin()) {
            return productService.updateProduct(product);
        } else {
            throw new IllegalArgumentException("You are not eligible to update the product");
        }

    }


    public boolean updateProductId(Product product, User user,String oldProductId){
        if (user.isAdmin()) {

            return productService.updateProductId(product,oldProductId);
        } else {
            throw new IllegalArgumentException("You are not eligible to update the product");
        }

    }

    public boolean deleteProduct(Product product, User user){
        if (user.isAdmin()) {
            return productService.deleteProduct(product);
        } else {
            throw new IllegalArgumentException("You are not eligible to delete the product");
        }

    }

    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }


    public Product getProduct(String productId) {
        Product product = new Product();
        product.setProductId(productId);
        Product savedProduct = productService.getProduct(product);
        if (Objects.isNull(savedProduct)) {
            throw new RuntimeException("Product doesn't exist ");
        }
        return savedProduct;
    }


    public boolean isProductExist(String productId) {
        Product product = new Product();
        product.setProductId(productId);
        if (productService.isProductExist(product)) {
            throw new RuntimeException("Product already  exists ");
        }
        return false;
    }






}
