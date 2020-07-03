package controller;

import domain.cart.impl.CartServiceImpl;
import domain.product.Product;
import domain.user.User;

public class CartController {

    CartServiceImpl cartService = new CartServiceImpl();

    public boolean addToCart(User user, Product product){
        return cartService.addToCart(user,product);
    }

    public void checkout(User user){
         cartService.checkout(user);
    }
}
