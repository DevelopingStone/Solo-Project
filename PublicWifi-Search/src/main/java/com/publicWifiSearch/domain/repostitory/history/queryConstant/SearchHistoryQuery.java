package com.publicWifiSearch.domain.repostitory.history.queryConstant;

public final class SearchHistoryQuery {
    public static final String TABLE_NAME = "history";
    public static final String SAVE_QUERY = String.format("%s%s%s%s", "insert into ",TABLE_NAME," values ", "(?,?,?,?)");
    public static final String SELECT_ALL_QUERY = String.format("%s%s", "select * from ", TABLE_NAME);
    public static final String DELETE_ALL_QUERY = String.format("%s%s", "delete from ", TABLE_NAME);
    public static final String DELETE_BY_ID_QUERY = String.format("%s%s%s%s", "delete from ", TABLE_NAME, " where history_Id = ", "(?)");
    public static final String SELECT_BY_MANAGEMENT_ID_QUERY = String.format("%s%s%s%s", "select history_Id from ", TABLE_NAME, "where history_Id = ", "(?)");
}
