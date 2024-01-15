package com.publicWifiSearch.service;

import com.publicWifiSearch.domain.model.publicWifi.PublicWifi;
import com.publicWifiSearch.domain.repostitory.bookmark.bookmark.BookmarkRepository;
import com.publicWifiSearch.domain.repostitory.bookmark.bookmarkGroup.BookmarkGroupRepository;
import com.publicWifiSearch.domain.repostitory.common.Repository;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.DbConnectionMaker;
import com.publicWifiSearch.domain.repostitory.history.SearchHistoryRepository;
import com.publicWifiSearch.domain.repostitory.publicWifi.publicWifiDetail.address.AddressRepository;
import com.publicWifiSearch.domain.repostitory.publicWifi.publicWifiDetail.installation.InstallationRepository;
import com.publicWifiSearch.domain.repostitory.publicWifi.publicWifiDetail.wifi.WifiRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InitiationService {

    private final DbConnectionMaker dbConnectionMaker;
    private Connection connection;
    private final List<PublicWifi> publicWifiTotal = new ArrayList<>();
    public InitiationService(DbConnectionMaker dbConnectionMaker) {
        this.dbConnectionMaker = dbConnectionMaker;
    }

    private void makeConnection(){
        this.connection = dbConnectionMaker.makeConnection();
    }
    private void closeConnection(){
        dbConnectionMaker.closeConnection();
    }

    public <T> void initiationRepository(Repository<T> repository){
        try {
            makeConnection();
            repository.connectDataBaseWith(connection);
            repository.deleteAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }


    public void initiate(){
        try {
            initiationRepository(new BookmarkGroupRepository());
            initiationRepository(new AddressRepository());
            initiationRepository(new InstallationRepository());
            initiationRepository(new WifiRepository());
            initiationRepository(new BookmarkRepository());
            initiationRepository(new SearchHistoryRepository());
        } finally {
            closeConnection();
        }


    }
}
