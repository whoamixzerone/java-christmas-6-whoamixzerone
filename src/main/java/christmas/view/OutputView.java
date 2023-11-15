package christmas.view;

import christmas.domain.Discount;
import christmas.domain.GiveawayEvent;
import christmas.domain.Restaurant;

import java.util.List;

public class OutputView {
    private static final long ZERO_AMOUNT = 0L;
    private static final long DEFAULT_EVENT_AMOUNT = 10_000L;
    private static final String FORMATTER = "%,d원";

    public void showOrderMenu(Restaurant restaurant) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n%n", restaurant.getReservationDate().getDayOfMonth());
        System.out.println("<주문 메뉴>");
        System.out.println(restaurant);
    }

    public void showTotalAmountBeforeDiscount(Restaurant restaurant) {
        long totalAmount = restaurant.calculateTotalAmountBeforeDiscount();

        System.out.println("<할인 전 총주문 금액>");

        if (totalAmount < DEFAULT_EVENT_AMOUNT) {
            System.out.printf("총주문 금액 %s 이상부터 이벤트가 적용됩니다%n", String.format(FORMATTER, DEFAULT_EVENT_AMOUNT));
        }

        System.out.println(String.format(FORMATTER, totalAmount));
        System.out.println();
    }

    public void showBenefits(Restaurant restaurant) {
        List<Discount> benefits = restaurant.getBenefits();
        long totalAmountBenefit = restaurant.calculateTotalAmountBenefit();
        long payAmount = restaurant.calculateTotalAmountAfterDiscount();

        showGiveaway(benefits);
        showBenefitDetails(benefits, totalAmountBenefit);
        showTotalAmountBenefit(totalAmountBenefit);
        showTotalAMountDiscount(payAmount);
        showBadge(restaurant);
    }

    private void showBadge(Restaurant restaurant) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(restaurant.findBadgeByAmountDiscount().getTitle());
    }

    private void showTotalAMountDiscount(long payAmount) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%,d원%n", payAmount);
        System.out.println();
    }

    private void showTotalAmountBenefit(long totalAmountBenefit) {
        System.out.println("<총혜택 금액>");
        System.out.printf("%,d원%n", -totalAmountBenefit);
        System.out.println();
    }

    private void showBenefitDetails(List<Discount> benefits, long totalAmountBenefit) {
        System.out.println("<혜택 내역>");

        if (totalAmountBenefit == ZERO_AMOUNT) {
            System.out.println("없음");
            System.out.println();
            return;
        }

        for (Discount benefit : benefits) {
            if (benefit.getAmount() != ZERO_AMOUNT) {
                System.out.printf("%s%n", benefit);
            }
        }
        System.out.println();
    }

    private void showGiveaway(List<Discount> benefits) {
        StringBuilder giveaway = new StringBuilder();
        giveaway.append("<증정 메뉴>\n").append("샴페인 1개");

        benefits.stream()
                .filter(benefit -> isGiveawayAndZeroAmount(benefit))
                .forEach(benefit -> {
                    giveaway.setLength(0);
                    giveaway.append("<증정 메뉴>\n").append("없음");
                });

        System.out.println(giveaway.toString());
        System.out.println();
    }

    private boolean isGiveawayAndZeroAmount(Discount benefit) {
        return benefit instanceof GiveawayEvent && benefit.getAmount() == ZERO_AMOUNT;
    }
}
