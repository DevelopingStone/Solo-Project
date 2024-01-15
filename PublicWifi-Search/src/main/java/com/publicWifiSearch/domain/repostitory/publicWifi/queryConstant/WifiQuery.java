package com.publicWifiSearch.domain.repostitory.publicWifi.queryConstant;

public final class WifiQuery {
    public static final String TABLE_NAME = "wifi";
    public static final String SAVE_QUERY = String.format("%s%s%s%s", "insert into ",TABLE_NAME," values ", "(?,?,?,?,?,?,?,?)");
    public static final String SELECT_ALL_QUERY = String.format("%s%s", "select * from ", TABLE_NAME);
    public static final String DELETE_ALL_QUERY = String.format("%s%s", "delete from ", TABLE_NAME);
    public static final String SELECT_BY_MANAGEMENT_ID_QUERY = String.format("%s%s%s%s", "select wifi_Id from ", TABLE_NAME, "where wifi_Id = ", "(?)");
}
