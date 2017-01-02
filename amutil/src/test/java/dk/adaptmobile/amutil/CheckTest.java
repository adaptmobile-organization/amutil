package dk.adaptmobile.amutil;

import org.junit.Test;

import static junit.framework.TestCase.*;


/**
 * Created by Alex on 30/12/16.
 */

public class CheckTest {

    public CheckTest() {
        super();
    }

    @Test
    public void multiplicationOfZeroIntegersShouldReturnZero() {
        // assert statements
        assertEquals("10 x 0 must be 0", 0, 0);
        assertEquals("0 x 10 must be 0", 0, 5);
        assertEquals("0 x 0 must be 0", 0, 0);
    }

}
