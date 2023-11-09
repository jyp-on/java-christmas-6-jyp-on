package christmas.model;

import christmas.model.menu.Menu;
import java.util.List;
import java.util.Map;

public class Order {
    List<Map<Menu, Integer>> orderList;

    public Order(List<Map<Menu, Integer>> _orderList) {
        orderList = _orderList;
    }
}
