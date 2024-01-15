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

@WebServlet(name="BookmarkGroupSave", value = "/BookmarkGroupSaveServlet")
public class BookmarkGroupSaveServlet extends HttpServlet {
    private static int id = 0;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        BookmarkGroupSaveDto bookmarkGroupSaveDto = BookmarkGroupSaveDto.builder()
                .bookmarkGroupId(id++)
                .order(Integer.parseInt(request.getParameter("bookmarkGroupOrder")))
                .bookmarkName(request.getParameter("bookmarkGroupName"))
                .enrollmentDate(request.getParameter("enrollmentTime"))
                .modifiedDate("")
                .build();

        DbConnectionMaker dbConnectionMaker = new SqliteConnectionMaker();
        BookmarkGroupService bookmarkGroupService = new BookmarkGroupService(dbConnectionMaker);
        bookmarkGroupService.save(bookmarkGroupSaveDto);

        String viewPath = "/BookmarkGroupResponseServlet";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
