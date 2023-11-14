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

    @DisplayName("할인 전 총주문 금액을 구한다")
    @Test
    void calculateTotalAmountBeforeDisCount() {
        long result = restaurant.calculateTotalAmountBeforeDiscount();

        assertThat(result).isEqualTo(9_000L);
    }

    @DisplayName("총혜택 금액을 구한다")
    @Test
    void calculateTotalAmountBenefit() {
        reservationDay = 25;
        orders = new EnumMap<>(Menu.class);
        orders.put(Menu.MUSHROOM_CREATE_SOUP, 1);
        orders.put(Menu.T_BONE_STEAK, 2);
        orders.put(Menu.CHRISTMAS_PASTA, 1);
        orders.put(Menu.RED_WINE, 1);

        restaurant = new Restaurant(orders, reservationDay);
        restaurant.applyBenefits();

        long result = restaurant.calculateTotalAmountBenefit();

        assertThat(result).isEqualTo(29_400L);
    }

    @DisplayName("혜택을 못받으면 총혜택 금액은 0원을 반환한다")
    @Test
    void notTotalAmountBenefit() {
        restaurant.applyBenefits();

        long result = restaurant.calculateTotalAmountBenefit();

        assertThat(result).isEqualTo(0L);
    }
}