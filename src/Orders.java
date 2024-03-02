import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Orders {

    int table;
    int pieces;
    Dish meal;
    LocalDateTime orderTime;
    LocalDateTime fulfilmentTime = LocalDateTime.parse(LocalDateTime.of(0000, 1, 1, 0, 0).toString());
    boolean paid;
    List<Orders> orders;

    public Orders(int table, int pieces, Dish meal, LocalDateTime orderTime) {
        this.table = table;
        this.pieces = pieces;
        this.meal = meal;
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

    public Dish getMeal() {
        return meal;
    }

    public void setMeal(Dish meal) {
        this.meal = meal;
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

    public void saveToFile(String fileName) throws OrdersException {
        String delimiter = ";";
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Orders order : orders) {
                writer.println(order.getTable() + delimiter
                        + order.getPieces() + delimiter
                        + order.getMeal().getDishID() + delimiter
                        + order.getOrderTime() + delimiter
                        + order.getFulfilmentTime());
            }

        } catch (FileNotFoundException e) {
            System.err.println("Soubor " + fileName + " nenalezen");
        } catch (IOException e) {
            throw new OrdersException("Chyba výstupu při zápisu do souboru " + fileName + ". " + e.getLocalizedMessage());
        }
    }

    @Override
    public String toString() {
        return  "table:" + table +
                ", pieces:" + pieces +
                ", dish:" + meal.getDishID() +
                ", orderTime:" + orderTime +
                ", fulfilmentTime:" + fulfilmentTime +
                ", paid:" + paid;
    }
}
