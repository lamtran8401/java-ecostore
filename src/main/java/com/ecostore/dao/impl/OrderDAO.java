package com.ecostore.dao.impl;

import com.ecostore.dao.IOrderDAO;
import com.ecostore.mapper.OrdersMapper;
import com.ecostore.model.OrdersModel;
import com.ecostore.paging.IPageble;

import java.util.List;

public class OrderDAO extends AbstractDAO<OrdersModel> implements IOrderDAO {

    @Override
    public long insert(OrdersModel model) {
        String sql = "INSERT INTO orders (userid, paymentid, customername, phone, address,message,totalprice,status,createddate,createdby) VALUES (?,?,?,?,?,?,?,?,?,?)";
        return insert(sql, model.getUserId(), model.getPaymentId(), model.getCustomerName(), model.getPhone(),
                model.getAddress(), model.getMessage(), model.getTotalPrice(), model.getStatus(), model.getCreatedDate(), model.getCreatedBy());
    }

    @Override
    public boolean update(OrdersModel model) {
        String sql = "UPDATE orders SET customername = ?, phone = ?, address = ?, message = ?, totalprice = ?,paymentid = ?, status = ?, modifieddate = ?, modifiedby = ? WHERE id = ?";
        return update(sql, model.getCustomerName(), model.getPhone(), model.getAddress(),
                model.getMessage(), model.getTotalPrice(), model.getPaymentId(),
                model.getStatus(), model.getModifiedDate(), model.getModifiedBy(), model.getId());
    }

    @Override
    public OrdersModel findOneById(long id) {
        String sql = "SELECT orders.*, payment.name AS namepayment FROM orders JOIN payment on orders.paymentid = payment.id  WHERE orders.id = ?";
        List<OrdersModel> ordersModels = query(sql, new OrdersMapper(), id);
        return ordersModels.isEmpty() ? null : ordersModels.get(0);
    }

    @Override
    public List<OrdersModel> findAll() {
        String sql = "SELECT orders.*, payment.name AS namepayment FROM orders JOIN payment on orders.paymentid = payment.id";
        return query(sql, new OrdersMapper());
    }

    @Override
    public List<OrdersModel> findAllByUserId(long userid, IPageble pageble) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT orders.*, payment.name AS namepayment FROM orders JOIN payment on orders.paymentid = payment.id WHERE userid = ? ORDER BY orders.createddate desc");
        if (pageble.getOffset() != null && pageble.getLimit() != null) {
            sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit());
        }
        return query(sql.toString(), new OrdersMapper(), userid);
    }
    @Override
    public int getTotalItems(Long userid) {
        String sql = "SELECT COUNT(*) FROM orders JOIN payment on orders.paymentid = payment.id WHERE orders.userid = ?";
        return count(sql,userid);
    }

    @Override
    public int getTotalOrders() {
        String sql = "SELECT COUNT(*) FROM orders";
        return count(sql);
    }

    @Override
    public List<OrdersModel> findAllByStatus(int status) {
        String sql = "SELECT * FROM orders WHERE status = ?";
        return query(sql, new OrdersMapper(), status);
    }

    @Override
    public OrdersModel findOneByUserId(Long userid) {
        String sql = "SELECT orders.*, payment.name AS namepayment FROM orders JOIN payment on orders.paymentid = payment.id WHERE userid = ?";
        List<OrdersModel> ordersModels = query(sql, new OrdersMapper(), userid);
        return ordersModels.isEmpty() ? null : ordersModels.get(0);
    }


}
