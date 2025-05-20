package com.bcnc.r2dbc.models.price;

import com.bcnc.model.price.entities.Price;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {
  Price toDomain(PriceData priceData);
}
