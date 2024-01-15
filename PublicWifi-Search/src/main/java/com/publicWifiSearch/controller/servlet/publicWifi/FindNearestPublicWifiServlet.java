package com.publicWifiSearch.controller.servlet.publicWifi;
import com.publicWifiSearch.domain.dto.history.CoordinateDto;
import com.publicWifiSearch.domain.dto.publicWifi.PublicWifiResponseDto;
import com.publicWifiSearch.domain.model.publicWifi.PublicWifi;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.DbConnectionMaker;
import com.publicWifiSearch.domain.repostitory.common.dbConnection.SqliteConnectionMaker;
import com.publicWifiSearch.service.PublicWifiService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name="findNearestPublicWifi", value = "/FindNearestPublicWifi")
public class FindNearestPublicWifiServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        double coordinate_x = Double.parseDouble(request.getParameter("coordinateX"));
        double coordinate_y = Double.parseDouble(request.getParameter("coordinateY"));

        CoordinateDto coordinateDto = new CoordinateDto(coordinate_x, coordinate_y);

        DbConnectionMaker dbConnectionMaker = new SqliteConnectionMaker();
        PublicWifiService service = new PublicWifiService(dbConnectionMaker);
        List<PublicWifi> publicWifiBundle = service.findNearestWifi(coordinateDto.toEntity());

        PublicWifiResponseDto publicWifiResponseDto = new PublicWifiResponseDto(publicWifiBundle);

        request.setAttribute("NearestTwentyPublicWifi", publicWifiResponseDto.NearestPublicWifiToJson());

        String viewPath = "/WEB-INF/index.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
