package christmas.domain;

import christmas.constants.Menu;
import christmas.constants.MenuCategory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;

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

    public long calculateDiscountWeekdays(Restaurant restaurant) {
        if (isNotWeekdays(restaurant.getReservationDate())) {
            return 0L;
        }

        return sumDessertMenuCount(restaurant.getOrders()) * DEFAULT_DISCOUNT_AMOUNT;
    }

    private int sumDessertMenuCount(Map<Menu, Integer> orders) {
        return orders.entrySet().stream()
                .filter(order -> MenuCategory.isDessert(order.getKey()))
                .mapToInt(order -> order.getValue())
                .sum();
    }

    private boolean isNotWeekdays(LocalDate reservationDay) {
        return Arrays.stream(Weekdays.values())
                .noneMatch(weekday -> weekday.getWeekday() == reservationDay.getDayOfWeek());
    }
}
