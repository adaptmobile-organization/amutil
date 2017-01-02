package dk.adaptmobile.amutil;

import org.junit.Test;


import dk.adaptmobile.amutil.date.AMDateUtil;

import static junit.framework.Assert.*;

/**
 * Created by Alex on 30/12/16.
 */

public class AMDateUtilTest {

    @Test
    public void testLocaleNotEmpty() {
        assertNull(AMDateUtil.getDanishLocale());
    }


}
