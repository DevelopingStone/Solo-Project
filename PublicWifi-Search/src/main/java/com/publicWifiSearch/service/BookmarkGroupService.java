package com.publicWifiSearch.service;

import com.publicWifiSearch.domain.dto.bookmark.bookmarkGroup.BookmarkGroupSaveDto;
import com.publicWifiSearch.domain.dto.bookmark.bookmarkGroup.BookmarkGroupUpdateDto;
import com.publicWifiSearch.domain.model.bookMark.BookmarkGroup;
import com.publicWifiSearch.domain.model.publicWifi.PublicWifi;
import com.publicWifiSearch.domain.repostitory.bookmark.bookmark.BookmarkRepository;
import com.publicWifiSearch.domain.repostitory.bookmark.bookmarkGroup.BookmarkGroupRepository;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.DbConnectionMaker;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookmarkGroupService {

    private final DbConnectionMaker dbConnectionMaker;
    private Connection connection;
    private final List<PublicWifi> publicWifiTotal = new ArrayList<>();
    public BookmarkGroupService(DbConnectionMaker dbConnectionMaker) {
        this.dbConnectionMaker = dbConnectionMaker;
    }

    private void makeConnection(){
        this.connection = dbConnectionMaker.makeConnection();
    }
    private void closeConnection(){
        dbConnectionMaker.closeConnection();
    }

    public void save(BookmarkGroupSaveDto bookmarkGroupSaveDto){
        BookmarkGroupRepository bookmarkGroupRepository = new BookmarkGroupRepository();

        try {
            makeConnection();
            bookmarkGroupRepository.connectDataBaseWith(connection);
            bookmarkGroupRepository.save(bookmarkGroupSaveDto.toEntity());
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
        finally {
            closeConnection();
        }
    }

    public void deleteById(String bookmarkGroupId){
        BookmarkGroupRepository bookmarkGroupRepository = new BookmarkGroupRepository();
        try {
            makeConnection();
            bookmarkGroupRepository.connectDataBaseWith(connection);
            bookmarkGroupRepository.deleteById(bookmarkGroupId);
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        finally {
            closeConnection();
        }
    }

    public List<BookmarkGroup> findAll(){
        BookmarkGroupRepository bookmarkGroupRepository = new BookmarkGroupRepository();
        List<BookmarkGroup> bookmarkGroupBundle = new ArrayList<>();
        try {
            makeConnection();
            bookmarkGroupRepository.connectDataBaseWith(connection);
            bookmarkGroupBundle = bookmarkGroupRepository.findAll();
        } finally {
            closeConnection();
        }

        return bookmarkGroupBundle;
    }

    public void updateById(BookmarkGroupUpdateDto bookmarkGroupUpdateDto){
        try {
            BookmarkGroupRepository bookmarkGroupRepository = new BookmarkGroupRepository();
            makeConnection();
            bookmarkGroupRepository.connectDataBaseWith(connection);
            bookmarkGroupRepository.updateById(bookmarkGroupUpdateDto);

        } finally {
            closeConnection();
        }
    }

    public void deleteAll(){
        try {
            BookmarkGroupRepository bookmarkGroupRepository = new BookmarkGroupRepository();
            makeConnection();
            bookmarkGroupRepository.connectDataBaseWith(connection);
            bookmarkGroupRepository.deleteAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }


    }
}
