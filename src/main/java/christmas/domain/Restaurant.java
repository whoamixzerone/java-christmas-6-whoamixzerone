package christmas.domain;

import christmas.constants.Badge;
import christmas.constants.Menu;
import christmas.constants.Promotion;

import java.time.LocalDate;
import java.util.*;

public class Restaurant {
    private final Map<Menu, Integer> orders;
    private final List<Discount> benefits;
    private final LocalDate reservationDate;

    public Restaurant(Map<Menu, Integer> orders, int reservationDay) {
        this.orders = new EnumMap<>(orders);
        this.reservationDate = LocalDate.of(Promotion.YEAR, Promotion.MONTH, reservationDay);
        benefits = new ArrayList<>();

        benefits.add(new ChristmasDiscount(this));
        benefits.add(new WeekdaysDiscount(this));
        benefits.add(new WeekendsDiscount(this));
        benefits.add(new SpecialDiscount(this));
        benefits.add(new GiveawayEvent(this));
    }

    public static int sumMenuCount(Map<Menu, Integer> orders) {
        return orders.entrySet().stream()
                .mapToInt(order -> order.getValue())
                .sum();
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
            return Promotion.ZERO_AMOUNT;
        }

        return benefits.stream()
                .mapToLong(benefit -> benefit.getAmount())
                .sum();
    }

    public boolean isNotBenefits() {
        return Promotion.DEFAULT_BENEFIT_AMOUNT > calculateTotalAmountBeforeDiscount();
    }

    public Badge findBadgeByAmountDiscount() {
        return Badge.findByAmount(calculateTotalAmountBenefit());
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
