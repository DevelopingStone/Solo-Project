package com.publicWifiSearch.domain.repostitory.publicWifi.wifi;

import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.wifi.Wifi;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.DbConnectionMaker;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.SqliteConnectionMaker;
import com.publicWifiSearch.domain.repostitory.publicWifi.publicWifiDetail.wifi.WifiRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WifiRepositoryTest {
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
    public void wifi_테이블의_데이터_수는_전체_데이터_수와_같다(){
        WifiRepository wifiRepository = new WifiRepository();
        wifiRepository.connectDataBaseWith(connection);
        int totalCount = 23304;

        // when
        List<Wifi> wifi = wifiRepository.findAll();
        closeConnection();

        // then
        assertEquals(wifi.size(), totalCount);
    }
}