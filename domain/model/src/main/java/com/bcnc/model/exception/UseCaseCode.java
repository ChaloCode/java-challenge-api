package com.bcnc.model.exception;

import com.bcnc.model.utils.Generated;
import lombok.Getter;

@Generated
@Getter
public enum UseCaseCode {

  GENERIC("generic", 0);

  private final String useCaseName;
  private final int useCaseCode;

  UseCaseCode(String useCaseName, int useCaseCode) {
    this.useCaseName = useCaseName;
    this.useCaseCode = useCaseCode;
  }

}
