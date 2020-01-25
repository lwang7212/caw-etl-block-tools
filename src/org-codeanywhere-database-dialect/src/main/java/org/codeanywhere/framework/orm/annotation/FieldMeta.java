package org.codeanywhere.framework.orm.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 字段属性元数据
 * 
 * @author 王磊
 * @Email lwang7212@163.com
 * @QQ 15857884
 * @Phone: 18955116326
 * @version 1.0
 * @since jDK1.6 2011-2-14
 */
@Target({ METHOD, FIELD })
@Retention(RUNTIME)
public @interface FieldMeta {
	/**
	 * 标题
	 * 
	 * @return
	 */
	String Caption();

	/**
	 * 抽象类型
	 * 
	 * @return
	 */
	String AbstractType();

	/**
	 * 字段名
	 * 
	 * @return String
	 */
	String FieldName();

	/**
	 * 是否主键
	 * 
	 * @return boolean
	 */
	boolean Primary() default false;

	/**
	 * 不允许空
	 * 
	 * @return
	 */
	boolean IsNotNull() default false;

	/**
	 * 版型,
	 * 
	 * @return
	 */
	String StereoType() default "";

	/**
	 * 此列的精度级别
	 * 
	 * @return
	 */
	String Prec() default "20";

	/**
	 * 列的小数位数
	 * 
	 * @return
	 */
	String Scale() default "4";

	/**
	 * 长度
	 * 
	 * @return
	 */
	String Length() default "10";
	/**
	 * 是否标识列
	 * @return
	 */
	 boolean Identity()  default false;
	/**
	 * 是否外建
	 */
     boolean IsForeign()  default false;
     /**
      * 属性序号
      * @return
      */
     int Idx_no() default 0;
     

}