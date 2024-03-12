import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

public class RestaurantManager {

    List<Dish> menu;
    List<Orders> orders;

    public RestaurantManager(Cookbook cookbook, Orders orders) {
        this.menu = cookbook.getCookbook();
        this.orders = orders.getOrders();
    }

    public int ordersInProgress(List<Orders> orders) {
        int inProgress = 0;
        for(Orders order : orders) {
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

    public int averageOrderProcessingTime (List<Orders> orders) {
        int numberOfOrders = 0;
        int totalProcessingTime = 0;
        for (Orders order : orders) {
            if (order.isFulfilled()) {
                numberOfOrders++;
                totalProcessingTime += ChronoUnit.MINUTES.between(order.getOrderTime(), order.getFulfilmentTime());
            }
        }
        return totalProcessingTime /numberOfOrders;
    }
}
