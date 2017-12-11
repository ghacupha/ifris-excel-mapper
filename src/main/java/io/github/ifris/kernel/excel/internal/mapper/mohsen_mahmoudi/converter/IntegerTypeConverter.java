/*
 * code https://github.com/mohsen-mahmoudi/excel-object-mapping
 */
package io.github.ifris.kernel.excel.internal.mapper.mohsen_mahmoudi.converter;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author redcrow
 */
public class IntegerTypeConverter implements TypeConverter<Integer> {

    private final static Logger LOGGER = getLogger(IntegerTypeConverter.class);

    @Override
    public Integer convert(Object value, String... pattern) {
        if (value == null) {
            return null;
        }

        if (value instanceof Integer) {
            return (Integer) value;
        }

        if (value instanceof String) {
            //try {
                return Integer.valueOf(((String) value).trim());
            //} catch (Exception ex) {
            //    LOG.warn(null, ex);
            //    return null;
            //}
        }

        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).intValue();
        }

        return null;
    }

}
