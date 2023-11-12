package christmas.constants;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class MenuTypeTest {
    @DisplayName("메뉴판에 없는 메뉴인지 확인한다.")
    @ParameterizedTest
    @ValueSource(strings = {"알리오올리오파스타", "하이볼", "딸기케이크"})
    void isNotMenuType(String menu) {
        boolean result = MenuType.isNotMenuType(menu);

        assertThat(result).isTrue();
    }
}