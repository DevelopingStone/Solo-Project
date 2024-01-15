package com.publicWifiSearch.controller.servlet.searchHistory;

import com.publicWifiSearch.domain.dto.history.SearchHistorySaveDto;
import com.publicWifiSearch.domain.model.history.Coordinate;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.DbConnectionMaker;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.SqliteConnectionMaker;
import com.publicWifiSearch.service.SearchHistoryService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="searchHistorySave", value= "/SaveSearchHistoryServlet")
public class SearchHistorySaveServlet extends HttpServlet {
    private static int id = 0;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        double coordinate_x = Double.parseDouble(request.getParameter("coordinateX"));
        double coordinate_y = Double.parseDouble(request.getParameter("coordinateY"));
        String searchTime = request.getParameter("searchTime");

        SearchHistorySaveDto searchHistorySaveDto = new SearchHistorySaveDto(id++,new Coordinate(coordinate_x, coordinate_y), searchTime);

        DbConnectionMaker dbConnectionMaker = new SqliteConnectionMaker();
        SearchHistoryService searchHistoryService = new SearchHistoryService(dbConnectionMaker);
        searchHistoryService.save(searchHistorySaveDto);

        request.setAttribute("coordinateX", coordinate_x);
        request.setAttribute("coordinateY", coordinate_y);
        request.setAttribute("searchTime", searchTime);
        String viewPath = "/WEB-INF/index.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
