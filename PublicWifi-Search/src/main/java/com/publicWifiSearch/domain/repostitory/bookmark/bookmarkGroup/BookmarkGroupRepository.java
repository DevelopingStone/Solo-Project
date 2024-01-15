package com.publicWifiSearch.domain.repostitory.bookmark.bookmarkGroup;

import com.publicWifiSearch.domain.dto.bookmark.bookmarkGroup.BookmarkGroupUpdateDto;
import com.publicWifiSearch.domain.model.bookMark.BookmarkGroup;
import com.publicWifiSearch.domain.repostitory.bookmark.queryConstant.BookmarkGroupQuery;
import com.publicWifiSearch.domain.repostitory.common.Repository;
import com.publicWifiSearch.domain.repostitory.common.columnConstant.Column;
import com.publicWifiSearch.domain.repostitory.common.jdbcUtil.JdbcLauncher;
import com.publicWifiSearch.domain.repostitory.common.jdbcUtil.ParameterHelper;
import com.publicWifiSearch.domain.repostitory.common.jdbcUtil.SqlStatement;
import com.publicWifiSearch.domain.repostitory.history.queryConstant.SearchHistoryQuery;
import com.publicWifiSearch.domain.repostitory.publicWifi.queryConstant.AddressQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookmarkGroupRepository extends Repository<BookmarkGroup> {

    private JdbcLauncher jdbcLauncher;

    public void connectDataBaseWith(Connection connection) {
        jdbcLauncher = new JdbcLauncher(connection);
    }
    @Override
    public void save(BookmarkGroup bookmarkGroup) throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(BookmarkGroupQuery.SAVE_QUERY);
        ParameterHelper parameterHelper = (preparedStatement) -> {
            preparedStatement.setInt(Column.FIRST.getPosition(), bookmarkGroup.getBookmarkGroupId());
            preparedStatement.setInt(Column.SECOND.getPosition(),bookmarkGroup.getOrder());
            preparedStatement.setString(Column.THIRD.getPosition(), bookmarkGroup.getBookmarkName());
            preparedStatement.setObject(Column.FOURTH.getPosition(), bookmarkGroup.getEnrollmentDate());
            preparedStatement.setObject(Column.FIFTH.getPosition(), bookmarkGroup.getModifiedDate());
        };
        this.jdbcLauncher.executeUpdateWithPreparedStatement(sqlStatement, parameterHelper);
    }

    @Override
    public void deleteById(String id) throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(BookmarkGroupQuery.DELETE_BY_ID_QUERY);
        ParameterHelper parameterHelper = (preparedStatement) -> {
            preparedStatement.setInt(Column.FIRST.getPosition(), Integer.parseInt(id));
        };
        this.jdbcLauncher.executeUpdateWithPreparedStatement(sqlStatement, parameterHelper);
    }

    @Override
    public void deleteAll() throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(BookmarkGroupQuery.DELETE_ALL_QUERY);
        this.jdbcLauncher.executeUpdateWithPreparedStatement(sqlStatement, null);
    }
    @Override
    public BookmarkGroup transformToEntity(ResultSet resultSet) throws SQLException {
        return BookmarkGroup.builder()
                .bookmarkGroupId(resultSet.getInt(Column.FIRST.getPosition()))
                .order(resultSet.getInt(Column.SECOND.getPosition()))
                .bookmarkName(resultSet.getString(Column.THIRD.getPosition()))
                .enrollmentDate(resultSet.getString(Column.FOURTH.getPosition()))
                .modifiedDate(resultSet.getString(Column.FIFTH.getPosition()))
                .build();
    }
    @Override
    public List<BookmarkGroup> transformToEntityBundle(ResultSet resultSet) throws SQLException {
        List<BookmarkGroup> bookmarkGroupBundle = new ArrayList<>();
        if(resultSet != null){
            while (resultSet.next()){
                bookmarkGroupBundle.add(transformToEntity(resultSet));
            }
        }
        return bookmarkGroupBundle;
    }

    @Override
    public BookmarkGroup findById(String bookmarkGroupId) throws SQLException{
        ResultSet bookmarkGroupResult = jdbcLauncher.executeQueryWithPreparedStatement(
                connection -> connection.prepareStatement(BookmarkGroupQuery.SELECT_BY_ID_QUERY),
                (preparedStatement, index) -> preparedStatement.setInt(Column.FIRST.getPosition(), Integer.parseInt(bookmarkGroupId)));
        return transformToEntity(bookmarkGroupResult);
    }

    @Override
    public List<BookmarkGroup> findAll() {
        List<BookmarkGroup> bookmarkGroupBundle;
        try {
            ResultSet resultSet = jdbcLauncher.executeQueryWithPreparedStatement(
                    connection -> connection.prepareStatement(BookmarkGroupQuery.SELECT_ALL_QUERY));
            bookmarkGroupBundle = transformToEntityBundle(resultSet);
        }
        catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return bookmarkGroupBundle;
    }
    public void updateById(BookmarkGroupUpdateDto bookmarkGroupUpdateDto){
        BookmarkGroup bookmarkGroup = bookmarkGroupUpdateDto.toEntity();
        try {
            SqlStatement sqlStatement = connection -> connection.prepareStatement(BookmarkGroupQuery.UPDATE_BOOKMARK_BY_ID_QUERY);
            ParameterHelper parameterHelper = (preparedStatement -> {
                preparedStatement.setString(Column.FIRST.getPosition(), bookmarkGroup.getBookmarkName());
                preparedStatement.setInt(Column.SECOND.getPosition(), bookmarkGroup.getOrder());
                preparedStatement.setString(Column.THIRD.getPosition(), bookmarkGroup.getModifiedDate());
                preparedStatement.setInt(Column.FOURTH.getPosition(), bookmarkGroup.getBookmarkGroupId());
            });
            this.jdbcLauncher.executeUpdateWithPreparedStatement(sqlStatement, parameterHelper);

        }
        catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
    }

}
