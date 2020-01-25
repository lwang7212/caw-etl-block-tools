package org.codeanywhere.data;

/**
 * 列值
 */
public class ColumnValue  extends  MetaColumn{
    public Object getValue() {
        return value;
    }

    public ColumnValue setValue(Object value) {
        this.value = value;
        return this;
    }

    private  Object value;
}
