import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Orders {

    int table;
    int pieces;
    int mealID;
    LocalDateTime orderTime;
    LocalDateTime fulfilmentTime = LocalDateTime.of(1899, 1, 1, 0, 0).MAX.truncatedTo(ChronoUnit.MINUTES);
    boolean paid;
    List<Orders> orders;

    public Orders(int table, int pieces, int mealID, LocalDateTime orderTime) {
        this.table = table;
        this.pieces = pieces;
        this.mealID = mealID;
        this.orderTime = orderTime.truncatedTo(ChronoUnit.MINUTES);
        paid = false;
    }

    public Orders() {
        this.orders = new ArrayList<>();
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public int getMeal() {
        return mealID;
    }

    public void setMeal(int mealID) {
        this.mealID = mealID;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public LocalDateTime getFulfilmentTime() {
        return fulfilmentTime;
    }

    public void setFulfilmentTime(LocalDateTime fulfilmentTime) {
        this.fulfilmentTime = fulfilmentTime;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public void addOrder(Orders order) {
        orders.add(order);
    }

    public List<Orders> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "Orders:\n" +
                "table:" + table +
                ", pieces:" + pieces +
                ", mealID:" + mealID +
                ", orderTime:" + orderTime +
                ", fulfilmentTime:" + fulfilmentTime +
                ", paid:" + paid;
    }
}

