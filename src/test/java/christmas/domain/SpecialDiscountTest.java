package christmas.domain;

import christmas.constants.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class SpecialDiscountTest {
    private int reservationDay;
    private Map<Menu, Integer> orders;
    private Restaurant restaurant;
    private Discount specialDiscount;

    @BeforeEach
    void setUp() {
        reservationDay = 14;
        orders = new EnumMap<>(Menu.class);
        orders.put(Menu.CHOCOLATE_CAKE, 1);
        orders.put(Menu.ICE_CREAM, 3);

        restaurant = new Restaurant(orders, reservationDay);    // 14일 목요일

        specialDiscount = new SpecialDiscount(restaurant);
    }

    @DisplayName("일요일이 아니고 크리스마스가 아니면 특별 할인은 못받는다. 0원을 반환")
    @Test
    void isNotSunDayAndChristmas() {
        long discount = specialDiscount.calculateDiscount(restaurant);

        assertThat(discount).isEqualTo(0L);
    }

    @DisplayName("일요일이면 특별 할인을 받는다. 1000원을 반환")
    @Test
    void isSunDay() {
        reservationDay = 3;
        restaurant = new Restaurant(orders, reservationDay);    // 3일 일요일

        long discount = specialDiscount.calculateDiscount(restaurant);

        assertThat(discount).isEqualTo(1_000L);
    }

    @DisplayName("크리스마스 25일이면 특별 할인을 받는다. 1000원을 반환")
    @Test
    void isChristmas() {
        reservationDay = 25;
        restaurant = new Restaurant(orders, reservationDay);    // 25일 월요일

        long discount = specialDiscount.calculateDiscount(restaurant);

        assertThat(discount).isEqualTo(1_000L);
    }
}