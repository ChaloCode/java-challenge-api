package com.bcnc.model.exception;

import com.bcnc.model.utils.Generated;
import lombok.Getter;

@Generated
@Getter
public enum UseCaseEnum {

  GENERIC("generic", 0);

  private final String useCaseName;
  private final int useCaseCode;

  UseCaseEnum(String useCaseName, int useCaseCode) {
    this.useCaseName = useCaseName;
    this.useCaseCode = useCaseCode;
  }

}
