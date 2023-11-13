package christmas.constants;

import java.util.*;

public enum Menu {
    MUSHROOM_CREATE_SOUP("양송이수프", 6_000),
    TAPAS("타파스", 5_500),
    CAESAR_SALAD("시저샐러드", 8_000),
    T_BONE_STEAK("티본스테이크", 55_000),
    BBQ_RIBS("바베큐립", 54_000),
    SEA_FOOD_PASTA("해산물파스타", 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000),
    CHOCOLATE_CAKE("초코케이크", 15_000),
    ICE_CREAM("아이스크림", 5_000),
    COKE_ZERO("제로콜라", 3_000),
    RED_WINE("레드와인", 60_000),
    CHAMPAGNE("샴페인", 25_000),
    EMPTY("없음", 0);

    private String food;
    private long amount;

    Menu(String food, long amount) {
        this.food = food;
        this.amount = amount;
    }

    public static Menu findByFood(String order) {
        return Arrays.stream(Menu.values())
                .filter(menu -> order.equals(menu.getFood()))
                .findAny()
                .orElse(EMPTY);
    }

    public String getFood() {
        return food;
    }

    public long getAmount() {
        return amount;
    }
}
