package example;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CoffeeShopTest {
    @Test
    public void testCoffeeShop() throws Exception {
        CoffeeShop starbucks = new CoffeeShop();
        starbucks.callAlba().order("Americano", 2);
        starbucks.callAlba().order("CafeLatte", 1);

        int totalMoney = starbucks.callSajang().getTotalMoney();

        assertThat(totalMoney, is(16000));
    }
}