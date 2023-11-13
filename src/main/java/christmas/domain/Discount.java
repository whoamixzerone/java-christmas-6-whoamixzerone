package christmas.domain;

public abstract class Discount {
    protected long amount;

    public abstract long calculateDiscount(Restaurant restaurant);
}
