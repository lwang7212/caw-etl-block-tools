package org.codeanywhere.framework.core.meta;


import java.lang.reflect.Field;



import java.util.HashMap;
import java.util.Map;

import org.codeanywhere.framework.orm.annotation.FieldMeta;
import org.codeanywhere.framework.orm.annotation.TableMeta;

/**
 * 实体元数据管理
 * 
 * @author 王磊
 * @Email lwang7212@163.com
 * @QQ 15857884
 * @Phone: 18955116326
 * @version 1.0
 * @since jDK1.6 2011-2-20
 */
public class MetaDataManager {
	private MetaDataManager() {
		_mapCache = new HashMap<String, EntityMetaInfo>();
	}

	private static MetaDataManager _instance = null;
	private Map<String, EntityMetaInfo> _mapCache;

	public static synchronized MetaDataManager getCurrent() {
		if (_instance == null) {
			_instance = new MetaDataManager();
		}
		return _instance;
	}

	public EntityMetaInfo GetEntityMeta(Class<?> p_class) {
		String strKey = p_class.getName().trim().toLowerCase();
		if (_mapCache.containsKey(strKey)) {
			return _mapCache.get(strKey);
		} else {
			EntityMetaInfo meta = new EntityMetaInfo();
			meta.Tablemeta = p_class.getAnnotation(TableMeta.class);
			Field[] fs = p_class.getDeclaredFields();
			for (Field f : fs) {

				FieldMeta m = f.getAnnotation(FieldMeta.class);

				if (m != null) {

					try {
						ProperyMetaInfo prop = new ProperyMetaInfo();
						prop.FieldInfo = m;
						prop.FieldParam = f.getType();
						String propKey = m.FieldName().trim().toLowerCase();
						prop.getMethod = p_class.getMethod(
								"get" + m.FieldName(), new Class<?>[] {});
						prop.setMethod = p_class.getMethod(
								"set" + m.FieldName(),
								new Class<?>[] { prop.FieldParam });
						if (!meta.getPropMeta().containsKey(propKey)) {
							meta.getPropMeta().put(propKey, prop);
						}
						

					} catch (SecurityException e) {
						
						e.printStackTrace();
					} catch (NoSuchMethodException e) {						
						e.printStackTrace();
					}

				}

			}
			//属性字段排序
			 meta.SortFields();
			_mapCache.put(strKey, meta);
			return meta;
		}

	}


	


}
