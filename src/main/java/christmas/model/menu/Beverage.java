package christmas.model.menu;

public enum Beverage implements Menu {
    제로콜라(3000),
    레드와인(60000),
    샴페인(25000);

    private int price;

    Beverage(int _price) {
        this.price = _price;
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
