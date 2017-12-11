/*
 * code https://github.com/mohsen-mahmoudi/excel-object-mapping
 */
package io.github.ifris.kernel.excel.internal.mapper.mohsen_mahmoudi.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author redcrow
 * @author Mohsen.Mahmoudi 
 * 
 *         set <b>name</b> of column like <code>@Col(name = "abcd")</code> or
 *         <b>index</b> of column like <code>@Col(index = 0)</code> based on
 *         requirement. <b>Be careful, order of name is higher than
 *         index.</b>
 */
@Target(ElementType.FIELD)
@Retention(RUNTIME)
public @interface Col {

	String name() default "";

	int index() default 0;

	String pattern() default "";

}
