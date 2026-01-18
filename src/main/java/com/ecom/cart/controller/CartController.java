package com.ecom.cart.controller;

import com.ecom.cart.dto.CartDto;
import com.ecom.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

	private final CartService cartService;

	@GetMapping("/{userId}")
	public CartDto getCart(@PathVariable Long userId) {
		return cartService.getCart(userId);
	}

	@PostMapping("/{userId}/add/{productId}")
	public CartDto addItem(@PathVariable Long userId, @PathVariable Long productId,
			@RequestParam(defaultValue = "1") int qty) {
		return cartService.addItem(userId, productId, qty);
	}

	@DeleteMapping("/{userId}/remove/{productId}")
	public CartDto removeItem(@PathVariable Long userId, @PathVariable Long productId) {
		return cartService.removeItem(userId, productId);
	}

	@DeleteMapping("/{userId}/clear")
	public void clearCart(@PathVariable Long userId) {
		cartService.clearCart(userId);
	}
}
