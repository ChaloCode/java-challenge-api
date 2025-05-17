package com.mercadolibre.model.exception;

import com.mercadolibre.model.utils.Generated;
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
