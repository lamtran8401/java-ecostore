package com.ecostore.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Long, CartItem> cart = new HashMap<>();

    public Cart() {

    }

    public CartItem put(CartItem item) {
        if (item == null) return null;
        if (cart.containsKey(item.getProductId())) {
            cart.get(item.getProductId()).add();
            return cart.get(item.getProductId());
        }
        item.setQuantity(1);
        cart.put(item.getProductId(), item);
        return cart.get(item.getProductId());
    }

    public CartItem update(long productId, int quantity) {
        if (quantity < 1) return null;
        if (cart.containsKey(productId)) cart.get(productId).setQuantity(quantity);
        return cart.get(productId);
    }

    public CartItem remove(long productId) {
        return cart.remove(productId);
    }

    public long total() {
        long total = 0;
        for (CartItem item : cart.values())
            total += item.getQuantity() * item.getUnitPrice();
        return total;
    }

    public int size() {
        int size = 0;
        for (CartItem item : cart.values())
            size += item.getQuantity();
        return size;
    }

    public Collection<CartItem> data() {
        return cart.values();
    }
}
