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

        Restaurant restaurant = new Restaurant(order, reservationDay);

        outputView.showOrderMenu(restaurant);
        outputView.showTotalAmountBeforeDiscount(restaurant);

        boolean isBenefits = checkedBenefits(restaurant);
        if (!isBenefits) {
            outputView.showNotBenefits(restaurant.calculateTotalAmountBeforeDiscount());
        }
    }

    private boolean checkedBenefits(Restaurant restaurant) {
        if (restaurant.isNotBenefits()) {
            return false;
        }

        restaurant.checkedBenefits();

        return true;
    }
}
