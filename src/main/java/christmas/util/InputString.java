package christmas.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InputString {
    public static String[] menuGroupSplit(String input, String delimiter) {
        return removeSpace(input.split(delimiter));
    }

    public static Map<String, Integer> menuSplit(String[] menuGroup) {
        Map<String, Integer> menus = new HashMap<>();

        for (String menu : menuGroup) {
            String[] food = menuGroupSplit(menu, "-");
            menus.put(food[0], InputNumber.toInt(food[1]));
        }
        return menus;
    }

    private static String[] removeSpace(String[] menuGroup) {
        return Arrays.stream(menuGroup)
                .map(menu -> menu.replaceAll("\\s", ""))
                .toArray(String[]::new);
    }
}
