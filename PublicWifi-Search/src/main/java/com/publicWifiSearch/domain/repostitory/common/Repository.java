package com.publicWifiSearch.domain.repostitory.common;

import com.publicWifiSearch.domain.repostitory.publicWifi.queryConstant.AddressQuery;
import com.publicWifiSearch.domain.repostitory.common.jdbcUtil.JdbcLauncher;
import com.publicWifiSearch.domain.repostitory.common.jdbcUtil.SqlStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Repository <T>{
    private JdbcLauncher jdbcLauncher;

    public void connectDataBaseWith(Connection connection) {
        jdbcLauncher = new JdbcLauncher(connection);
    }
    public void save(T t) throws SQLException {

    }

    public void saveByBatch(List<T> bundle) throws SQLException {
    }
    public void deleteById(String id) throws SQLException {

    }

    public void deleteAll() throws SQLException {
        SqlStatement sqlStatement = connection -> connection.prepareStatement(AddressQuery.DELETE_ALL_QUERY);
        this.jdbcLauncher.executeUpdateWithPreparedStatement(sqlStatement, null);
    }

    public T transformToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }

    public List<T> transformToEntityBundle(ResultSet resultSet) throws SQLException {

        return null;
    }


    public T findById(String id) throws SQLException{
       return null;
    }


    public List<T> findAll() {
        return null;
    }
    public void update(){};

}
