package christmas.domain;

import christmas.constants.Menu;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class Restaurant {
    private static final long DEFAULT_BENEFIT_AMOUNT = 10_000L;
    private static final long GIVEAWAY_AMOUNT = 120_000L;

    private final Map<Menu, Integer> orders;
    private final ChristmasDiscount christmasDiscount;

    private LocalDate reservationDate;

    public Restaurant(Map<Menu, Integer> orders) {
        this.orders = new EnumMap<>(orders);
        christmasDiscount = new ChristmasDiscount();
    }

    public long calculateTotalAmountBeforeDiscount() {
        return orders.entrySet().stream()
                .filter(order -> Arrays.stream(Menu.values())
                        .anyMatch(menuType -> menuType == order.getKey()))
                .mapToLong(order -> order.getKey().getAmount() * order.getValue())
                .sum();
    }

    public boolean isNotBenefits() {
        return DEFAULT_BENEFIT_AMOUNT > calculateTotalAmountBeforeDiscount();
    }

    public void checkedBenefits(int reservationDay) {
        this.reservationDate = LocalDate.of(2023, 12, reservationDay);

        christmasDiscount.calculateDiscountChristmas(this.reservationDate);
    }

    public Map<Menu, Integer> getOrders() {
        return orders;
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
