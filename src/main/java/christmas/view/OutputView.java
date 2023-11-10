package christmas.view;

import christmas.model.Order;
import christmas.model.discount.Discount;
import christmas.model.menu.Menu;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class OutputView {

    public final DecimalFormat formatter = new DecimalFormat("###,###원");
    private final int date;
    private final Order order;

    public OutputView(int _date, Order _order) {
        this.date = _date;
        this.order = _order;
    }

    public void printMenu() {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
        System.out.println("<주문 메뉴>");
        List<Map<Menu, Integer>> maps = order.getOrder();

        for (Map<Menu, Integer> map : maps) {
            for (Map.Entry<Menu, Integer> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue() + "개");
            }
        }
        System.out.println();
    }

    public void totalPriceBeforeDiscount() {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(formatter.format(order.getTotalPrice()));
        System.out.println();
    }

    public void receiveMenu() {
        System.out.println("<증정 메뉴>");
        if (order.getTotalPrice() >= 120000 && Discount.EVENT_FLAG) {
            Discount.RECEIVE_FLAG = true;
            System.out.println("샴페인 1개");
        } else if (order.getTotalPrice() < 120000) {
            System.out.println("없음");
        }
        System.out.println();
    }

    public void printBenefits() {
        System.out.println("<혜택 내역>");
        Discount discount = new Discount(date, order);
        int dDay = discount.dDay();
        int weekDay = discount.weekDay();
        int weenEnd = discount.weekEnd();
        int special = discount.special();
        checkBenefits(dDay, weekDay, weenEnd, special);
    }

    public void checkBenefits(int dDay, int weekDay, int weekEnd, int special) {
        if (dDay + weekDay + weekEnd + special == 0) {
            System.out.println("없음");
        } else if (dDay + weekDay + weekEnd + special > 0) {
            if (dDay > 0) {
                System.out.println("크리스마스 디데이 할인: -" + formatter.format(dDay));
            }
            if (weekDay > 0) {
                System.out.println("평일 할인: -" + formatter.format(weekDay));
            }
            if (weekEnd > 0) {
                System.out.println("주말 할인: -" + formatter.format(weekEnd));
            }
            if (special > 0) {
                System.out.println("특별 할인: -" + formatter.format(special));
            }
            if (Discount.RECEIVE_FLAG) {
                System.out.println("증정 이벤트: -" + formatter.format(25000));
            }
        }
        System.out.println();
    }

    public void printTotalBenefits() {
        System.out.println("<총혜택 금액>");
        if (order.getTotalPrice() > 120000) {
            System.out.println(formatter.format((Discount.TOTAL_DISCOUNT + 25000) * -1));
        } else if (order.getTotalPrice() <= 120000) {
            System.out.println(formatter.format(Discount.TOTAL_DISCOUNT * -1));
        }
        System.out.println();
    }

    public void afterDiscountPrice() {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(formatter.format(order.getTotalPrice() - Discount.TOTAL_DISCOUNT));
        System.out.println();
    }

    public void printEventBadge() {
        System.out.println("<12월 이벤트 배지>");
        int receivePrice = 0;
        if (Discount.RECEIVE_FLAG && Discount.EVENT_FLAG) {
            receivePrice = 25000;
        }

        if (Discount.TOTAL_DISCOUNT + receivePrice >= 20000) {
            System.out.println("산타");
        } else if (Discount.TOTAL_DISCOUNT + receivePrice >= 10000) {
            System.out.println("트리");
        } else if (Discount.TOTAL_DISCOUNT + receivePrice >= 5000) {
            System.out.println("별");
        } else if (Discount.TOTAL_DISCOUNT + receivePrice >= 0) {
            System.out.println("없음");
        }
    }
}