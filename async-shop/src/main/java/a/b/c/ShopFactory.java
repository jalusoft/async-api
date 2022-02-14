package a.b.c;

public final class ShopFactory {
    public static Shop createShop(String name) {
        return new OnlineShop(name);
    }
}
