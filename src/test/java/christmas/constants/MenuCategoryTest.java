package christmas.constants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class MenuCategoryTest {
    Set<Menu> menus;

    @BeforeEach
    void setUp() {
        menus = new HashSet<>();
    }

    @DisplayName("음료만 주문 시 true를 반환한다")
    @Test
    void isOnlyBeverage() {
        menus.add(Menu.COKE_ZERO);
        menus.add(Menu.RED_WINE);

        boolean result = MenuCategory.isOnlyBeverage(menus);

        assertThat(result).isTrue();
    }

    @DisplayName("음료와 이외의 카테고리 같이 주문 시 false를 반환한다")
    @Test
    void isBeverageOrOtherMenu() {
        menus.add(Menu.RED_WINE);
        menus.add(Menu.TAPAS);
        menus.add(Menu.COKE_ZERO);

        boolean result = MenuCategory.isOnlyBeverage(menus);

        assertThat(result).isFalse();
    }

    @DisplayName("음료 외 다른 카테고리만 주문해도 false를 반환한다")
    @Test
    void isNotBeverage() {
        menus.add(Menu.MUSHROOM_CREATE_SOUP);
        menus.add(Menu.SEA_FOOD_PASTA);
        menus.add(Menu.ICE_CREAM);

        boolean result = MenuCategory.isOnlyBeverage(menus);

        assertThat(result).isFalse();
    }
}