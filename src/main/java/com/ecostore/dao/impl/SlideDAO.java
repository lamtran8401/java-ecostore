package com.ecostore.dao.impl;

import com.ecostore.dao.ISlideDAO;
import com.ecostore.mapper.SlideMapper;
import com.ecostore.model.SlideModel;

import java.util.List;

public class SlideDAO extends AbstractDAO<SlideModel> implements ISlideDAO {
    @Override
    public Long insert(SlideModel model) {
        String sql = "INSERT INTO slide (title, description, displayorder, imagelink, status, createddate,createdby) VALUES (?,?,?,?,?,?,?)";
        return insert(sql, model.getTitle(), model.getDescription(),  model.getDisplayorder(), model.getImageLink() ,model.getStatus(), model.getCreatedDate(),model.getCreatedBy());
    }

    @Override
    public List<SlideModel> findAll() {
        String sql = "SELECT * FROM slide";
        return query(sql, new SlideMapper());
    }

    @Override
    public List<SlideModel> findAllByStatus() {
        String sql = "SELECT * FROM slide WHERE status = 1 ORDER BY displayorder asc";
        return query(sql, new SlideMapper());
    }

    @Override
    public SlideModel findOneByStatusAndDisplayorder(int displayorder) {
        String sql = "SELECT * FROM slide WHERE status = 1 and displayorder = ?";
        List<SlideModel> slides = query(sql, new SlideMapper(), displayorder);
        return slides.isEmpty() ? null : slides.get(0);
    }

    @Override
    public SlideModel findOneById(long id) {
        String sql ="SELECT * FROM slide WHERE id = ?";
        List<SlideModel> slides = query(sql, new SlideMapper(), id);
        return slides.isEmpty() ? null : slides.get(0);
    }

    @Override
    public boolean update(SlideModel model) {
        String sql = "UPDATE slide SET title = ?, description = ?, imagelink = ?, displayorder = ?, status = ?, modifieddate = ?, modifiedby = ? WHERE id = ?";
        return update(sql, model.getTitle(), model.getDescription(),model.getImageLink(), model.getDisplayorder(), model.getStatus(), model.getModifiedDate(),model.getModifiedBy(), model.getId());
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM slide WHERE id = ?";
        return update(sql, id);
    }
}
