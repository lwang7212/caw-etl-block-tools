package org.codeanywhere.database.dialect;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codeanywhere.database.dialect.meta.MetaTypesBase;
import org.codeanywhere.database.dialect.meta.MySQLMetaTypes;
import org.codeanywhere.framework.orm.annotation.FieldMeta;

/**
 * @author lwang7212
  * mysql 配置
	mysql 表名默认 windows不区分大小写 在linux区分大小写
	MySQL在Linux下数据库名、表名、列名、别名大小写规则：
	　　 1、数据库名与表名是严格区分大小写
	　　 2、表的别名是严格区分大小写
	　　 3、列名与列的别名在所有的情况下均是忽略大小写的
	　　 4、变量名也是严格区分大小写的
	用root帐号登录，在/etc/my.cnf 或 /etc/mysql/my.cnf 中的[mysqld]后
	添加添加lower_case_table_names=1，重启MySQL服务，
	若设置成功，则不再区分表名的大小写，前提是表名必须是小写（自动将表名转换为小写
  */
public class MySQLDialect extends Dialect{

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
	public String getPageSQL(String strSql, Object parameterObject, int nStartIndex, int nEndIndex)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from (")
		.append(strSql)
		.append(") _b limit ")
		.append(nStartIndex - 1)
		.append(",")
		.append(nEndIndex - nStartIndex);
        return buffer.toString();
	}
	/**
	 * 
	 */
	@Override
	public String getTableCommentSql(String TableName, String comment)
	{
		
		// TODO 不支持表注释
		return null;
	}
	@Override
	public String getColumnComment(String TableName, String ColName, String comment)
	{
		// TODO 不支持列注释
		return null;
	}
	@Override
	public String getVersionSql()
	{
		 return "SELECT VERSION() version";
	}

	/**
	 * 获取批量插入表达式
	 * @return
	 */
	@Override
	public String getBatchInsertStatment() {
		return "codeanywhere.framework.orm.common.ForMySqlH2Db_InsertDataTable";
	}

	@Override
	public String getExistTableSql(String TableName)
	{
		
		 return String.format("show tables like '%1$s'", TableName.trim().toLowerCase());
	}
	@Override
	protected String getOpenQuote()
	{
		
		 return "`";
	}
	@Override
	protected String getCloseQuote()
	{
		
		 return "`";
	}
	@Override
	protected boolean getSupportsIdentityColumns(FieldMeta p_field)
	{
		if(p_field.IsForeign())
		{
			return false;
		}
		return  p_field.Identity();
	}
	/**
	 * 获取创建表的名称
	 * @param p_tableName
	 * @return
	 */
	@Override
	protected Object getCreateTableString( String p_tableName)
	{

		return "create table  if not exists " + quote(p_tableName.toLowerCase());
	}

	/**
	 * 根据不同数据库规则，转换表名，默认不转换
	 * @param tableName
	 * @return
	 */
	public String warpTableName(String tableName) {
		return  quote(tableName.toLowerCase()) ;
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
		//不支持
		return null;
	}

	@Override
	protected String getIdentityColumnString()
	{
		
		return "IDENTITY NOT NULL";
	}
	@Override
	protected MetaTypesBase getMetaTypes()
	{
		// TODO Auto-generated method stub
		return new MySQLMetaTypes();
	}
	/**
	 * 修正批量处理SQL
	 * @param sql
	 * @return
	 */
	@Override
	public String getBatchInsertSql(String sql)
	{
		// my sql insert into values() 
		// ) => ) values
		//select >>(  
		// from dual )
		String rtn=sql;
		//1  ) => ) values
		Pattern p = Pattern.compile("\\)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
		Matcher m = p.matcher(rtn);
		if (m.find())
		{

			rtn=m.replaceFirst(" ) values ");

		} 
		//2 select >>(  
		p = Pattern.compile("select", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
		m = p.matcher(rtn);
		if (m.find())
		{

			rtn=m.replaceAll(" ( ");

		} 
		p = Pattern.compile("union\\s*?all", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
		m = p.matcher(rtn);
		if (m.find())
		{

			rtn=m.replaceAll(",");

		} 
		
		//from dual )
		p = Pattern.compile("from\\s*?dual", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
		m = p.matcher(rtn);
		if (m.find())
		{

			rtn=m.replaceAll(" ) ");

		} 
		
		p = Pattern.compile("null\\s*?\\w", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
		m = p.matcher(rtn);
		if (m.find())
		{

			rtn=m.replaceAll(" null ");

		} 
		//替换别名
		p = Pattern.compile("~.*?~", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
		m = p.matcher(rtn);
		if (m.find())
		{

			rtn=m.replaceAll("");

		}
		return rtn;
	}
	/**
	 * 数据库类型名称
	 * Mysql | PostgreSQL | SQLite | H2Db  | GBase  | Oracle | SqlServer
	 *
	 * @return
	 */
	@Override
	public String getDbType() {
		 
		return "Mysql";
	}
    
	
  
}
