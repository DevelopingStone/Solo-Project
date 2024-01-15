package com.publicWifiSearch.domain.repostitory.publicWifi;

import com.publicWifiSearch.domain.model.publicWifi.PublicWifi;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.DbConnectionMaker;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.SqliteConnectionMaker;
import com.publicWifiSearch.domain.repostitory.publicWifi.publicWifi.PublicWifiRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PublicWifiRepositoryTest {
    private static final DbConnectionMaker dbConnectionMaker = new SqliteConnectionMaker();
    private static Connection connection;
    @BeforeEach
    public void getConnection(){
        connection = dbConnectionMaker.makeConnection();
    }
    @AfterEach
    public void closeConnection(){
        dbConnectionMaker.closeConnection();
    }
    @Test
    public void installation_테이블의_데이터_수는_전체_데이터_수와_같다() throws SQLException {
        PublicWifiRepository publicWifiRepository = new PublicWifiRepository();
        publicWifiRepository.connectDataBaseWith(connection);
        int totalCount = 23304;

        // when
        List<PublicWifi> publicWifiBundle = publicWifiRepository.findAll();
        closeConnection();
        // then
        assertEquals(publicWifiBundle.size(), totalCount);
    }
}