package com.ecostore.controller.admin.api;


import com.ecostore.constant.SystemConstant;
import com.ecostore.model.SlideModel;
import com.ecostore.model.UserModel;
import com.ecostore.service.ISlideService;
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

@WebServlet(urlPatterns = "/api-admin-banner")
public class BannerAPI extends HttpServlet {
    @Inject
    private ISlideService slideService;
    @Inject
    private UploadFileUtil uploadFile;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        SlideModel slide = mapper.readValue(request.getInputStream(), SlideModel.class);
        UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        SlideModel slideDisplayorderExist = slideService.findOneByStatusAndDisplayorder(slide.getDisplayorder());
        //kiem tra neu displayorder cua slide moi them vao trung voi displayorder slide cu van con hoat dong
        if (slideDisplayorderExist!= null){
            slide = null;
        } else {
            String image = null;
            if (slide.getUploadFile().getBase64() != null) {
                byte[] decodeBase64 = Base64.getDecoder().decode(slide.getUploadFile().getBase64().getBytes()); // convert base64 ve mang byte[]
                String path = request.getServletContext().getRealPath(File.separator) + SystemConstant.BANNER_DIR;
                uploadFile.writeOrUpdate(decodeBase64, path + slide.getUploadFile().getName());
                image = SystemConstant.BANNER_DIR + slide.getUploadFile().getName();
            }
            slide.setImageLink(image);
            slide.setCreatedBy(userModel.getUsername());
            slide = slideService.insert(slide);
        }
        mapper.writeValue(response.getOutputStream(), slide);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        SlideModel slide = mapper.readValue(request.getInputStream(), SlideModel.class);
        SlideModel slideOld = slideService.findOneById(slide.getId());
        UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        SlideModel slideDisplayorderExist = slideService.findOneByStatusAndDisplayorder(slide.getDisplayorder());
        //kiem tra neu displayorder cua slide moi them vao trung voi displayorder slide cu van con hoat dong
        if (slideDisplayorderExist!= null && slideDisplayorderExist.getId() != slide.getId()){
            slide = null;
        } else {
            String image = slideOld.getImageLink();
            if (slide.getUploadFile().getBase64() != null) {
                byte[] decodeBase64 = Base64.getDecoder().decode(slide.getUploadFile().getBase64().getBytes()); // convert base64 ve mang byte[]
                String path = request.getServletContext().getRealPath(File.separator) + SystemConstant.BANNER_DIR;
                uploadFile.writeOrUpdate(decodeBase64, path + slide.getUploadFile().getName());
                image = SystemConstant.BANNER_DIR + slide.getUploadFile().getName();
            }
            slide.setImageLink(image);
            slide.setModifiedBy(userModel.getUsername());
            slide = slideService.update(slide);
        }

        mapper.writeValue(response.getOutputStream(), slide);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        SlideModel slide = mapper.readValue(request.getInputStream(), SlideModel.class);
        boolean result = slideService.delete(slide.getIds());
        mapper.writeValue(response.getOutputStream(), result);
    }
}
