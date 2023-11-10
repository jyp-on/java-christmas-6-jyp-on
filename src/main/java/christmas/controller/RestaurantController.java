package christmas.controller;

import christmas.model.Order;
import christmas.view.InputView;
import christmas.view.OutputView;

public class RestaurantController {

    public void start() {
        InputView.start();
        int date = readDate();
        Order order = readOrder();
        printResult(date, order);
    }

    private int readDate() {
        try {
            return InputView.readDate();
        } catch (IllegalArgumentException e) {
            return readDate();
        }
    }

    private Order readOrder() {
        try {
            return InputView.readOrder();
        } catch (IllegalArgumentException e) {
            return readOrder();
        }
    }

    private void printResult(int date, Order order) {
        OutputView outputView = new OutputView(date, order);

        outputView.printMenu();
        outputView.totalPriceBeforeDiscount();
        outputView.receiveMenu();
        outputView.printBenefits();
        outputView.printTotalBenefits();
        outputView.afterDiscountPrice();
        outputView.printEventBadge();
    }
}
