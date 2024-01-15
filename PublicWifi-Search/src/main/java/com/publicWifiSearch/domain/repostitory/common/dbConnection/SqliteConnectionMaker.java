package com.publicWifiSearch.domain.repostitory.common.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnectionMaker implements DbConnectionMaker {
    private Connection dbConnection = null;
    private static final String DB_CLASS = "org.sqlite.JDBC";
    private static final String DB_NAME="D:\\zerobase\\mission\\ZeroBaseMission\\PublicWifiSearch\\publicWifi.db";


    @Override
    public Connection makeConnection() {
        try {
            Class.forName(DB_CLASS);
            dbConnection = DriverManager.getConnection("jdbc:sqlite:"+ DB_NAME);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    @Override
    public void closeConnection(){
        try {
            this.dbConnection.close();
        }
        catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }

    }
}
