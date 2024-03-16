import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RestaurantManager {


    private List<Orders> orders;

    private List<Orders> ordersByTable;

    public RestaurantManager(Orders orders) {
        this.orders = orders.getOrders();
    }

    public int ordersInProgress(List<Orders> orders) {
        int inProgress = 0;
        for (Orders order : orders) {
            if (!order.isFulfilled()) {
                inProgress++;
            }
        }
        return inProgress;
    }

    public List<Orders> sortOrdersByOrderTime(List<Orders> orders) {

        orders.sort(
                new Comparator<Orders>() {
                    @Override
                    public int compare(Orders o1, Orders o2) {
                        if (o1.getOrderTime().compareTo(o2.getOrderTime()) >= 0) {
                            return 1;
                        } else return -1;
                    }
                });
        return orders;
    }

    public int averageOrderProcessingTime(List<Orders> orders) {
        int numberOfOrders = 0;
        int totalProcessingTime = 0;
        for (Orders order : orders) {
            if (order.isFulfilled()) {
                numberOfOrders++;
                totalProcessingTime += ChronoUnit.MINUTES.between(order.getOrderTime(), order.getFulfilmentTime());
            }
        }
        return totalProcessingTime / numberOfOrders;
    }

    public List<Dish> dishesOrderedToday(List<Orders> orders) {
        List<Dish> dishesOrderedToday = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (Orders order : orders) {
            if (order.getOrderTime().toLocalDate().equals(today) && !dishesOrderedToday.contains(order.getMeal())) {
                dishesOrderedToday.add(order.getMeal());
            }
        }
        return dishesOrderedToday;
    }

    public List<Orders> exportOrdersByTable(int table) {
        List<Orders> ordersByTable = new ArrayList<>();
        for (Orders order : orders) {
            if (order.getTable() == table) {
                ordersByTable.add(order);
            }
        }
        this.ordersByTable=ordersByTable;
        return ordersByTable;
        }


        public void printOrdersByTable(int table) {
            exportOrdersByTable(table);
            String tablePadded = String.format("%02d", table);
            System.out.println("** Objednávky pro stůl č. " + tablePadded + " **\n" + "****");
            for (int i=0; i < ordersByTable.size(); i++) {
                System.out.println(
                        i+1 + ". "
                        + ordersByTable.get(i).getMeal().getTitle() + " "
                        + ordersByTable.get(i).getPieces() + "x "
                        + "(" + ordersByTable.get(i).getMeal().getPrice().multiply(BigDecimal.valueOf(ordersByTable.get(i).getPieces())) + " Kč):\t"
                        + ordersByTable.get(i).getOrderTime().getHour() + ":" + ordersByTable.get(i).getOrderTime().getMinute()
                        + "-" + ordersByTable.get(i).getFulfilmentTime().getHour() + ":" + ordersByTable.get(i).getFulfilmentTime().getMinute()
                        + "\t" + (ordersByTable.get(i).isPaid()? "zaplaceno" : ""));
            }
        }

        public BigDecimal pricePerTable(int table){
        BigDecimal pricePerTable = BigDecimal.valueOf(0);
        for(Orders order : orders) {
            if (order.getTable() == table) {
                pricePerTable= pricePerTable.add(order.getMeal().getPrice().multiply(BigDecimal.valueOf(order.getPieces())));
            }
        }
        return pricePerTable;
        }

        public void printPricePerTable(int table){
        System.out.println("Celková cena konzumace pro stůl č. " + table + " je: " + pricePerTable(table) + " Kč");
        }

    }
