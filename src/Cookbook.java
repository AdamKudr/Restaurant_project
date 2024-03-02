import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    public void removeDish(Dish dish) {
        cookbook.remove(dish);
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
}
