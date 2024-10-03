package test;

import assignment4.annotations.Order;
import assignment4.annotations.Ordered;
import assignment4.annotations.Test;
import assignment4.assertions.Assert;

@Ordered
public class TestE {
    @Test
    public void testA() {
        Assert.assertEquals(1, 1);
    }
    @Test
    public void testC() {
        Assert.assertEquals(3, 1 + 2);
    }
    @Test
    public void atest() {
        Assert.assertEquals(3, 3);
    }
    @Test
    public void b0() {
    }
    @Test
    public void foo() {
    }
    @Test
    @Order(-1)
    public void foo1() {
    }
}
