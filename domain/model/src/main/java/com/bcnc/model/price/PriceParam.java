package com.bcnc.model.price;

import com.bcnc.model.exception.ErrorFormatToLocalDateException;
import com.bcnc.model.exception.codes.MyCustomCodes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Builder(toBuilder = true)
@Slf4j
public record PriceParam(
    Long brandId,
    Long productId,
    String consultationDate
) {

  public LocalDateTime toLocalDateTime() {
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      String date = consultationDate.contains(" ")
          ? consultationDate
          : consultationDate + " 00:00:00";
      return LocalDateTime.parse(date, formatter);
    } catch (Exception e) {
      log.error("Error al parsear la fecha: {}", consultationDate, e);
      throw new ErrorFormatToLocalDateException(MyCustomCodes.ILLEGAL_ARGUMENT, "Formato de fecha no válido: " + consultationDate);
    }
  }

}

