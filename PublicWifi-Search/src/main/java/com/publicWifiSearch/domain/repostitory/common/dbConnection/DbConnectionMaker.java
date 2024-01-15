package com.publicWifiSearch.domain.repostitory.common.dbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public interface DbConnectionMaker {
    Connection makeConnection();
    void closeConnection();

}
