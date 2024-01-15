package com.publicWifiSearch.controller.servlet;

import com.publicWifiSearch.domain.dto.constant.PublicWifiFeature;
import com.publicWifiSearch.domain.dto.publicWifi.PublicWifiFeatureDto;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.DbConnectionMaker;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.SqliteConnectionMaker;
import com.publicWifiSearch.service.BookmarkGroupService;
import com.publicWifiSearch.service.InitiationService;
import com.publicWifiSearch.service.SearchHistoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mainServlet", value ="/")
public class MainController extends HttpServlet {
    private static int numberOfHomepageAccessCount=0;
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println(numberOfHomepageAccessCount);
        if(numberOfHomepageAccessCount==0){
            DbConnectionMaker dbConnectionMaker = new SqliteConnectionMaker();
            InitiationService initiationService = new InitiationService(dbConnectionMaker);
            initiationService.initiate();
        }
        numberOfHomepageAccessCount++;
        String viewPath = "/WEB-INF/index.jsp";

        PublicWifiFeatureDto publicWifiFeatureDto = new PublicWifiFeatureDto();
        request.setAttribute("NearestTwentyPublicWifiFeatures", publicWifiFeatureDto.featuresToJson());
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

}