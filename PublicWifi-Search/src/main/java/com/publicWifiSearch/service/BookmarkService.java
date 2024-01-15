package com.publicWifiSearch.service;

import com.publicWifiSearch.domain.dto.bookmark.bookmark.BookmarkSaveDto;
import com.publicWifiSearch.domain.model.bookMark.Bookmark;
import com.publicWifiSearch.domain.model.bookMark.BookmarkGroup;
import com.publicWifiSearch.domain.model.publicWifi.PublicWifi;
import com.publicWifiSearch.domain.repostitory.bookmark.bookmark.BookmarkRepository;
import com.publicWifiSearch.domain.repostitory.bookmark.bookmarkGroup.BookmarkGroupRepository;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.DbConnectionMaker;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookmarkService {
    private final DbConnectionMaker dbConnectionMaker;
    private Connection connection;
    private final List<PublicWifi> publicWifiTotal = new ArrayList<>();
    public BookmarkService(DbConnectionMaker dbConnectionMaker) {
        this.dbConnectionMaker = dbConnectionMaker;
    }

    private void makeConnection(){
        this.connection = dbConnectionMaker.makeConnection();
    }
    private void closeConnection(){
        dbConnectionMaker.closeConnection();
    }

    public void save(BookmarkSaveDto bookmarkSaveDto){
        BookmarkRepository bookmarkRepository = new BookmarkRepository();

        try {
            makeConnection();
            bookmarkRepository.connectDataBaseWith(connection);
            bookmarkRepository.save(bookmarkSaveDto.toEntity());
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
        finally {
            closeConnection();
        }

    }

    public List<Bookmark> findAll(){
        BookmarkRepository bookmarkRepository = new BookmarkRepository();
        List<Bookmark> bookmarkGroupBundle;
        try {
            makeConnection();
            bookmarkRepository.connectDataBaseWith(connection);
            bookmarkGroupBundle = bookmarkRepository.findAll();
        } finally {
            closeConnection();
        }

        return bookmarkGroupBundle;
    }

    public void deleteById(String id){
        BookmarkRepository bookmarkRepository = new BookmarkRepository();
        try {
            makeConnection();
            bookmarkRepository.connectDataBaseWith(connection);
            bookmarkRepository.deleteById(id);
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        finally {
            closeConnection();
        }
    }
}
