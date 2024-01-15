package com.publicWifiSearch.controller.servlet.bookmarkGroup;

import com.publicWifiSearch.domain.dto.bookmark.bookmarkGroup.BookmarkGroupSaveDto;
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

@WebServlet(name="BookmarkGroupUpdate", value = "/BookmarkGroupUpdateServlet")
public class BookmarkGroupUpdateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String bookmarkGroupId= request.getParameter("bookmarkGroupId");
        String bookmarkGroupName = request.getParameter("bookmarkGroupName");
        String bookmarkGroupOrder = request.getParameter("bookmarkGroupOrder");
        String bookmarkGroupModified = request.getParameter("bookmarkGroupModified");

        BookmarkGroupSaveDto bookmarkGroupSaveDto = BookmarkGroupSaveDto.builder()
                .bookmarkGroupId(Integer.parseInt(bookmarkGroupId))
                .order(Integer.parseInt(bookmarkGroupOrder))
                .bookmarkName(bookmarkGroupName)
                .modifiedDate(bookmarkGroupModified)
                .build();

        DbConnectionMaker dbConnectionMaker = new SqliteConnectionMaker();
        BookmarkGroupService bookmarkGroupService = new BookmarkGroupService(dbConnectionMaker);
        bookmarkGroupService.save(bookmarkGroupSaveDto);

        String viewPath = "/WEB-INF/BookmarkGroupModification.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}

