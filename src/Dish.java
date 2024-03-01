import java.math.BigDecimal;

public class Dish {

    int dishID;
    private String title;
    private BigDecimal price;
    private int preparationTime;
    private String url;

    //konstruktor - s foto

    public Dish(int dishID, String title, BigDecimal price, int preparationTime, String url) throws OrdersException {
        this.dishID = dishID;
        this.title = title;
        this.price = price;
        setPreparationTime(preparationTime);
        this.url = url;
    }

    //konstruktor - bez foto


    public Dish(int dishID, String title, BigDecimal price, int preparationTime) throws OrdersException {
        this.dishID = dishID;
        this.title = title;
        this.price = price;
        setPreparationTime(preparationTime);
        this.url = "blank";
    }

    public int getDishID() {
        return dishID;
    }

    public void setDishID(int dishID) {
        this.dishID = dishID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) throws OrdersException {
        if (preparationTime <= 0) {
            throw new OrdersException("Doba přípravy musí být kladné číslo! Zadali jste: " + preparationTime + "!");
        }
        this.preparationTime = preparationTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dishID=" + dishID +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", preparationTime=" + preparationTime +
                ", url='" + url + '\'' +
                '}';
    }
}
