/*
 * code https://github.com/mohsen-mahmoudi/excel-object-mapping
 */
package io.github.ifris.kernel.excel.internal.mapper.mohsen_mahmoudi.converter;

/**
 * @author redcrow
 */
public interface TypeConverter<T> {

    T convert(Object value, String... pattern);
}
