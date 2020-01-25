package org.codeanywhere.database.dialect;

import org.codeanywhere.database.dialect.meta.H2MetaTypes;
import org.codeanywhere.database.dialect.meta.MetaTypesBase;
import org.codeanywhere.framework.orm.annotation.FieldMeta;

/**
 * A dialect compatible with the H2 database.
 * 
 * @author lwang7212
 * @author 
 */
public class H2Dialect extends MySQLDialect
{

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

		StringBuffer sb = new StringBuffer();
		sb.append("select * from ( ");
		processOrderby(strSql, parameterObject, sb);
		sb.append(" ) t").append(" limit ").append(nStartIndex - 1).append(",").append(nEndIndex - nStartIndex);
		return sb.toString();
	}
	

	@Override
	public String getTableCommentSql(String TableName, String comment)
	{
		return String.format("COMMENT ON TABLE %1$s IS '%2$s'", TableName, comment);
		
	}
	@Override
	public String getColumnComment(String TableName, String ColName, String comment)
	{
		 return String.format("COMMENT ON COLUMN %1$s.%2$s IS '%3$s'", TableName,
				 ColName, comment);
		
	}

	@Override
	public String getVersionSql()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getExistTableSql(String TableName)
	{
		return "select 1 from INFORMATION_SCHEMA.TABLES  where TABLE_type ='TABLE' and TABLE_NAME='"+TableName+"'";
	}

	@Override
	protected String getOpenQuote()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getCloseQuote()
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected boolean getSupportsIdentityColumns(FieldMeta p_field)
	{
		// TODO  不支持 
		return false;
	}


	@Override
	protected String getIdentityColumnString()
	{
		// TODO  不支持 
		return "";
	}

	@Override
	protected MetaTypesBase getMetaTypes()
	{
		// TODO Auto-generated method stub
		return new H2MetaTypes();
	}
	/**
	 * 数据库类型名称
	 * Mysql | PostgreSQL | SQLite | H2Db  | GBase  | Oracle | SqlServer
	 *
	 * @return
	 */
	@Override
	public String getDbType() {
		return "H2Db";
	}
}