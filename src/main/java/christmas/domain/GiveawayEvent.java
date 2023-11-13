package christmas.domain;

public class GiveawayEvent {
    private static final long GIVEAWAY_AMOUNT = 120_000L;
    private static final long GIVEAWAY_CHAMPAGNE_AMOUNT = 25_000L;

    public GiveawayEvent() {
    }

    public long calculateDiscount(Restaurant restaurant) {
        if (isGiveawayAmountLessThan(restaurant.calculateTotalAmountBeforeDiscount())) {
            return 0L;
        }

        return GIVEAWAY_CHAMPAGNE_AMOUNT;
    }

    private boolean isGiveawayAmountLessThan(long amount) {
        return amount < GIVEAWAY_AMOUNT;
    }
}
