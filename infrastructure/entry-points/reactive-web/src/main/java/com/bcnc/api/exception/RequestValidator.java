package com.bcnc.api.exception;

import com.bcnc.model.exception.MyCustomException;
import com.bcnc.model.exception.codes.MyCustomPricingCodes;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.val;
import org.apache.commons.lang3.StringUtils;

public class RequestValidator {
  private RequestValidator() {
  }

  public static <T> T validate(T data, MyCustomPricingCodes pricingCodesEnum) {
    val concatCharacter = "%s - %s";
    val errorMessage = "Validate error on '%s': ";
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    Set<ConstraintViolation<T>> violations = validator.validate(data);
    if (violations.isEmpty()) {
      return data;
    } else {
      String message = violations.stream()
          .map(v -> String.format(concatCharacter, v.getPropertyPath(), v.getMessage()))
          .collect(Collectors.joining(";",
              String.format(errorMessage, data.getClass().getSimpleName()), StringUtils.EMPTY));
      throw new MyCustomException(pricingCodesEnum, message);
    }
  }
}
