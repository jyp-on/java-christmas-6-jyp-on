package christmas.model;

import christmas.model.menu.Menu;
import java.util.List;
import java.util.Map;

public class Order {
    List<Map<Menu, Integer>> orderList;

    public Order(List<Map<Menu, Integer>> _orderList) {
        orderList = _orderList;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (Map<Menu, Integer> orderMap : orderList) {
            for (Map.Entry<Menu, Integer> entry : orderMap.entrySet()) {
                totalPrice += (entry.getKey().getPrice() * entry.getValue());
            }
        }
        return totalPrice;
    }

    public List<Map<Menu, Integer>> getOrder() {
        return orderList;
    }
}
