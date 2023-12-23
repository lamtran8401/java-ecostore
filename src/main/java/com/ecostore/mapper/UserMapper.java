package com.ecostore.mapper;

import com.ecostore.model.RoleModel;
import com.ecostore.model.UserModel;
import com.ecostore.utils.MapperUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements IRowMapper<UserModel> {
    @Override
    public UserModel mapRow(ResultSet resultSet) {
        try {
            UserModel user = new UserModel();
            user.setId(resultSet.getLong("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setFullname(resultSet.getString("fullname"));
            user.setEmail(resultSet.getString("email"));
            user.setPhone(resultSet.getString("phone"));
            user.setAvatar(resultSet.getString("avatar"));
            user.setStatus(resultSet.getInt("status"));
            user.setRoleId(resultSet.getLong("roleid"));
            user.setKeycode(resultSet.getString("keycode"));
            user.setKeytime(resultSet.getTimestamp("keytime"));
            RoleModel role = new RoleModel();
            if (MapperUtil.hasColumn(resultSet, "code"))
                role.setCode(resultSet.getString("code"));
            if (MapperUtil.hasColumn(resultSet, "name"))
                role.setName(resultSet.getString("name"));
            user.setRole(role);
            user.setCreatedDate(resultSet.getTimestamp("createddate"));
            user.setCreatedBy(resultSet.getString("createdby"));
            user.setModifiedDate(resultSet.getTimestamp("modifieddate"));
            user.setModifiedBy(resultSet.getString("modifiedby"));

            return user;
        } catch (SQLException throwables) {
            return null;
        }
    }
}
