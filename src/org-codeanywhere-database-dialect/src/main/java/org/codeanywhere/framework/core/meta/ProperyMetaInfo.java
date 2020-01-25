package org.codeanywhere.framework.core.meta;

import java.lang.reflect.Method;
import java.util.Currency;

import org.apache.commons.lang.StringUtils;
import org.codeanywhere.UtilHelp;
import org.codeanywhere.framework.orm.annotation.FieldMeta;


/**
 * 属性元数据信息
 * 
 * @author 王磊
 * @Email lwang7212@163.com
 * @QQ 15857884
 * @Phone: 18955116326
 * @version 1.0
 * @since jDK1.6 2011-2-20
 */
public class ProperyMetaInfo {
	public FieldMeta FieldInfo;
	public Method getMethod;
	public Method setMethod;
	public Class<?> FieldParam;

	/**
	 * 转换对象类型
	 * 
	 * @param p_org
	 * @return
	 */
	public Object CastToFieldType(Object p_org) {
		try {
			if (p_org == null) {
				return null;
			}
			if (p_org.getClass().equals(FieldParam)) {
				return p_org;
			}
			if (FieldParam.equals(Boolean.class) || FieldParam.equals(boolean.class)) {
				if(p_org.toString().equalsIgnoreCase("1"))
				{
					return true;
				}
				else if(p_org.toString().equalsIgnoreCase("0"))
				{
					return false;
				}
				return Boolean.parseBoolean(p_org.toString());
			}
			if (FieldParam.equals(Byte.class) || FieldParam.equals(byte.class)) {
				if(StringUtils.isEmpty(p_org.toString()))
				{
					return null;
				}
				return Byte.parseByte(p_org.toString().replaceAll("\\.\\d+", ""));
			}
			if (FieldParam.equals(Byte[].class)) {
				// return p_org;
			}
			if (FieldParam.equals(Currency.class)) {

			}
			if (FieldParam.equals(Double.class) || FieldParam.equals(double.class)) {
				if(StringUtils.isEmpty(p_org.toString()))
				{
					return null;
				}
				return Double.parseDouble(p_org.toString());
			}
			if (FieldParam.equals(Integer.class) || FieldParam.equals(int.class)) {
				if(StringUtils.isEmpty(p_org.toString()))
				{
					return null;
				}
				return Integer.parseInt(p_org.toString().replaceAll("\\.\\d+", ""));
			}
			if (FieldParam.equals(Long.class) || FieldParam.equals(long.class)) {
				if(StringUtils.isEmpty(p_org.toString()))
				{
					return null;
				}
				return Long.parseLong(p_org.toString().replaceAll("\\.\\d+", ""));
			}
			if (FieldParam.equals(Short.class) || FieldParam.equals(short.class)) {
				if(StringUtils.isEmpty(p_org.toString()))
				{
					return null;
				}
				return Short.parseShort(p_org.toString());
			}
			if (FieldParam.equals(Number.class)) {
				if(StringUtils.isEmpty(p_org.toString()))
				{
					return null;
				}
				return Double.parseDouble(p_org.toString());
			}
			if (FieldParam.equals(java.util.Date.class)) {
				if(StringUtils.isEmpty(p_org.toString()))
				{
					return null;
				}
				return UtilHelp.parseDate(p_org.toString());
			}
			if (FieldParam.equals(String.class)) {
				return p_org.toString();
			}
			if (FieldParam.equals(java.sql.Blob.class)) {
				
			}

			return p_org;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p_org;

	}

}
