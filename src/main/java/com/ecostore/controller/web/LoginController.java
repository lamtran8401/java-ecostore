package com.ecostore.controller.web;

import com.ecostore.constant.SystemConstant;
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

@WebServlet(urlPatterns = {"/dang-nhap", "/thoat"})
public class LoginController extends HttpServlet {
    @Inject
    private ILayoutAttributeService layoutAttributeService;
    @Inject
    private IUserService userService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        layoutAttributeService.setHeaderWeb(request);
        layoutAttributeService.setFooterWeb(request);

        String action = request.getParameter("action");
        if (action != null && action.equalsIgnoreCase("logout")) {
            SessionUtil.getInstance().removeValue(request, "USERMODEL");
            SessionUtil.getInstance().removeValue(request, "CART");
            response.sendRedirect(request.getContextPath() + "/trang-chu");
        } else {
            MessageUtil.showMessage(request);
            // Neu da dang nhap thi redirect ve trang thong tin nguoi dung
            if (SessionUtil.getInstance().getValue(request, "USERMODEL") != null) {
                response.sendRedirect(request.getContextPath() + "/trang-chu");
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("views/web/login.jsp");
                rd.forward(request, response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserModel model = userService.checkLogin(username, password, 1);
        if (model != null) {
            SessionUtil.getInstance().putValue(request, "USERMODEL", model);
            if (model.getRole().getCode().equalsIgnoreCase(SystemConstant.ADMIN))
                response.sendRedirect(request.getContextPath() + "/quan-tri/trang-chu");
            else if (model.getRole().getCode().equalsIgnoreCase(SystemConstant.USER))
                response.sendRedirect(request.getContextPath() + "/trang-chu");
        }
        // khong ton tai account
        else {
            response.sendRedirect(request.getContextPath()
                    + "/dang-nhap?message=username_password_invalid&alert=danger");
        }
    }
}
