package org.codeanywhere.database.dialect;
import org.apache.commons.lang.StringUtils;
import org.codeanywhere.data.DataTableDescriptor;
import org.codeanywhere.data.MetaColumn;
import org.codeanywhere.database.dialect.meta.MetaType;
import org.codeanywhere.database.dialect.meta.MetaTypesBase;
import org.codeanywhere.framework.core.meta.EntityMetaInfo;
import org.codeanywhere.framework.core.meta.MetaDataManager;
import org.codeanywhere.framework.core.meta.ProperyMetaInfo;
import org.codeanywhere.framework.orm.annotation.FieldMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类似hibernate的Dialect,但只精简出分页部分
 *
 * @author badqiu
 * @author miemiedev
 */
public abstract class Dialect {
    private static Logger log = LoggerFactory.getLogger(Dialect.class);
    /**
     * 包含的排序Key
     */
    protected static final String ORDERBY_KEY = "__orderby__";

    /**
     * 取分页Sql语句
     *
     * @param strSql
     * @param parameterObject 参数对象
     * @param nStartIndex     从1开始的起始序号 （包括开始序号)
     * @param nEndIndex       从1开始的结束序号，不包括结束序号
     * @return
     */
    public abstract String getPageSQL(String strSql, Object parameterObject, int nStartIndex, int nEndIndex);

    public String getCountSQL(String strSql) {
        StringBuffer sb = new StringBuffer().append("select count(1) from ( ");
        sb.append(this.FliterOrderBy(strSql)).append(" )  tmpCount ");
        return sb.toString();
    }

