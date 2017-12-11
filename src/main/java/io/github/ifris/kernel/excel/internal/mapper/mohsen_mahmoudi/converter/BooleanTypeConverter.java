/*
 * code https://github.com/mohsen-mahmoudi/excel-object-mapping
 */
package io.github.ifris.kernel.excel.internal.mapper.mohsen_mahmoudi.converter;

import java.math.BigDecimal;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author redcrow
 */
public class BooleanTypeConverter implements TypeConverter<Boolean> {

    private final static Logger LOGGER = getLogger(BooleanTypeConverter.class);

    @Override
    public Boolean convert(Object value, String... pattern) {

        LOGGER.debug("Converting : {} to boolean", value);

        if (value == null) {
            LOGGER.debug("The value passed to param is null...using default => False");
            return Boolean.FALSE;
        }

        if (value instanceof Boolean) {
            LOGGER.debug("The value is instance of boolean, casting the value accordingly...");
            return (Boolean) value;
        }

        if (value instanceof Integer) {
            LOGGER.debug("The value passed is instance of Integer");
            //try {
                //value = (Integer)value;
            //} catch (Exception ex) {
                //return Boolean.FALSE;
            //}
            LOGGER.warn("The appropriate integer value is : {}. Unfortunately given the Model configuration," +
                    "this is going to be of no use to you. Thanks for trying ifris excel mapper",value);
            return (Integer)value == 1 ? Boolean.TRUE : Boolean.FALSE;
        }

        if (value instanceof String) {
            LOGGER.debug("The value passed is instance of string, converting to boolean...");
            //try {
			if (((String) value).trim().equals("false")) {
                LOGGER.debug("The string passed equals to false, changing to Boolean.FALSE");
                return Boolean.FALSE;
			} else if (((String) value).trim().equals("true")) {
                LOGGER.debug("The string passed equals to true, changing to Boolean.TRUE");
				return Boolean.TRUE;
			} else {
				value = BigDecimal.valueOf(Long.parseLong(((String) value).trim()));
                LOGGER.debug("The string passed does not resonate with any Boolean type, so we have converted it to long" +
                        "and hope that you will change your model configuration accordingly, else we can't assist you. Thanks" +
                        "for trying ifris excel mapper");
			}
            //} catch (Exception ex) {
                //return Boolean.FALSE;
            //}
        }

        if (value instanceof BigDecimal) {
            LOGGER.warn("Qookay. I see the value you passed is BigDecimal. So if it is 1 we are going to convert it" +
                    "to True, otherwise then we will assume Boolean.FALSE. If this be not the desired behaviour, you know what to do");
            BigDecimal bd = (BigDecimal) value;
            int intValue = bd.intValue();
            return intValue == 1
                    ? Boolean.TRUE
                    : Boolean.FALSE;
        }

        return Boolean.FALSE;
    }

}
