package com.publicWifiSearch.controller.servlet.bookmark;

import com.publicWifiSearch.domain.dto.bookmark.bookmark.BookmarkFeature;
import com.publicWifiSearch.domain.dto.bookmark.bookmark.BookmarkResponseDto;
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
import java.util.List;

@WebServlet(name = "BookmarkResponse", value = "/BookmarkResponseServlet")
public class BookmarkResponseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        DbConnectionMaker dbConnectionMaker = new SqliteConnectionMaker();
        BookmarkService bookmarkService = new BookmarkService(dbConnectionMaker);

        List<Bookmark> bookmarkBundle = bookmarkService.findAll();
        BookmarkResponseDto bookmarkResponseDto = new BookmarkResponseDto(bookmarkBundle);

        String bookmarkFeatureName = BookmarkFeature.getFeatureNameForService();

        request.setAttribute("bookmarkFeature", bookmarkFeatureName);
        request.setAttribute("bookmarkJson", bookmarkResponseDto.toJson());

        String viewPath = "/WEB-INF/Bookmark.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
