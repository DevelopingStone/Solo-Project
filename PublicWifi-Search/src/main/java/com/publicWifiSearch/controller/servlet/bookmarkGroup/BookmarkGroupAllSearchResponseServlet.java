package com.publicWifiSearch.controller.servlet.bookmarkGroup;

import com.publicWifiSearch.domain.dto.bookmark.bookmarkGroup.BookmarkGroupRecordDto;
import com.publicWifiSearch.domain.model.bookMark.BookmarkGroup;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.DbConnectionMaker;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.SqliteConnectionMaker;
import com.publicWifiSearch.service.BookmarkGroupService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookmarkGroupAllSearchResponse", value = "/WifiDetail/BookmarkGroupAllSearchResponseServlet")
public class BookmarkGroupAllSearchResponseServlet extends HttpServlet {
    private static final String PUBLIC_WIFI_DETAIL_FEATURE = "publicWifiDetailFeature";
    private static final String PUBLIC_WIFI_DETAIL = "publicWifiDetail";
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        DbConnectionMaker dbConnectionMaker = new SqliteConnectionMaker();
        BookmarkGroupService bookmarkGroupService = new BookmarkGroupService(dbConnectionMaker);
        List<BookmarkGroup> bookmarkGroupBundle = bookmarkGroupService.findAll();
        BookmarkGroupRecordDto bookmarkGroupRecordDto = new BookmarkGroupRecordDto(bookmarkGroupBundle);

        request.setAttribute("bookmarkGroupJson", bookmarkGroupRecordDto.toJson());

        String viewPath;
        String[] url = request.getRequestURI().split("/");
        String name = url[url.length-2];
        if(name.contains("WifiDetail")){
            String publicWifiDetailFeature = request.getParameter("publicWifiFeatureName");
            String publicWifiDetail = request.getParameter("publicWifiFeature");
            request.setAttribute(PUBLIC_WIFI_DETAIL_FEATURE, publicWifiDetailFeature);
            request.setAttribute(PUBLIC_WIFI_DETAIL, publicWifiDetail);
            viewPath = "/WEB-INF/WifiDetail.jsp";
        }
        else{
            viewPath = "/WEB-INF/BookmarkGroup.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
