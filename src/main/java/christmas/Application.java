package christmas;

import christmas.controller.RestaurantController;

public class Application {
    public static void main(String[] args) {
        RestaurantController controller = new RestaurantController();
        controller.start();
    }
}
