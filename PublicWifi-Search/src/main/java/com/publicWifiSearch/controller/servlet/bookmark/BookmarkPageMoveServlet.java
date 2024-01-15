package com.publicWifiSearch.controller.servlet.bookmark;

import com.publicWifiSearch.domain.dto.bookmark.bookmark.BookmarkFeature;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "BookmarkPageMove", value = "/BookmarkPageMoveServlet")
public class BookmarkPageMoveServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String bookmarkFeatureName = BookmarkFeature.getFeatureNameForService();

        request.setAttribute("bookmarkFeature", bookmarkFeatureName);

        String viewPath = "/WEB-INF/Bookmark.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
