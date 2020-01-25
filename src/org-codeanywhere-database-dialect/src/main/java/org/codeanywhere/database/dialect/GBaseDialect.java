package org.codeanywhere.database.dialect;

/**
 *   Gbase数据库
 */
public class GBaseDialect extends  MySQLDialect {
    /**
     * 数据库类型名称
     * Mysql | PostgreSQL | SQLite | H2Db  | GBase  | Oracle | SqlServer
     *
     * @return
     */
    @Override
    public String getDbType() {
        return "GBase";
    }
}
