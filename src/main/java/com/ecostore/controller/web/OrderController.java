package com.ecostore.controller.web;

import com.ecostore.constant.SystemConstant;
import com.ecostore.model.OrderDetailsModel;
import com.ecostore.model.OrdersModel;
import com.ecostore.model.ProductModel;
import com.ecostore.model.UserModel;
import com.ecostore.paging.IPageble;
import com.ecostore.paging.PageRequest;
import com.ecostore.service.ILayoutAttributeService;
import com.ecostore.service.IOrderDetailService;
import com.ecostore.service.IOrderService;
import com.ecostore.service.IProductService;
import com.ecostore.sort.Sorter;
import com.ecostore.utils.FormUtil;
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
import java.util.List;


@WebServlet(urlPatterns = "/theo-doi-don-hang")
public class OrderController extends HttpServlet {
    @Inject
    private ILayoutAttributeService layoutAttributeService;
    @Inject
    private IOrderService orderService;
    @Inject
    private IOrderDetailService orderDetailService;
    @Inject
    private IProductService productService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        layoutAttributeService.setHeaderWeb(request);
        layoutAttributeService.setFooterWeb(request);
        OrdersModel model = FormUtil.toModel(OrdersModel.class, request);
        UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        String url = "";
        if (user == null)
            response.sendRedirect(request.getContextPath() + "/dang-nhap?message=not_login&alert=danger");
        else {
            String id = request.getParameter("id");
            if (id != null){
                OrdersModel order = orderService.findOneById(Long.parseLong(id));
                List<OrderDetailsModel> orderDetails = orderDetailService.findAllByOrderId(order.getId());
                for (OrderDetailsModel orderDetail: orderDetails) {
                    ProductModel product = productService.findOneById(orderDetail.getProductId());
                    orderDetail.setProduct(product);
                }
                order.setList(orderDetails);
                request.setAttribute("order", order);
                url = "views/web/orderdetails.jsp";
            } else {
                if (request.getParameter("page")==null) model.setPage(1);
                IPageble pageble = new PageRequest(model.getPage(), SystemConstant.LIMIT_ORDERS, null);
                List<OrdersModel> orders = orderService.findAllByUserId(user.getId(), pageble);
                model.setList(orders);
                model.setTotalItems(orderService.getTotalItems(user.getId()));
                model.setTotalPage((int) Math.ceil(model.getTotalItems() * 1.0 / pageble.getLimit()));
                request.setAttribute("model", model);
                url = "views/web/orderhistory.jsp";

            }
            MessageUtil.showMessage(request);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }

    }

}
