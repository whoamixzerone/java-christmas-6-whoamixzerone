package christmas.domain;

import christmas.constants.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChristmasDiscountTest {
    private int reservationDay;
    private Map<Menu, Integer> orders;
    private Restaurant restaurant;
    private ChristmasDiscount christmasDiscount;

    @BeforeEach
    void setUp() {
        reservationDay = 30;
        orders = new EnumMap<>(Menu.class);
        orders.put(Menu.MUSHROOM_CREATE_SOUP, 1);
        orders.put(Menu.COKE_ZERO, 1);

        restaurant = new Restaurant(orders, reservationDay);

        christmasDiscount = new ChristmasDiscount();
    }

    @DisplayName("현재 날짜가 크리스마스가 지났으면 0원을 반환한다")
    @Test
    void isPassedChristmas() {
        long discount = christmasDiscount.calculateDiscountChristmas(restaurant);

        assertThat(discount).isEqualTo(0L);
    }

    @DisplayName("크리스마스 디데이 할인을 받는다")
    @ParameterizedTest
    @CsvSource({"1,1000", "2,1100", "25,3400"})
    void isNotPassedChristmas(int day, long expected) {
        restaurant = new Restaurant(orders, day);

        long discount = christmasDiscount.calculateDiscountChristmas(restaurant);

        assertThat(discount).isEqualTo(expected);
    }
}