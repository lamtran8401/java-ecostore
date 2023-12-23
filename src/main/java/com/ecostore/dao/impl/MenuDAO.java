package com.ecostore.dao.impl;

import com.ecostore.dao.IMenuDAO;
import com.ecostore.mapper.MenuMapper;
import com.ecostore.model.MenuModel;

import java.util.List;

public class MenuDAO extends AbstractDAO<MenuModel> implements IMenuDAO {
    @Override
    public List<MenuModel> findAllByMenuTypeId(long menuTypeId, int status) {
        String sql = "SELECT m.*, mt.name as menutypename FROM menu m join menutype mt on m.menutypeid = mt.id WHERE m.menutypeid = ? AND m.status = ?";
        return query(sql, new MenuMapper(), menuTypeId, status);
    }

    @Override
    public List<MenuModel> findAll() {
        String sql = "SELECT m.*, mt.name as menutypename FROM menu m join menutype mt on m.menutypeid = mt.id";
        return query(sql, new MenuMapper());
    }

    @Override
    public MenuModel findOneById(long id) {
        String sql = "SELECT m.*, mt.name as menutypename FROM menu m join menutype mt on m.menutypeid = mt.id WHERE m.id = ?";
        List<MenuModel> menus = query(sql, new MenuMapper(), id);
        if (menus.size() == 0) return null;
        return menus.get(0);
    }

    @Override
    public Long insert(MenuModel menuModel) {
        String sql = "INSERT INTO menu (name,link,status,menutypeid,createddate,createdby) VALUES (?,?,?,?,?,?)";
        return insert(sql, menuModel.getName(), menuModel.getLink(),
                menuModel.getStatus(), menuModel.getMenuTypeId(), menuModel.getCreatedDate(), menuModel.getCreatedBy());
    }

    @Override
    public boolean update(MenuModel menuModel) {
        String sql = "UPDATE menu SET name = ?, link = ?, status = ?, menutypeid = ?, modifieddate = ?, modifiedby = ? WHERE id = ?";
        return update(sql, menuModel.getName(), menuModel.getLink(), menuModel.getStatus(),
                menuModel.getMenuTypeId(), menuModel.getModifiedDate(), menuModel.getModifiedBy(), menuModel.getId());
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM menu WHERE id = ?";
        return update(sql, id);
    }
}
