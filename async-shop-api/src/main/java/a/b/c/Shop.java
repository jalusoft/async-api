package a.b.c;

import java.util.concurrent.Future;

public interface Shop {
    String getName();

    double getPrice(String product);

    Future<Double> getPriceAsync(String product);

    Future<Double> getPriceAsync2(String product);
}
