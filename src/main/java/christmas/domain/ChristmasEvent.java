package christmas.domain;

import christmas.constants.Menu;
import christmas.view.InputView;

import java.util.Map;

public class ChristmasEvent {
    private final InputView inputView;

    public ChristmasEvent() {
        inputView = new InputView();
    }

    public void execute() {
        int reservationDay = inputView.readDate();
        Map<Menu, Integer> menus = inputView.readOrder();
    }
}
