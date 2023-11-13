package christmas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class WeekendsDiscountTest {
    private LocalDate now;
    private WeekendsDiscount weekendsDiscount;

    @BeforeEach
    void setUp() {
        now = LocalDate.of(2023, 12, 14);   // 목요일
        weekendsDiscount = new WeekendsDiscount();
    }

    @DisplayName("주말(금,토)이 아니면 할인을 못받는다. 0원을 반환")
    @Test
    void isNotWeekends() {
        long discount = weekendsDiscount.calculateDiscountWeekends(now, 5);

        assertThat(discount).isEqualTo(0L);
    }
}