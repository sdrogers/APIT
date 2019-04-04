import java.util.ArrayList;
public class ShopComposite implements ShopComponent {
    private String name;
    private ArrayList<ShopComponent> children;
    public ShopComposite(String name) {
        this.name = name;
        children = new ArrayList<ShopComponent>();
    }
    public String getName() {
        return name;
    }
    public Double compPrice() {
        Double d = 0.0;
        for(ShopComponent child: children) {
            d += child.compPrice();
        }
        return d;
    }
    public void add(ShopComponent child) {
        children.add(child);
    }
    public String toString() {
        return this.name + String.format(" (£%5.2f)",compPrice());
    }
}