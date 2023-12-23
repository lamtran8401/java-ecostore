package com.ecostore.controller.admin;

import com.ecostore.model.CommentModel;
import com.ecostore.service.ICommentService;
import com.ecostore.service.ILayoutAttributeService;
import com.ecostore.utils.MessageUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/quan-tri/binh-luan")
public class CommentController extends HttpServlet {
    @Inject
    private ILayoutAttributeService layoutAttributeService;
    @Inject
    private ICommentService commentService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        layoutAttributeService.setMenuLeftAdmin(request);
        String url = "";
        String id = request.getParameter("id");
        MessageUtil.showMessage(request);
        if (id != null) {
            CommentModel commentModel = commentService.findOneById(Long.parseLong(id));
            request.setAttribute("commentModel", commentModel);
            url = "../views/admin/editcomment.jsp";
        } else {
            List<CommentModel> comments = commentService.findAll();
            request.setAttribute("comments", comments);
            url = "../views/admin/comment.jsp";
        }
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
