package christmas.domain;

import christmas.constants.Menu;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class ChristmasEvent {
    private final InputView inputView;
    private final OutputView outputView;


    public ChristmasEvent() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void execute() {
        int reservationDay = inputView.readDate();
        Map<Menu, Integer> order = inputView.readOrder();

        Restaurant restaurant = new Restaurant(order);

        outputView.showOrderMenu(restaurant, reservationDay);
        outputView.showTotalAmountBeforeDiscount(restaurant);

        boolean isBenefits = checkedBenefits(restaurant, reservationDay);
        if (!isBenefits) {
            outputView.showNotBenefits(restaurant.calculateTotalAmountBeforeDiscount());
        }
    }

    private boolean checkedBenefits(Restaurant restaurant, int reservationDay) {
        if (restaurant.isNotBenefits()) {
            return false;
        }

        restaurant.checkedBenefits(reservationDay);

        return true;
    }
}
