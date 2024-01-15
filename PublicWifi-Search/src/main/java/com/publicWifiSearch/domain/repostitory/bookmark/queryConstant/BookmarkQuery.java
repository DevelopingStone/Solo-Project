package com.publicWifiSearch.domain.repostitory.bookmark.queryConstant;

public final class BookmarkQuery {
    public static final String TABLE_NAME = "bookmark";
    public static final String SAVE_QUERY = String.format("%s%s%s%s", "insert into ", TABLE_NAME, " values ", "(?,?,?,?)");
    public static final String SELECT_ALL_QUERY = String.format("%s", "select b.bookmark_Id, bg.bookmark_name, b.wifi_name, b.bookmark_enrollment " +
                                                                        "from bookmark b left outer join bookmark_group bg on " +
                                                                        "bg.bookmark_group_Id = b.bookmark_group_Id;");
    public static final String DELETE_BY_ID_QUERY = String.format("%s%s%s%s", "delete from ", TABLE_NAME, " where bookmark_Id = ", "(?)");
    public static final String DELETE_ALL_QUERY = String.format("%s%s", "delete from ", TABLE_NAME);
}
