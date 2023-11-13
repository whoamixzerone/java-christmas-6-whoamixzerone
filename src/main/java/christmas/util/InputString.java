package christmas.util;

import christmas.constants.Menu;
import christmas.validate.ValidateOrder;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class InputString {
    public static String[] orderGroupSplit(String input, String delimiter) {
        return removeSpace(input.split(delimiter));
    }

    public static Map<Menu, Integer> orderSplit(String[] orderGroup) {
        Map<Menu, Integer> orders = new EnumMap<>(Menu.class);

        for (String order : orderGroup) {
            String[] food = orderGroupSplit(order, "-");

            Menu menu = orderToMenu(food[0]);
            ValidateOrder.isNonExistentMenu(menu);

            orders.put(menu, InputNumber.toInt(food[1]));
        }
        return orders;
    }

    private static Menu orderToMenu(String order) {
        return Menu.findByFood(order);
    }

    private static String[] removeSpace(String[] menuGroup) {
        return Arrays.stream(menuGroup)
                .map(menu -> menu.replaceAll("\\s", ""))
                .toArray(String[]::new);
    }
}
