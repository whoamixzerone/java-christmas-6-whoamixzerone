package christmas.domain;

import christmas.constants.Menu;
import christmas.constants.MenuCategory;
import christmas.constants.Promotion;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;

public class WeekendsDiscount extends Discount {
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

    public WeekendsDiscount(Restaurant restaurant) {
        amount = calculateDiscount(restaurant);
    }

    @Override
    public long calculateDiscount(Restaurant restaurant) {
        if (isNotWeekends(restaurant.getReservationDate())) {
            return Promotion.ZERO_AMOUNT;
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

    @Override
    public String toString() {
        return String.format("주말 할인: -%,d원", amount);
    }
}
