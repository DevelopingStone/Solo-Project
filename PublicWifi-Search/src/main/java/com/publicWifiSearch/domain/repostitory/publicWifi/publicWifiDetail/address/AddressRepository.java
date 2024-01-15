package com.publicWifiSearch.domain.repostitory.publicWifi.publicWifiDetail.address;

import com.publicWifiSearch.domain.repostitory.publicWifi.queryConstant.AddressQuery;
import com.publicWifiSearch.domain.repostitory.common.jdbcUtil.ParameterBatchHelper;
import com.publicWifiSearch.domain.repostitory.common.jdbcUtil.JdbcLauncher;
import com.publicWifiSearch.domain.repostitory.common.Repository;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.address.Address;
import com.publicWifiSearch.domain.repostitory.common.jdbcUtil.SqlStatement;
import com.publicWifiSearch.domain.repostitory.common.columnConstant.Column;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressRepository extends Repository<Address> {

    private JdbcLauncher jdbcLauncher;

    @Override
    public void connectDataBaseWith(Connection connection) {
        jdbcLauncher = new JdbcLauncher(connection);
    }

    @Override
    public void saveByBatch(List<Address> addressList) throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(AddressQuery.SAVE_QUERY);

        ParameterBatchHelper parameterBatchHelper = (preparedStatement, index) -> {
            preparedStatement.setLong(Column.FIRST.getPosition(), index);
            preparedStatement.setObject(Column.SECOND.getPosition(), addressList.get(index).getDistrict());
            preparedStatement.setObject(Column.THIRD.getPosition(), addressList.get(index).getRoadAddress());
            preparedStatement.setObject(Column.FOURTH.getPosition(), addressList.get(index).getDetailAddress());
        };

        this.jdbcLauncher.executeSaveByBatchWithPreparedStatement(sqlStatement, addressList, parameterBatchHelper);
    }

    @Override
    public void deleteAll() throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(AddressQuery.DELETE_ALL_QUERY);
        this.jdbcLauncher.executeUpdateWithPreparedStatement(sqlStatement, null);
    }
    @Override
    public Address transformToEntity(ResultSet resultSet) throws SQLException {
        return Address.builder()
                .district(resultSet.getString(Column.SECOND.getPosition()))
                .roadAddress(resultSet.getString(Column.THIRD.getPosition()))
                .detailAddress(resultSet.getString(Column.FOURTH.getPosition()))
                .build();
    }

    @Override
    public List<Address> transformToEntityBundle(ResultSet resultSet) throws SQLException {
        List<Address> addresses = new ArrayList<>();
        if(resultSet != null){
            while (resultSet.next()){
                addresses.add(transformToEntity(resultSet));
            }
        }
        return addresses;
    }

    @Override
    public Address findById(String addressId) throws SQLException{
        ResultSet addressResultSet = jdbcLauncher.executeQueryWithPreparedStatement(
                connection -> connection.prepareStatement(AddressQuery.SELECT_BY_MANAGEMENT_ID_QUERY),
                (preparedStatement, index) -> preparedStatement.setObject(Column.FIRST.getPosition(), addressId));
        return transformToEntity(addressResultSet);
    }

    @Override
    public List<Address> findAll() {
        List<Address> addresses;
        try {
            ResultSet resultSet = jdbcLauncher.executeQueryWithPreparedStatement(
                    connection -> connection.prepareStatement(AddressQuery.SELECT_ALL_QUERY));
            addresses = transformToEntityBundle(resultSet);
        }
        catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
        return addresses;
    }

}
