package christmas.view;

public class InfoMessage {
    private static final String UNDER_PRICE = "[INFO] 10000원 이하의 주문 금액은 이벤트를 받지 못합니다.";

    public static void getUnderPrice() {
        System.out.println(UNDER_PRICE);
    }
}
