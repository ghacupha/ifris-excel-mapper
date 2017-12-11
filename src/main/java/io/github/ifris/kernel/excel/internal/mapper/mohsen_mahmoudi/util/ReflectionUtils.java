/*
 * code https://github.com/mohsen-mahmoudi/excel-object-mapping
 */
package io.github.ifris.kernel.excel.internal.mapper.mohsen_mahmoudi.util;

import io.github.ifris.kernel.excel.internal.mapper.mohsen_mahmoudi.annotation.Col;
import io.github.ifris.kernel.excel.internal.mapper.mohsen_mahmoudi.converter.TypeConverter;
import io.github.ifris.kernel.excel.internal.mapper.mohsen_mahmoudi.converter.TypeConverters;
import static io.github.ifris.kernel.excel.internal.mapper.mohsen_mahmoudi.util.CollectionUtils.isEmpty;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author redcrow
 * @author Mohsen.Mahmoudi
 */
public class ReflectionUtils {

	private static final Logger LOG = LoggerFactory.getLogger(ReflectionUtils.class);

	private static String toUpperCaseFirstCharacter(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static void setValueOnField(Object instance, Field field, Object value) throws Exception {
		Class<?> clazz = instance.getClass();
		String setMethodName = "set" + toUpperCaseFirstCharacter(field.getName());

		for (Map.Entry<Class, TypeConverter> entry : TypeConverters.getConverter().entrySet()) {
			if (field.getType().equals(entry.getKey())) {
				Method method = clazz.getDeclaredMethod(setMethodName, entry.getKey());
				Col column = field.getAnnotation(Col.class);

				method.invoke(instance, entry.getValue().convert(value, column == null ? null : column.pattern()));
			}
		}
	}

	public static void eachFields(Class<?> clazz, EachFieldCallback callback) throws Throwable {
		Field[] fields = clazz.getDeclaredFields();
		if (!CollectionUtils.isEmpty(fields)) {
			for (Field field : fields) {
				if (field.isAnnotationPresent(Col.class)) {
					if (!field.getAnnotation(Col.class).name().isEmpty()) {
						callback.each(field, field.getAnnotation(Col.class).name(), null);
					} else {
						callback.each(field, null, field.getAnnotation(Col.class).index());
					}
				} else {
					callback.each(field, field.getName(), null);
				}
			}
		}
	}

	public static Field mapNameToField(Class<?> clazz, String name) throws Throwable {
		Field[] fields = clazz.getDeclaredFields();
		if (!CollectionUtils.isEmpty(fields)) {
			for (Field field : fields) {
				if (field.getName().equals(name)) {
					return field;
				}
			}
		}
		throw new Exception("Error -- " + name + " Property of Class is not Found...");
	}
}
