package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constants.Menu;
import christmas.util.InputNumber;
import christmas.util.InputString;
import christmas.validate.ValidateDay;
import christmas.validate.ValidateOrder;

import java.util.Map;

public class InputView {
    public int readDate() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");

        do {
            System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
            String input = Console.readLine();

            try {
                ValidateDay.blank(input);
                ValidateDay.numeric(input);
                ValidateDay.range(input);

                return InputNumber.toInt(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public Map<Menu, Integer> readOrder() {
        do {
            System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
            String input = Console.readLine();

            try {
                String[] orderGroup = InputString.orderGroupSplit(input, ",");
                ValidateOrder.form(orderGroup);

                Map<Menu, Integer> orders = InputString.orderSplit(orderGroup);
                ValidateOrder.eachMenuCount(orders);
                ValidateOrder.menuTotalCount(orders);
                ValidateOrder.menuDuplicate(orders, orderGroup);
                ValidateOrder.onlyBeverage(orders);

                return orders;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
}
