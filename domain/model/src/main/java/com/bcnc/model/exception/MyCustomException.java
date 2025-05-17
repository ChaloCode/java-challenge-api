package com.mercadolibre.model.exception;

import com.mercadolibre.model.exception.codes.MyCustomPricingCodesEnum;
import com.mercadolibre.model.utils.Generated;
import lombok.Getter;

@Generated
@Getter
public class MyCustomException extends RuntimeException {
  private final MyCustomPricingCodesEnum pricingCodesEnum;

  public MyCustomException(MyCustomPricingCodesEnum myCustomPricingCodesEnum, String message) {
    super(message);
    this.pricingCodesEnum = myCustomPricingCodesEnum;
  }

  public MyCustomException(MyCustomPricingCodesEnum myCustomPricingCodesEnum) {
    super(myCustomPricingCodesEnum.getMessage());
    this.pricingCodesEnum = myCustomPricingCodesEnum;
  }
}
