package christmas.constants;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BadgeTest {
    @DisplayName("총혜택 금액에 해당하는 뱃지를 반환한다")
    @Test
    void findByAmount() {
        Badge result1 = Badge.findByAmount(5_000L);
        Badge result2 = Badge.findByAmount(6_500L);
        Badge result3 = Badge.findByAmount(12_000L);
        Badge result4 = Badge.findByAmount(35_000L);

        assertThat(result1).isEqualTo(Badge.STAR);
        assertThat(result2).isEqualTo(Badge.STAR);
        assertThat(result3).isEqualTo(Badge.TREE);
        assertThat(result4).isEqualTo(Badge.SANTA);
    }

    @DisplayName("총혜택이 5천원 미만이라면 EMPTY를 반환한다")
    @Test
    void findByAmount_return_empty() {
        Badge result1 = Badge.findByAmount(3_500L);

        assertThat(result1).isEqualTo(Badge.EMPTY);
    }
}