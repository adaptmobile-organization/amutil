package dk.adaptmobile.amutil;

import org.junit.Test;

import dk.adaptmobile.amutil.misc.AMTextUtils;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Thomas on 06/04/2017.
 */

public class AMTextUtilTest {

    @Test
    public void testIfCharSequenceIsEmpty() {
        assertTrue(AMTextUtils.isEmpty(""));
        assertTrue(AMTextUtils.isEmpty(null));
        assertFalse(AMTextUtils.isEmpty("dfgadf"));
    }

    @Test
    public void testIfCharSequenceIsDigitsOnly() {
        assertTrue(AMTextUtils.isDigitsOnly("1213434"));
        assertFalse(AMTextUtils.isDigitsOnly("f23fdh4325"));
    }

    @Test
    public void testIfStringIsValidDouble() {
        assertTrue(AMTextUtils.isValidDouble("1212.3425"));
        assertTrue(AMTextUtils.isValidDouble("3253253"));
        assertFalse(AMTextUtils.isValidDouble("432532dfhfdh"));
    }
}
