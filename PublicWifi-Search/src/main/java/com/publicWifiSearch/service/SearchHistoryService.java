package com.publicWifiSearch.service;

import com.publicWifiSearch.domain.dto.history.SearchHistorySaveDto;
import com.publicWifiSearch.domain.model.history.SearchHistory;
import com.publicWifiSearch.domain.repostitory.bookmark.bookmarkGroup.BookmarkGroupRepository;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.DbConnectionMaker;
import com.publicWifiSearch.domain.repostitory.history.SearchHistoryRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SearchHistoryService {

    private final DbConnectionMaker dbConnectionMaker;
    private Connection connection;

    public SearchHistoryService(DbConnectionMaker dbConnectionMaker) {
        this.dbConnectionMaker = dbConnectionMaker;
    }
    private void makeConnection(){
        this.connection = dbConnectionMaker.makeConnection();
    }
    private void closeConnection(){
        dbConnectionMaker.closeConnection();
    }
    public void save(SearchHistorySaveDto searchHistorySaveDto){
        SearchHistoryRepository searchHistoryRepository = new SearchHistoryRepository();

        try {
            makeConnection();
            searchHistoryRepository.connectDataBaseWith(connection);
            searchHistoryRepository.save(searchHistorySaveDto.toEntity());
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
        finally {
            closeConnection();
        }
    }

    public void deleteById(String historyId) {
        SearchHistoryRepository searchHistoryRepository = new SearchHistoryRepository();
        try {
            makeConnection();
            searchHistoryRepository.connectDataBaseWith(connection);
            searchHistoryRepository.deleteById(historyId);
        }
        catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        finally {
            closeConnection();
        }

    }
    public SearchHistory findById(String historyId) throws SQLException {
        SearchHistoryRepository searchHistoryRepository = new SearchHistoryRepository();
        searchHistoryRepository.connectDataBaseWith(connection);
        SearchHistory searchHistory = searchHistoryRepository.findById(historyId);
        closeConnection();
        return searchHistory;
    }

    public List<SearchHistory> findAll(){
        SearchHistoryRepository searchHistoryRepository = new SearchHistoryRepository();
        List<SearchHistory> searchHistoryBundle;

        makeConnection();
        searchHistoryRepository.connectDataBaseWith(connection);
        searchHistoryBundle = searchHistoryRepository.findAll();
        closeConnection();

        return searchHistoryBundle;
    }

    public void deleteAll(){
        try {
            SearchHistoryRepository searchHistoryRepository = new SearchHistoryRepository();
            makeConnection();
            searchHistoryRepository.connectDataBaseWith(connection);
            searchHistoryRepository.deleteAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }
}
