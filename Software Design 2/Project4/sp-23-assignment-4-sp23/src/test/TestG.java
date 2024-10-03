package test;

import assignment4.annotations.Alphabetical;
import assignment4.annotations.Test;
import assignment4.assertions.Assert;

	@Alphabetical
	public class TestG {
	    @Test
	    public void testA() {
	        Assert.assertFalse(true);
	    }
	    @Test
	    public void testC() {
	    	double x = 3.0;
	    	int y= 3;
	        Assert.assertEquals(y,x);
	    }
	    public void atest() {
	        Assert.assertEquals(2, 2);
	    }
	}

	
