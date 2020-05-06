package org.runecraft.runecore.db;

import org.runecraft.runecore.RuneCore;
import org.runecraft.runecore.db.enums.DatabaseOperation;
import org.runecraft.runecore.db.enums.Table;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.sql.SqlService;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class DataBase {

    private static SqlService sql;
    private static Connection connection;
    private static String url = "jdbc:mysql://127.0.0.1:3306/runecraft?user=root&password=test";

    public static DataSource getDataSource(String jdbcConnection) throws SQLException {
        if (sql == null) {
            sql = Sponge.getServiceManager().provide(SqlService.class).get();
        }
        return sql.getDataSource(jdbcConnection);
    }

    public static void connect() throws SQLException {
        if(connection == null || connection.isClosed()){
            connection = getDataSource(url).getConnection();
        }
    }

    public static Connection getConnection() throws SQLException {
        if(connection == null || connection.isClosed()){
            return getDataSource(url).getConnection();
        }else{
            return connection;
        }
    }

    public static void checkForTables(){
        for(Table table : Table.values()) {
            if(!tableExist(table.getName())) {
                try {
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate(table.getBuildString(DatabaseOperation.CREATE));
                    System.out.println("One missing table in Database (" + table.getName() + ") was created successfully.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean tableExist(String table) {
        try {
            ResultSet rs = connection.getMetaData().getTables(null, null, table, null);
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
