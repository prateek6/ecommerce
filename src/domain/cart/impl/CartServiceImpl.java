package domain.cart.impl;

import domain.cart.Cart;
import domain.cart.CartService;
import domain.product.Product;
import domain.user.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class CartServiceImpl implements CartService {

    public static Map<String,Cart> cartInMemoryDb = new HashMap<>();

    @Override
    public void checkout(User user) {
        Cart cart = cartInMemoryDb.get(user.getEmail());
        if(Objects.isNull(cart)){
            System.out.println("Cart is empty!!!!");
            return;
        }
        AtomicReference<Float> sum= new AtomicReference<>((float) 0);
        System.out.println("Product present in the cart are....");
        cart.getProductList().parallelStream().forEach(product -> {
            System.out.println(product);
            sum.updateAndGet(v -> (float) (v + product.getPrice()));
        });
        System.out.println("Total prices is  : "+sum.get());
    }

    @Override
    public Cart getCartByUser(User user) {
        return cartInMemoryDb.get(user.getEmail());
    }

    @Override
    public boolean addToCart(User user, Product product) {
        Cart cart = cartInMemoryDb.get(user.getEmail());
        if(Objects.isNull(cart)){
            cart = new Cart();
            cart.setUser(user);
        }

        List<Product> productList = cart.getProductList();
        if (Objects.isNull(cart.getProductList())) {
            productList = new ArrayList<>();
        }
        productList.add(product);
        cart.setProductList(productList);
        cartInMemoryDb.put(user.getEmail(),cart);
        return true;
    }
}
