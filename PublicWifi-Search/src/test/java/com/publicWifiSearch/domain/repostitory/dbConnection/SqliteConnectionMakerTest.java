package com.publicWifiSearch.domain.repostitory.dbConnection;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.DbConnectionMaker;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.SqliteConnectionMaker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SqliteConnectionMakerTest {

    @DisplayName("데이터베이스 연결 테스트")
    @Test
    public void 데이터베이스와_정상적으로_연결되면_null을_반환하지_않는다() {
        // given
        DbConnectionMaker dbConnectionMaker = new SqliteConnectionMaker();

        //when
        Connection connection = dbConnectionMaker.makeConnection();

        // then
        assertNotNull(connection);
    }

    @DisplayName("데이터베이스 종료 테스트")
    @Test
    public void 데이터베이스와_정상적으로_종료되면_null을_반환한다() throws SQLException {
        // given
        DbConnectionMaker dbConnectionMaker = new SqliteConnectionMaker();

        //when
        Connection connection = dbConnectionMaker.makeConnection();
        dbConnectionMaker.closeConnection();
        // then
        assertTrue(connection.isClosed());
    }


}