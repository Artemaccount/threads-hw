public class Seller {
    private final CarShop carShop;
    private final int BUY_СAR_TIME = 2000;
    private final int PRODUCE_CAR_TIME = 3000;

    public Seller(CarShop carShop) {
        this.carShop = carShop;
    }

    public void produceCar() {
        while (carShop.getCarCount() < carShop.getMaxSellCount()) {
            synchronized (carShop) {
                System.out.println("Производитель " + Thread.currentThread().getName() + " выпустил 1 авто");
                System.out.println("Автомобиль поступил в автосалон");
                carShop.getCars().add(new Car());
                carShop.notify();
            }
            try {
                Thread.sleep(PRODUCE_CAR_TIME);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " производит автомобиль");
            }
        }
        Thread.currentThread().interrupt();
    }

    public void buyCar() {
        while (carShop.getCarCount() <= (carShop.getMaxSellCount() -
                Thread.currentThread().getThreadGroup().activeCount())) {
            synchronized (carShop) {
                    System.out.println(Thread.currentThread().getName() + " зашёл(-ла) в автосалон");
                    while (carShop.getCars().size() == 0) {
                        System.out.println("Автосалон: машин нет");
                        try {
                            carShop.wait();
                        } catch (InterruptedException e) {
                            System.out.println("Покупатель " + Thread.currentThread().getName() + " ожидает");
                        }
                }
                carShop.plusCarCount();
                System.out.println(Thread.currentThread().getName() + " уехал(-а) на новеньком авто");
                System.out.println("**** ПРОДАНО АВТОМОБИЛЕЙ: " + carShop.getCarCount() + " ****");
                if (carShop.getCars().size() != 0) {
                    carShop.getCars().remove(0);
                }
            }
            try {
                Thread.sleep(BUY_СAR_TIME);
            } catch (InterruptedException e) {
                System.out.println("Покупатель " + Thread.currentThread().getName() + " оформляет автомобиль");
            }
        }
        Thread.currentThread().interrupt();
    }
}