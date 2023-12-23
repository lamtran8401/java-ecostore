package com.ecostore.controller.admin;

import com.ecostore.model.PrivacyPolicyModel;
import com.ecostore.service.ILayoutAttributeService;
import com.ecostore.service.IPrivacyPolicyService;
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

@WebServlet(urlPatterns = "/quan-tri/chinh-sach-bao-mat")
public class PrivacyPolicyController extends HttpServlet {
    @Inject
    private ILayoutAttributeService layoutAttributeService;
    @Inject
    private IPrivacyPolicyService privacyPolicyService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        layoutAttributeService.setMenuLeftAdmin(request);
        String url = "";
        String id = request.getParameter("id");
        if (id != null) {
            PrivacyPolicyModel privacyPolicyModel = privacyPolicyService.findOneById(Long.parseLong(id));
            request.setAttribute("privacyPolicyModel", privacyPolicyModel);
            url = "../views/admin/editprivacypolicy.jsp";
        } else {

            List<PrivacyPolicyModel> privacyPolicyModels = privacyPolicyService.findAll();
            request.setAttribute("privacyPolicyModels", privacyPolicyModels);
            url = "../views/admin/privacypolicy.jsp";
        }
        MessageUtil.showMessage(request);
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
