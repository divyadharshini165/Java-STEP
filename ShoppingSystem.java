// Assignment 2: Online Shopping Cart System
// Topic: Object Relationships and Method Interaction

import java.util.*;

class Product {
    // Attributes
    private String productId;
    private String productName;
    private double price;
    private String category;
    private int stockQuantity;

    // Static variables
    private static int totalProducts = 0;
    private static String[] categories = {"Electronics", "Clothing", "Books", "Home", "Toys"};

    // Constructor
    public Product(String productName, double price, String category, int stockQuantity) {
        this.productId = "P" + (1000 + ++totalProducts);
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
    }

    // Getters
    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public int getStockQuantity() { return stockQuantity; }

    // Stock management
    public void reduceStock(int quantity) {
        if (quantity <= stockQuantity) {
            stockQuantity -= quantity;
        }
    }
    public void increaseStock(int quantity) {
        stockQuantity += quantity;
    }

    // Display product
    public void displayProduct() {
        System.out.printf("%s | %-15s | %-12s | $%.2f | Stock: %d\n",
                productId, productName, category, price, stockQuantity);
    }

    // Static Methods
    public static Product findProductById(Product[] products, String productId) {
        for (Product p : products) {
            if (p != null && p.getProductId().equals(productId)) {
                return p;
            }
        }
        return null;
    }

    public static List<Product> getProductsByCategory(Product[] products, String category) {
        List<Product> list = new ArrayList<>();
        for (Product p : products) {
            if (p != null && p.getCategory().equalsIgnoreCase(category)) {
                list.add(p);
            }
        }
        return list;
    }
}

class ShoppingCart {
    private String cartId;
    private String customerName;
    private Product[] products;
    private int[] quantities;
    private double cartTotal;
    private int itemCount;

    // Constructor
    public ShoppingCart(String customerName) {
        this.cartId = "CART" + new Random().nextInt(1000);
        this.customerName = customerName;
        this.products = new Product[20]; // max 20 products in cart
        this.quantities = new int[20];
        this.cartTotal = 0.0;
        this.itemCount = 0;
    }

    // Add product to cart
    public void addProduct(Product product, int quantity) {
        if (product.getStockQuantity() < quantity) {
            System.out.println("Not enough stock available!");
            return;
        }
        // Check if already in cart
        for (int i = 0; i < itemCount; i++) {
            if (products[i].getProductId().equals(product.getProductId())) {
                quantities[i] += quantity;
                product.reduceStock(quantity);
                calculateTotal();
                System.out.println("Updated quantity for " + product.getProductName());
                return;
            }
        }
        // Add new product
        products[itemCount] = product;
        quantities[itemCount] = quantity;
        itemCount++;
        product.reduceStock(quantity);
        calculateTotal();
        System.out.println(product.getProductName() + " added to cart!");
    }

    // Remove product
    public void removeProduct(String productId) {
        for (int i = 0; i < itemCount; i++) {
            if (products[i].getProductId().equals(productId)) {
                products[i].increaseStock(quantities[i]); // return stock
                System.out.println(products[i].getProductName() + " removed from cart.");
                // Shift arrays
                for (int j = i; j < itemCount - 1; j++) {
                    products[j] = products[j + 1];
                    quantities[j] = quantities[j + 1];
                }
                products[itemCount - 1] = null;
                quantities[itemCount - 1] = 0;
                itemCount--;
                calculateTotal();
                return;
            }
        }
        System.out.println("Product not found in cart!");
    }

    // Calculate total
    public void calculateTotal() {
        cartTotal = 0.0;
        for (int i = 0; i < itemCount; i++) {
            cartTotal += products[i].getPrice() * quantities[i];
        }
    }

    // Display cart
    public void displayCart() {
        System.out.println("\n===== Shopping Cart =====");
        System.out.println("Customer: " + customerName + " | Cart ID: " + cartId);
        if (itemCount == 0) {
            System.out.println("Cart is empty!");
            return;
        }
        System.out.printf("%-6s | %-15s | Qty | Price | Subtotal\n", "ID", "Product");
        System.out.println("----------------------------------------------------");
        for (int i = 0; i < itemCount; i++) {
            double subtotal = products[i].getPrice() * quantities[i];
            System.out.printf("%-6s | %-15s | %-3d | $%.2f | $%.2f\n",
                    products[i].getProductId(),
                    products[i].getProductName(),
                    quantities[i],
                    products[i].getPrice(),
                    subtotal);
        }
        System.out.println("----------------------------------------------------");
        System.out.println("Cart Total: $" + cartTotal);
    }

    // Checkout
    public void checkout() {
        if (itemCount == 0) {
            System.out.println("Cart is empty, nothing to checkout!");
            return;
        }
        displayCart();
        System.out.println("Checkout successful! Thank you for shopping, " + customerName);
        itemCount = 0; // clear cart
        cartTotal = 0.0;
    }
}

public class ShoppingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create products
        Product[] products = new Product[10];
        products[0] = new Product("Laptop", 60000, "Electronics", 5);
        products[1] = new Product("Smartphone", 30000, "Electronics", 10);
        products[2] = new Product("Shirt", 1500, "Clothing", 20);
        products[3] = new Product("Jeans", 2500, "Clothing", 15);
        products[4] = new Product("Novel", 500, "Books", 30);
        products[5] = new Product("Cookbook", 700, "Books", 25);
        products[6] = new Product("Mixer", 4000, "Home", 8);
        products[7] = new Product("Vacuum Cleaner", 7000, "Home", 6);
        products[8] = new Product("Toy Car", 800, "Toys", 12);
        products[9] = new Product("Board Game", 1200, "Toys", 10);

        // Welcome
        System.out.print("Enter your name: ");
        String customerName = sc.nextLine();
        ShoppingCart cart = new ShoppingCart(customerName);

        // Menu-driven system
        int choice;
        do {
            System.out.println("\n===== Online Shopping System =====");
            System.out.println("1. Browse All Products");
            System.out.println("2. Browse Products by Category");
            System.out.println("3. Add Product to Cart");
            System.out.println("4. Remove Product from Cart");
            System.out.println("5. View Cart");
            System.out.println("6. Checkout");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n--- All Products ---");
                    for (Product p : products) {
                        p.displayProduct();
                    }
                    break;
                case 2:
                    System.out.print("Enter category (Electronics/Clothing/Books/Home/Toys): ");
                    String cat = sc.nextLine();
                    List<Product> filtered = Product.getProductsByCategory(products, cat);
                    if (filtered.isEmpty()) {
                        System.out.println("No products found in this category!");
                    } else {
                        for (Product p : filtered) {
                            p.displayProduct();
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter Product ID to add: ");
                    String pid = sc.nextLine();
                    Product prod = Product.findProductById(products, pid);
                    if (prod == null) {
                        System.out.println("Invalid product ID!");
                    } else {
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();
                        sc.nextLine();
                        cart.addProduct(prod, qty);
                    }
                    break;
                case 4:
                    System.out.print("Enter Product ID to remove: ");
                    String rid = sc.nextLine();
                    cart.removeProduct(rid);
                    break;
                case 5:
                    cart.displayCart();
                    break;
                case 6:
                    cart.checkout();
                    break;
                case 7:
                    System.out.println("Thank you for visiting!");
                    break;
                default:
                    System.out.println("Invalid choice, try again!");
            }
        } while (choice != 7);

        sc.close();
    }
}
