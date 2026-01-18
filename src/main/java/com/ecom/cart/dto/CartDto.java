package com.ecom.cart.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

	private Long userId;
	private List<CartItemDto> items;

}
