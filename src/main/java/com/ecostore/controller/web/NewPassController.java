package com.ecostore.controller.web;

import com.ecostore.model.UserModel;
import com.ecostore.service.ILayoutAttributeService;
import com.ecostore.service.IUserService;
import com.ecostore.utils.Check24Hours;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/mat-khau-moi")
public class NewPassController extends HttpServlet {
    @Inject
    private ILayoutAttributeService layoutAttributeService;
    @Inject
    private IUserService userService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        layoutAttributeService.setHeaderWeb(request);
        layoutAttributeService.setFooterWeb(request);
        String email = request.getParameter("email");
        String key = request.getParameter("key");
        if (email == null || key == null) {
            response.sendRedirect(request.getContextPath() + "/trang-chu");
            return;
        }

        UserModel user = userService.findOneByEmail(email);
        if (key.equals(user.getKeycode()) && Check24Hours.check(user)) {
            request.setAttribute("user", user);
            RequestDispatcher rd = request.getRequestDispatcher("views/web/newpass.jsp");
            rd.forward(request, response);
        } else
            response.sendRedirect(request.getContextPath() + "/quen-mat-khau?message=email_change_pass_expired&alert=danger");
    }
}
