package christmas.domain;

import christmas.constants.Menu;
import christmas.constants.Promotion;

public class GiveawayEvent extends Discount {
    private static final long GIVEAWAY_AMOUNT = 120_000L;
    private static final long GIVEAWAY_CHAMPAGNE_AMOUNT = Menu.CHAMPAGNE.getAmount();

    public GiveawayEvent(Restaurant restaurant) {
        amount = calculateDiscount(restaurant);
    }

    @Override
    public long calculateDiscount(Restaurant restaurant) {
        if (isGiveawayAmountLessThan(restaurant.calculateTotalAmountBeforeDiscount())) {
            return Promotion.ZERO_AMOUNT;
        }

        return GIVEAWAY_CHAMPAGNE_AMOUNT;
    }

    public static boolean isGiveawayAmountLessThan(long amount) {
        return amount < GIVEAWAY_AMOUNT;
    }

    @Override
    public String toString() {
        return String.format("증정 이벤트: -%,d원", amount);
    }
}
