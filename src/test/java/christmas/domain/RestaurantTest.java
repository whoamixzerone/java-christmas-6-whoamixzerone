package christmas.domain;

import christmas.constants.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RestaurantTest {
    private int reservationDay;
    private Map<Menu, Integer> orders;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        reservationDay = 1;
        orders = new EnumMap<>(Menu.class);
        orders.put(Menu.MUSHROOM_CREATE_SOUP, 1);
        orders.put(Menu.COKE_ZERO, 1);

        restaurant = new Restaurant(orders, reservationDay);
    }

    @DisplayName("할인 전 총주문 금액을 구한다.")
    @Test
    void calculateTotalAmountBeforeDisCount() {
        long result = restaurant.calculateTotalAmountBeforeDiscount();

        assertThat(result).isEqualTo(9_000L);
    }
}