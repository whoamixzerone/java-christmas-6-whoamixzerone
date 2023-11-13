package christmas.domain;

import christmas.constants.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class WeekendsDiscountTest {
    private int reservationDay;
    private Map<Menu, Integer> orders;
    private Restaurant restaurant;
    private WeekendsDiscount weekendsDiscount;

    @BeforeEach
    void setUp() {
        reservationDay = 14;
        orders = new EnumMap<>(Menu.class);
        orders.put(Menu.T_BONE_STEAK, 1);
        orders.put(Menu.MUSHROOM_CREATE_SOUP, 2);
        orders.put(Menu.SEA_FOOD_PASTA, 3);

        restaurant = new Restaurant(orders, reservationDay);

        weekendsDiscount = new WeekendsDiscount();
    }

    @DisplayName("주말(금,토)이 아니면 할인을 못받는다. 0원을 반환")
    @Test
    void isNotWeekends() {
        long discount = weekendsDiscount.calculateDiscountWeekends(restaurant);

        assertThat(discount).isEqualTo(0L);
    }

    @DisplayName("메인 메뉴당 할인을 받는다.")
    @Test
    void isWeekdays() {
        reservationDay = 2;
        restaurant = new Restaurant(orders, reservationDay);

        long discount = weekendsDiscount.calculateDiscountWeekends(restaurant);

        assertThat(discount).isEqualTo(8092L);
    }
}