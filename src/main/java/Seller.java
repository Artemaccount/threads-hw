public class Seller {
    private CarShop carShop;
    private final int BUY_СAR_TIME = 2000;
    private final int PRODUCE_CAR_TIME = 3000;
    private int carCount = 0;

    public Seller(CarShop carShop) {
        this.carShop = carShop;
    }

    public synchronized void produceCar() {
        if (carShop.getCarCount() < carShop.getMaxSellCount()) {
            try {
                Thread.sleep(PRODUCE_CAR_TIME);
                System.out.println("Производитель " + Thread.currentThread().getName() + " выпустил 1 авто");
                carShop.getCars().add(new Car());
                System.out.println("Автомобиль поступил в автосалон");
                notify();
            } catch (InterruptedException e) {
                carShop.getCars().add(new Car());
                Thread.currentThread().interrupt();
            }
            new Car();
        }
    }

    public synchronized void buyCar() {
        if (carShop.getCarCount() < carShop.getMaxSellCount()) {
            try {
                System.out.println(Thread.currentThread().getName() + " зашёл(-ла) в автосалон");
                while (carShop.getCars().size() == 0) {
                    System.out.println("Автосалон: машин нет");
                    wait();
                }
                carShop.plusCarCount();
                Thread.sleep(BUY_СAR_TIME);
                System.out.println(Thread.currentThread().getName() + " уехал(-а) на новеньком авто");
                System.out.println("**** ПРОДАНО АВТОМОБИЛЕЙ: " + carShop.getCarCount() + " ****");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (carShop.getCars().size() != 0) {
                carShop.getCars().remove(0);
            }
        }
    }
}