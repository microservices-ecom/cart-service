package com.ecom.cart.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {

	private Long productId;
	private Integer quantity;

}
