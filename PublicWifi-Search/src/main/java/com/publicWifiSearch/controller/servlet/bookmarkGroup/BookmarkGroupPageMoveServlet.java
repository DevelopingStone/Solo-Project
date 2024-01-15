package com.publicWifiSearch.controller.servlet.bookmarkGroup;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "BookmarkPageGroupMove", value = "/BookmarkPageGroupMoveServlet")
public class BookmarkGroupPageMoveServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String viewPath="";
        String requestType = request.getParameter("method");
        System.out.println(requestType);
        if(requestType.equals("UPDATE")){
            viewPath = "/WEB-INF/BookmarkGroupModification.jsp";
        }

        if(requestType.equals("MOVE")){
            viewPath = "BookmarkGroupResponseServlet";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
