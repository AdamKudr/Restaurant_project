import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cookbook {

    List<Dish> cookbook;

    public Cookbook() {
        this.cookbook = new ArrayList<>();
    }

    public List<Dish> getCookbook() {
        return cookbook;
    }

    public void setCookbook(List<Dish> cookbook) {
        this.cookbook = cookbook;
    }

    public void addDish(Dish dish) {
        cookbook.add(dish);
    }

    public void removeDish(int dishID) {
        cookbook.remove(cookbook.get(dishID+-1));
    }

    public void saveToFile (String fileName) throws OrdersException {
        String delimiter = ";";
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Dish dish : cookbook) {
                writer.println(dish.getDishID() + delimiter
                + dish.getTitle() + delimiter
                + dish.getPreparationTime() + delimiter
                + dish.getPrice() + delimiter
                + dish.getUrl());
            }

    } catch (FileNotFoundException e) {
            System.err.println("Soubor " + fileName + " nenalezen");
        }
        catch (IOException e) {
            throw new OrdersException("Chyba výstupu při zápisu do souboru " + fileName + ". " + e.getLocalizedMessage());
        }
        }
    public void loadFromFile (String fileName) throws OrdersException {
        int linecounter = 0;
        cookbook.clear();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNextLine()) {
                linecounter++;
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                if (parts.length != 5) throw new OrdersException("Nesprávný počet položek na řádku " + linecounter + " !");
                int dishID = Integer.parseInt(parts[0]);
                String title = parts[1];
                BigDecimal price = new BigDecimal(parts[3]);
                int preparationTime = Integer.parseInt(parts[2]);
                String url = parts[4];
                Dish dish = new Dish(dishID, title, price, preparationTime, url);
                cookbook.add(dish);
                }
            }
        catch (FileNotFoundException e) {
            throw new OrdersException("Soubor " + fileName + " nenalezen! " + e.getLocalizedMessage());
        }
        }
    }
