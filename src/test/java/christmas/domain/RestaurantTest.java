package christmas.domain;

import christmas.constants.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RestaurantTest {
    @DisplayName("할인 전 총주문 금액을 구한다.")
    @Test
    void calculateTotalAmountBeforeDisCount() {
        Map<Menu, Integer> orders = new EnumMap<>(Menu.class);
        orders.put(Menu.MUSHROOM_CREATE_SOUP, 1);
        orders.put(Menu.T_BONE_STEAK, 1);
        orders.put(Menu.SEA_FOOD_PASTA, 1);
        orders.put(Menu.ICE_CREAM, 1);
        Restaurant restaurant = new Restaurant(orders);

        long result = restaurant.calculateTotalAmountBeforeDiscount();

        assertThat(result).isEqualTo(101_000L);
    }
}