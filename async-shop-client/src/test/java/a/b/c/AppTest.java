package a.b.c;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.concurrent.Future;
import java.util.function.Supplier;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppTest {

    private static void log(Supplier<String> supplier) {
        System.out.println(supplier.get());
    }

    private static void doSomethingElse() {
        for (int i = 0; i < 10000; i++) {
            ;
        }
    }

    @Test
    public void tc_001_testGetPriceSync() {
        Shop shop = ShopFactory.createShop("Best Shop");

        long start = System.nanoTime();
        double price = shop.getPrice("IFon1");
        long invocationTime = ((System.nanoTime() - start) / 1000000);
        log(() -> "Price " + price);
        log(() -> "Price returned after " + invocationTime + "[msc]");
    }

    @Test
    public void tc_002_testGetPriceAsync() {
        Shop shop = ShopFactory.createShop("Best Shop");

        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("IFon1");
        long invocationTime = ((System.nanoTime() - start) / 1000000);
        log(() -> "Invocation [async] returned after " + invocationTime + "[msc]");

        doSomethingElse();

        try {
            double price = futurePrice.get();
            log(() -> "Price is " + price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long retrievalTime = ((System.nanoTime() - start) / 1000000);
        log(() -> "Price returned after " + retrievalTime + "[msc]");
    }

    @Test
    public void tc_003_testGetPriceAsync2() {
        Shop shop = ShopFactory.createShop("Best Shop");

        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync2("IFon1");
        long invocationTime = ((System.nanoTime() - start) / 1000000);
        log(() -> "Invocation [async] returned after " + invocationTime + "[msc]");

        doSomethingElse();

        try {
            double price = futurePrice.get();
            log(() -> "Price is " + price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long retrievalTime = ((System.nanoTime() - start) / 1000000);
        log(() -> "Price returned after " + retrievalTime + "[msc]");
    }

    @Test
    public void tc_004_testFindPricesSequential() {
        PriceFinder priceFinder = PriceFinder.create();

        long start = System.nanoTime();
        List<String> prices = priceFinder.findPricesSequential("IFon1");
        long duration = (System.nanoTime() - start) / 1000000;
        prices.forEach(price -> log(() -> price));

        log(() -> "Done in " + duration + "[msc]");
    }

    @Test
    public void tc_005_testFindPricesParallel() {
        PriceFinder priceFinder = PriceFinder.create();

        long start = System.nanoTime();
        List<String> prices = priceFinder.findPricesParallel("IFon1");
        long duration = (System.nanoTime() - start) / 1000000;
        prices.forEach(price -> log(() -> price));

        log(() -> "Done in " + duration + "[msc]");
    }

    @Test
    public void tc_006_testFindPricesAsync1() {
        PriceFinder priceFinder = PriceFinder.create();

        long start = System.nanoTime();
        List<String> prices = priceFinder.findPricesAsync("IFon1");
        long duration = (System.nanoTime() - start) / 1000000;
        prices.forEach(price -> log(() -> price));

        log(() -> "Done in " + duration + "[msc]");
    }

    @Test
    public void tc_007_testFindPricesAsyncCustomExecutor() {
        PriceFinder priceFinder = PriceFinder.create();

        long start = System.nanoTime();
        List<String> prices = priceFinder.findPricesAsyncCustomExecutor("IFon1");
        long duration = (System.nanoTime() - start) / 1000000;
        prices.forEach(price -> log(() -> price));

        log(() -> "Done in " + duration + "[msc]");
    }

}
