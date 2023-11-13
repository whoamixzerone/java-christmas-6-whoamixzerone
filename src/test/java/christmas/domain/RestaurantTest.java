package christmas.domain;

import christmas.constants.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RestaurantTest {
    private Map<Menu, Integer> orders;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        orders = new EnumMap<>(Menu.class);
        orders.put(Menu.MUSHROOM_CREATE_SOUP, 1);
        orders.put(Menu.COKE_ZERO, 1);

        restaurant = new Restaurant(orders);
    }

    @DisplayName("할인 전 총주문 금액을 구한다.")
    @Test
    void calculateTotalAmountBeforeDisCount() {
        long result = restaurant.calculateTotalAmountBeforeDiscount();

        assertThat(result).isEqualTo(9_000L);
    }

    @DisplayName("할인 혜택이 적용되지 않으면 true를 반환한다")
    @Test
    void isNotBenefits() {
        boolean isNotBenefits = restaurant.isNotBenefits();

        assertThat(isNotBenefits).isTrue();
    }

    @DisplayName("할인 혜택이 적용되면 false를 반환한다")
    @Test
    void isBenefits() {
        orders.put(Menu.MUSHROOM_CREATE_SOUP, 1);
        orders.put(Menu.COKE_ZERO, 1);
        orders.put(Menu.BBQ_RIBS, 1);

        restaurant = new Restaurant(orders);

        boolean isBenefits = restaurant.isNotBenefits();

        assertThat(isBenefits).isFalse();
    }
}