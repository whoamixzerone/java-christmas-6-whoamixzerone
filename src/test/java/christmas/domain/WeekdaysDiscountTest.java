package christmas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        long discount = weekdaysDiscount.calculateDiscountWeekdays(now);

        assertThat(discount).isEqualTo(0L);
    }
}