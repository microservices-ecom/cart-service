package com.ecom.cart.mapper;

import com.ecom.cart.dto.CartDto;
import com.ecom.cart.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartMapper {

	CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

	CartDto toDto(Cart cart);

	Cart toEntity(CartDto dto);

}
