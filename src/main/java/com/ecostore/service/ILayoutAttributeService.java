package com.ecostore.service;

import javax.servlet.http.HttpServletRequest;

public interface ILayoutAttributeService {
    void setHeaderWeb(HttpServletRequest request);
    void setFooterWeb(HttpServletRequest request);
    void setMenuLeftAdmin(HttpServletRequest request);
}
