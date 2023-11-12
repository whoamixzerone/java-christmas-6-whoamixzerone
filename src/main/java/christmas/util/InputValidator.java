package christmas.util;

import christmas.constants.Menu;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;

public class InputValidator {
    private static final Pattern NUMBER_REGEX = Pattern.compile("^[0-9]+$");
    private static final Pattern MENU_REGEX = Pattern.compile("([ㄱ-ㅎ|ㅏ-ㅣ|가-힣]+)-(\\d+)");

    private static final String INVALID_DAY = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String INVALID_ORDER = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String INVALID_TOTAL_MENU_COUNT = "[ERROR] 최대 주문 개수는 20개입니다. 다시 입력해 주세요.";

    private static final int FROM_DAY = 1;
    private static final int TO_DAY = 31;
    private static final int DEFAULT_MENU_COUNT = 1;
    private static final int TOTAL_MENU_COUNT = 20;

    public static void validNumber(String input) {
        validBlank(input);
        validNumeric(input);
        validDateRange(input);
    }

    private static void validBlank(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(INVALID_DAY);
        }
    }

    private static void validNumeric(String input) {
        if (isNotDigit(input)) {
            throw new IllegalArgumentException(INVALID_DAY);
        }
    }

    private static void validDateRange(String input) {
        if (isNotBetweenFromOrTo(InputNumber.toInt(input))) {
            throw new IllegalArgumentException(INVALID_DAY);
        }
    }

    private static boolean isNotBetweenFromOrTo(int day) {
        return day < FROM_DAY || day > TO_DAY;
    }

    private static boolean isNotDigit(String input) {
        return !NUMBER_REGEX.matcher(input).matches();
    }

    public static void validOrderForm(String[] menus) {
        Arrays.stream(menus)
                .filter(menu -> isNotOrderForm(menu))
                .findAny()
                .ifPresent(menu -> {
                    throw new IllegalArgumentException(INVALID_ORDER);
                });
    }

    private static boolean isNotOrderForm(String menu) {
        return !MENU_REGEX.matcher(menu).matches();
    }

    public static void validOrder(Map<String, Integer> menus) {
        validMenu(menus);
        validMenuCount(menus);
        validMenuTotalCount(menus);
    }

    private static void validMenu(Map<String, Integer> menus) {
        menus.keySet().stream()
                .filter(menu -> Menu.isNotInMenu(menu))
                .findAny()
                .ifPresent(menu -> {
                    throw new IllegalArgumentException(INVALID_ORDER);
                });
    }

    private static void validMenuCount(Map<String, Integer> menus) {
        menus.entrySet().stream()
                .filter(menu -> isNotOneMoreThan(menu.getValue()))
                .findAny()
                .ifPresent(menu -> {
                    throw new IllegalArgumentException(INVALID_ORDER);
                });
    }

    private static boolean isNotOneMoreThan(int menuCount) {
        return menuCount < DEFAULT_MENU_COUNT;
    }

    public static void validOrderDuplicate(Map<String, Integer> menus, String[] menuGroup) {
        if (menus.size() != menuGroup.length) {
            throw new IllegalArgumentException(INVALID_ORDER);
        }
    }

    private static void validMenuTotalCount(Map<String, Integer> menus) {
        if (TOTAL_MENU_COUNT < sumMenuCount(menus)) {
            throw new IllegalArgumentException(INVALID_TOTAL_MENU_COUNT);
        }
    }

    private static int sumMenuCount(Map<String, Integer> menus) {
        return menus.entrySet().stream()
                .mapToInt(menu -> menu.getValue())
                .sum();
    }
}
