package com.publicWifiSearch.controller.servlet.publicWifi;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name="publicWifiResponse", value = "/PublicWifiResponseServlet")
public class PublicWifiResponseServlet extends HttpServlet {
    private static final String PUBLIC_WIFI_DETAIL_FEATURE = "publicWifiDetailFeature";
    private static final String PUBLIC_WIFI_DETAIL = "publicWifiDetail";
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        System.out.println("무야호");
        System.out.println(request.getParameter("publicWifiFeatureName"));
        System.out.println(request.getParameter("publicWifiFeature"));
        String publicWifiDetailFeature = request.getParameter("publicWifiFeatureName");
        String publicWifiDetail = request.getParameter("publicWifiFeature");
        request.setAttribute(PUBLIC_WIFI_DETAIL_FEATURE, publicWifiDetailFeature);
        request.setAttribute(PUBLIC_WIFI_DETAIL, publicWifiDetail);

        String viewPath = "/WEB-INF/WifiDetail.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
