package dk.adaptmobile.amutil.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Methods for handling dates
 */
public class AMDateUtil {
    /**
     * Builds a date object from a timestamp
     */
    public static class AMDateBuilder {
        private final long timestamp;
        private Locale locale;
        private String dateFormat;

        /**
         * Creates a date object from a timestamp.
         * The date uses the default locale of the device and
         * formats the date as dd-MM/yyyy hh:mm
         * @param timestamp The timestamp to be converted
         */

        public AMDateBuilder(long timestamp) {
            this.timestamp = timestamp;
            this.locale = getDefaultLocale();
            this.dateFormat = "dd-MM/yyyy hh:mm";
        }

        /**
         * Sets the locale value of the returned date
         * Default is device locale
         * @param locale The locale to be set
         * @return The AMDateBuilder object
         */
        public AMDateBuilder setLocale(Locale locale) {
            this.locale = locale;
            return this;
        }

        /**
         * Sets the format of the returned date
         * Default value is dd-MM/yyyy hh:mm
         * @param dateFormat The dateformat to be set
         * @return THe AMDateBuilder object
         */
        public AMDateBuilder setDateFormat(String dateFormat) {
            this.dateFormat = dateFormat;
            return this;
        }

        /**
         * Creates the date object from the builder object
         * @return The constructed date object
         */
        public String build() {
            Date date = new Date(timestamp);
            SimpleDateFormat format = new SimpleDateFormat(dateFormat, locale);
            return format.format(date);
        }
    }

    /**
     * Changes the format of a given date
     * @param date The date to change format of
     * @param formatTo The format to change the date to
     * @param formatFrom The format to change the date from
     * @return The date formatted
     */
    public static String changeFormattedDate(String date, String formatTo, String formatFrom) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat(formatFrom, getDanishLocale());
        Date dateObj = null;
        try {
            dateObj = simpledateformat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new SimpleDateFormat(formatTo, getDanishLocale()).format(dateObj);
    }

    public static Locale getDanishLocale() {
        return new Locale("da", "DK");
    }

    public static Locale getDefaultLocale() {
        return Locale.getDefault();
    }


}
