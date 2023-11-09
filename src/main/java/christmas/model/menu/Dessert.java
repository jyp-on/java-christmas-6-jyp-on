package christmas.model.menu;

public enum Dessert implements Menu {
    초코케이크(15000),
    아이스크림(5000);

    private int price;

    Dessert(int _price) {
        this.price = _price;
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
