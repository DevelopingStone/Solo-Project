package com.publicWifiSearch.domain.repostitory.publicWifi.address;

import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.address.Address;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.DbConnectionMaker;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.SqliteConnectionMaker;
import com.publicWifiSearch.domain.repostitory.publicWifi.publicWifiDetail.address.AddressRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddressRepositoryTest {
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

    @DisplayName("모든 데이터 읽기 테스트")
    @Test
    public void address_테이블의_데이터_수는_전체_데이터_수와_같다(){
        // given
        AddressRepository addressRepository = new AddressRepository();
        addressRepository.connectDataBaseWith(connection);
        int totalCount = 23304;

        // when
        List<Address> addresses = addressRepository.findAll();
        closeConnection();

        // then
        assertEquals(addresses.size(), totalCount);
    }


}