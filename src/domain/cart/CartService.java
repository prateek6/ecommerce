package domain.cart;

import domain.product.Product;
import domain.user.User;

public interface CartService {
    void checkout(User user);
    Cart getCartByUser(User user);
    boolean addToCart(User user, Product product);
}
