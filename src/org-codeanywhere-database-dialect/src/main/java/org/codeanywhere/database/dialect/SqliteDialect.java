package org.codeanywhere.database.dialect;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import org.codeanywhere.database.dialect.meta.MetaTypesBase;
import org.codeanywhere.database.dialect.meta.SqliteMetaTypes;

import org.codeanywhere.framework.orm.annotation.FieldMeta;


public class SqliteDialect extends Dialect
{

	
	/**
	 * 获取创建表的名称
	 * @param p_tableName
	 * @return
	 */
	@Override
	protected Object getCreateTableString( String p_tableName)
	{
		// TODO Auto-generated method stub
		return "create table  if not exists " + quote(p_tableName);
	}
	@Override
	public String getExistTableSql(String TableName)
	{
		return String.format("Select 1 From [MAIN].[sqlite_master]  where type='table' and lower([name])='%1$s';", TableName.trim()
				.toLowerCase());
	}

	@Override
	public String getTableCommentSql(String TableName, String comment)
	{
		// 不支持
		return null;
	}

	@Override
	public String getColumnComment(String TableName, String ColName, String comment)
	{
		// 不支持
		return null;
	}

	@Override
	protected boolean getSupportsIdentityColumns(FieldMeta p_field)
	{
		// 不支持
		return false;
	}

	@Override
	protected String getIdentityColumnString()
	{
		// 不支持
		return null;
	}

	@Override
	protected MetaTypesBase getMetaTypes()
	{

		return new SqliteMetaTypes();
	}

	@Override
	public String getVersionSql()
	{

		return "select sqlite_version()  version";
	}

	@Override
	protected String getOpenQuote()
	{
		return "[";
	}

	@Override
	protected String getCloseQuote()
	{
		return "]";
	}

	/**
	 * 取分页Sql语句
	 * 
	 * @param strSql
	 * @param parameterObject
	 *            参数对象
	 * @param nStartIndex
	 *            从1开始的起始序号 （包括开始序号)
	 * @param nEndIndex
	 *            从1开始的结束序号，不包括结束序号
	 * @return
	 */
	@Override
	public String getPageSQL(String strSql, Object parameterObject, int nStartIndex, int nEndIndex)
	{

		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from (").append(strSql).append(") _b limit ").append(nStartIndex - 1).append(",")
				.append(nEndIndex - nStartIndex);
		return buffer.toString();
	}

	@Override
	public String getCountSQL(String strSql)
	{
		StringBuffer sb = new StringBuffer().append("select count(*) from ( ");
		sb.append(this.FliterOrderBy(strSql)).append(" )  tmpCount ");
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codeanywhere.database.dialect.dialect.Dialect#getBatchInsertSql(
	 * java.lang.String)
	 */
	@Override
	public String getBatchInsertSql(String sql)
	{
		// 1 from dual 替换掉
		Pattern p = Pattern.compile("from\\s*?dual", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
		Matcher m = p.matcher(sql);
		if (m.find())
		{

			sql = m.replaceAll("");

		}

		// 替换别名
		p = Pattern.compile("~", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
		m = p.matcher(sql);
		if (m.find())
		{

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
	@Override
	public String getDbType() {
		
		return "SQLite";
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
		//不支持
		return null;
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
		 //不支持
		return null;
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
		if (StringUtils.isEmpty(fliter))
		{
			return "select [name] tbname ,[name]  tbComment " +
					" From MAIN.[sqlite_master]  " +
					" where [type]='table' order by [name] ";

		}
		else
		{
			return String.format("Select [name] tbname ,[name]  tbComment " +
					"from MAIN.[sqlite_master] " +
					" where [type]='table' and %s " +
					" order by [name]",fliter.replaceAll("tbname","[name]"));

		}
	}

	/**
	 * 获取批量插入表达式
	 * @return
	 */
	@Override
	public String getBatchInsertStatment() {
		return "codeanywhere.framework.orm.common.ForSqlServerSqlite_InsertDataTable";
	}

}
