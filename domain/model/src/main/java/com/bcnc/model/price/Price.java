package com.bcnc.model.price;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Price {

  @NotNull(message = "Brand ID no puede ser nulo")
  private Long brandId;

  @NotNull(message = "Start Date no puede ser nulo")
  private LocalDateTime startDate;

  @NotNull(message = "End Date no puede ser nulo")
  @FutureOrPresent(message = "End Date debe ser una fecha futura o presente")
  private LocalDateTime endDate;

  @NotNull(message = "Price List no puede ser nulo")
  @Positive(message = "Price List debe ser un número positivo")
  private Integer priceList;

  @NotNull(message = "Product ID no puede ser nulo")
  @Positive(message = "Product ID debe ser un número positivo")
  private Long productId;

  @Min(value = 0, message = "Priority no puede ser menor que 0")
  private int priority;

  @NotNull(message = "Price no puede ser nulo")
  @DecimalMin(value = "0.0", inclusive = false, message = "Price debe ser mayor que 0")
  private double price;

  @NotBlank(message = "Currency no puede estar vacío")
  @Size(min = 3, max = 3, message = "Currency debe tener exactamente 3 caracteres")
  private String currency;
}