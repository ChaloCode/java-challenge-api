package com.bcnc.model.exception;

import com.bcnc.model.exception.codes.MyCustomCodes;
import com.bcnc.model.utils.Generated;
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
