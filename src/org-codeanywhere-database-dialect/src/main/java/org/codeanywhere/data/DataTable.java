package org.codeanywhere.data;

import org.codeanywhere.framework.core.meta.EntityMetaInfo;
import org.codeanywhere.framework.core.meta.MetaDataManager;
import org.codeanywhere.framework.core.meta.ProperyMetaInfo;


import java.util.*;

/**
 * 具有数据的表
 */
public class DataTable {

    private int size = 0;
    private Iterable<?> _raw;
    private boolean _isEntity = false;






    public DataTable() {
        columns = new LinkedHashMap<String, MetaColumn>();
        rows = new ArrayList<LinkedHashMap<String, ColumnValue>>();
    }

    public LinkedHashMap<String, MetaColumn> getColumns() {
        return columns;
    }


    public List<LinkedHashMap<String, ColumnValue>> getRows() {
        return rows;
    }

    public DataTable addMetaColumn(String p_columnName, String metaType) {
        columns.put(p_columnName, new MetaColumn().setName(p_columnName).setMetaType(metaType));
        return this;
    }

    /**
     * 添加元数据
     * @param column
     * @return
     */
    public DataTable addMetaColumn(MetaColumn column) {
        columns.put(column.getName(), column);
        return this;
    }
    public DataTable addAllMetaColumn(LinkedHashMap<String, MetaColumn> linkMap) {
        columns.clear();
        columns.putAll(linkMap);
        return this;
    }


    public String getTableName() {
        return tableName;
    }

    public DataTable setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    private final LinkedHashMap<String, MetaColumn> columns;

    private final List<LinkedHashMap<String, ColumnValue>> rows;

    private String tableName;




    /**
     * 包装为Map数组
     *
     * @param lstDoc
     * @return
     */
    public DataTable warpListMap(List<Map<String, Object>> lstDoc) {
        setSize(lstDoc.size());
        _raw = lstDoc;
        _isEntity = false;

        return this;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
