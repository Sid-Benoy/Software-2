package test;

import assignment4.annotations.Test;
import assignment4.assertions.Assert;

import test.TestA;

public class TestF{
    @Test
    public void longTest() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
        }
    }
}
