package com.publicWifiSearch.controller.servlet.bookmarkGroup;

import com.publicWifiSearch.HelloServlet;
import com.publicWifiSearch.domain.dto.bookmark.bookmarkGroup.BookmarkGroupFeature;
import com.publicWifiSearch.domain.dto.bookmark.bookmarkGroup.BookmarkGroupRecordDto;
import com.publicWifiSearch.domain.model.bookMark.BookmarkGroup;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.DbConnectionMaker;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.SqliteConnectionMaker;
import com.publicWifiSearch.service.BookmarkGroupService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookmarkGroupResponse", value = "/BookmarkGroupResponseServlet")
public class BookmarkGroupResponseServlet extends HelloServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        DbConnectionMaker dbConnectionMaker = new SqliteConnectionMaker();

        BookmarkGroupService bookmarkGroupService = new BookmarkGroupService(dbConnectionMaker);
        List<BookmarkGroup> bookmarkGroupBundle = bookmarkGroupService.findAll();

        BookmarkGroupRecordDto bookmarkGroupRecordDto = new BookmarkGroupRecordDto(bookmarkGroupBundle);
        String bookmarkGroupFeature = BookmarkGroupFeature.getFeatureNameForService();

        request.setAttribute("bookmarkGroupFeature", bookmarkGroupFeature);
        request.setAttribute("bookmarkGroupJson", bookmarkGroupRecordDto.toJson());

        String viewPath = "/WEB-INF/BookmarkGroup.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
