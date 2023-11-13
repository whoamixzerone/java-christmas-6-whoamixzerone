package christmas.view;

import christmas.domain.Restaurant;

public class OutputView {
    private static final long DEFAULT_EVENT_AMOUNT = 10_000L;

    public void showOrderMenu(Restaurant restaurant, int reservationDay) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n%n", reservationDay);
        System.out.println("<주문 메뉴>");
        System.out.println(restaurant);
    }

    public void showTotalAmountBeforeDiscount(Restaurant restaurant) {
        System.out.println("<할인 전 총주문 금액>");

        String formatter = "%,d원";
        System.out.println(String.format(formatter, restaurant.calculateTotalAmountBeforeDiscount()));

        System.out.printf("총주문 금액 %s 이상부터 이벤트가 적용됩니다%n", String.format(formatter, DEFAULT_EVENT_AMOUNT));
    }
}
