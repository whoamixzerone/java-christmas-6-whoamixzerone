package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

public class WeekendsDiscount {
    private enum Weekends {
        FRIDAY(DayOfWeek.FRIDAY),
        SUNDAY(DayOfWeek.SUNDAY);

        private final DayOfWeek weekend;

        Weekends(DayOfWeek weekend) {
            this.weekend = weekend;
        }

        public DayOfWeek getWeekend() {
            return weekend;
        }
    }

    private static final long DEFAULT_DISCOUNT_AMOUNT = 2_023L;

    public WeekendsDiscount() {
    }

    public long calculateDiscountWeekends(LocalDate reservationDay, int mainCount) {
        if (isNotWeekends(reservationDay)) {
            return 0L;
        }

        return 1L;
    }

    private boolean isNotWeekends(LocalDate reservationDay) {
        return Arrays.stream(Weekends.values())
                .noneMatch(weekend -> weekend.getWeekend() == reservationDay.getDayOfWeek());
    }
}
