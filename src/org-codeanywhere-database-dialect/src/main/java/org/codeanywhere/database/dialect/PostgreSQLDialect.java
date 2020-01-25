package org.codeanywhere.database.dialect;

/**
 * PostgreSql
 */
public class PostgreSQLDialect extends  OracleDialect {
    /**
     * 数据库类型名称
     * Mysql | PostgreSQL | SQLite | H2Db  | GBase  | Oracle | SqlServer
     *
     * @return
     */
    @Override
    public String getDbType() {
        return "PostgreSQL";
    }
}
