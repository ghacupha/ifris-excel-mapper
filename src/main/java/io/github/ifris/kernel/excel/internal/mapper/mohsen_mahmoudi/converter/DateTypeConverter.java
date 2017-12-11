/*
 * code https://github.com/mohsen-mahmoudi/excel-object-mapping
 */
package io.github.ifris.kernel.excel.internal.mapper.mohsen_mahmoudi.converter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author redcrow
 */
public class DateTypeConverter implements TypeConverter<Date> {

    private final static Logger LOGGER = getLogger(DateTypeConverter.class);

    @Override
    public Date convert(Object value, String... pattern) {
        LOGGER.debug("Converting : {} to java.util.Date using pattern: {}", value, pattern[0]);
        if (value == null) {
            LOGGER.warn("Hmmm! Looks like the value passed for conversion is null....Any particular reason you are doing this?");
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        if (value instanceof Timestamp) {
            LOGGER.debug("The value passed contains a timestamp, setting time in milliseconds");
            calendar.setTimeInMillis(((Timestamp) value).getTime());
        } else if (value instanceof Date) {
            calendar.setTimeInMillis(((Date) value).getTime());
        } else if (value instanceof String) {
            LOGGER.debug("The value passed is a string : {}. We are now using SimpleDateFormat for parsing with the pattern" +
                    "{}",value,pattern[0]);
            try {
                return new SimpleDateFormat(pattern[0]).parse((String) value);
            } catch (java.text.ParseException ex1) {
                LOGGER.error("ParseException encountered : ",ex1.getMessage(), ex1.getErrorOffset(), ex1.getStackTrace());
                return null;
            }
        } else {
            return null;
        }

        return calendar.getTime();
    }

}
