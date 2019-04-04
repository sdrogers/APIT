public class ShopTest {
    public static void main(String[] args) {
        ShopLeaf apple = new ShopLeaf("Apple",0.35);
        ShopComposite multiApple = new ShopComposite("Apple pack");
        for(int i=0;i<6;i++) {
            multiApple.add(apple);
        }        
        ShopComposite basket = new ShopComposite("Basket");
        basket.add(multiApple);
        ShopLeaf bread = new ShopLeaf("Bread",0.8);
        basket.add(bread);

        
        System.out.println(basket);
    }
}