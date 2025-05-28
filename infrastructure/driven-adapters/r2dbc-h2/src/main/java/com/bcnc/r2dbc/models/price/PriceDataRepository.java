package com.bcnc.r2dbc.models.price;

import java.time.LocalDateTime;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PriceDataRepository extends ReactiveCrudRepository<PriceData, Long>,
    ReactiveQueryByExampleExecutor<PriceData> {


  @Query("""
          SELECT * FROM prices
          WHERE brand_id = :brandId
            AND product_id = :productId
            AND start_date <= :consultationDate
            AND end_date >= :consultationDate
          ORDER BY priority DESC
          LIMIT 1
      """)
  Mono<PriceData> findAllValidPricesForProductAndBrandAt(
      @Param("brandId") Long brandId,
      @Param("productId") Long productId,
      @Param("consultationDate") LocalDateTime consultationDate
  );
}
