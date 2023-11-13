package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialDiscount {
    private static final LocalDate STAR_CHRISTMAS_DAY = LocalDate.of(2023, 12, 25);
    private static final DayOfWeek STAR_DAY_OF_WEEK = DayOfWeek.SUNDAY;
    private static final long SPECIAL_DISCOUNT_AMOUNT = 1_000L;

    public SpecialDiscount() {
    }


    public long calculateDiscount(Restaurant restaurant) {
        if (isNotStarDay(restaurant.getReservationDate())) {
            return 0L;
        }

        return SPECIAL_DISCOUNT_AMOUNT;
    }

    private boolean isNotStarDay(LocalDate reservationDate) {
        return !(isSunDay(reservationDate.getDayOfWeek())
                || isChristmasDay(reservationDate));
    }

    private boolean isSunDay(DayOfWeek weekday) {
        return weekday.compareTo(STAR_DAY_OF_WEEK) == 0;
    }

    private boolean isChristmasDay(LocalDate reservationDate) {
        return reservationDate.compareTo(STAR_CHRISTMAS_DAY) == 0;
    }
}
