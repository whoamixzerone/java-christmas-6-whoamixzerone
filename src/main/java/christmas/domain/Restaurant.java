package christmas.domain;

import christmas.constants.Menu;
import christmas.constants.MenuCategory;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class Restaurant {
    private static final long DEFAULT_BENEFIT_AMOUNT = 10_000L;
    private static final long GIVEAWAY_AMOUNT = 120_000L;

    private final Map<Menu, Integer> orders;
    private final LocalDate reservationDate;
    private final ChristmasDiscount christmasDiscount;
    private final WeekdaysDiscount weekdaysDiscount;
    private final WeekendsDiscount weekendsDiscount;
    private final SpecialDiscount specialDiscount;

    public Restaurant(Map<Menu, Integer> orders, int reservationDay) {
        this.orders = new EnumMap<>(orders);
        this.reservationDate = LocalDate.of(2023, 12, reservationDay);

        christmasDiscount = new ChristmasDiscount();
        weekdaysDiscount = new WeekdaysDiscount();
        weekendsDiscount = new WeekendsDiscount();
        specialDiscount = new SpecialDiscount();
    }

    public long calculateTotalAmountBeforeDiscount() {
        return orders.entrySet().stream()
                .filter(order -> Arrays.stream(Menu.values())
                        .anyMatch(menu -> menu == order.getKey()))
                .mapToLong(order -> order.getKey().getAmount() * order.getValue())
                .sum();
    }

    public boolean isNotBenefits() {
        return DEFAULT_BENEFIT_AMOUNT > calculateTotalAmountBeforeDiscount();
    }

    public void checkedBenefits() {
        long christmasDiscount = this.christmasDiscount.calculateDiscountChristmas(this);
        long weekdaysDiscount = this.weekdaysDiscount.calculateDiscountWeekdays(this);
        long weekendsDiscount = this.weekendsDiscount.calculateDiscountWeekends(this);
        long specialDiscount = this.specialDiscount.calculateDiscount(this);
    }

    public Map<Menu, Integer> getOrders() {
        return orders;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    @Override
    public String toString() {
        StringBuilder menus = new StringBuilder();

        for (Map.Entry<Menu, Integer> order : orders.entrySet()) {
            menus.append(order.getKey().getFood())
                    .append(" ")
                    .append(order.getValue())
                    .append("개\n");
        }
        return menus.toString();
    }
}
