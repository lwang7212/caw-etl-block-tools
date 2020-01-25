package org.codeanywhere.data;

/**
 *
 */
public class MetaColumn {
    public String getName() {
        return name;
    }

    public MetaColumn setName(String name) {
        this.name = name;
        return this;
    }

    public String getMetaType() {
        return metaType;
    }

    public MetaColumn setMetaType(String metaType) {
        this.metaType = metaType;
        return this;
    }

    private String name;
    private String metaType;
    /**
     * 数字位数
     */
    private String prec;

    public String getPrec() {
        return prec;
    }

    public MetaColumn setPrec(String prec) {
        this.prec = prec;
        return this;
    }

    public String getLength() {
        return length;
    }

    public MetaColumn setLength(String length) {
        this.length = length;
        return this;
    }

    public String getScale() {
        return scale;
    }

    public MetaColumn setScale(String scale) {
        this.scale = scale;
        return this;
    }

    /**
     * 长度
     */
    private String length;
    /**
     * 小数点精度
     */
    private String scale;

    /**
     *
     */
    private String comment;
    private  boolean notNull=false;
    /**
     * 字段脚本
     */
    private String fieldScript;
    /**
     * 是否主键
     */
    private boolean primary = false;

    public boolean getPrimary() {
        return primary;
    }

    public MetaColumn setPrimary(boolean primary) {
        this.primary = primary;
        return this;
    }


    public ColumnValue createValue(Object p_value) {
        return (ColumnValue) new ColumnValue().setValue(p_value).setName(this.getName()).setMetaType(this.getMetaType());
    }

    /**
     * 字段注释
     *
     * @return
     */
    public String getComment() {
        return comment;
    }

    /**
     * 字段注释
     *
     * @param comment
     * @return
     */
    public MetaColumn setComment(String comment) {
        this.comment = comment;
        return this;
    }


    public boolean isNotNull() {
        return notNull;
    }

    public MetaColumn setNotNull(boolean notNull) {
        this.notNull = notNull;
        return this;
    }

    public String getFieldScript() {
        return fieldScript;
    }

    public MetaColumn setFieldScript(String fieldScript) {
        this.fieldScript = fieldScript;
        return this;
    }
}
