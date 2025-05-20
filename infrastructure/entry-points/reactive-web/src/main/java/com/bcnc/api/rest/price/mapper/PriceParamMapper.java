package com.bcnc.api.rest.price.mapper;

import com.bcnc.api.rest.price.dto.PriceParamRequest;
import com.bcnc.model.price.value.objects.PriceParam;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceParamMapper {

  PriceParam toModel(PriceParamRequest priceParamRequest);
}
