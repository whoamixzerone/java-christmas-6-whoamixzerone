package christmas.constants;

import java.util.Arrays;

public enum Badge {
    STAR("별", 5_000L),
    TREE("트리", 10_000L),
    SANTA("산타", 20_000L),
    EMPTY("없음", 0L);

    private String title;
    private long amount;

    Badge(String title, long amount) {
        this.title = title;
        this.amount = amount;
    }

    public static Badge findByAmount(long amount) {
        return Arrays.stream(Badge.values())
                .sorted((badge1, badge2) -> Long.compare(badge2.getAmount(), badge1.getAmount()))
                .filter(badge -> amount >= badge.getAmount())
                .findAny()
                .orElse(EMPTY);
    }

    public String getTitle() {
        return title;
    }

    public long getAmount() {
        return amount;
    }
}
