package christmas.domain;

import christmas.constants.Promotion;

import java.time.LocalDate;
import java.time.Period;

public class ChristmasDiscount extends Discount {
    private static final LocalDate FORM_DATE = LocalDate.of(Promotion.YEAR, Promotion.MONTH, 1);
    private static final LocalDate CHRISTMAS_DATE = LocalDate.of(Promotion.YEAR, Promotion.MONTH, 25);

    private static final long INCREMENT_DISCOUNT_AMOUNT = 100L;
    private static final long DEFAULT_CHRISTMAS_DISCOUNT_AMOUNT = 1_000L;

    public ChristmasDiscount(Restaurant restaurant) {
        amount = calculateDiscount(restaurant);
    }

    @Override
    public long calculateDiscount(Restaurant restaurant) {
        LocalDate reservationDate = restaurant.getReservationDate();
        if (isPassedChristmas(reservationDate)) {
            return Promotion.ZERO_AMOUNT;
        }

        Period periodDate = Period.between(FORM_DATE, reservationDate);
        return periodDate.getDays() * INCREMENT_DISCOUNT_AMOUNT + DEFAULT_CHRISTMAS_DISCOUNT_AMOUNT;
    }

    private boolean isPassedChristmas(LocalDate reservationDate) {
        return !reservationDate.isBefore(FORM_DATE) && reservationDate.isAfter(CHRISTMAS_DATE);
    }

    @Override
    public String toString() {
        return String.format("크리스마스 디데이 할인: -%,d원", amount);
    }
}
