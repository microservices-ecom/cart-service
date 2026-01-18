package com.ecom.cart.service.impl;

import com.ecom.cart.dto.CartDto;
import com.ecom.cart.mapper.CartMapper;
import com.ecom.cart.model.Cart;
import com.ecom.cart.model.CartItem;
import com.ecom.cart.repository.CartRepository;
import com.ecom.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

	private final CartRepository cartRepository;
	private final CartMapper cartMapper;

	@Override
	public CartDto getCart(Long userId) {
		Cart cart = cartRepository.findByUserId(userId)
				.orElseGet(() -> cartRepository.save(Cart.builder().userId(userId).createdAt(Instant.now()).build()));

		return cartMapper.toDto(cart);
	}

	@Override
	public CartDto addItem(Long userId, Long productId, int qty) {
		Cart cart = cartRepository.findByUserId(userId).orElseThrow();

		cart.getItems().stream().filter(i -> i.getProductId().equals(productId)).findFirst().ifPresentOrElse(
				i -> i.setQuantity(i.getQuantity() + qty),
				() -> cart.getItems().add(CartItem.builder().cart(cart).productId(productId).quantity(qty).build()));

		cart.setUpdatedAt(Instant.now());
		return cartMapper.toDto(cartRepository.save(cart));
	}

	@Override
	public CartDto removeItem(Long userId, Long productId) {
		Cart cart = cartRepository.findByUserId(userId).orElseThrow();

		cart.getItems().removeIf(i -> i.getProductId().equals(productId));
		return cartMapper.toDto(cartRepository.save(cart));
	}

	@Override
	public void clearCart(Long userId) {
		Cart cart = cartRepository.findByUserId(userId).orElseThrow();
		cart.getItems().clear();
		cartRepository.save(cart);
	}
}
