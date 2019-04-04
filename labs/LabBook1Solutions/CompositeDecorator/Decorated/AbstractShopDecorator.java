public abstract class AbstractShopDecorator implements ShopComponent {
    protected ShopComponent component;
    public AbstractShopDecorator(ShopComponent component) {
        this.component = component;
    }
    public String getName() {
        return this.component.getName();
    }
    public Double compPrice() {
        return this.component.compPrice();
    }
    public String toString() {
        return this.component.toString();
    }
}