package com.bcnc.model.exception;

import com.bcnc.model.exception.codes.MyCustomPricingCodesEnum;
import com.bcnc.model.utils.Generated;
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
