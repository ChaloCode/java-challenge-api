package com.bcnc.r2dbc.models.price;

import com.bcnc.model.price.Price;
import com.bcnc.model.price.PriceParam;
import com.bcnc.r2dbc.helper.AdapterOperations;
import com.bcnc.usecase.repository.PriceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.convert.MappingR2dbcConverter;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@Slf4j
public class PriceService extends AdapterOperations<Price, PriceData, Long, PriceDataRepository> implements PriceRepository {

  private final MappingR2dbcConverter converter;
  private final DatabaseClient client;
  private final PriceMapper priceMapper;

  protected PriceService(PriceDataRepository repository, ObjectMapper mapper, DatabaseClient client,
                         MappingR2dbcConverter converter, PriceMapper priceMapper) {
    super(repository, mapper,
        driverData -> mapper.convertValue(driverData, Price.class));

    this.client = client;
    this.converter = converter;
    this.priceMapper = priceMapper;
  }

  @Override
  public Flux<Price> getPrices(PriceParam param) {
    return repository.findAllValidPricesForProductAndBrandAt(
            param.brandId(),
            param.productId(),
            param.toLocalDateTime())
        .map(priceMapper::toDomain);
  }

}
