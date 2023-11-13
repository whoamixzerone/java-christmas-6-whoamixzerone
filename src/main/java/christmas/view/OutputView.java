package christmas.view;

import christmas.domain.Restaurant;

public class OutputView {
    private static final long DEFAULT_EVENT_AMOUNT = 10_000L;

    public void showOrderMenu(Restaurant restaurant, int reservationDay) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n%n", reservationDay);
        System.out.println("<주문 메뉴>");
        System.out.println(restaurant);
    }
}
