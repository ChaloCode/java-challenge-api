package com.bcnc.exception;

import com.bcnc.utils.Generated;
import lombok.Getter;

@Generated
@Getter
public enum UseCaseCode {

  GENERIC("generic", 0),
  CRITICAL("critical", 1);

  private final String useCaseName;
  private final int useCaseCode;

  UseCaseCode(String useCaseName, int useCaseCode) {
    this.useCaseName = useCaseName;
    this.useCaseCode = useCaseCode;
  }

}
