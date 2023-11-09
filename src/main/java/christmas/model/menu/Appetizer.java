package christmas.model.menu;

public enum Appetizer implements Menu {
    양송이수프(6000),
    타파스(5500),
    시저샐러드(8000);

    private int price;

    Appetizer(int _price) {
        this.price = _price;
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
