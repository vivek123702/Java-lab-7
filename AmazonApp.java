import java.util.*;

// Customer Class
class Customer {
    private String customerId;
    private String name;
    private String email;
    private int loyaltyPoints;
    private HashSet<String> purchasedProducts;

    public Customer(String customerId, String name, String email, int loyaltyPoints) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.loyaltyPoints = loyaltyPoints;
        this.purchasedProducts = new HashSet<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void addPurchasedProduct(String productId) {
        purchasedProducts.add(productId);
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId='" + customerId + '\'' + ", name='" + name + '\'' + ", loyaltyPoints="
                + loyaltyPoints + '}';
    }
}

// Product Class
class Product {
    private String productId;
    private String name;
    private double price;
    private String category;

    public Product(String productId, String name, double price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Product{" + "productId='" + productId + '\'' + ", name='" + name + '\'' + ", price=" + price
                + ", category='" + category + '\'' + '}';
    }
}

// Order Class
class Order {
    private String orderId;
    private String customerId;
    private String productId;
    private Date orderDate;
    private Date deliveryDate;

    public Order(String orderId, String customerId, String productId, Date orderDate, Date deliveryDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId='" + orderId + '\'' + ", customerId='" + customerId + '\'' + ", productId='"
                + productId + '\'' + ", deliveryDate=" + deliveryDate + '}';
    }
}

// Comparator for Product Sorting by Price
class ProductPriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return Double.compare(p1.getPrice(), p2.getPrice());
    }
}

// Comparator for Order Sorting by Delivery Date
class OrderDeliveryDateComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return o1.getDeliveryDate().compareTo(o2.getDeliveryDate());
    }
}

// Main Application
public class AmazonApp {
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static HashMap<String, Product> products = new HashMap<>();
    private static ArrayList<Order> orders = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Customer");
            System.out.println("2. Add Product");
            System.out.println("3. Place Order");
            System.out.println("4. View Products (Sorted by Price)");
            System.out.println("5. View Orders (Sorted by Delivery Date)");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addCustomer(sc);
                case 2 -> addProduct(sc);
                case 3 -> placeOrder(sc);
                case 4 -> viewProductsByPrice();
                case 5 -> viewOrdersByDeliveryDate();
                case 6 -> System.exit(0);
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addCustomer(Scanner sc) {
        System.out.print("Enter customer ID: ");
        String id = sc.next();
        System.out.print("Enter name: ");
        String name = sc.next();
        System.out.print("Enter email: ");
        String email = sc.next();
        System.out.print("Enter loyalty points: ");
        int points = sc.nextInt();
        customers.add(new Customer(id, name, email, points));
        System.out.println("Customer added successfully!");
    }

    private static void addProduct(Scanner sc) {
        System.out.print("Enter product ID: ");
        String id = sc.next();
        System.out.print("Enter name: ");
        String name = sc.next();
        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        System.out.print("Enter category: ");
        String category = sc.next();
        products.put(id, new Product(id, name, price, category));
        System.out.println("Product added successfully!");
    }

    private static void placeOrder(Scanner sc) {
        System.out.print("Enter order ID: ");
        String orderId = sc.next();
        System.out.print("Enter customer ID: ");
        String customerId = sc.next();
        System.out.print("Enter product ID: ");
        String productId = sc.next();
        System.out.print("Enter delivery date (yyyy-MM-dd): ");
        String date = sc.next();
        try {
            Date deliveryDate = new Date(date);
            orders.add(new Order(orderId, customerId, productId, new Date(), deliveryDate));
            System.out.println("Order placed successfully!");
        } catch (Exception e) {
            System.out.println("Invalid date format. Order not placed.");
        }
    }

    private static void viewProductsByPrice() {
        TreeSet<Product> sortedProducts = new TreeSet<>(new ProductPriceComparator());
        sortedProducts.addAll(products.values());
        sortedProducts.forEach(System.out::println);
    }

    private static void viewOrdersByDeliveryDate() {
        TreeSet<Order> sortedOrders = new TreeSet<>(new OrderDeliveryDateComparator());
        sortedOrders.addAll(orders);
        sortedOrders.forEach(System.out::println);
    }
}
