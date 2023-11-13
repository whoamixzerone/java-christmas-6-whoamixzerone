package christmas.domain;

import christmas.constants.Menu;
import christmas.constants.MenuCategory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;

public class WeekendsDiscount {
    private enum Weekends {
        FRIDAY(DayOfWeek.FRIDAY),
        SATURDAY(DayOfWeek.SATURDAY);

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

    public long calculateDiscountWeekends(Restaurant restaurant) {
        if (isNotWeekends(restaurant.getReservationDate())) {
            return 0L;
        }

        return sumMainMenuCount(restaurant.getOrders()) * DEFAULT_DISCOUNT_AMOUNT;
    }

    private int sumMainMenuCount(Map<Menu, Integer> orders) {
        return orders.entrySet().stream()
                .filter(order -> MenuCategory.isMain(order.getKey()))
                .mapToInt(order -> order.getValue())
                .sum();
    }

    private boolean isNotWeekends(LocalDate reservationDay) {
        return Arrays.stream(Weekends.values())
                .noneMatch(weekend -> weekend.getWeekend() == reservationDay.getDayOfWeek());
    }
}
