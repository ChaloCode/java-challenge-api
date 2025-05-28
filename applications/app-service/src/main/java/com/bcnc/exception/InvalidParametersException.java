package com.bcnc.exception;

import com.bcnc.exception.codes.MyCustomCodes;
import com.bcnc.utils.Generated;
import lombok.Getter;

@Generated
@Getter
public class InvalidParametersException extends RuntimeException {
  private final MyCustomCodes pricingCodesEnum;

  public InvalidParametersException(MyCustomCodes myCustomCodes, String message) {
    super(message);
    this.pricingCodesEnum = myCustomCodes;
  }
}
