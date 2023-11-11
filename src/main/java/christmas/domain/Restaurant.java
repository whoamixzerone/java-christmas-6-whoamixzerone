package christmas.domain;

import christmas.constants.MenuType;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class Restaurant {
    private final Map<MenuType, Integer> orders;

    public Restaurant() {
        orders = new EnumMap<>(MenuType.class);
    }

    public long calculateTotalAmountBeforeDiscount() {
        return orders.entrySet().stream()
                .filter(order -> Arrays.stream(MenuType.values())
                        .anyMatch(menuType -> menuType == order.getKey()))
                .mapToLong(order -> order.getKey().getAmount() * order.getValue())
                .sum();
    }
}
