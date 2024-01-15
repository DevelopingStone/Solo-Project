package com.publicWifiSearch.domain.repostitory.publicWifi.publicWifiDetail.wifi;

import com.publicWifiSearch.domain.repostitory.publicWifi.queryConstant.WifiQuery;
import com.publicWifiSearch.domain.repostitory.common.jdbcUtil.JdbcLauncher;
import com.publicWifiSearch.domain.repostitory.common.jdbcUtil.ParameterBatchHelper;
import com.publicWifiSearch.domain.repostitory.common.Repository;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.wifi.Wifi;
import com.publicWifiSearch.domain.repostitory.common.jdbcUtil.SqlStatement;
import com.publicWifiSearch.domain.repostitory.common.columnConstant.Column;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WifiRepository extends Repository<Wifi> {

    private JdbcLauncher jdbcLauncher;

    @Override
    public void connectDataBaseWith(Connection connection) {
        jdbcLauncher = new JdbcLauncher(connection);
    }
    @Override
    public void saveByBatch(List<Wifi> wifiList) throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(WifiQuery.SAVE_QUERY);

        ParameterBatchHelper parameterBatchHelper = (preparedStatement, index) -> {
            preparedStatement.setLong(Column.FIRST.getPosition(), index);
            preparedStatement.setObject(Column.SECOND.getPosition(), wifiList.get(index).getWifiName());
            preparedStatement.setObject(Column.THIRD.getPosition(), wifiList.get(index).getCoordinateX());
            preparedStatement.setObject(Column.FOURTH.getPosition(), wifiList.get(index).getCoordinateY());
            preparedStatement.setObject(Column.FIFTH.getPosition(), wifiList.get(index).getConnectionEnvironment());
            preparedStatement.setObject(Column.SIXTH.getPosition(), wifiList.get(index).getNetworkType());
            preparedStatement.setObject(Column.SEVENTH.getPosition(), wifiList.get(index).getService());
            preparedStatement.setObject(Column.EIGHTH.getPosition(), wifiList.get(index).getDateOfWork());
        };

        this.jdbcLauncher.executeSaveByBatchWithPreparedStatement(sqlStatement, wifiList, parameterBatchHelper);
    }

    @Override
    public void deleteAll() throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(WifiQuery.DELETE_ALL_QUERY);
        this.jdbcLauncher.executeUpdateWithPreparedStatement(sqlStatement, null);
    }

    @Override
    public Wifi findById(String wifiId) throws SQLException {
        ResultSet installationResultSet = jdbcLauncher.executeQueryWithPreparedStatement(
                connection -> connection.prepareStatement(WifiQuery.SELECT_BY_MANAGEMENT_ID_QUERY),
                (preparedStatement, index) -> preparedStatement.setObject(Column.FIRST.getPosition(), wifiId));
        return transformToEntity(installationResultSet);
    }
    public Wifi transformToEntity(ResultSet resultSet) throws SQLException {
        return Wifi.builder()
                .wifiName(resultSet.getString(Column.SECOND.getPosition()))
                .coordinateX(resultSet.getDouble(Column.THIRD.getPosition()))
                .coordinateY(resultSet.getDouble(Column.FOURTH.getPosition()))
                .connectionEnvironment(resultSet.getString(Column.FIFTH.getPosition()))
                .networkType(resultSet.getString(Column.SIXTH.getPosition()))
                .service(resultSet.getString(Column.SEVENTH.getPosition()))
                .dateOfWork(resultSet.getString(Column.EIGHTH.getPosition()))
                .build();
    }
    @Override
    public List<Wifi> findAll() {
        List<Wifi> wifiBundle;
        ResultSet resultSet;
        try {
            resultSet = jdbcLauncher.executeQueryWithPreparedStatement(connection -> connection.prepareStatement(WifiQuery.SELECT_ALL_QUERY));
            wifiBundle = transformToEntityBundle(resultSet);
        }
        catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return wifiBundle;
    }

    @Override
    public List<Wifi> transformToEntityBundle(ResultSet resultSet) throws SQLException {
        List<Wifi> wifiBundle = new ArrayList<>();
        try {
            if(resultSet != null){
                while (resultSet.next()){
                    wifiBundle.add(transformToEntity(resultSet));
                }
            }
        }
        catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return wifiBundle;
    }
}
