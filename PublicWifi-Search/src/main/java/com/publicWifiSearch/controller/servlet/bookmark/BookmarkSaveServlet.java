package com.publicWifiSearch.controller.servlet.bookmark;

import com.publicWifiSearch.domain.dto.bookmark.bookmark.BookmarkFeature;
import com.publicWifiSearch.domain.dto.bookmark.bookmark.BookmarkSaveDto;
import com.publicWifiSearch.domain.model.bookMark.Bookmark;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.DbConnectionMaker;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.SqliteConnectionMaker;
import com.publicWifiSearch.service.BookmarkService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BookmarkSave", value = "/BookmarkSaveServlet")
public class BookmarkSaveServlet extends HttpServlet {
    private static int bookmarkId = 0;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String bookmarkFeatureName = BookmarkFeature.getFeatureNameForService();

        String bookmarkGroupId = request.getParameter("bookmarkGroupId");
        String wifiName = request.getParameter("wifiName");
        String enrollmentTime = request.getParameter("enrollmentTime");

        BookmarkSaveDto bookmarkSaveDto = BookmarkSaveDto.builder()
                                                            .bookMarkId(bookmarkId++)
                                                            .bookmarkGroupId(Integer.parseInt(bookmarkGroupId))
                                                            .wifiName(wifiName)
                                                            .enrollmentDate(enrollmentTime)
                                                            .build();

        DbConnectionMaker dbConnectionMaker = new SqliteConnectionMaker();
        BookmarkService bookmarkService = new BookmarkService(dbConnectionMaker);
        bookmarkService.save(bookmarkSaveDto);
        request.setAttribute("bookmarkFeature", bookmarkFeatureName);

        String viewPath = "/WEB-INF/WifiDetail.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
