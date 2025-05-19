package com.bcnc.r2dbc.models.price;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prices")
public class PriceData {

  @Id
  private Long id;

  @Column("brand_id")
  private Long brandId;

  @Column("start_date")
  private LocalDateTime startDate;

  @Column("end_date")
  private LocalDateTime endDate;

  @Column("price_list")
  private Integer priceList;

  @Column("product_id")
  private Long productId;

  @Column("priority")
  private int priority;

  @Column("price")
  private double price;

  @Column("currency")
  private String currency;

}
