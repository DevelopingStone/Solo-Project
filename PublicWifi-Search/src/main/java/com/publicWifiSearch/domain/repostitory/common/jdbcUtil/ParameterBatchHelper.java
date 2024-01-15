package com.publicWifiSearch.domain.repostitory.common.jdbcUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ParameterBatchHelper {

    void setParameter(PreparedStatement preparedStatement, int index) throws SQLException;

}
