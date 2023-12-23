package com.ecostore.controller.web.api;

import com.ecostore.model.Cart;
import com.ecostore.model.CartItem;
import com.ecostore.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api-cart")
public class CartAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        CartItem item = mapper.readValue(request.getInputStream(), CartItem.class);
        Cart cart = (Cart) SessionUtil.getInstance().getValue(request, "CART");
        if (cart == null) {
            cart = new Cart();
            SessionUtil.getInstance().putValue(request, "CART", cart);
        }
        cart.put(item);
        mapper.writeValue(response.getOutputStream(), cart.data());
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        CartItem item = mapper.readValue(request.getInputStream(), CartItem.class);
        Cart cart = (Cart) SessionUtil.getInstance().getValue(request, "CART");
        if (cart == null) {
            mapper.writeValue(response.getOutputStream(), null);
            return;
        }
        cart.update(item.getProductId(),item.getQuantity());
        mapper.writeValue(response.getOutputStream(), cart.data());
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        CartItem item = mapper.readValue(request.getInputStream(), CartItem.class);
        Cart cart = (Cart) SessionUtil.getInstance().getValue(request, "CART");
        if (cart == null) {
            mapper.writeValue(response.getOutputStream(), null);
            return;
        }
        if(item.getProductId() == null) return;
        cart.remove(item.getProductId());
        mapper.writeValue(response.getOutputStream(), cart.data());
    }
}
