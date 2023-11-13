package christmas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class WeekdaysDiscountTest {
    private LocalDate now;
    private WeekdaysDiscount weekdaysDiscount;

    @BeforeEach
    void setUp() {
        now = LocalDate.of(2023, 12, 30);   // 토요일
        weekdaysDiscount = new WeekdaysDiscount();
    }

    @DisplayName("평일(일~목)이 아니면 할인을 못받는다. 0원을 반환")
    @Test
    void isNotWeekdays() {
        long discount = weekdaysDiscount.calculateDiscountWeekdays(now, 5);

        assertThat(discount).isEqualTo(0L);
    }

    @DisplayName("디저트 메뉴당 할인을 받는다.")
    @ParameterizedTest
    @ValueSource(ints = {5, 2, 1, 4})
    void isWeekdays(int count) {
        now = LocalDate.of(2023, 12, 14);   // 목요일
        long discount = weekdaysDiscount.calculateDiscountWeekdays(now, count);

        assertThat(discount).isEqualTo(2023L * count);
    }
}