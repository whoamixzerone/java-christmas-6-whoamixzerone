package christmas.util;

import christmas.constants.Menu;

import java.util.Map;

public class InputNumber {
    public static int toInt(String input) {
        return Integer.parseInt(input);
    }

    public static int sumMenuCount(Map<Menu, Integer> orders) {
        return orders.entrySet().stream()
                .mapToInt(order -> order.getValue())
                .sum();
    }
}
