package test;

import assignment4.annotations.Order;
import assignment4.annotations.Ordered;
import assignment4.annotations.Test;
import assignment4.assertions.Assert;
@Ordered
public class TestB {
    @Test
    @Order(0)
    public void test3() {
        Assert.assertTrue(false);
    }

    public void test4(){ Assert.assertEquals(1, 3-2);}
    @Test
    @Order(0)
    public void test2(){Assert.assertEquals(1, 2 +1);
    }
    @Test
    @Order(0)
    public void test1(){Assert.assertEquals(2, 1-1);}
}
