import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Cookbook menu = new Cookbook();
        Orders orders = new Orders();

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

        orders.addOrder(new Orders(1, 3, menu.cookbook.get(0).getDishID(), LocalDateTime.now()));

        System.out.println(menu.getCookbook());
        System.out.println(orders.getOrders());

        try {
            menu.saveToFile("menu.txt");
        } catch (OrdersException e) {
            System.err.println("Nastala chyba při ukládání do souboru " + e.getLocalizedMessage());
        }


    }
}