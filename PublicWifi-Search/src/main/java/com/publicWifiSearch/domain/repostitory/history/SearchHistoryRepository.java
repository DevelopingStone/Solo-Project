package com.publicWifiSearch.domain.repostitory.history;

import com.publicWifiSearch.domain.model.history.Coordinate;
import com.publicWifiSearch.domain.model.history.SearchHistory;
import com.publicWifiSearch.domain.repostitory.history.queryConstant.SearchHistoryQuery;
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

public class SearchHistoryRepository extends Repository<SearchHistory> {
    private JdbcLauncher jdbcLauncher;
    @Override
    public void connectDataBaseWith(Connection connection) {
        this.jdbcLauncher = new JdbcLauncher(connection);
    }

    @Override
    public void save(SearchHistory searchHistory) throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(SearchHistoryQuery.SAVE_QUERY);
        ParameterHelper parameterHelper = (preparedStatement) -> {
            preparedStatement.setInt(Column.FIRST.getPosition(), searchHistory.getId());
            preparedStatement.setDouble(Column.SECOND.getPosition(),searchHistory.getCoordinate().getX());
            preparedStatement.setDouble(Column.THIRD.getPosition(), searchHistory.getCoordinate().getY());
            preparedStatement.setObject(Column.FOURTH.getPosition(), searchHistory.getSearchTime());
        };

        this.jdbcLauncher.executeUpdateWithPreparedStatement(sqlStatement, parameterHelper);
    }

    @Override
    public void deleteById(String historyId) throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(SearchHistoryQuery.DELETE_BY_ID_QUERY);
        ParameterHelper parameterHelper = (preparedStatement -> {
           preparedStatement.setInt(Column.FIRST.getPosition(), Integer.parseInt(historyId));
        });
        this.jdbcLauncher.executeUpdateWithPreparedStatement(sqlStatement, parameterHelper);
    }

    @Override
    public void deleteAll() throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(SearchHistoryQuery.DELETE_ALL_QUERY);
        this.jdbcLauncher.executeUpdateWithPreparedStatement(sqlStatement, null);
    }
    @Override
    public SearchHistory transformToEntity(ResultSet resultSet) throws SQLException {
        Coordinate coordinate = Coordinate.builder()
                .x(resultSet.getDouble(Column.SECOND.getPosition()))
                .y(resultSet.getDouble(Column.THIRD.getPosition()))
                .build();
        return SearchHistory.builder()
                .id(resultSet.getInt(Column.FIRST.getPosition()))
                .coordinate(coordinate)
                .searchTime(resultSet.getString(Column.FOURTH.getPosition()))
                .build();
    }

    @Override
    public List<SearchHistory> transformToEntityBundle(ResultSet resultSet) throws SQLException {
        List<SearchHistory> searchHistoryBundle = new ArrayList<>();
        if(resultSet != null){
            while (resultSet.next()){
                searchHistoryBundle.add(transformToEntity(resultSet));
            }
        }
        return searchHistoryBundle;
    }

    @Override
    public SearchHistory findById(String historyId) throws SQLException{
        ResultSet searchHistoryResult = jdbcLauncher.executeQueryWithPreparedStatement(
                connection -> connection.prepareStatement(SearchHistoryQuery.SELECT_BY_MANAGEMENT_ID_QUERY),
                (preparedStatement, index) -> preparedStatement.setInt(Column.FIRST.getPosition(), Integer.parseInt(historyId)));
        return transformToEntity(searchHistoryResult);
    }

    @Override
    public List<SearchHistory> findAll() {
        List<SearchHistory> searchHistoryBundle;
        try {
            ResultSet resultSet = jdbcLauncher.executeQueryWithPreparedStatement(
                    connection -> connection.prepareStatement(SearchHistoryQuery.SELECT_ALL_QUERY));
            searchHistoryBundle = transformToEntityBundle(resultSet);
        }
        catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return searchHistoryBundle;
    }
}
