package com.publicWifiSearch.controller.servlet.publicWifi;

import com.publicWifiSearch.domain.dto.openAPIRequestdtos.OpenApiRequestPublicWifiRecordDto;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.DbConnectionMaker;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.SqliteConnectionMaker;
import com.publicWifiSearch.service.OpenAPIService;
import com.publicWifiSearch.service.PublicWifiService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name="openAPIRequestServlet", value= "/JsonRequest")
public class JsonRequestServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OpenAPIService openAPIService = new OpenAPIService();
        List<OpenApiRequestPublicWifiRecordDto> openApiRequestDto = openAPIService.requestToOpenAPI();

        DbConnectionMaker dbConnectionMaker = new SqliteConnectionMaker();

        PublicWifiService publicWifiService = new PublicWifiService(dbConnectionMaker);
        publicWifiService.saveOpenApiRequest(openApiRequestDto);
        request.setAttribute("numberOfTotalData", publicWifiService.getNumberOfData());

        String viewPath = "/WEB-INF/OpenApiRequest.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
