package aquarium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AquariumTests {
private Aquarium aquarium;
private List<Fish> fishList;
@Before
    public void setUp(){
     fishList = new ArrayList<>();
}

    @Test
    public void testConstructor(){
    this.aquarium = new Aquarium("Bon", 2);
    Fish fish = new Fish("Chick");
    this.aquarium.add(fish);
    Assert.assertEquals(1, this.aquarium.getCount());
}
    @Test
    public void testGetFishName(){

        Fish fish = new Fish("Chick");
        this.fishList.add(fish);
        Assert.assertEquals(this.fishList.get(0).getName(), "Chick");
    }
    @Test
    public void testGetAquariumName(){

        this.aquarium = new Aquarium("Bon", 2);
        Assert.assertEquals( this.aquarium.getName(), "Bon");
    }
    @Test(expected = NullPointerException.class)
    public void testSetAquariumNullName(){

        this.aquarium = new Aquarium(" ", 2);
    }
    @Test
    public void testGetCapacity(){

        this.aquarium = new Aquarium("Bon", 2);
        Assert.assertEquals( this.aquarium.getCapacity(), 2);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityLessThenNull(){

        this.aquarium = new Aquarium("Bon", -1);

    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddMoreFishThenCapacityIs(){
        this.aquarium = new Aquarium("Bon", 1);
        Fish fish = new Fish("Chick");
        Fish fish1 = new Fish("Pick");
        this.fishList.add(fish);
        this.fishList.add(fish1);
        this.aquarium.add(fish);
        this.aquarium.add(fish1);

    }
    @Test
    public void testRemoveFishCorrectly(){

        this.aquarium = new Aquarium("Bon", 2);
        Fish fish = new Fish("Chick");
        Assert.assertEquals( this.aquarium.getCount(), 0);
        this.aquarium.add(fish);
        this.aquarium.remove("Chick");
        Assert.assertEquals( this.aquarium.getCount(), 0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullFish(){

        this.aquarium = new Aquarium("Bon", 2);
        this.aquarium.remove("Chick");

    }
    @Test
    public void testSellFishCorrectly(){

        this.aquarium = new Aquarium("Bon", 2);
        Fish fish = new Fish("Chick");
        this.fishList.add(fish);
        this.aquarium.add(fish);
        Assert.assertEquals( this.aquarium.getCount(), 1);
        Assert.assertTrue(this.fishList.get(0).isAvailable());
        this.aquarium.sellFish(this.fishList.get(0).getName());
        Assert.assertFalse(this.fishList.get(0).isAvailable());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFishToSellIsNull(){
        this.aquarium = new Aquarium("Bon", 2);
        this.aquarium.sellFish("Chick");

    }

    @Test
    public void testReport(){
        this.aquarium = new Aquarium("Bon", 5);
        Fish fish = new Fish("Chick");
        Fish fish1 = new Fish("Pick");
        this.fishList.add(fish);
        this.fishList.add(fish1);
        this.aquarium.add(fish);
        this.aquarium.add(fish1);
        Assert.assertEquals(this.fishList.get(0).getName(), "Chick");
        Assert.assertEquals(this.fishList.get(1).getName(), "Pick");
        this.aquarium.report();
        Assert.assertEquals("Fish available at Chick: Bon", String.format("Fish available at %s: %s",
                this.fishList.get(0).getName(), this.aquarium.getName()));
        Assert.assertEquals("Fish available at Pick: Bon", String.format("Fish available at %s: %s",
                this.fishList.get(1).getName(), this.aquarium.getName()));

    }
}

