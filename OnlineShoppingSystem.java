import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
class Product {
    private String name;
    private double price;
    private int stockQuantity;
    public Product(String name, double price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getStockQuantity() {
        return stockQuantity;
    }
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
class ShoppingCart {
    private Map<Product, Integer> items;
    public ShoppingCart() {
        this.items = new HashMap<>();
    }
    public void addProduct(Product product, int quantity) {
        if (items.containsKey(product)) {
            items.put(product, items.get(product) + quantity);
        } else {
            items.put(product, quantity);
        }
    }
    public void removeProduct(Product product, int quantity) {
        if (items.containsKey(product)) {
            int currentQuantity = items.get(product);
            if (currentQuantity <= quantity) {
                items.remove(product);
            } else {
                items.put(product, currentQuantity - quantity);
            }
        }
    }
    public Map<Product, Integer> getItems() {
        return items;
    }
    public void displayCart() {
        System.out.println("Shopping Cart Contents:");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(product.getName() + " - Quantity: " + quantity + " - Total Price: $" + product.getPrice() * quantity);
        }
    }
}
class User {
    private String username;
    private String password;
    private List<String> orderHistory;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.orderHistory = new ArrayList<>();
    }
    public String getUsername() {
        return username;
    }
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
    public void addToOrderHistory(String order) {
        orderHistory.add(order);
    }
    public void displayOrderHistory() {
        System.out.println("Order History for " + username + ":");
        for (String order : orderHistory) {
            System.out.println(order);
        }
    }
}
public class OnlineShoppingSystem {
    public static void main(String[] args) {
        Product laptop = new Product("Laptop", 1000.0, 5);
        Product phone = new Product("Phone", 500.0, 10);
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(laptop, 2);
        cart.addProduct(phone, 1);
        User user = new User("chiru", "Chiru@986");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String enteredUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String enteredPassword = scanner.nextLine();
        if (enteredUsername.equals(user.getUsername()) && user.authenticate(enteredPassword)) {
            System.out.println("Login successful.");
            cart.displayCart();
            double totalOrderPrice = 0.0;
            for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                totalOrderPrice += product.getPrice() * quantity;
                product.setStockQuantity(product.getStockQuantity() - quantity);
            }
            user.addToOrderHistory("Order Total: $" + totalOrderPrice);
            user.displayOrderHistory();

        } else {
            System.out.println("Login failed. Incorrect username or password.");
        }
    }
}
