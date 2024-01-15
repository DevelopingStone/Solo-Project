package com.publicWifiSearch.controller.servlet.searchHistory;

import com.publicWifiSearch.HelloServlet;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.DbConnectionMaker;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.SqliteConnectionMaker;
import com.publicWifiSearch.service.SearchHistoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/searchHistoryDelete")
public class SearchHistoryDeleteServlet extends HelloServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String historyId = request.getParameter("historyId");

        DbConnectionMaker dbConnectionMaker = new SqliteConnectionMaker();
        SearchHistoryService searchHistoryService = new SearchHistoryService(dbConnectionMaker);
        searchHistoryService.deleteById(historyId);

        String viewPath = "/requestSearchHistoryServlet";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }

}
