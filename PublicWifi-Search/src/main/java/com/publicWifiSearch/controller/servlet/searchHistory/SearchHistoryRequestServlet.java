package com.publicWifiSearch.controller.servlet.searchHistory;

import com.publicWifiSearch.domain.dto.history.SearchHistoryResponseDto;
import com.publicWifiSearch.domain.dto.history.SearchHistoryResponseFeatureDto;
import com.publicWifiSearch.domain.model.history.SearchHistory;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.DbConnectionMaker;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.SqliteConnectionMaker;
import com.publicWifiSearch.service.SearchHistoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/requestSearchHistoryServlet")
public class SearchHistoryRequestServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        DbConnectionMaker dbConnectionMaker = new SqliteConnectionMaker();
        SearchHistoryService searchHistoryService = new SearchHistoryService(dbConnectionMaker);
        List<SearchHistory> searchHistoryBundle = searchHistoryService.findAll();
        SearchHistoryResponseDto searchHistoryResponseDto = new SearchHistoryResponseDto(searchHistoryBundle);
        SearchHistoryResponseFeatureDto searchHistoryResponseFeatureDto = new SearchHistoryResponseFeatureDto();

        request.setAttribute("searchHistory", searchHistoryResponseDto.toJson());
        request.setAttribute("searchHistoryFeature", searchHistoryResponseFeatureDto.featuresToJson());

        String viewPath = "/WEB-INF/History.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }
}
