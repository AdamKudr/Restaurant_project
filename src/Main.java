import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Cookbook menu = new Cookbook();
        Orders orders = new Orders();
        RestaurantManager manager = new RestaurantManager(menu, orders);

        try {
            menu.addDish(new Dish(1, "Ragu", BigDecimal.valueOf(125.50), 35, "ragu_01"));
        } catch (OrdersException e) {
            System.err.println("Nastala chyba při vytváření jídla:" + e.getLocalizedMessage());
        }

        try {
            menu.addDish(new Dish(2, "Spagety", BigDecimal.valueOf(160), 40));
        } catch (OrdersException e) {
            System.err.println("Nastala chyba při vytváření jídla:" + e.getLocalizedMessage());
        }

        orders.addOrder(new Orders(1, 3, menu.cookbook.get(0), LocalDateTime.now()));
        orders.addOrder(new Orders(4, 2, menu.cookbook.get(1), LocalDateTime.of(2024, 3, 12, 16, 20)));
        orders.addOrder(new Orders(4, 1, menu.cookbook.get(0), LocalDateTime.of(2024, 3, 12, 16, 50)));
        try {
            menu.saveToFile("menu.txt");
        } catch (OrdersException e) {
            System.err.println("Nastala chyba při ukládání do souboru " + e.getLocalizedMessage());
        }

        System.out.println(menu.getCookbook());

        try {
            orders.saveToFile("orders.txt");
        } catch (OrdersException e) {
            System.err.println("Nastala chyba při ukládání do souboru " + e.getLocalizedMessage());
        }

        try {
            menu.loadFromFile("menu.txt");
        } catch (OrdersException e) {
                System.err.println("Nastala chyba při načtení souboru! " + e.getLocalizedMessage());
            }

        try {
            orders.loadFromFile("orders.txt", menu);
        } catch (OrdersException e) {
            System.err.println("Nastala chyba při načtení souboru! " + e.getLocalizedMessage());
        }

        System.out.println(manager.ordersInProgress(orders.getOrders()));

        orders.orderFulfilled(orders.getOrders().get(1));
        orders.orderFulfilled(orders.getOrders().get(2));
        System.out.println(manager.averageOrderProcessingTime(orders.getOrders()));

    }
    }