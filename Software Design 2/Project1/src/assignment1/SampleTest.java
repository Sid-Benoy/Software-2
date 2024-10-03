package assignment1;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;

public class SampleTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);

    @Test
    public void sampleTest() {
        int[] x = new int[]{-5, -4, -3, -2, -1, 0};
        int[] original = x.clone();
        int n = x.length;

        assertEquals(2, SortTools.find(x, n, -3));
        assertArrayEquals(original, x);
    }

    @Test
    public void sampleTest1() {
        int[] x = new int[]{-5, -4, -3, -2, -1, 0};
        int[] original = x.clone();
        int n = 1;

        assertEquals(-1, SortTools.find(x, n, -3));
        assertArrayEquals(original, x);
    }
    @Test
    public void SampleTest3(){
        int[] x = {1, 3, 3 , 7};
        int[] y = {1, 3, 3, 4 };
        int n = 3;
        x = SortTools.copyAndInsert(x, n, 4);
        assertArrayEquals(y, x);
    }
    @Test
    public void SampleTest4(){
        int[] x ={1,3,5,7};
        int[] y = {1,3, 4, 5};
        int v = 4;
        int n = 3;
        int a = SortTools.insertInPlace(x,n,v);
        assertArrayEquals(y,x);
        assertEquals(3, a);
    }
    @Test
    public void SampleTest5(){
        int[] x = {1,3,5,6,2,7};
        int[] y = {1,2,3,5,6,7};
        int n = 6;

        SortTools.insertSort(x, n);
        assertArrayEquals(y,x);


    }
    @Test
    public void SampletTest6(){
        int[] x= {12,25,1,2,65,1,325,1,25,1,2,13};
        int n = 5;
        boolean yo = SortTools.isSorted(x,n);
        assertEquals(false, yo);
    }
}
