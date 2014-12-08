package uk.ac.gre.cw.aircraft.utils;

import uk.ac.gre.cw.aircraft.Common;
import uk.ac.gre.cw.aircraft.SystemProperties;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
    public static String toPrettyDateString(Date date) {
        if (date == null) return "";
        DateFormat df = new SimpleDateFormat(SystemProperties.getValue(Common.DATE_FORMAT));
        return df.format(date);
    }
}
