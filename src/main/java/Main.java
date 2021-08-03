public class Main {

    public static void main(String[] args) throws InterruptedException {
        final CarShop carShop = new CarShop();
        ThreadGroup customers = new ThreadGroup("All Threads");
        ThreadGroup producers = new ThreadGroup("Producers");

        while (carShop.getCarCount() <= carShop.getMaxSellCount()) {

            new Thread(producers, carShop::produceCar, "Toyota").start();

            new Thread(customers, carShop::buyCar, "Вася").start();
            new Thread(customers, carShop::buyCar, "Коля").start();
            new Thread(customers, carShop::buyCar, "Маша").start();


            Thread.sleep(3000);
        }
        customers.interrupt();
        producers.interrupt();
    }
}
