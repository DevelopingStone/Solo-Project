package com.publicWifiSearch.domain.repostitory.publicWifi.queryConstant;

public final class PublicWifiQuery {
    public static final String TABLE_NAME = "public_wifi";
    public static final String SAVE_QUERY = String.format("%s%s%s%s", "insert into ", TABLE_NAME, " values ", "(?,?,?,?)");
    public static final String SELECTED_COLUMNS_PUBLIC_WIFI = "pw.managementId,";
    public static final String SELECTED_COLUMNS_ADDRESS = "addr.district, addr.road_address, addr.detail_address,";
    public static final String SELECTED_COLUMNS_INSTALLATION = "install.install_location, install.install_type, install.install_office, install.install_year, install.install_division,";
    public static final String SELECTED_COLUMNS_WIFI = "wifi.wifi_name, wifi.coordinate_x, wifi.coordinate_y, wifi.connection_environment, wifi.network_type, wifi.service, wifi.date_of_work";
    public static final String FROM = String.format("%s%s%s ", " from ", TABLE_NAME, " pw");
    public static final String JOIN_ADDRESS = " left outer join address addr on pw.address_Id = addr.address_Id";
    public static final String JOIN_INSTALLATION = " left outer join installation install on pw.installation_Id = install.installation_Id ";
    public static final String JOIN_WIFI = " left outer join wifi on pw.wifi_Id = wifi.wifi_Id";
    public static final String SELECT_ALL_QUERY = String.format("%s%s%s%s%s%s%s%s%s","select ", SELECTED_COLUMNS_PUBLIC_WIFI, SELECTED_COLUMNS_ADDRESS, SELECTED_COLUMNS_INSTALLATION, SELECTED_COLUMNS_WIFI, FROM, JOIN_ADDRESS, JOIN_INSTALLATION, JOIN_WIFI);
    public static final String DELETE_ALL_QUERY = String.format("%s%s", "delete from ", TABLE_NAME);
    public static final String SELECT_BY_MANAGEMENT_ID_QUERY = String.format("%s%s%s%s", "select managementId from ", TABLE_NAME, " where managementId = ", "(?)");

}
