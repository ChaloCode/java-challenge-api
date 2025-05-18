package com.bcnc.model.exception;

import com.bcnc.model.exception.codes.MyCustomPricingCodes;
import com.bcnc.model.utils.Generated;
import lombok.Getter;

@Generated
@Getter
public class MyCustomException extends RuntimeException {
  private final MyCustomPricingCodes pricingCodesEnum;

  public MyCustomException(MyCustomPricingCodes myCustomPricingCodes, String message) {
    super(message);
    this.pricingCodesEnum = myCustomPricingCodes;
  }

  public MyCustomException(MyCustomPricingCodes myCustomPricingCodes) {
    super(myCustomPricingCodes.getMessage());
    this.pricingCodesEnum = myCustomPricingCodes;
  }
}
