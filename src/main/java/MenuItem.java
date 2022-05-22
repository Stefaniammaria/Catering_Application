import java.io.Serializable;

public abstract class MenuItem  implements Serializable {

    private String title;
    private double rating;
    private int calories;
    private int proteins;
    private int fats;
    private int sodium;
    private int price;

    public abstract String getTitle();
    public abstract double getRating();
    public abstract int getCalories();
    public abstract int getProteins();
    public abstract int getFats();
    public abstract int getSodium();
    public abstract int getPrice();


    @Override
    public String toString() {
        return title + ',' + rating + "," + calories + "," + proteins + "," + fats + "," + sodium + "," + price + ",\n" ;
    }
}
