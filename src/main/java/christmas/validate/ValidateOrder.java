package christmas.validate;

import christmas.constants.Menu;
import christmas.constants.MenuCategory;
import christmas.constants.Promotion;
import christmas.domain.Restaurant;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;

public class ValidateOrder {
    private static final Pattern MENU_REGEX = Pattern.compile("([ㄱ-ㅎ|ㅏ-ㅣ|가-힣]+)-(\\d+)");

    private static final String INVALID_ORDER = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String INVALID_ORDER_MENU = "[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요.";
    private static final String INVALID_TOTAL_MENU_COUNT = "[ERROR] 최대 주문 개수는 20개입니다. 다시 입력해 주세요.";

    public static void form(String[] menus) {
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
    public static void isNonExistentMenu(Menu menu) {
        if (menu == Menu.EMPTY) {
            throw new IllegalArgumentException(INVALID_ORDER);
        }
    }
    public static void eachMenuCount(Map<Menu, Integer> orders) {
        orders.entrySet().stream()
                .filter(order -> isNotOneMoreThan(order.getValue()))
                .findAny()
                .ifPresent(menu -> {
                    throw new IllegalArgumentException(INVALID_ORDER);
                });
    }
    private static boolean isNotOneMoreThan(int menuCount) {
        return menuCount < Promotion.DEFAULT_MENU_COUNT;
    }
    public static void menuTotalCount(Map<Menu, Integer> orders) {
        if (Promotion.TOTAL_MENU_COUNT < Restaurant.sumMenuCount(orders)) {
            throw new IllegalArgumentException(INVALID_TOTAL_MENU_COUNT);
        }
    }
    public static void menuDuplicate(Map<Menu, Integer> orders, String[] menuGroup) {
        if (orders.size() != menuGroup.length) {
            throw new IllegalArgumentException(INVALID_ORDER);
        }
    }
    public static void onlyBeverage(Map<Menu, Integer> orders) {
        if (MenuCategory.isOnlyBeverage(orders.keySet())) {
            throw new IllegalArgumentException(INVALID_ORDER_MENU);
        }
    }
}
