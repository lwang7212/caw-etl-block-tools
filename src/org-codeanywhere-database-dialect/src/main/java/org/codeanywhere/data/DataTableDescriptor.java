package org.codeanywhere.data;

import java.util.LinkedList;
import java.util.List;

/**
 * DataTable 元数据描述
 */
public class DataTableDescriptor {
    private String tableName;

    /**
     * 表注释
     */
    public String getTableComment() {
        return tableComment;
    }

    /**
     * 表注释
     */
    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    /**
     * 表注释
     */
    private String tableComment;

    private List<MetaColumn> meta;
    /**
     * 主键列
     */
    private List<MetaColumn> pkColumns;

    public String getTableName() {
        return tableName;
    }

    public DataTableDescriptor setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public DataTableDescriptor addMeta(String name, String metaType) {
        this.getMeta().add(new MetaColumn().setMetaType(metaType).setName(name));
        return this;
    }

    public DataTableDescriptor addMeta(String name, String metaType, String comment) {
        this.getMeta().add(new MetaColumn().setMetaType(metaType).setName(name).setComment(comment));
        return this;
    }


    public List<MetaColumn> getMeta() {
        if (meta == null) {
            meta = new LinkedList<MetaColumn>();
        }
        return meta;
    }

    public DataTableDescriptor setMeta(List<MetaColumn> meta) {

        this.meta = meta;
        return this;
    }

    /**
     * 创建相关联DataTable
     * @return
     */
    public DataTable createDataTable() {
        DataTable rtn = new DataTable().setTableName(this.getTableName());

        this.getMeta().forEach(item -> {
            rtn.addMetaColumn(item);
            if(item.getPrimary())
            {
                getPkColumns().add(item);
            }
        });
        return rtn;
    }

    public List<MetaColumn> getPkColumns() {
        if(pkColumns==null)
        {
            pkColumns=new LinkedList<>();
        }
        return pkColumns;
    }
}
