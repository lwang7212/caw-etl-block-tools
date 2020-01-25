package org.codeanywhere.database.dialect;

import org.apache.commons.lang.StringUtils;
import org.codeanywhere.database.dialect.meta.MetaTypesBase;
import org.codeanywhere.database.dialect.meta.Oracle9MetaTypes;
import org.codeanywhere.framework.orm.annotation.FieldMeta;

/**
 * @author badqiu
 * @author miemiedev
 */
public class OracleDialect extends Dialect {

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

        StringBuffer sb = new StringBuffer();
        sb.append("select * from ( ")
                .append("select a_.*, rownum row_num ")
                .append("from (  ")
                .append(strSql)
                .append(" ) a_) b_  ")
                .append(" where b_.row_num >=")
                .append(nStartIndex)
                .append(" and  b_.row_num<")
                .append(nEndIndex);
        return sb.toString();
    }

    @Override
    protected String FliterOrderBy(String strSql) {
        return strSql;
    }

    /**
     * 取得对应数据库版本号
     */
    public String getVersionSql() {
        return "select banner version  from sys.v_$version";
    }

    @Override
    public String getTableCommentSql(String TableName, String comment) {
        return String.format("COMMENT ON TABLE %1$s IS '%2$s'", TableName, comment);
    }

    @Override
    public String getColumnComment(String TableName, String ColName, String comment) {
        return String.format("COMMENT ON COLUMN %1$s.%2$s IS '%3$s'", TableName,
                ColName, comment);

    }

    @Override
    public String getExistTableSql(String TableName) {

        return String.format("select 1 from sys.tab where tabType='TABLE' and tname='%1$s'",
                TableName.toUpperCase());
    }

    @Override
    protected String getOpenQuote() {
        // TODO Auto-generated method stub
        //return "\"";
        return null;
    }

    @Override
    protected String getCloseQuote() {

        //return "\"";
        return null;
    }

    /**
     * 取带左右标识的标识符
     *
     * @param identifierName
     * @return
     */
    public String quote(String identifierName) {
        if (getOpenQuote() == null) {
            return identifierName.toUpperCase();
        }
        String quotedName = identifierName.replaceAll(getOpenQuote(), getOpenQuote() + getOpenQuote());
        if (getOpenQuote() != getCloseQuote()) {
            quotedName = identifierName.replaceAll(getCloseQuote(), getCloseQuote() + getCloseQuote());
        }

        return getOpenQuote() + quotedName.toUpperCase() + getCloseQuote();
    }

    @Override
    protected boolean getSupportsIdentityColumns(FieldMeta p_field) {
        // TODO  不支持
        return false;
    }

    @Override
    protected String getIdentityColumnString() {
        // TODO  不支持
        return "";
    }

    @Override
    protected MetaTypesBase getMetaTypes() {
        // TODO Auto-generated method stub
        return new Oracle9MetaTypes();
    }

    /**
     * 数据库类型名称
     * Mysql | PostgreSQL | SQLite | H2Db  | GBase  | Oracle | SqlServer
     *
     * @return
     */
    @Override
    public String getDbType() {

        return "Oracle";
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
        if (tableName.contains(".")) {
            String[] str = tableName.split(".");
            String owner = str[0];
            String shortName = str[1];
            return String.format("select a.OWNER,\n" +
                    "       a.table_name,\n" +
                    "       a.Column_name as Col_Code,\n" +
                    "       a.Column_name as Col_Name,\n" +
                    "       a.Data_Type as Col_Type,\n" +
                    "       b.comments as Col_Comment,\n" +
                    "       b.comments as Col_Description,\n" +
                    "       a.Data_length as Col_Len,\n" +
                    "       a.DATA_PRECISION as Col_Prec,\n" +
                    "       a.DATA_SCALE as Col_Scale,\n" +
                    "       a.Nullable as IsNullable,\n" +
                    "       (select count(*)\n" +
                    "          from Sys.All_trigger_cols tc, Sys.All_dependencies ud\n" +
                    "         where tc.trigger_name = ud.name\n" +
                    "           and ud.Referenced_type = 'SEQUENCE'\n" +
                    "           and tc.table_name = a.table_name\n" +
                    "           and tc.column_name = a.Column_name\n" +
                    "           and rownum < 2) as Is_Identity,\n" +
                    "       '' as StereoType,\n" +
                    "       (select count(*)\n" +
                    "          from Sys.All_constraints aa, Sys.All_cons_columns bb\n" +
                    "         where aa.constraint_name = bb.constraint_name\n" +
                    "           and aa.table_name = a.table_name\n" +
                    "           and bb.column_name = a.Column_name\n" +
                    "           and constraint_type = 'P') as Is_Key,\n" +
                    "       (select count(*)\n" +
                    "          from Sys.All_constraints aa, Sys.All_cons_columns bb\n" +
                    "         where aa.constraint_name = bb.constraint_name\n" +
                    "           and aa.table_name = a.table_name\n" +
                    "           and bb.column_name = a.Column_name\n" +
                    "           and constraint_type = 'R') as Is_FKey,\n" +
                    "       null fKeyTable\n" +
                    "  from SYS.All_TAB_COLUMNS a, Sys.All_col_comments b\n" +
                    " where a.OWNER = b.OWNER\n" +
                    "   and a.table_name = b.table_name\n" +
                    "   and a.column_name = b.column_name\n" +
                    "   and a.OWNER = UPPER('%s')\n" +
                    "   and a.table_name = UPPER('%s')\n" +
                    " order by a.column_Id", owner, shortName);
        } else {
            return String.format("select a.OWNER,\n" +
                    "       a.table_name,\n" +
                    "       a.Column_name as Col_Code,\n" +
                    "       a.Column_name as Col_Name,\n" +
                    "       a.Data_Type as Col_Type,\n" +
                    "       b.comments as Col_Comment,\n" +
                    "       b.comments as Col_Description,\n" +
                    "       a.Data_length as Col_Len,\n" +
                    "       a.DATA_PRECISION as Col_Prec,\n" +
                    "       a.DATA_SCALE as Col_Scale,\n" +
                    "       a.Nullable as isnullable,\n" +
                    "       (select count(*)\n" +
                    "          from user_trigger_cols tc, user_dependencies ud\n" +
                    "         where tc.trigger_name = ud.name\n" +
                    "           and ud.Referenced_type = 'SEQUENCE'\n" +
                    "           and tc.table_name = a.table_name\n" +
                    "           and tc.column_name = a.Column_name\n" +
                    "           and rownum < 2) as Is_Identity,\n" +
                    "       '' as StereoType,\n" +
                    "       (select count(*)\n" +
                    "          from user_constraints aa, user_cons_columns bb\n" +
                    "         where aa.table_name = bb.table_name\n" +
                    "           and aa.constraint_name = bb.constraint_name\n" +
                    "           and aa.table_name = a.table_name\n" +
                    "           and bb.column_name = a.Column_name\n" +
                    "           and constraint_type = 'P') as Is_Key,\n" +
                    "       \n" +
                    "       (select count(*)\n" +
                    "          from user_constraints aa, user_cons_columns bb\n" +
                    "         where aa.constraint_name = bb.constraint_name\n" +
                    "           and aa.table_name = a.table_name\n" +
                    "           and bb.column_name = a.Column_name\n" +
                    "           and constraint_type = 'R') as Is_FKey,\n" +
                    "       null fKeyTable\n" +
                    "  from USER_TAB_COLUMNS a, user_col_comments b\n" +
                    " where a.table_name = b.table_name\n" +
                    "   and a.column_name = b.column_name\n" +
                    "   and a.table_name = UPPER('%s')\n" +
                    " order by a.table_name, a.column_Id", tableName);
        }


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
     * @param owner
     * @return String
     */
    @Override
    public String genAllTableSchemaSql(String owner) {
        if (StringUtils.isEmpty(owner)) {
            return "select a.OWNER,\n" +
                    "       a.table_name,\n" +
                    "       a.Column_name as Col_Code,\n" +
                    "       a.Column_name as Col_Name,\n" +
                    "       a.Data_Type as Col_Type,\n" +
                    "       b.comments as Col_Comment,\n" +
                    "       b.comments as Col_Description,\n" +
                    "       a.Data_length as Col_Len,\n" +
                    "       a.DATA_PRECISION as Col_Prec,\n" +
                    "       a.DATA_SCALE as Col_Scale,\n" +
                    "       a.Nullable as isnullable,\n" +
                    "       (select count(*)\n" +
                    "          from user_trigger_cols tc, user_dependencies ud\n" +
                    "         where tc.trigger_name = ud.name\n" +
                    "           and ud.Referenced_type = 'SEQUENCE'\n" +
                    "           and tc.table_name = a.table_name\n" +
                    "           and tc.column_name = a.Column_name\n" +
                    "           and rownum < 2) as Is_Identity,\n" +
                    "       '' as StereoType,\n" +
                    "       (select count(*)\n" +
                    "          from user_constraints aa, user_cons_columns bb\n" +
                    "         where aa.table_name = bb.table_name\n" +
                    "           and aa.constraint_name = bb.constraint_name\n" +
                    "           and aa.table_name = a.table_name\n" +
                    "           and bb.column_name = a.Column_name\n" +
                    "           and constraint_type = 'P') as Is_Key,\n" +
                    "       \n" +
                    "       (select count(*)\n" +
                    "          from user_constraints aa, user_cons_columns bb\n" +
                    "         where aa.constraint_name = bb.constraint_name\n" +
                    "           and aa.table_name = a.table_name\n" +
                    "           and bb.column_name = a.Column_name\n" +
                    "           and constraint_type = 'R') as Is_FKey,\n" +
                    "       null fKeyTable\n" +
                    "  from USER_TAB_COLUMNS a, user_col_comments b\n" +
                    " where a.table_name = b.table_name\n" +
                    "   and a.column_name = b.column_name   \n" +
                    " order by a.table_name, a.column_Id";
        } else {
            return String.format("select a.OWNER,\n" +
                    "       a.table_name,\n" +
                    "       a.Column_name as Col_Code,\n" +
                    "       a.Column_name as Col_Name,\n" +
                    "       a.Data_Type as Col_Type,\n" +
                    "       b.comments as Col_Comment,\n" +
                    "       b.comments as Col_Description,\n" +
                    "       a.Data_length as Col_Len,\n" +
                    "       a.DATA_PRECISION as Col_Prec,\n" +
                    "       a.DATA_SCALE as Col_Scale,\n" +
                    "       a.Nullable as IsNullable,\n" +
                    "       (select count(*)\n" +
                    "          from Sys.All_trigger_cols tc, Sys.All_dependencies ud\n" +
                    "         where tc.trigger_name = ud.name\n" +
                    "           and ud.Referenced_type = 'SEQUENCE'\n" +
                    "           and tc.table_name = a.table_name\n" +
                    "           and tc.column_name = a.Column_name\n" +
                    "           and rownum < 2) as Is_Identity,\n" +
                    "       '' as StereoType,\n" +
                    "       (select count(*)\n" +
                    "          from Sys.All_constraints aa, Sys.All_cons_columns bb\n" +
                    "         where aa.constraint_name = bb.constraint_name\n" +
                    "           and aa.table_name = a.table_name\n" +
                    "           and bb.column_name = a.Column_name\n" +
                    "           and constraint_type = 'P') as Is_Key,\n" +
                    "       (select count(*)\n" +
                    "          from Sys.All_constraints aa, Sys.All_cons_columns bb\n" +
                    "         where aa.constraint_name = bb.constraint_name\n" +
                    "           and aa.table_name = a.table_name\n" +
                    "           and bb.column_name = a.Column_name\n" +
                    "           and constraint_type = 'R') as Is_FKey,\n" +
                    "       null fKeyTable\n" +
                    "  from SYS.All_TAB_COLUMNS a, Sys.All_col_comments b\n" +
                    " where a.OWNER = b.OWNER\n" +
                    "   and a.table_name = b.table_name\n" +
                    "   and a.column_name = b.column_name\n" +
                    "   and a.OWNER = UPPER('%s')   \n" +
                    " order by a.table_name,a.column_Id", owner);
        }

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
        if (StringUtils.isEmpty(owner)) {
            if (StringUtils.isEmpty(fliter)) {
                return "select a.table_name tbname ,b.comments  tbComment \n" +
                        "        from user_tables a ,user_tab_comments b\n" +
                        "        where a.table_name=b.table_name\n" +
                        "        order by a.table_name";

            } else {
                return String.format("select a.table_name tbname, b.comments tbComment\n" +
                        "  from user_tables a, user_tab_comments b\n" +
                        " where a.table_name = b.table_name\n" +
                        "   and {0}\n" +
                        " order by a.table_name", fliter.replaceAll("tbname", "a.table_name"));

            }
        } else {
            if (StringUtils.isEmpty(fliter)) {
                return String.format(" select a.table_name tbname, b.comments tbComment\n" +
                        "   from Sys.All_tables a, Sys.All_tab_comments b\n" +
                        "  where a.owner = Upper('%s')   \n" +
                        "    and a.owner=b.owner\n" +
                        "    and a.table_name = b.table_name\n" +
                        "  order by a.table_name", owner);

            } else {
                return String.format(" select a.table_name tbname, b.comments tbComment\n" +
                        "   from Sys.All_tables a, Sys.All_tab_comments b\n" +
                        "  where a.owner = Upper('%s')   \n" +
                        "    and a.owner=b.owner\n" +
                        "    and a.table_name = b.table_name\n" +
                        "    and %s\n" +
                        "  order by a.table_name", owner,
                        fliter.replaceAll("tbname", "a.table_name"));

            }
        }
    }

    /**
     * 获取批量插入表达式
     *
     * @return
     */
    @Override
    public String getBatchInsertStatment() {
        return "codeanywhere.framework.orm.common.ForOracle_InsertDataTable";
    }
}


