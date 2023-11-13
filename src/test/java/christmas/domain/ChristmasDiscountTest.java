package christmas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class ChristmasDiscountTest {
    private LocalDate now;
    private ChristmasDiscount christmasDiscount;

    @BeforeEach
    void setUp() {
        now = LocalDate.of(2023, 12, 30);
        christmasDiscount = new ChristmasDiscount();
    }

    @DisplayName("현재 날짜가 크리스마스가 지났으면 0원을 반환한다")
    @Test
    void isPassedChristmas() {
        long discount = christmasDiscount.calculateDiscountChristmas(now);

        assertThat(discount).isEqualTo(0L);
    }

    @DisplayName("크리스마스 디데이 할인을 받는다")
    @ParameterizedTest
    @CsvSource({"2023,12,1,1000", "2023,12,2,1100", "2023,12,25,3400"})
    void isNotPassedChristmas(int year, int month, int day, int expected) {
        now = LocalDate.of(year, month, day);

        long discount = christmasDiscount.calculateDiscountChristmas(now);

        assertThat(discount).isEqualTo(expected);
    }
}