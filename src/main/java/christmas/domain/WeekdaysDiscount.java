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

    public WeekdaysDiscount() {
    }

    public long calculateDiscountWeekdays(LocalDate reservationDay) {
        if (isNotWeekdays(reservationDay)) {
            return 0L;
        }

        return 1L;
    }

    private boolean isNotWeekdays(LocalDate reservationDay) {
        return Arrays.stream(Weekdays.values())
                .noneMatch(weekday -> weekday.getWeekday() == reservationDay.getDayOfWeek());
    }
}
