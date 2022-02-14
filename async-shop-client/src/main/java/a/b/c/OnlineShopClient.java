package a.b.c;

import java.util.concurrent.Future;
import java.util.function.Supplier;

/**
 * Hello world!
 */
public class OnlineShopClient {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Shop shop1 = ShopFactory.createShop("Best Shop");

        long start = System.nanoTime();
        double price = shop1.getPrice("IFon1");
        long invocationTime = ((System.nanoTime() - start) / 1000000);
        log(() -> "Invocation [sync] returned after " + invocationTime + "[msc]");
        log(() -> "Price " + price);


        long start2 = System.nanoTime();
        Future<Double> futurePrice = shop1.getPriceAsync("IFon1");
        long invocationTime2 = ((System.nanoTime() - start2) / 1000000);
        log(() -> "Invocation [sync] returned after " + invocationTime2 + "[msc]");

        doSomethingElse();

        try {
            double price2 = futurePrice.get();
            log(() -> "Price is " + price2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long retrievalTime2 = ((System.nanoTime() - start2) / 1000000);
        log(() -> "Price returned after " + retrievalTime2 + "[msc]");
    }

    private static void log(Supplier<String> supplier) {
        System.out.println(supplier.get());
    }

    private static void doSomethingElse() {
        for (int i = 0; i < 10000; i++) {
            ;
        }
    }
}
