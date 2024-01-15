package com.publicWifiSearch.domain.repostitory.bookmark.queryConstant;

public class BookmarkGroupQuery {
    public static final String TABLE_NAME = "bookmark_group";
    public static final String SAVE_QUERY = String.format("%s%s%s%s", "insert into ", TABLE_NAME, " values ", "(?,?,?,?,?)");
    public static final String SELECT_ALL_QUERY = String.format("%s%s", "select * from ", TABLE_NAME);
    public static final String DELETE_BY_ID_QUERY = String.format("%s%s%s%s", "delete from ", TABLE_NAME, " where bookmark_group_Id = ", "(?)");
    public static final String DELETE_ALL_QUERY = String.format("%s%s", "delete from ", TABLE_NAME);
    public static final String SELECT_BY_ID_QUERY = String.format("%s%s%s%s", "select installation_Id from ", TABLE_NAME, " where installation_Id = ", "(?)");
    public static final String UPDATE_BOOKMARK_NAME_BY_ID_QUERY = String.format("%s%s%s%s%s","update ",TABLE_NAME, " set"," bookmark_name=?", "where bookmark_group_Id = ?");
    public static final String UPDATE_BOOKMARK_BY_ID_QUERY = String.format("%s%s%s%s%s","update ",TABLE_NAME, " set"," bookmark_name=(?), bookmark_order=(?) , bookmark_modified=(?) ", "where bookmark_group_Id = (?)");

}
