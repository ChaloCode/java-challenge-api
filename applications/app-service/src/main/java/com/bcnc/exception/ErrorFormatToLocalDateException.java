package com.bcnc.exception;

import com.bcnc.exception.codes.MyCustomCodes;
import com.bcnc.utils.Generated;
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
