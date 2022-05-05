package shopAndGoods;


import org.junit.Assert;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.Map;

public class ShopTest {
    @Test
    public void testConstructor() throws OperationNotSupportedException {
        Shop shop = new Shop();
        Goods goods = new Goods("cake", "123");
       shop.addGoods("Shelves3", goods);
        Assert.assertEquals(goods.getName(), "cake");
        Assert.assertEquals(goods.getGoodsCode(), "123");

    }
    @Test
    public void testUnmodifiableMap() throws OperationNotSupportedException {
        Goods goods1 = new Goods("take", "1234");
        Shop shop = new Shop();
        shop.addGoods("Shelves1", goods1);
        Assert.assertEquals(shop.getShelves().getClass().getSimpleName()
                ,"UnmodifiableMap");
    }
    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableMapTwo() throws OperationNotSupportedException {
        Goods goods1 = new Goods("take", "1234");
        Shop shop = new Shop();
        shop.getShelves().clear();;

    }
    @Test
    public void testConstructorShop() throws OperationNotSupportedException {
        Shop shop = new Shop();
        Goods goods = new Goods("cake", "123");
        shop.addGoods("Shelves1", goods );
        Assert.assertEquals(shop.getShelves().get("Shelves1").getName(), "cake");

    }
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidShelf() throws OperationNotSupportedException {
        Shop shop = new Shop();
        Goods goods = new Goods("cake", "123");
        shop.addGoods("Shelves15", goods );
       Assert.assertFalse(shop.getShelves().containsKey("Shelves15"));


    }
    @Test(expected = IllegalArgumentException.class)
    public void testShelfIsAlreadyTaken() throws OperationNotSupportedException {
        Goods goods = new Goods("cake", "123");
        Shop shop = new Shop();
        shop.addGoods("Shelves1", goods);
       Assert.assertNotNull(shop.getShelves().get("Shelves1"));
       shop.addGoods("Shelves1", goods);
       Assert.assertTrue(shop.getShelves().containsValue(goods));

    }
    @Test
    public void testAddGoodInMap() throws OperationNotSupportedException {
        Goods goods = new Goods("cake", "123");
        Shop shop = new Shop();
        shop.addGoods("Shelves1", goods);
        Assert.assertEquals(String.format("Goods: %s is placed successfully!",
                goods.getName()), "Goods: cake is placed successfully!");

    }
    @Test
    public void testAddGoodInMapGetCode() throws OperationNotSupportedException {
        Goods goods = new Goods("cake", "123");
        Shop shop = new Shop();
        shop.addGoods("Shelves1", goods);
        Assert.assertEquals(String.format("Goods: %s is placed successfully!",
                goods.getGoodsCode()), "Goods: 123 is placed successfully!");

    }
    @Test (expected = OperationNotSupportedException.class)
    public void testItemExist() throws OperationNotSupportedException {

        Goods goods = new Goods("cake", "123");
        Shop shop = new Shop();
        shop.addGoods("Shelves1", goods);
        shop.addGoods("Shelves2", goods);

    }
    @Test
    public void testRemoveGoodCorrectly() throws OperationNotSupportedException {
        Shop shop = new Shop();

        Goods goods = new Goods("cake", "123");
        shop.addGoods("Shelves1", goods );
        shop.removeGoods("Shelves1", goods );
        Assert.assertNull(shop.getShelves().get("Shelves1"));

    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodFromUNotExistedShelf() throws OperationNotSupportedException {
        Shop shop = new Shop();

        Goods goods = new Goods("cake", "123");
        shop.addGoods("Shelves1", goods );
        shop.removeGoods("Shelves15", goods );
        Assert.assertFalse(shop.getShelves().containsKey("Shelves15"));

    }
    @Test(expected = NullPointerException.class)
    public void testRemoveNotExistedGoodFromShelf() throws OperationNotSupportedException {
        Shop shop = new Shop();
        shop.removeGoods("Shelves1", null );
        Assert.assertEquals(shop.getShelves().get("Shelves1"), null);

    }
}