package christmas.constants;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class MenuTest {
    @DisplayName("메뉴판에 없는 메뉴를 주문 시 Menu.EMPTY를 반환한다")
    @ParameterizedTest
    @ValueSource(strings = {"알리오올리오파스타", "딸기케이크", "참이슬"})
    void notFindByMenu(String order) {
        Menu menu = Menu.findByMenu(order);

        assertThat(menu).isEqualTo(Menu.EMPTY);
    }

    @DisplayName("메뉴판에 있는 메뉴를 주문 시 해당 Menu를 반환한다")
    @Test
    void findByMenu() {
        Menu menu1 = Menu.findByMenu("해산물파스타");
        Menu menu2 = Menu.findByMenu("양송이수프");

        assertThat(menu1).isEqualTo(Menu.SEA_FOOD_PASTA);
        assertThat(menu2).isEqualTo(Menu.MUSHROOM_CREATE_SOUP);
    }
}