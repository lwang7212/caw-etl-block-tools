package org.codeanywhere.framework.core.meta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codeanywhere.framework.orm.annotation.TableMeta;

/**
 * 实体元数据信息
 * 
 * @author 王磊
 * @Email lwang7212@163.com
 * @QQ 15857884
 * @Phone: 18955116326
 * @version 1.0
 * @since jDK1.6 2011-2-20
 */
public class EntityMetaInfo {
	private Map<String, ProperyMetaInfo> propMeta;

	/**
	 * 对应表的元数据
	 */
	public TableMeta Tablemeta;
	/**
	 * Id列属性
	 */
	public ProperyMetaInfo IdField;

	/**
	 * 获取所有属性元数据
	 * 
	 * @return
	 */
	public Map<String, ProperyMetaInfo> getPropMeta() {
		if (propMeta == null) {
			propMeta = new HashMap<String, ProperyMetaInfo>();
		}
		return propMeta;
	}

	/**
	 * 获取对应字段的属性元数据
	 * 
	 * @param pFieldName
	 * @return
	 */
	public ProperyMetaInfo GetProp(String pFieldName) {
		String strKey = pFieldName.toLowerCase().trim();
		if (getPropMeta().containsKey(strKey)) {
			return getPropMeta().get(strKey);
		} else {
			return null;
		}
	}

	/**
	 * 获取主键集合
	 * 
	 * @return
	 */
	public List<ProperyMetaInfo> GetPrimaryKeys() {
		List<ProperyMetaInfo> rtn = new ArrayList<ProperyMetaInfo>();

		for (ProperyMetaInfo prop : getPropMeta().values()) {
			if (prop.FieldInfo.Primary()) {
				rtn.add(prop);
			}
		}
		return rtn;
	}
	/**
	 * 对属性重新排序
	 */
	public List<Map.Entry<String, ProperyMetaInfo>> SortFields()
	{
		List<Map.Entry<String, ProperyMetaInfo>> lstProps =
			    new ArrayList<Map.Entry<String, ProperyMetaInfo>>(getPropMeta().entrySet());

		Collections.sort(lstProps, new Comparator<Map.Entry<String, ProperyMetaInfo>>() {

			@Override
			public int compare(Entry<String, ProperyMetaInfo> o1, Entry<String, ProperyMetaInfo> o2)
			{
				
				
					return o1.getValue().FieldInfo.Idx_no()-o2.getValue().FieldInfo.Idx_no();
				
			}});
		
		return lstProps;
		
	}
}
