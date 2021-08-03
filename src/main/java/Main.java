public class Main {

    public static void main(String[] args) {
        final CarShop carShop = new CarShop();
        ThreadGroup customers = new ThreadGroup("All Threads");
        ThreadGroup producers = new ThreadGroup("Producers");

        new Thread(customers, carShop::buyCar, "Вася").start();
        new Thread(customers, carShop::buyCar, "Петя").start();
        new Thread(customers, carShop::buyCar, "Маша").start();

        new Thread(producers, carShop::produceCar, "Toyota").start();

        producers.interrupt();
        customers.interrupt();

    }
}
