package christmas.view;

import christmas.domain.Restaurant;

public class OutputView {
    private static final long DEFAULT_EVENT_AMOUNT = 10_000L;
    private static final String FORMATTER = "%,d원";

    public void showOrderMenu(Restaurant restaurant) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n%n", restaurant.getReservationDate().getDayOfMonth());
        System.out.println("<주문 메뉴>");
        System.out.println(restaurant);
    }

    public void showTotalAmountBeforeDiscount(Restaurant restaurant) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(String.format(FORMATTER, restaurant.calculateTotalAmountBeforeDiscount()));
        System.out.printf("총주문 금액 %s 이상부터 이벤트가 적용됩니다%n%n", String.format(FORMATTER, DEFAULT_EVENT_AMOUNT));
    }

    public void showNotBenefits(long amount) {
        System.out.println("<증정 메뉴>");
        System.out.println("없음");
        System.out.println();

        System.out.println("<혜택 내역>");
        System.out.println("없음");
        System.out.println();

        System.out.println("<총혜택 내역>");
        System.out.println("없음");
        System.out.println();

        System.out.println("<총혜택 금액>");
        System.out.println("0원");
        System.out.println();

        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(String.format(FORMATTER, amount));
        System.out.println();

        System.out.println("<12월 이벤트 배지>");
        System.out.println("없음");
    }
}
