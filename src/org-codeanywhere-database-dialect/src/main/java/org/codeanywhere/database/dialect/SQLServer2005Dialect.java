package org.codeanywhere.database.dialect;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.codeanywhere.database.dialect.meta.MetaTypesBase;
import org.codeanywhere.database.dialect.meta.MsSql2005MetaTypes;
import org.codeanywhere.framework.orm.annotation.FieldMeta;

/**
 * @author lwang7212
 */
public class SQLServer2005Dialect extends Dialect {

    private String GetPaggerSqlNoOrderby(String strSql, int nStartIndex, int nEndIndex) {
        StringBuffer sb = new StringBuffer();
        sb.append(" select * from ").append(" ( ").append(" select row_number() over(order by _tc)  _rownuber, * ").append(" from (   ")
                .append(" select top    ").append(nEndIndex - 1).append(" 0 as _tc,*  ").append("  from ").append(" ( ").append(strSql)
                .append(" ) _t0  ) _t2 ) _t1 ").append(" where _rownuber >=").append(nStartIndex);
        return sb.toString();
    }

    /**
     * 取分页Sql语句
     *
     * @param strSql
     * @param parameterObject 参数对象
     * @param nStartIndex     从1开始的起始序号 （包括开始序号)
     * @param nEndIndex       从1开始的结束序号，不包括结束序号
     * @return
     */
    public String getPageSQL(String strSql, Object parameterObject, int nStartIndex, int nEndIndex) {
        String strRtn = Pattern.compile("select", Pattern.CASE_INSENSITIVE).matcher(strSql).replaceFirst("select TOP (100) PERCENT ");
        // 获取order by
        Pattern p = Pattern.compile("order\\s*?by.*$", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        Matcher m = p.matcher(strRtn);
        if (m.find()) {
            String strOrderby = m.group();
            // \S*?\.
            // 替换掉别名
            strOrderby = Pattern.compile("\\S*?\\.", Pattern.CASE_INSENSITIVE).matcher(strOrderby).replaceAll(" ");
            String sqlnew = Pattern.compile("order\\s*?by.*$", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE).matcher(strSql)
                    .replaceAll(" ");
            sqlnew = GetPaggerSqlNoOrderby(sqlnew, nStartIndex, nEndIndex);
            return Pattern.compile("\\)\\s*?_t0", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE).matcher(sqlnew)
                    .replaceAll(")_t0 " + strOrderby);
        } else {
            return GetPaggerSqlNoOrderby(strSql, nStartIndex, nEndIndex);
        }
    }

    @Override
    public String getTableCommentSql(String TableName, String comment) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getColumnComment(String TableName, String ColName, String comment) {
        StringBuilder sb = new StringBuilder();
        sb.append("if exists(SELECT   *	FROM   ::fn_listextendedproperty (NULL, 'user', 'dbo', 'table','" + TableName
                + "', 'column', default)");
        sb.append(" where name='MS_Description' and objname='" + ColName + "' and objtype='COLUMN')	");
        sb.append(" EXECUTE sp_updateextendedproperty N'MS_Description', N'" + comment + "', N'user', N'dbo', N'table', N'" + TableName
                + "', N'column', N'" + ColName + "'");
        sb.append(" else ");
        sb.append(" EXECUTE sp_addextendedproperty N'MS_Description', N'" + comment + "', N'user', N'dbo', N'table', N'" + TableName
                + "', N'column', N'" + ColName + "' ");

        return sb.toString();
    }

    @Override
    public String getVersionSql() {
        return "select @@version [version]";
    }

    @Override
    public String getExistTableSql(String TableName) {

        return String.format(
                "select 1 from SysObjects where xtype='U'   and lower([name])='%1$s'",
                TableName.toLowerCase());
    }

    @Override
    protected String getOpenQuote() {

        return "[";
    }

    @Override
    protected String getCloseQuote() {

        return "]";
    }

    @Override
    protected boolean getSupportsIdentityColumns(FieldMeta p_field) {
        if (p_field.IsForeign()) {
            return false;
        }
        return p_field.Identity();
    }

    @Override
    protected String getIdentityColumnString() {

        return "IDENTITY NOT NULL";
    }

    @Override
    protected MetaTypesBase getMetaTypes() {
        // TODO Auto-generated method stub
        return new MsSql2005MetaTypes();
    }

    @Override
    public String getDbType() {

        return "SqlServer";
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
    @Override
    public String genTableSchemaSql(String tableName) {
        String strSql = "select \n" +
                "c.COLUMN_NAME as Col_Code,\n" +
                "  c.COLUMN_NAME as Col_name \n" +
                "  ,c.DATA_TYPE as Col_type\n" +
                "  ,sc.max_length as Col_len ----最大物理存储长度。\n" +
                "  ,c.NUMERIC_PRECISION  as  Col_Prec--此列的精度级别。\n" +
                "  ,c.NUMERIC_SCALE  as Col_scale,--此列的小数位数 \n" +
                "  isnull((SELECT  distinct value AS [Extended Property]\n" +
                "      FROM sys.extended_properties AS ep ,sys.tables AS t ,sys.columns AS cl\n" +
                "      WHERE class = 1 and ep.major_id = object_id(c.TABLE_NAME) \n" +
                "      and ep.major_id = cl.object_id  and  cl.object_id =object_id(c.TABLE_NAME)\n" +
                "      AND ep.minor_id = cl.column_id   and cl.name= c.COLUMN_NAME )  \n" +
                "      ,c.COLUMN_NAME ) as Col_Comment,\n" +
                "  isnull((SELECT  distinct value AS [Extended Property]\n" +
                "      FROM sys.extended_properties AS ep ,sys.tables AS t ,sys.columns AS cl\n" +
                "      WHERE class = 1 and ep.major_id = object_id(c.TABLE_NAME) \n" +
                "      and ep.major_id = cl.object_id  and  cl.object_id =object_id(c.TABLE_NAME)\n" +
                "      AND ep.minor_id = cl.column_id   and cl.name= c.COLUMN_NAME )  \n" +
                "      ,c.COLUMN_NAME ) as Col_Description,\n" +
                "  sc.is_Identity \n" +
                "  , \n" +
                "    '' as StereoType\n" +
                "    ,\n" +
                "  case c.IS_NULLABLE \n" +
                "    when 'YES' then 1\n" +
                "    when 'NO' then 0\n" +
                "  end as isnullable\n" +
                "  ,\n" +
                "  case when  c.COLUMN_NAME in\n" +
                "  (select COLUMN_NAME from INFORMATION_SCHEMA.KEY_COLUMN_USAGE  A \n" +
                "    join sys.objects B on  B.Name=A.CONSTRAINT_NAME \n" +
                "  where A.Table_Name =c.TABLE_NAME and b.type='PK')\n" +
                "  then 1\n" +
                "  else 0\n" +
                "  end as Is_key \n" +
                "  ,\n" +
                "  case when  c.COLUMN_NAME in\n" +
                "  (select COLUMN_NAME from INFORMATION_SCHEMA.KEY_COLUMN_USAGE  A \n" +
                "    join sys.objects B on  B.Name=A.CONSTRAINT_NAME \n" +
                "  where A.Table_Name =c.TABLE_NAME and b.type='F')\n" +
                "  then c.COLUMN_NAME\n" +
                "  else NULL\n" +
                "  end as fKey                \n" +
                "  ,\n" +
                "   (select object_name(referenced_object_id) from sys.foreign_key_columns fk\n" +
                "  join sys.columns  \n" +
                "  on  sys.columns.object_Id=fk.parent_object_id and sys.columns.Column_Id=fk.parent_column_id\n" +
                "  where fk.parent_object_id=object_id(c.TABLE_NAME) and sys.columns.name=c.COLUMN_NAME)\n" +
                "  as fKeyTable\n" +
                "  ,\n" +
                "  case when  c.COLUMN_NAME in\n" +
                "  (select COLUMN_NAME from INFORMATION_SCHEMA.KEY_COLUMN_USAGE  A \n" +
                "    join sys.objects B on  B.Name=A.CONSTRAINT_NAME \n" +
                "  where A.Table_Name =c.TABLE_NAME and b.type='F')\n" +
                "  then 1\n" +
                "  else 0\n" +
                "  end as is_fKey\n" +
                "from INFORMATION_SCHEMA.COLUMNS c \n" +
                "  left join  sys.columns  sc on sc.object_id=object_id(c.TABLE_NAME) and sc.name=c.COLUMN_NAME\n" +
                "  where c.Table_Name='%s'\n" +
                "  order by c.ORDINAL_POSITION ";

        String strTableName = tableName.replaceAll("'", "''");
        return String.format(strSql, strTableName);

    }

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
    @Override
    public String genAllTableSchemaSql(String owner) {
        return "select \n" +
                "c.COLUMN_NAME as Col_Code,\n" +
                "  c.COLUMN_NAME as Col_name \n" +
                "  ,c.DATA_TYPE as Col_type\n" +
                "  ,sc.max_length as Col_len ----最大物理存储长度。\n" +
                "  ,c.NUMERIC_PRECISION  as  Col_Prec--此列的精度级别。\n" +
                "  ,c.NUMERIC_SCALE  as Col_scale,--此列的小数位数 \n" +
                "  isnull((SELECT  distinct value AS [Extended Property]\n" +
                "      FROM sys.extended_properties AS ep ,sys.tables AS t ,sys.columns AS cl\n" +
                "      WHERE class = 1 and ep.major_id = object_id(c.TABLE_NAME) \n" +
                "      and ep.major_id = cl.object_id  and  cl.object_id =object_id(c.TABLE_NAME)\n" +
                "      AND ep.minor_id = cl.column_id   and cl.name= c.COLUMN_NAME )  \n" +
                "      ,c.COLUMN_NAME ) as Col_Comment,\n" +
                "  isnull((SELECT  distinct value AS [Extended Property]\n" +
                "      FROM sys.extended_properties AS ep ,sys.tables AS t ,sys.columns AS cl\n" +
                "      WHERE class = 1 and ep.major_id = object_id(c.TABLE_NAME) \n" +
                "      and ep.major_id = cl.object_id  and  cl.object_id =object_id(c.TABLE_NAME)\n" +
                "      AND ep.minor_id = cl.column_id   and cl.name= c.COLUMN_NAME )  \n" +
                "      ,c.COLUMN_NAME ) as Col_Description,\n" +
                "  sc.is_Identity \n" +
                "  , \n" +
                "    '' as StereoType\n" +
                "    ,\n" +
                "  case c.IS_NULLABLE \n" +
                "    when 'YES' then 1\n" +
                "    when 'NO' then 0\n" +
                "  end as isnullable\n" +
                "  ,\n" +
                "  case when  c.COLUMN_NAME in\n" +
                "  (select COLUMN_NAME from INFORMATION_SCHEMA.KEY_COLUMN_USAGE  A \n" +
                "    join sys.objects B on  B.Name=A.CONSTRAINT_NAME \n" +
                "  where A.Table_Name =c.TABLE_NAME and b.type='PK')\n" +
                "  then 1\n" +
                "  else 0\n" +
                "  end as Is_key \n" +
                "  ,\n" +
                "  case when  c.COLUMN_NAME in\n" +
                "  (select COLUMN_NAME from INFORMATION_SCHEMA.KEY_COLUMN_USAGE  A \n" +
                "    join sys.objects B on  B.Name=A.CONSTRAINT_NAME \n" +
                "  where A.Table_Name =c.TABLE_NAME and b.type='F')\n" +
                "  then c.COLUMN_NAME\n" +
                "  else NULL\n" +
                "  end as fKey                \n" +
                "  ,\n" +
                "   (select object_name(referenced_object_id) from sys.foreign_key_columns fk\n" +
                "  join sys.columns  \n" +
                "  on  sys.columns.object_Id=fk.parent_object_id and sys.columns.Column_Id=fk.parent_column_id\n" +
                "  where fk.parent_object_id=object_id(c.TABLE_NAME) and sys.columns.name=c.COLUMN_NAME)\n" +
                "  as fKeyTable\n" +
                "  ,\n" +
                "  case when  c.COLUMN_NAME in\n" +
                "  (select COLUMN_NAME from INFORMATION_SCHEMA.KEY_COLUMN_USAGE  A \n" +
                "    join sys.objects B on  B.Name=A.CONSTRAINT_NAME \n" +
                "  where A.Table_Name =c.TABLE_NAME and b.type='F')\n" +
                "  then 1\n" +
                "  else 0\n" +
                "  end as is_fKey\n" +
                "from INFORMATION_SCHEMA.COLUMNS c \n" +
                "  left join  sys.columns  sc on sc.object_id=object_id(c.TABLE_NAME) and sc.name=c.COLUMN_NAME  \n" +
                "  order by c.Table_Name,c.ORDINAL_POSITION ";
    }

    /**
     * 取所有表的Sql语句 类似 select  tbname,tbComment  from  XX
     * 表名字段必须是tbname
     *
     * @param owner  表的架构所有者
     * @param fliter 类型过滤
     * @return
     */
    @Override
    public String genAllTableSql(String owner, String fliter) {
        if (StringUtils.isEmpty(fliter)) {
            return "select [ name ] tbname, [ name ] tbComment\n" +
                    "  from SysObjects\n" +
                    " where xtype = 'U'\n" +
                    "   and [ name ] <> 'dtproperties'\n" +
                    " order by [ name ]";

        } else {
            return String.format("select [ name ] tbname, [ name ] tbComment\n" +
                    "  from SysObjects\n" +
                    " where xtype = 'U'\n" +
                    "   and [ name ] <> 'dtproperties'\n" +
                    "   and %s\n" +
                    " order by [ name ]", fliter.replaceAll("tbname", " [name]"));

        }
    }

    /**
     * 获取批量插入表达式
     *
     * @return
     */
    @Override
    public String getBatchInsertStatment() {
        return "codeanywhere.framework.orm.common.ForSqlServerSqlite_InsertDataTable";
    }

    @Override
    public String getBatchInsertSql(String sql) {
        // 1 from dual 替换掉
        Pattern p = Pattern.compile("from\\s*?dual", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
        Matcher m = p.matcher(sql);
        if (m.find()) {

            sql = m.replaceAll("");

        }

        // 替换别名
        p = Pattern.compile("~", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
        m = p.matcher(sql);
        if (m.find()) {

            sql = m.replaceAll("");

        }
        return sql;

    }
}
