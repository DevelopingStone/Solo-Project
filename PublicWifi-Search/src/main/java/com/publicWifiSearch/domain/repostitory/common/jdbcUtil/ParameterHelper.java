package com.publicWifiSearch.domain.repostitory.common.jdbcUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ParameterHelper {

    void setParameter(PreparedStatement preparedStatement) throws SQLException;

}
