package christmas.domain;

import christmas.constants.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GiveawayEventTest {
    private int reservationDay;
    private Map<Menu, Integer> orders;
    private Restaurant restaurant;
    private Discount giveawayEvent;

    @BeforeEach
    void setUp() {
        reservationDay = 14;
        orders = new EnumMap<>(Menu.class);
        orders.put(Menu.SEA_FOOD_PASTA, 1);
        orders.put(Menu.T_BONE_STEAK, 1);
        orders.put(Menu.MUSHROOM_CREATE_SOUP, 2);
        orders.put(Menu.ICE_CREAM, 2);
        orders.put(Menu.RED_WINE, 1);

        restaurant = new Restaurant(orders, reservationDay);    // 14일 목요일

        giveawayEvent = new GiveawayEvent(restaurant);
    }

    @DisplayName("총주문 금액이 120,000원 미만이면 증정 이벤트는 못받는다. 0원 반환")
    @Test
    void isNotGiveaway() {
        orders = new EnumMap<>(Menu.class);
        orders.put(Menu.CHOCOLATE_CAKE, 1);
        orders.put(Menu.ICE_CREAM, 3);
        restaurant = new Restaurant(orders, reservationDay);    // 14일 목요일

        long discount = giveawayEvent.calculateDiscount(restaurant);

        assertThat(discount).isEqualTo(0L);
    }

    @DisplayName("총주문 금액이 120,000원 이상이면 증정 이벤트를 받는다. 25,000원 반환")
    @Test
    void isGiveaway() {
        long discount = giveawayEvent.calculateDiscount(restaurant);

        assertThat(discount).isEqualTo(25_000L);
    }
}