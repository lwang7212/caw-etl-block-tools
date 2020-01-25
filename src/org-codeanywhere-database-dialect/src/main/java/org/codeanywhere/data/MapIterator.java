package org.codeanywhere.data;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wanglei on 2017/5/3.
 */
public class MapIterator  extends  RowsIterator {

    private Iterator<Map<String,Object>> wrapIterator;
    private  DataTable ctxTable;
    public MapIterator(Iterator<Map<String,Object>> p_wrapIter, DataTable dataTable)
    {
        wrapIterator=p_wrapIter;
        ctxTable=dataTable;
    }
    @Override
    public boolean hasNext() {
        return wrapIterator.hasNext();
    }

    @Override
    public LinkedHashMap<String, ColumnValue> next() {

        Map<String,Object> doc=wrapIterator.next();
        LinkedHashMap<String,ColumnValue> next= new LinkedHashMap<String,ColumnValue>();

        for (Map.Entry<String,MetaColumn>  kv : ctxTable.getColumns().entrySet() ) {
            if(doc.containsKey(kv.getKey()))
            {
                next.put(kv.getKey(),kv.getValue().createValue(doc.get(kv.getKey())));
            }
            else
            {
                next.put(kv.getKey(),kv.getValue().createValue(null));
            }

        }
        return next;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }


    @Override
    public Iterator<LinkedHashMap<String, ColumnValue>> iterator() {
        return  this;
    }
}