    /**
     * 过滤掉Order by 关键字
     *
     * @param strSql
     * @return
     */
    protected String FliterOrderBy(String strSql) {
        //首先清除内层排序的
        log.trace("过滤order by 前>>" + strSql);
        String strRtn = strSql;
        Pattern p1 = Pattern.compile("order\\s*?by.*?(?=\\))", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
        Matcher m1 = p1.matcher(strRtn);
        if (m1.find()) {

            strRtn = m1.replaceAll("");

        }
        //todo  窗口函数不过滤order by
        //再次清除行尾排序
        Pattern p = Pattern.compile("order\\s*?by.*$", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
        Matcher m = p.matcher(strRtn);
        if (m.find()) {
            strRtn = m.replaceAll("");
        }
        log.trace("过滤order by 后>>" + strRtn);
        return strRtn;

    }

    protected void processOrderby(String strSql, Object parameterObject, StringBuffer sb) {
        if (parameterObject != null && parameterObject instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) parameterObject;
            if (map.containsKey(ORDERBY_KEY) && map.get(ORDERBY_KEY) != null) {
                sb.append(FliterOrderBy(strSql));
                sb.append(" order by ").append(map.get(ORDERBY_KEY));
            } else {
                sb.append(strSql);
            }
        } else {
            sb.append(strSql);
        }
    }

    /**
     * 是否存在表sql语句
     *
     * @param TableName
     * @return
     */
    public abstract String getExistTableSql(String TableName);

    /**
     * 添加表注释
     *
     * @param TableName
     * @param comment
     * @return
     */
    public abstract String getTableCommentSql(String TableName, String comment);

    /**
     * 添加列注释
     *
     * @param TableName
     * @param ColName
     * @param comment
     * @return
     */
    public abstract String getColumnComment(String TableName, String ColName, String comment);

    /**
     * 创建表ＳＱＬ
     *
     * @param EntityClass
     * @return
     */
    public String genCreateTable(Class<?> EntityClass, String p_TabName) {
        String p_newTabName = p_TabName;
        // 标识符名称长度判断
        if (p_newTabName.length() > getMaxTableNameLenght()) {
            p_newTabName = p_newTabName.substring(p_newTabName.length() - getMaxTableNameLenght());
        }
        EntityMetaInfo met = MetaDataManager.getCurrent().GetEntityMeta(EntityClass);
        if (met == null) {
            log.error("对象" + EntityClass.getName() + "元数据没有发现");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getCreateTableString(p_newTabName));
        sb.append("(\r\n");

        List<String> lstColumn = new ArrayList<String>();
        List<String> pkColumn = new ArrayList<String>();
        for (Map.Entry<String, ProperyMetaInfo> item : met.SortFields()) {
            if (item.getValue().FieldInfo.Primary()) {
                pkColumn.add(item.getValue().FieldInfo.FieldName());
            }
            lstColumn.add(getFieldScript(item.getValue()));
        }
        sb.append(StringUtils.join(lstColumn.toArray(), ",\r\n"));
        sb.append("\r\n");

        if (pkColumn.size() > 0) {
            // 创建主键
            sb.append(String.format(",constraint pk_%1$s primary key (%2$s)",
                    genObjNameBylength(p_newTabName, getMaxTableNameLenght() - 3), StringUtils.join(pkColumn.toArray(), ",")));
            sb.append("\r\n");
        }

        sb.append(")");// .append("\r\n");
        return sb.toString();
    }

    /**
     * 创建表ＳＱＬ
     *
     * @param tableDescriptor dataTable 描述信息
     * @return
     */
    public String genCreateTable(DataTableDescriptor tableDescriptor) {
        String p_newTabName = tableDescriptor.getTableName();
        // 标识符名称长度判断
        if (p_newTabName.length() > getMaxTableNameLenght()) {
            p_newTabName = p_newTabName.substring(p_newTabName.length() - getMaxTableNameLenght());
            tableDescriptor.setTableName(p_newTabName);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getCreateTableString(p_newTabName));
        sb.append("(\r\n");

        List<String> lstColumn = new ArrayList<>();
        List<String> pkColumn = new ArrayList<>();
        for (MetaColumn item : tableDescriptor.getMeta()) {
            if (item.getPrimary()) {
                pkColumn.add(item.getName());
            }
            lstColumn.add(getFieldScript(item));
        }
        sb.append(StringUtils.join(lstColumn.toArray(), ",\r\n"));
        sb.append("\r\n");
        if (pkColumn.size() > 0) {
            // 创建主键
            sb.append(String.format(",constraint pk_%1$s primary key (%2$s)",
                    genObjNameBylength(p_newTabName, getMaxTableNameLenght() - 3), StringUtils.join(pkColumn.toArray(), ",")));
            sb.append("\r\n");
        }

        sb.append(")");
        return sb.toString();
    }

    protected String genObjNameBylength(String objectName, int p_length) {
        if (objectName.trim().length() < p_length) {
            return objectName.trim();
        } else {
            return objectName.substring(objectName.trim().length() - p_length);
        }
    }

    /**
     * 创建列脚本
     *
     * @param prop
     * @return
     */
    protected String getFieldScript(ProperyMetaInfo prop) {
        FieldMeta p_field = prop.FieldInfo;
        String dataType = genDataType(p_field);

        if (p_field.Identity() && getSupportsIdentityColumns(p_field)) {

            return String.format("%1$s %2$s %3$s", quote(p_field.FieldName()), dataType, getIdentityColumnString());
        } else {

            return String.format("%1$s %2$s %3$s", quote(p_field.FieldName()), dataType, p_field.IsNotNull() ? "not null" : "null");
        }
    }

    /**
     * 创建列脚本
     *
     * @param metaColumn
     * @return
     */
    protected String getFieldScript(MetaColumn metaColumn) {


        return StringUtils.isBlank(metaColumn.getFieldScript()) ?
                String.format("%1$s %2$s %3$s", quote(metaColumn.getName()), genDataType(metaColumn), metaColumn.isNotNull() ? "not null" : "null")
                : metaColumn.getFieldScript();

    }

    protected abstract boolean getSupportsIdentityColumns(FieldMeta p_field);

    protected abstract String getIdentityColumnString();

    /**
     * 获取当前元数据类型
     *
     * @return
     */
    protected abstract MetaTypesBase getMetaTypes();

    /**
     * 获取字段类型表达式
     *
     * @param p_field
     * @return
     */
    public String genDataType(FieldMeta p_field) {
        MetaType meta = getMetaTypes().GetMetaType(p_field);

        String dataType = meta.getColumnDefineTemplate();
        if (dataType.contains("$l")) {
            dataType = dataType
                    .replaceAll(
                            "\\$l",
                            (StringUtils.isEmpty(p_field.Length()) || p_field.Length().trim() == "0") ? String.valueOf(meta
                                    .getDefaultLength()) : p_field.Length());
        }
        if (dataType.contains("$p")) {
            dataType = dataType.replaceAll("\\$p",
                    (StringUtils.isEmpty(p_field.Prec()) || p_field.Prec().trim() == "0") ? String.valueOf(meta.getDefaultPrec())
                            : p_field.Prec());
        }
        if (dataType.contains("$s")) {
            dataType = dataType.replaceAll("\\$s",
                    (StringUtils.isEmpty(p_field.Scale()) || p_field.Scale().trim() == "0") ? String.valueOf(meta.getDefaultScale())
                            : p_field.Scale());
        }
        return dataType;
    }

    /**
     * 生成字段类型表达式
     *
     * @param metaColumn
     * @return
     */
    public String genDataType(MetaColumn metaColumn) {
        MetaType meta = getMetaTypes().GetMetaType(metaColumn.getMetaType());

        String dataType = meta.getColumnDefineTemplate();
        if (dataType.contains("$l")) {
            dataType = dataType.replaceAll("\\$l", StringUtils.isEmpty(metaColumn.getLength()) ? String.valueOf(meta.getDefaultLength()) : metaColumn.getLength());
        }
        if (dataType.contains("$p")) {
            dataType = dataType.replaceAll("\\$p", StringUtils.isEmpty(metaColumn.getPrec()) ? String.valueOf(meta.getDefaultPrec()) : metaColumn.getPrec());
        }
        if (dataType.contains("$s")) {
            dataType = dataType.replaceAll("\\$s", StringUtils.isEmpty(metaColumn.getScale()) ? String.valueOf(meta.getDefaultScale()) : metaColumn.getScale());
        }
        return dataType;
    }

    /**
     * 获取创建表的名称
     *
     * @param p_tableName
     * @return
     */
    protected Object getCreateTableString(String p_tableName) {
        // TODO Auto-generated method stub
        return "create table " + quote(p_tableName);
    }

    /**
     * 取得对应数据库版本号
     */
    public abstract String getVersionSql();

    /**
     * 获取批量插入表达式
     *
     * @return
     */
    public abstract String getBatchInsertStatment();

    /**
     * 取带左右标识的标识符
     *
     * @param identifierName
     * @return
     */
    public String quote(String identifierName) {
        if (getOpenQuote() == null) {
            return identifierName;
        }

        String quotedName = identifierName.replaceAll(getESCRex(getOpenQuote()), getOpenQuote() + getOpenQuote());
        if (getOpenQuote() != getCloseQuote()) {
            quotedName = identifierName.replaceAll(getESCRex(getCloseQuote()), getCloseQuote() + getCloseQuote());
        }

        return getOpenQuote() + quotedName + getCloseQuote();
    }

    /**
     * 正则表达式转义符号
     *
     * @param rex
     * @return
     */
    private String getESCRex(String rex) {
        if (rex.equalsIgnoreCase("[") || rex.equalsIgnoreCase("]") || rex.equalsIgnoreCase(".")) {
            return "\\" + rex;
        } else {
            return rex;
        }
    }

    /**
     * 标识符的左引 ，如果不支持，返回空
     *
     * @return
     */
    protected abstract String getOpenQuote();

    /**
     * 标识符的右引号 如果不支持，返回空
     *
     * @return
     */
    protected abstract String getCloseQuote();

    /**
     * 最大命名长度
     *
     * @return
     */
    protected int getMaxTableNameLenght() {
        return 30;
    }





    /**
     * 修正批量处理SQL
     *
     * @param sql
     * @return
     */
    public String getBatchInsertSql(String sql) {
        // 替换别名
        Pattern p = Pattern.compile("~", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
        Matcher m = p.matcher(sql);
        if (m.find()) {

            sql = m.replaceAll("");

        }
        return sql;
    }





    /**
     * 数据库类型名称
     * Mysql | PostgreSQL | SQLite | H2Db  | GBase  | Oracle | SqlServer
     *
     * @return
     */
    public abstract String getDbType();

    /**
     * 判断数据库方言为类型
     *
     * @param dbType
     * @return
     */
    public boolean Is(String dbType) {
        return dbType.equalsIgnoreCase(getDbType());
    }

    /**
     * 根据不同数据库规则，转换表名，默认不转换
     *
     * @param tableName
     * @return
     */
    public String warpTableName(String tableName) {
        return tableName;
    }




    /**
     * 取得指定表的架构信息，然后如下列的字符串
     *  <table border="1">
     * <tr><td> 字段名称</td><td> 字段含义</td><td> 备注</td></tr>
     * <tr><td> Col_Code</td><td> 列定义的代码</td><td> 不能空，且唯一</td></tr>
     * <tr><td>Col_Code</td><td>列定义的代码</td><td>不能空，且唯一</td></tr>
     * <tr><td>Col_Name</td><td>列名称</td><td>列的中文名称，如果不存在就等同Col_Code</td></tr>
     * <tr><td>Col_Comment</td><td>列备注</td><td>列的中文名称，如果不存在就等同Col_name</td></tr>
     * <tr><td>Col_Description</td><td>列详细说明</td><td>列详细说明，如果不存在就等同Col_Comment</td></tr>
     * <tr><td>Col_Type</td><td>字段类型</td><td>不包括长度及括号</td></tr>
     * <tr><td>Col_Len</td><td>长度</td><td></td></tr>
     * <tr><td>Col_Prec</td><td>精度</td><td>包括小数位</td></tr>
     * <tr><td>Col_Scale</td><td>小数位</td><td></td></tr>
     * <tr><td>Is_Identity</td><td>是否自动增长</td><td>如果系统不支持，则为0，1为自动增长</td></tr>
     * <tr><td>IsNullable</td><td>是否为空</td><td>1：允许空 ,0:不允许空</td></tr>
     * <tr><td>Is_Key</td><td>是否主键</td><td>1：是 ,0:不是</td></tr>
     * <tr><td>Is_FKey</td><td>是否外键</td><td>1：是 ,0:不是</td></tr>
     * <tr><td>FKeyTable</td><td>是外键时的外键表</td><td>1：是 ,0:不是</td></tr>
     * </table>
     *
     * @param tableName 数据库表名
     * @return String
     */
    public abstract String genTableSchemaSql(String tableName);

    /**
     * 取得所有表的架构信息，然后如下列的字符串
     *  <table border="1">
     * <tr><td> 字段名称</td><td> 字段含义</td><td> 备注</td></tr>
     * <tr><td> Col_Code</td><td> 列定义的代码</td><td> 不能空，且唯一</td></tr>
     * <tr><td>Col_Code</td><td>列定义的代码</td><td>不能空，且唯一</td></tr>
     * <tr><td>Col_Name</td><td>列名称</td><td>列的中文名称，如果不存在就等同Col_Code</td></tr>
     * <tr><td>Col_Comment</td><td>列备注</td><td>列的中文名称，如果不存在就等同Col_name</td></tr>
     * <tr><td>Col_Description</td><td>列详细说明</td><td>列详细说明，如果不存在就等同Col_Comment</td></tr>
     * <tr><td>Col_Type</td><td>字段类型</td><td>不包括长度及括号</td></tr>
     * <tr><td>Col_Len</td><td>长度</td><td></td></tr>
     * <tr><td>Col_Prec</td><td>精度</td><td>包括小数位</td></tr>
     * <tr><td>Col_Scale</td><td>小数位</td><td></td></tr>
     * <tr><td>Is_Identity</td><td>是否自动增长</td><td>如果系统不支持，则为0，1为自动增长</td></tr>
     * <tr><td>IsNullable</td><td>是否为空</td><td>1：允许空 ,0:不允许空</td></tr>
     * <tr><td>Is_Key</td><td>是否主键</td><td>1：是 ,0:不是</td></tr>
     * <tr><td>Is_FKey</td><td>是否外键</td><td>1：是 ,0:不是</td></tr>
     * <tr><td>FKeyTable</td><td>是外键时的外键表</td><td>1：是 ,0:不是</td></tr>
     * </table>
     *
     * @param owner 架构所有者
     * @return String
     */
    public abstract String genAllTableSchemaSql(String owner);

    /**
     * 取得所有表的架构信息，然后如下列的字符串
     *  <table border="1">
     * <tr><td> 字段名称</td><td> 字段含义</td><td> 备注</td></tr>
     * <tr><td> Col_Code</td><td> 列定义的代码</td><td> 不能空，且唯一</td></tr>
     * <tr><td>Col_Code</td><td>列定义的代码</td><td>不能空，且唯一</td></tr>
     * <tr><td>Col_Name</td><td>列名称</td><td>列的中文名称，如果不存在就等同Col_Code</td></tr>
     * <tr><td>Col_Comment</td><td>列备注</td><td>列的中文名称，如果不存在就等同Col_name</td></tr>
     * <tr><td>Col_Description</td><td>列详细说明</td><td>列详细说明，如果不存在就等同Col_Comment</td></tr>
     * <tr><td>Col_Type</td><td>字段类型</td><td>不包括长度及括号</td></tr>
     * <tr><td>Col_Len</td><td>长度</td><td></td></tr>
     * <tr><td>Col_Prec</td><td>精度</td><td>包括小数位</td></tr>
     * <tr><td>Col_Scale</td><td>小数位</td><td></td></tr>
     * <tr><td>Is_Identity</td><td>是否自动增长</td><td>如果系统不支持，则为0，1为自动增长</td></tr>
     * <tr><td>IsNullable</td><td>是否为空</td><td>1：允许空 ,0:不允许空</td></tr>
     * <tr><td>Is_Key</td><td>是否主键</td><td>1：是 ,0:不是</td></tr>
     * <tr><td>Is_FKey</td><td>是否外键</td><td>1：是 ,0:不是</td></tr>
     * <tr><td>FKeyTable</td><td>是外键时的外键表</td><td>1：是 ,0:不是</td></tr>
     * </table>
     *
     * @return String
     */
    public String genAllTableSchemaSql() {
        return genAllTableSchemaSql(null);
    }

    /**
     * 取所有表的Sql语句 类似 select  tbname,tbComment  from  XX
     * 表名字段必须是tbname
     *
     * @param owner  表的架构所有者
     * @param fliter 类型过滤
     * @return
     */
    public abstract String genAllTableSql(String owner, String fliter);
}
