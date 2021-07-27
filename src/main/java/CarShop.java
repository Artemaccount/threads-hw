import java.util.ArrayList;
import java.util.List;

public class CarShop {
    Seller seller = new Seller(this);
    List<Car> cars = new ArrayList<>(10);
    private int carCount = 0;
    protected static final int MAX_SELL_COUNT = 10;

    public void buyCar(){
        seller.buyCar();
    }

    public void produceCar(){
        seller.produceCar();
    }

    List<Car> getCars(){
        return cars;
    }

    public int getCarCount() {
        return carCount;
    }

    public int getMaxSellCount(){
        return MAX_SELL_COUNT;
    }

    public void plusCarCount(){
        carCount++;
    }
}
