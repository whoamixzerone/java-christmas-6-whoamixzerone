package christmas.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InputString {
    public static String[] orderGroupSplit(String input, String delimiter) {
        return removeSpace(input.split(delimiter));
    }

    public static Map<String, Integer> orderSplit(String[] orderGroup) {
        Map<String, Integer> orders = new HashMap<>();

        for (String order : orderGroup) {
            String[] food = orderGroupSplit(order, "-");

            orders.put(food[0], InputNumber.toInt(food[1]));
        }
        return orders;
    }

    private static String[] removeSpace(String[] menuGroup) {
        return Arrays.stream(menuGroup)
                .map(menu -> menu.replaceAll("\\s", ""))
                .toArray(String[]::new);
    }
}
