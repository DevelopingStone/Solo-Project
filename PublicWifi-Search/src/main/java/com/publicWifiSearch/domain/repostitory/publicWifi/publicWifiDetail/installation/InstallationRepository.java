package com.publicWifiSearch.domain.repostitory.publicWifi.publicWifiDetail.installation;

import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.installation.Installation;
import com.publicWifiSearch.domain.repostitory.publicWifi.queryConstant.InstallationQuery;
import com.publicWifiSearch.domain.repostitory.common.jdbcUtil.JdbcLauncher;
import com.publicWifiSearch.domain.repostitory.common.jdbcUtil.ParameterBatchHelper;
import com.publicWifiSearch.domain.repostitory.common.Repository;
import com.publicWifiSearch.domain.repostitory.common.jdbcUtil.SqlStatement;
import com.publicWifiSearch.domain.repostitory.common.columnConstant.Column;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstallationRepository extends Repository<Installation> {

    private JdbcLauncher jdbcLauncher;

    @Override
    public void connectDataBaseWith(Connection connection) {
        jdbcLauncher = new JdbcLauncher(connection);
    }

    @Override
    public void saveByBatch(List<Installation> installationList) throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(InstallationQuery.SAVE_QUERY);

        ParameterBatchHelper parameterBatchHelper = (preparedStatement, index) -> {
            preparedStatement.setLong(Column.FIRST.getPosition(), index);
            preparedStatement.setObject(Column.SECOND.getPosition(), installationList.get(index).getInstallLocation());
            preparedStatement.setObject(Column.THIRD.getPosition(), installationList.get(index).getInstallType());
            preparedStatement.setObject(Column.FOURTH.getPosition(), installationList.get(index).getInstallOffice());
            preparedStatement.setObject(Column.FIFTH.getPosition(), installationList.get(index).getInstallYear());
            preparedStatement.setObject(Column.SIXTH.getPosition(), installationList.get(index).getInstallDivision());
        };

        this.jdbcLauncher.executeSaveByBatchWithPreparedStatement(sqlStatement, installationList, parameterBatchHelper);
    }

    public Installation transformToEntity(ResultSet resultSet) throws SQLException {
        return Installation.builder()
                .installLocation(resultSet.getString(Column.SECOND.getPosition()))
                .installType(resultSet.getString(Column.THIRD.getPosition()))
                .installOffice(resultSet.getString(Column.FOURTH.getPosition()))
                .installYear(resultSet.getString(Column.FIFTH.getPosition()))
                .installDivision(resultSet.getString(Column.SIXTH.getPosition()))
                .build();
    }

    @Override
    public void deleteAll() throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(InstallationQuery.DELETE_ALL_QUERY);
        this.jdbcLauncher.executeUpdateWithPreparedStatement(sqlStatement, null);
    }

    @Override
    public Installation findById(String installationId) throws SQLException {
        ResultSet installationResultSet = jdbcLauncher.executeQueryWithPreparedStatement(
                connection -> connection.prepareStatement(InstallationQuery.SELECT_BY_MANAGEMENT_ID_QUERY),
                (preparedStatement, index) -> preparedStatement.setObject(Column.FIRST.getPosition(), installationId));
        return transformToEntity(installationResultSet);
    }

    @Override
    public List<Installation> findAll() {
        List<Installation> installations;
        ResultSet resultSet;
        try {
            resultSet = jdbcLauncher.executeQueryWithPreparedStatement(
                    connection -> connection.prepareStatement(InstallationQuery.SELECT_ALL_QUERY));
            installations = transformToEntityBundle(resultSet);
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
        return installations;
    }

    @Override
    public List<Installation> transformToEntityBundle(ResultSet resultSet) throws SQLException {
        List<Installation> installations = new ArrayList<>();
        if (resultSet != null) {
            while (resultSet.next()) {
                installations.add(transformToEntity(resultSet));
            }
        }
        return installations;
    }
}
