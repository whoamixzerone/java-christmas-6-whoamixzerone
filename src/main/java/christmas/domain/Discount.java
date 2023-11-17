package christmas.domain;

public abstract class Discount {
    protected long amount;

    protected abstract long calculateDiscount(Restaurant restaurant);

    public long getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
