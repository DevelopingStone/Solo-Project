package com.publicWifiSearch.domain.repostitory.bookmark.bookmark;

import com.publicWifiSearch.domain.model.bookMark.Bookmark;
import com.publicWifiSearch.domain.model.bookMark.BookmarkGroup;
import com.publicWifiSearch.domain.repostitory.bookmark.queryConstant.BookmarkQuery;
import com.publicWifiSearch.domain.repostitory.common.Repository;
import com.publicWifiSearch.domain.repostitory.common.columnConstant.Column;
import com.publicWifiSearch.domain.repostitory.common.jdbcUtil.JdbcLauncher;
import com.publicWifiSearch.domain.repostitory.common.jdbcUtil.ParameterHelper;
import com.publicWifiSearch.domain.repostitory.common.jdbcUtil.SqlStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookmarkRepository extends Repository<Bookmark> {
    private JdbcLauncher jdbcLauncher;

    public void connectDataBaseWith(Connection connection) {
        jdbcLauncher = new JdbcLauncher(connection);
    }
    @Override
    public void save(Bookmark bookmark) throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(BookmarkQuery.SAVE_QUERY);
        ParameterHelper parameterHelper = (preparedStatement) -> {
            preparedStatement.setInt(Column.FIRST.getPosition(), bookmark.getBookMarkId());
            preparedStatement.setInt(Column.SECOND.getPosition(),bookmark.getBookmarkGroupId());
            preparedStatement.setString(Column.THIRD.getPosition(), bookmark.getWifiName());
            preparedStatement.setObject(Column.FOURTH.getPosition(), bookmark.getEnrollmentDate());
        };
        this.jdbcLauncher.executeUpdateWithPreparedStatement(sqlStatement, parameterHelper);
    }

    @Override
    public void deleteById(String id) throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(BookmarkQuery.DELETE_BY_ID_QUERY);
        ParameterHelper parameterHelper = (preparedStatement) -> {
            preparedStatement.setInt(Column.FIRST.getPosition(), Integer.parseInt(id));
        };
        this.jdbcLauncher.executeUpdateWithPreparedStatement(sqlStatement, parameterHelper);
    }

    @Override
    public void deleteAll() throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(BookmarkQuery.DELETE_ALL_QUERY);
        this.jdbcLauncher.executeUpdateWithPreparedStatement(sqlStatement, null);
    }
    @Override
    public Bookmark transformToEntity(ResultSet resultSet) throws SQLException {
        return new Bookmark(
                resultSet.getInt(Column.FIRST.getPosition()),
                resultSet.getString(Column.SECOND.getPosition()),
                resultSet.getString(Column.THIRD.getPosition()),
                resultSet.getString(Column.FOURTH.getPosition()));
    }
    @Override
    public List<Bookmark> transformToEntityBundle(ResultSet resultSet) throws SQLException {
        List<Bookmark> bookmarkBundle = new ArrayList<>();
        if(resultSet != null){
            while (resultSet.next()){
                bookmarkBundle.add(transformToEntity(resultSet));
            }
        }
        return bookmarkBundle;
    }

    @Override
    public List<Bookmark> findAll() {
        List<Bookmark> bookmarkGroupBundle;
        try {
            ResultSet resultSet = jdbcLauncher.executeQueryWithPreparedStatement(
                    connection -> connection.prepareStatement(BookmarkQuery.SELECT_ALL_QUERY));
            bookmarkGroupBundle = transformToEntityBundle(resultSet);
        }
        catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return bookmarkGroupBundle;
    }
}
