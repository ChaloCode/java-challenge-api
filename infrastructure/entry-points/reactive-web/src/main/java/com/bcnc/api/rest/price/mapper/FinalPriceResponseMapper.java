package com.bcnc.api.rest.price.mapper;

import com.bcnc.api.rest.price.dto.FinalPriceResponse;
import com.bcnc.model.price.value.objects.FinalPrice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FinalPriceResponseMapper {
  FinalPriceResponse toResponse(FinalPrice finalPrice);
}
