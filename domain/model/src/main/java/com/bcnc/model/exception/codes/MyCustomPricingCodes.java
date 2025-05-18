package com.bcnc.model.exception.codes;


import com.bcnc.model.exception.UseCaseCode;
import com.bcnc.model.utils.Generated;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Generated
public enum MyCustomPricingCodes {

  MY_CUSTOM_PRICING_CODES_ENUM("1", UseCaseCode.GENERIC, "Generic Bad Request Example", HttpStatus.BAD_REQUEST.value());

  public static final String SEPARATOR = "-";
  private final String errorCode;
  private final UseCaseCode useCase;
  @Getter
  private final String message;
  @Getter
  private final int statusCode;

  MyCustomPricingCodes(String errorCode, UseCaseCode useCase, String message, int statusCode) {
    this.errorCode = errorCode;
    this.useCase = useCase;
    this.message = message;
    this.statusCode = statusCode;
  }

  public String getErrorCode() {
    return String.format("%s%s%s%s%s", useCase.getUseCaseCode(), SEPARATOR, errorCode, SEPARATOR, statusCode);
  }
}
