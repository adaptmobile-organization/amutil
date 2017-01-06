package dk.adaptmobile.amutil;

import android.text.format.DateUtils;

import org.junit.Test;


import java.util.Locale;

import dk.adaptmobile.amutil.date.AMDateUtil;

import static junit.framework.Assert.*;

/**
 * Created by bjarkeseverinsen on 05/01/2017.
 */

public class AMDateUtilTest {

    @Test
    public void testDanishLocaleNotEmpty() {
        assertNotNull(AMDateUtil.getDanishLocale());
    }

    @Test
    public void testDanishLocaleIsDanish() {
        Locale locale = AMDateUtil.getDanishLocale();
        assertEquals(locale.getLanguage(), "da");
        assertEquals(locale.getCountry(), "DK");
        assertEquals(locale, new Locale("da", "DK"));
    }

    @Test
    public void testDefaultLocaleNotNull() {
        assertNotNull(AMDateUtil.getDefaultLocale());
    }

    @Test
    public void testDefaultDateBuilder() {
        //Test January 1 1970
        String januaryFirst1970 = new AMDateUtil.AMDateBuilder(0l).build();
        assertEquals("01-01/1970 01:00", januaryFirst1970);

        //Test January 5 2017
        String januaryFifth2017 = new AMDateUtil.AMDateBuilder(1483714832L * 1000L).setLocale(AMDateUtil.getDanishLocale()).build();
        assertEquals("06-01/2017 04:00", januaryFifth2017);
    }


    @Test
    public void testDanishDateBuilder() {
        String danishDateFormatExample = "EEEE dd-MM-yyyy HH:mm";

        //Test January 1 1970
        String januaryFirst1970 =
                new AMDateUtil.AMDateBuilder(0l)
                        .setLocale(AMDateUtil.getDanishLocale())
                        .setDateFormat(danishDateFormatExample)
                        .build();
        assertEquals("torsdag 01-01-1970 01:00", januaryFirst1970);

        //Test January 5 2017
        String januaryFifth2017 =
                new AMDateUtil.AMDateBuilder(1483714832L * 1000L)
                        .setLocale(AMDateUtil.getDanishLocale())
                        .setDateFormat(danishDateFormatExample)
                        .build();
        assertEquals("fredag 06-01-2017 16:00", januaryFifth2017);
    }


}
