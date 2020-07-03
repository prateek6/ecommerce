package processor;


import controller.CartController;
import controller.ProductController;
import controller.UserController;
import domain.product.Product;
import domain.user.User;

import java.io.BufferedReader;
import java.io.IOException;

import static commons.Constants.*;

public class CommandLineInputProcessor implements Processor{

    ProductController productController = new ProductController();
    UserController userController = new UserController();
    CartController cartController = new CartController();

    @Override
    public boolean validate(String input) {
        return true;
    }

    @Override
    public void execute(String input, BufferedReader bufferReader) throws IOException {
        switch (input.toLowerCase()) {
            case SIGN_UP : processUserSignUp(bufferReader);break;
            case SIGN_IN : processLogIn(bufferReader) ;break;
            case LOG_OUT : processLogout(bufferReader) ;break;
            case ADD_PRODUCT : processAddProduct(bufferReader) ;break;
            case UPDATE_PRODUCT : processUpdateProduct(bufferReader) ;break;
            case DELETE_PRODUCT : processDeleteProduct(bufferReader) ;break;
            case BROWSE_PRODCUCT : processBrowseProduct() ;break;
            case ADD_TO_CART : processAddToCart(bufferReader) ;break;
            case CHECKOUT : processCheckout(bufferReader);break;
            default:break;
        }
        System.out.println(input.toLowerCase()+" process done, add new input by referring commands!!!!!!!!!!!!");




    }

    private void processCheckout(BufferedReader bufferReader) throws IOException{
        System.out.println("Enter email");
        String email = bufferReader.readLine().trim();
        User user = getUser(email);
        validateLogin(user);
        cartController.checkout(user);
    }

    private void processAddToCart(BufferedReader bufferReader) throws IOException{
        System.out.println("Enter email");
        String email = bufferReader.readLine().trim();
        User user = getUser(email);
        validateLogin(user);

        System.out.println("Enter Product id");
        String productId = bufferReader.readLine().trim();
        Product product = getProduct(productId);

        cartController.addToCart(user,product);




    }

    private void processBrowseProduct() {
        productController.getAllProducts().parallelStream().forEach(System.out::println);

    }

    private void processLogout( BufferedReader bufferReader)throws IOException {
        System.out.println("Enter email");
        String email = bufferReader.readLine().trim();
        User user = getUser(email);
        validateLogin(user);
        userController.logout(user);
    }

    private void processLogIn(BufferedReader bufferReader) throws IOException{

        System.out.println("Enter email");
        String email = bufferReader.readLine().trim();

        User user = getUser(email);
        System.out.println("Enter Password");
        String password = bufferReader.readLine().trim();

        if(!user.getPassword().equals(password)){
            throw new RuntimeException("Password doesn't match ");
        }

        userController.logIn(user);

    }

    private void processUserSignUp(BufferedReader bufferReader) throws IOException{
        User user = new User();
        System.out.println("Enter email");
        String email = bufferReader.readLine().trim();

        userController.isUserExits(email);

        user.setEmail(email);

        System.out.println("Enter password");
        String password = bufferReader.readLine().trim();
        user.setPassword(password);

        System.out.println("Enter address");
        String address = bufferReader.readLine().trim();
        user.setAddress(address);

        System.out.println("Enter date of birth in dd-mm-yyyy");
        String dateOfBirth = bufferReader.readLine().trim();
        user.setDateOfBirth(dateOfBirth);

        System.out.println("Enter Full Name");
        String fullName = bufferReader.readLine().trim();
        user.setName(fullName);

        System.out.println("Are you admin answer in  ---> true/false");
        boolean isAdmin = Boolean.parseBoolean(bufferReader.readLine().trim());
        user.setAdmin(isAdmin);

        userController.signUp(user);



    }

    private void processDeleteProduct(BufferedReader bufferReader) throws IOException {
        System.out.println("Enter your email");
        String email = bufferReader.readLine().trim();
        User user = getUser(email);
        validateLogin(user);
        validateAdminUser(user);

        System.out.println("Enter Product id");
        String productId = bufferReader.readLine().trim();

        Product product = getProduct(productId);
        productController.deleteProduct(product,user);
    }




    private void processUpdateProduct(BufferedReader bufferReader) throws IOException {

        System.out.println("Enter your email");
        String email = bufferReader.readLine().trim();
        User user = getUser(email);
        validateLogin(user);
        validateAdminUser(user);

        System.out.println("Enter Product id");
        String productId = bufferReader.readLine().trim();
        Product product = getProduct(productId);

        printProductUpdateUsage();
        String productUpdateInput = bufferReader.readLine().trim();

        switch (productUpdateInput.toLowerCase()) {
            case UPDATE_PRODUCT_NAME:
                System.out.println("Enter new product name");
                String productName = bufferReader.readLine().trim();
                product.setName(productName);
                break;
            case UPDATE_PRODUCT_DESCRIPTION:
                System.out.println("Enter new product description");
                String description = bufferReader.readLine().trim();
                product.setDescription(description);
                break;
            case UPDATE_PRODUCT_PRICE:
                System.out.println("Enter new product price");
                float price = Float.parseFloat(bufferReader.readLine().trim());
                product.setPrice(price);
                break;
            case UPDATE_PRODUCT_ID:
                System.out.println("Enter new product id");
                String oldProductId = product.getProductId();
                String productIdValue = bufferReader.readLine().trim();
                product.setProductId(productIdValue);
                productController.updateProductId(product,user,oldProductId);
                break;
            default:break;
        }
        productController.updateProduct(product,user);

    }



    private void processAddProduct(BufferedReader bufferReader) throws IOException {
        System.out.println("Please enter your email ");
        String email = bufferReader.readLine().trim();
        User user = getUser(email);
        validateLogin(user);
        validateAdminUser(user);

        Product product = new Product();

        System.out.println("Add product id");
        String productId = bufferReader.readLine().trim();
        productController.isProductExist(productId);

        product.setProductId(productId);


        System.out.println("Add description");
        String description = bufferReader.readLine().trim();
        product.setDescription(description);

        System.out.println("Add Name");
        String name = bufferReader.readLine().trim();
        product.setName(name);

        System.out.println("Add price eg : 10.00");
        float price = Float.parseFloat(bufferReader.readLine().trim());
        product.setPrice(price);


        productController.addProduct(product,user);


    }


    private User getUser(String email) {
        return userController.getUser(email);
    }


    private Product getProduct(String productId) {
        return productController.getProduct(productId);
    }




    private void validateLogin(User user){
        if (!user.isLoggedIn()) {
            throw new RuntimeException("user is not logged in, Please login!!!!!!");
        }
    }




    private void validateAdminUser(User user) {
        if (!userController.isUserAdmin(user)) {
            throw new RuntimeException("User doesn't have admin permission ");
        }
    }


    public static void printProductUpdateUsage() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(
                "--------------Please Enter one of the below commands. -----------------------")
                .append("\n");

        stringBuilder.append("A) To update name -----> update_product_name ")
                .append("\n");


        stringBuilder
                .append("B) To update the Product Id -----> update_product_id")
                .append("\n");


        stringBuilder.append("C) To update the product description -----> update_product_description")
                .append("\n");

        stringBuilder.append("\n");

        stringBuilder.append("D) To update the product price -----> update_product_price").append("\n");

        System.out.println(stringBuilder.toString());
    }


}
