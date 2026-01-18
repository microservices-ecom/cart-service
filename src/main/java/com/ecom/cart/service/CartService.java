package com.ecom.cart.service;

import com.ecom.cart.dto.CartDto;

public interface CartService {

	CartDto getCart(Long userId);

	CartDto addItem(Long userId, Long productId, int qty);

	CartDto removeItem(Long userId, Long productId);

	void clearCart(Long userId);

}
