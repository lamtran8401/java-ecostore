package com.ecostore.controller.web;

import com.ecostore.model.UserModel;
import com.ecostore.service.ILayoutAttributeService;
import com.ecostore.service.IUserService;
import com.ecostore.utils.MessageUtil;
import com.ecostore.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/doi-mat-khau")
public class ResetPasswordController extends HttpServlet {
    @Inject
    private ILayoutAttributeService layoutAttributeService;
    @Inject
    private IUserService userService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPassword = request.getParameter("currentpassword");
        String newPassword = request.getParameter("password");
        long userId = Long.parseLong(request.getParameter("userid"));
        UserModel user = userService.resetPassword(userId, currentPassword, newPassword);
        if (user == null)
            response.sendRedirect(request.getContextPath() + "/doi-mat-khau?message=current_password_wrong&alert=danger");
        else
            response.sendRedirect(request.getContextPath() + "/doi-mat-khau?message=reset_password_success&alert=success");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        layoutAttributeService.setHeaderWeb(request);
        layoutAttributeService.setFooterWeb(request);
        UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        if (user == null)
            response.sendRedirect(request.getContextPath() + "/dang-nhap?message=not_login&alert=danger");
        else {
            MessageUtil.showMessage(request);
            request.setAttribute("userid", user.getId());
            RequestDispatcher rd = request.getRequestDispatcher("views/web/changepass.jsp");
            rd.forward(request, response);
        }
    }
}
