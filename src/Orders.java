import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public void removeOrder(int index) {orders.remove(index);
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
                        + order.getFulfilmentTime() + delimiter
                        + order.isPaid());
            }

        } catch (FileNotFoundException e) {
            System.err.println("Soubor " + fileName + " nenalezen");
        } catch (IOException e) {
            throw new OrdersException("Chyba výstupu při zápisu do souboru " + fileName + ". " + e.getLocalizedMessage());
        }
    }

    public void loadFromFile (String fileName, Cookbook cookbook) throws OrdersException {
        int linecounter = 0;
        orders.clear();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNextLine()) {
                linecounter++;
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                if (parts.length != 6) throw new OrdersException("Nesprávný počet položek na řádku " + linecounter + " !");
                int table = Integer.parseInt(parts[0]);
                int pieces = Integer.parseInt(parts[1]);
                int dishID = Integer.parseInt(parts[2]);
                LocalDateTime orderTime = LocalDateTime.parse(parts[3]);
                LocalDateTime fulfilmentTime = LocalDateTime.parse(parts[4]);
                boolean paid = Boolean.parseBoolean(parts[5]);
                Dish meal = null;
                for (Dish dish : cookbook.getCookbook()) {
                    if (dish.getDishID() == dishID) {
                        meal = dish;
                        break;
                    }
                }
                if (meal == null) {
                    throw new OrdersException("Dish with ID " + dishID + " not found in the cookbook");
                }
                Orders order = new Orders(table, pieces, meal, orderTime);
                orders.add(order);
            }
        }
        catch (FileNotFoundException e) {
            throw new OrdersException("Soubor " + fileName + " nenalezen! " + e.getLocalizedMessage());
        }
    }

    @Override
    public String toString() {
        return  "table:" + table +
                ", pieces:" + pieces +
                ", (dish: " + meal + " )" +
                ", orderTime:" + orderTime +
                ", fulfilmentTime:" + fulfilmentTime +
                ", paid:" + paid;
    }
}
