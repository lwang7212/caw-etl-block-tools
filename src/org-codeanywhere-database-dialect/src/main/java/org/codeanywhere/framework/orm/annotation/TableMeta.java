package org.codeanywhere.framework.orm.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 表对象元数据
 * 
 * @author 王磊
 * @Email lwang7212@163.com
 * @QQ 15857884
 * @Phone: 18955116326
 * @version 1.0
 * @since jDK1.6 2011-2-14
 */
@Target({ TYPE })
@Retention(RUNTIME)
public @interface TableMeta {

	/**
	 * 表名
	 * 
	 * @return
	 */
	String TableName();

	/**
	 * 标题
	 * 
	 * @return
	 */
	String Caption();

}
