package com.bcnc.model.exception;

import com.bcnc.model.exception.codes.MyCustomCodes;
import com.bcnc.model.utils.Generated;
import lombok.Getter;

@Generated
@Getter
public class ErrorFormatToLocalDateException extends RuntimeException {
  private final MyCustomCodes pricingCodesEnum;

  public ErrorFormatToLocalDateException(MyCustomCodes myCustomCodes, String message) {
    super(message);
    this.pricingCodesEnum = myCustomCodes;
  }
}
