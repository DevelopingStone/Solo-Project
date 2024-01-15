package com.publicWifiSearch.domain.repostitory.common.jdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlStatement {
    PreparedStatement makePreparedStatement(Connection connection) throws SQLException;
}
