package com.bcnc.exception.codes;

import com.bcnc.exception.UseCaseCode;
import com.bcnc.utils.Generated;
import lombok.Getter;

@Generated
public enum MyCustomCodes {

  ERROR_VALIDATING_REQUEST("1", UseCaseCode.GENERIC, "Bad Request"),
  ILLEGAL_ARGUMENT("1", UseCaseCode.CRITICAL, "Illegal Argument");

  public static final String SEPARATOR = "-";
  private final UseCaseCode useCase;
  private final String errorCode;
  @Getter
  private final String message;


  MyCustomCodes(String errorCode, UseCaseCode useCase, String message) {
    this.errorCode = errorCode;
    this.useCase = useCase;
    this.message = message;
  }

  public String getErrorCode() {
    return String.format("%s%s%s", useCase.getUseCaseCode(), SEPARATOR, errorCode);
  }
}
