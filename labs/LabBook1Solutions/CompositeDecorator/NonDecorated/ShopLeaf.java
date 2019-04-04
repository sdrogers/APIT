public class ShopLeaf implements ShopComponent {
    private String name;
    private Double price;
    public ShopLeaf(String name,Double price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public Double compPrice() {
        return price;
    }
    public String toString() {
        return this.name + String.format(" (£%5.2f)",compPrice());
    }
}