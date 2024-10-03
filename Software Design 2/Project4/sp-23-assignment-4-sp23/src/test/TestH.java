package test;

import assignment4.annotations.Order;
import assignment4.annotations.Ordered;
import assignment4.annotations.Test;
import assignment4.assertions.Assert;

@Ordered
public class TestH {
	@Test
    @Order(2)
    public void testA() {
        Assert.assertEquals(1, 1);
    }
    @Test
    @Order(2)
    public void testC() {
        Assert.assertEquals(3, 1 + 2);
    }
    @Test
    @Order(1)
    public void atest() {
        Assert.assertEquals(3, 3);
    }
    @Test
 //   @Order(999999)
    public void b0() {
    }
    @Test
    @Order(3)
    public void foo() {
    }
    @Test
    @Order(3)
    public void foo1() {
    }
}
