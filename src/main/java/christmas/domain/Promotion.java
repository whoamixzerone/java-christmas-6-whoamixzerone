package christmas.domain;

import christmas.view.InputView;

public class Promotion {
    private final InputView inputView;

    public Promotion() {
        inputView = new InputView();
    }

    public void execute() {
        int reservationDay = inputView.readDate();
        String[] menus = inputView.readOrder();
    }
}
