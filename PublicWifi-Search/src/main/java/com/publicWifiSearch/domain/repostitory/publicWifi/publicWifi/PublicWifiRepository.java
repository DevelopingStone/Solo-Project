package com.publicWifiSearch.domain.repostitory.publicWifi.publicWifi;

import com.publicWifiSearch.domain.model.publicWifi.PublicWifi;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.address.Address;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.installation.Installation;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.wifi.Wifi;
import com.publicWifiSearch.domain.repostitory.common.Repository;
import com.publicWifiSearch.domain.repostitory.common.columnConstant.Column;
import com.publicWifiSearch.domain.repostitory.publicWifi.queryConstant.PublicWifiQuery;
import com.publicWifiSearch.domain.repostitory.common.jdbcUtil.JdbcLauncher;
import com.publicWifiSearch.domain.repostitory.common.jdbcUtil.ParameterBatchHelper;
import com.publicWifiSearch.domain.repostitory.common.jdbcUtil.SqlStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublicWifiRepository extends Repository<PublicWifi> {
    private JdbcLauncher jdbcLauncher;

    @Override
    public void connectDataBaseWith(Connection connection) {
        this.jdbcLauncher = new JdbcLauncher(connection);
    }

    @Override
    public void saveByBatch(List<PublicWifi> publicWifiList) throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(PublicWifiQuery.SAVE_QUERY);

        ParameterBatchHelper parameterBatchHelper = (preparedStatement, index) -> {
            preparedStatement.setObject(Column.FIRST.getPosition(), publicWifiList.get(index).getManagementId());
            preparedStatement.setLong(Column.SECOND.getPosition(), index);
            preparedStatement.setLong(Column.THIRD.getPosition(), index);
            preparedStatement.setLong(Column.FOURTH.getPosition(), index);
        };
        this.jdbcLauncher.executeSaveByBatchWithPreparedStatement(sqlStatement, publicWifiList, parameterBatchHelper);
    }

    @Override
    public void deleteAll() throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(PublicWifiQuery.DELETE_ALL_QUERY);
        this.jdbcLauncher.executeUpdateWithPreparedStatement(sqlStatement, null);
    }

    @Override
    public PublicWifi findById(String managementId) throws SQLException {
        return null;
    }

    @Override
    public PublicWifi transformToEntity(ResultSet resultSet) throws SQLException {

        return new PublicWifi(
                resultSet.getString(Column.FIRST.getPosition()),
                Address.builder()
                        .district(resultSet.getString(Column.SECOND.getPosition()))
                        .roadAddress(resultSet.getString(Column.THIRD.getPosition()))
                        .detailAddress(resultSet.getString(Column.FOURTH.getPosition()))
                        .build(),
                Installation.builder()
                        .installLocation(resultSet.getString(Column.FIFTH.getPosition()))
                        .installType(resultSet.getString(Column.SIXTH.getPosition()))
                        .installOffice(resultSet.getString(Column.SEVENTH.getPosition()))
                        .installYear(resultSet.getString(Column.EIGHTH.getPosition()))
                        .installDivision(resultSet.getString(Column.NINTH.getPosition()))
                        .build(),
                Wifi.builder()
                        .wifiName(resultSet.getString(Column.TENTH.getPosition()))
                        .coordinateX(resultSet.getDouble(Column.ELEVENTH.getPosition()))
                        .coordinateY(resultSet.getDouble(Column.TWELFTH.getPosition()))
                        .connectionEnvironment(resultSet.getString(Column.THIRTEENTH.getPosition()))
                        .networkType(resultSet.getString(Column.FOURTEENTH.getPosition()))
                        .service(resultSet.getString(Column.FIFTEENTH.getPosition()))
                        .dateOfWork(resultSet.getString(Column.SIXTEENTH.getPosition()))
                        .build()
                );
    }

    @Override
    public List<PublicWifi> transformToEntityBundle(ResultSet resultSet) throws SQLException {
        List<PublicWifi> publicWifiList=new ArrayList<>();
        if(resultSet!=null){
            while (resultSet.next()){
                publicWifiList.add(transformToEntity(resultSet));
            }
        }
        return publicWifiList;
    }

    @Override
    public List<PublicWifi> findAll() {
        List<PublicWifi> publicWifiBundle;
        try {
            ResultSet resultSet = jdbcLauncher.executeQueryWithPreparedStatement(connection -> connection.prepareStatement(PublicWifiQuery.SELECT_ALL_QUERY));
            publicWifiBundle = transformToEntityBundle(resultSet);
        }
        catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return publicWifiBundle;
    }
}
