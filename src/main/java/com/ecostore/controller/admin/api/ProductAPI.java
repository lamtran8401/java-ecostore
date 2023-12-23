package com.ecostore.controller.admin.api;

import com.ecostore.constant.SystemConstant;
import com.ecostore.model.AboutModel;
import com.ecostore.model.ProductModel;
import com.ecostore.model.UploadFileModel;
import com.ecostore.model.UserModel;
import com.ecostore.service.IProductService;
import com.ecostore.utils.SessionUtil;
import com.ecostore.utils.UploadFileUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

@WebServlet(urlPatterns = "/api-admin-products")
public class ProductAPI extends HttpServlet {

    @Inject
    private IProductService productService;
    @Inject
    private UploadFileUtil uploadFileUtil;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        ProductModel productModel = mapper.readValue(request.getInputStream(), ProductModel.class);
        UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        productModel.setCreatedBy(userModel.getUsername());

        for (UploadFileModel u : productModel.getUploadFiles()) {
            byte[] decodeBase64 = Base64.getDecoder().decode(u.getBase64().getBytes()); // convert base64 ve mang byte[]
            String path = request.getServletContext().getRealPath(File.separator) + SystemConstant.PRODUCT_DIR;
            uploadFileUtil.writeOrUpdate(decodeBase64, path + u.getName());
            productModel.addImage(SystemConstant.PRODUCT_DIR + u.getName());
        }
        productModel = productService.insert(productModel);
        mapper.writeValue(response.getOutputStream(), productModel);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        ProductModel productModel = mapper.readValue(request.getInputStream(), ProductModel.class);
        UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        productModel.setModifiedBy(userModel.getUsername());

        if (productModel.getUploadFiles().size() != 0) {
            ProductModel oldProduct = productService.findOneById(productModel.getId());
            for (String path : oldProduct.getImages())
                uploadFileUtil.delete(request.getServletContext().getRealPath(File.separator) + path);
            for (UploadFileModel u : productModel.getUploadFiles()) {
                byte[] decodeBase64 = Base64.getDecoder().decode(u.getBase64().getBytes()); // convert base64 ve mang byte[]
                String path = request.getServletContext().getRealPath(File.separator) + SystemConstant.PRODUCT_DIR;
                uploadFileUtil.writeOrUpdate(decodeBase64, path + u.getName());
                productModel.addImage(SystemConstant.PRODUCT_DIR + u.getName());
            }
        }
        productModel = productService.update(productModel);
        mapper.writeValue(response.getOutputStream(), productModel);
    }
}
