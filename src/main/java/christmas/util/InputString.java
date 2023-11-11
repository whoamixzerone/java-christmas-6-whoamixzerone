package christmas.util;

import java.util.HashMap;
import java.util.Map;

public class InputString {
    public static String[] menuGroupSplit(String input, String delimiter) {
        return input.split(delimiter);
    }

    public static Map<String, Integer> menuSplit(String[] menuGroup) {
        Map<String, Integer> menus = new HashMap<>();

        for (String menu : menuGroup) {
            String[] food = menuGroupSplit(menu, "-");
            menus.put(food[0], InputNumber.toInt(food[1]));
        }
        return menus;
    }
}
