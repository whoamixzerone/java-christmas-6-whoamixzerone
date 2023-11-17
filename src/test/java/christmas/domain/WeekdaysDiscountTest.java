package christmas.domain;

import christmas.constants.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class WeekdaysDiscountTest {
    private int reservationDay;
    private Map<Menu, Integer> orders;
    private Restaurant restaurant;
    private Discount weekdaysDiscount;

    @BeforeEach
    void setUp() {
        reservationDay = 30;
        orders = new EnumMap<>(Menu.class);
        orders.put(Menu.CHOCOLATE_CAKE, 1);
        orders.put(Menu.ICE_CREAM, 3);

        restaurant = new Restaurant(orders, reservationDay);

        weekdaysDiscount = new WeekdaysDiscount(restaurant);
    }

    @DisplayName("평일(일~목)이 아니면 할인을 못받는다. 0원을 반환")
    @Test
    void isNotWeekdays() {
        long discount = weekdaysDiscount.calculateDiscount(restaurant);

        assertThat(discount).isEqualTo(0L);
    }

    @DisplayName("디저트 메뉴당 할인을 받는다.")
    @Test
    void isWeekdays() {
        reservationDay = 14;
        restaurant = new Restaurant(orders, reservationDay);

        long discount = weekdaysDiscount.calculateDiscount(restaurant);

        assertThat(discount).isEqualTo(8092L);
    }
}