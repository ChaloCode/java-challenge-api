package com.bcnc.r2dbc.models.price;

import com.bcnc.model.price.Price;
import com.bcnc.r2dbc.helper.AdapterOperations;
import com.bcnc.usecase.repository.PriceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.r2dbc.convert.MappingR2dbcConverter;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Repository
public class PriceService extends AdapterOperations<Price, PriceData, Long, PriceDataRepository> implements PriceRepository {

  private final MappingR2dbcConverter converter;
  private final DatabaseClient client;

  protected PriceService(PriceDataRepository repository, ObjectMapper mapper, DatabaseClient client,
                         MappingR2dbcConverter converter) {
    super(repository, mapper,
        driverData -> mapper.convertValue(driverData, Price.class));

    this.client = client;
    this.converter = converter;
  }

  @Transactional
  @Override
  public Mono<Price> saveOrUpdate(Price price) {
    return repository.findAllByBrandIdAndProductIdAndPriceListAndPriority(price.getBrandId(), price.getProductId(), price.getPriceList(),
            price.getPriority())
        .map(priceData -> priceData.toBuilder()
            .startDate(priceData.getStartDate())
            .endDate(priceData.getEndDate())
            .price(priceData.getPrice())
            .currency(priceData.getCurrency())
            .build())
        .flatMap(repository::save)
        .map(this::toEntity)
        .switchIfEmpty(Mono.defer(() -> repository.save(toData(price).toBuilder().build()).map(this::toEntity)))
        .onErrorResume(DuplicateKeyException.class::isInstance,
            value -> repository.findAllByBrandIdAndProductIdAndPriceListAndPriority(price.getBrandId(), price.getProductId(),
                    price.getPriceList(), price.getPriority())
                .map(this::toEntity));
  }
}
