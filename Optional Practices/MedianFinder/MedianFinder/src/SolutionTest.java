import static org.junit.Assert.*;

public class SolutionTest {

    @org.junit.Test
    public void update() {
        MedianFinder soln = new MedianFinder();
        soln.insert(4);
        soln.insert(2);
        soln.insert(3);
        assertEquals(3, soln.getMedian());
        soln.insert(8);
        soln.insert(2);
        soln.insert(7);
        soln.insert(1);
        assertEquals(4, soln.getMedian());
        assertEquals(2, soln.getMedian());
    }
}