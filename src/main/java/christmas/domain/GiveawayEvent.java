package christmas.domain;

public class GiveawayEvent extends Discount {
    private static final long GIVEAWAY_AMOUNT = 120_000L;
    private static final long GIVEAWAY_CHAMPAGNE_AMOUNT = 25_000L;

    public GiveawayEvent(Restaurant restaurant) {
        amount = calculateDiscount(restaurant);
    }

    @Override
    public long calculateDiscount(Restaurant restaurant) {
        if (isGiveawayAmountLessThan(restaurant.calculateTotalAmountBeforeDiscount())) {
            return 0L;
        }

        return GIVEAWAY_CHAMPAGNE_AMOUNT;
    }

    private boolean isGiveawayAmountLessThan(long amount) {
        return amount < GIVEAWAY_AMOUNT;
    }

    @Override
    public String toString() {
        return String.format("증정 이벤트: -%,d원", amount);
    }
}
