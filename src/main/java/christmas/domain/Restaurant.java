package christmas.domain;

import christmas.constants.Menu;

import java.time.LocalDate;
import java.util.*;

public class Restaurant {
    private static final long DEFAULT_BENEFIT_AMOUNT = 10_000L;

    private final Map<Menu, Integer> orders;
    private final List<Discount> benefits;
    private final LocalDate reservationDate;

    public Restaurant(Map<Menu, Integer> orders, int reservationDay) {
        this.orders = new EnumMap<>(orders);
        benefits = new ArrayList<>();
        this.reservationDate = LocalDate.of(2023, 12, reservationDay);
    }

    public long calculateTotalAmountBeforeDiscount() {
        return orders.entrySet().stream()
                .filter(order -> Arrays.stream(Menu.values())
                        .anyMatch(menu -> menu == order.getKey()))
                .mapToLong(order -> order.getKey().getAmount() * order.getValue())
                .sum();
    }

    public long calculateTotalAmountAfterDiscount() {
        long totalAmountBeforeDiscount = calculateTotalAmountBeforeDiscount();
        long totalAmountBenefit = calculateTotalAmountBenefit();

        if (!GiveawayEvent.isGiveawayAmountLessThan(totalAmountBeforeDiscount)) {
            totalAmountBeforeDiscount += Menu.CHAMPAGNE.getAmount();
        }

        return totalAmountBeforeDiscount - totalAmountBenefit;
    }

    public long calculateTotalAmountBenefit() {
        if (isNotBenefits()) {
            return 0L;
        }

        return benefits.stream()
                .mapToLong(benefit -> benefit.getAmount())
                .sum();
    }

    public void applyBenefits() {
        benefits.add(new ChristmasDiscount(this));
        benefits.add(new WeekdaysDiscount(this));
        benefits.add(new WeekendsDiscount(this));
        benefits.add(new SpecialDiscount(this));
        benefits.add(new GiveawayEvent(this));
    }

    public boolean isNotBenefits() {
        return DEFAULT_BENEFIT_AMOUNT > calculateTotalAmountBeforeDiscount();
    }

    public Map<Menu, Integer> getOrders() {
        return orders;
    }

    public List<Discount> getBenefits() {
        return benefits;
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
