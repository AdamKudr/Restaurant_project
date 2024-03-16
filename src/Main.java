import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Cookbook menu = new Cookbook();
        Orders orders = new Orders();
        RestaurantManager manager = new RestaurantManager(orders);

        //načtení dat - úkol č. 1
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

        // vložení jídel a objednávek do systému - úkol č. 2
        try {
            menu.addDish(new Dish(1, "Kuřecí řízek obalovaný 150g", BigDecimal.valueOf(125.50), 35, "Kureci_rizek_150"));
        } catch (OrdersException e) {
            System.err.println("Nastala chyba při vytváření jídla:" + e.getLocalizedMessage());
        }

        try {
            menu.addDish(new Dish(2, "Hranolky 150g", BigDecimal.valueOf(75), 10, "Hranolky_150"));
        } catch (OrdersException e) {
            System.err.println("Nastala chyba při vytváření jídla:" + e.getLocalizedMessage());
        }

        try {
            menu.addDish(new Dish(3, "Pstruh na víně 200g", BigDecimal.valueOf(255), 45, "Pstruh_200"));
        } catch (OrdersException e) {
            System.err.println("Nastala chyba při vytváření jídla:" + e.getLocalizedMessage());
        }

        try {
            menu.addDish(new Dish(4, "Kofola 0,5l", BigDecimal.valueOf(39), 4, "Kofola_05"));
        } catch (OrdersException e) {
            System.err.println("Nastala chyba při vytváření jídla:" + e.getLocalizedMessage());
        }

        orders.addOrder(new Orders(15, 2, menu.cookbook.get(0), LocalDateTime.of(2024, 3, 16, 17, 15)));
        orders.addOrder(new Orders(15, 2, menu.cookbook.get(1), LocalDateTime.of(2024, 3, 16, 17, 16)));
        orders.addOrder(new Orders(15, 2, menu.cookbook.get(3), LocalDateTime.of(2024, 3, 16, 17, 17)));
        orders.orderFulfilled(orders.orders.get(2));

        orders.addOrder(new Orders(2, 3, menu.cookbook.get(1), LocalDateTime.of(2024, 3, 16, 17, 22)));
        orders.addOrder(new Orders(2, 3, menu.cookbook.get(3), LocalDateTime.of(2024, 3, 16, 17, 23)));

        //celková cena konzumace za stůl č. 15 - úkol č. 3

        manager.printPricePerTable(15);

        //použití připravených metod - úkol č. 4

        //Počet rozpracovaných objednávek
        System.out.println(manager.ordersInProgress(orders.getOrders()));

        //Seřazení objednávek dle času zadání
        System.out.println(manager.sortOrdersByOrderTime(orders.getOrders()));

        //Průměrná doba zpracování objednávek
        System.out.println("Průměrná doba zpracování objednávek je: " + manager.averageOrderProcessingTime(orders.getOrders()) + " minut");

        //Seznam jídel, která byla dnes objednána
        System.out.println("Dnes byla objednána tato jídla: " + manager.dishesOrderedToday(orders.getOrders()));

        //Seznam objednávek pro jeden stůl
        manager.printOrdersByTable(15);

        //Uložení informací do souboru - úkol č. 5

        try {
            menu.saveToFile("menu.txt");
        } catch (OrdersException e) {
            System.err.println("Nastala chyba při ukládání do souboru " + e.getLocalizedMessage());
        }

        try {
            orders.saveToFile("orders.txt");
        } catch (OrdersException e) {
            System.err.println("Nastala chyba při ukládání do souboru " + e.getLocalizedMessage());
        }
    }
    }