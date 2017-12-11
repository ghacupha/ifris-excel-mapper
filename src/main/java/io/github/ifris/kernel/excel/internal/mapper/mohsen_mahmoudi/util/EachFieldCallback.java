/*
 * code https://github.com/mohsen-mahmoudi/excel-object-mapping
 */
package io.github.ifris.kernel.excel.internal.mapper.mohsen_mahmoudi.util;

import java.lang.reflect.Field;

/**
 * @author redcrow
 * @author Mohsen.Mahmoudi
 */
public interface EachFieldCallback {

    void each(Field field, String name, Integer index) throws Throwable;
}
