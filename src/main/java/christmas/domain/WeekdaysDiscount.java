package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

public class WeekdaysDiscount {
    private enum Weekdays {
        SUNDAY(DayOfWeek.SUNDAY),
        MONDAY(DayOfWeek.MONDAY),
        TUESDAY(DayOfWeek.TUESDAY),
        WEDNESDAY(DayOfWeek.WEDNESDAY),
        THURSDAY(DayOfWeek.THURSDAY);

        private DayOfWeek weekday;

        Weekdays(DayOfWeek weekday) {
            this.weekday = weekday;
        }

        public DayOfWeek getWeekday() {
            return weekday;
        }
    }

    private static final long DEFAULT_DISCOUNT_AMOUNT = 2_023L;

    public WeekdaysDiscount() {
    }

    public long calculateDiscountWeekdays(LocalDate reservationDay, int dessertCount) {
        if (isNotWeekdays(reservationDay)) {
            return 0L;
        }

        return dessertCount * DEFAULT_DISCOUNT_AMOUNT;
    }

    private boolean isNotWeekdays(LocalDate reservationDay) {
        return Arrays.stream(Weekdays.values())
                .noneMatch(weekday -> weekday.getWeekday() == reservationDay.getDayOfWeek());
    }
}
