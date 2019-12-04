package es.iesnervion.yeray.routemarker.Utilities;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Conversiones {

    /*
    * Interfaz
    * Nombre: formatDateTime
    * Comentario: Retrieving dates as strings from SQLite you can then format/convert them as required
    * into local regionalised formats using the Calendar or the android.text.format.DateUtils.formatDateTime method.
    * Cabecera: public static String formatDateTime(Context context, String timeToFormat)
    * Entrada:
    *   -Context context
    *   -String timeToFormat
    * Salida:
    *   -String finalDateTime
    * Postcondiciones: Devuelve una cadena asociada al nombre, que es una fecha en formato
    * dateTime.
    * */
    public static String formatDateTime(Context context, String timeToFormat) {

        String finalDateTime = "";

        SimpleDateFormat iso8601Format = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

        Date date = null;
        if (timeToFormat != null) {
            try {
                date = iso8601Format.parse(timeToFormat);
            } catch (ParseException e) {
                date = null;
            }

            if (date != null) {
                long when = date.getTime();
                int flags = 0;
                flags |= android.text.format.DateUtils.FORMAT_SHOW_TIME;
                flags |= android.text.format.DateUtils.FORMAT_SHOW_DATE;
                flags |= android.text.format.DateUtils.FORMAT_ABBREV_MONTH;
                flags |= android.text.format.DateUtils.FORMAT_SHOW_YEAR;

                finalDateTime = android.text.format.DateUtils.formatDateTime(context,
                        when + TimeZone.getDefault().getOffset(when), flags);
            }
        }
        return finalDateTime;
    }

}
