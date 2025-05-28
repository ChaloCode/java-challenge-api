package com.bcnc.usecase.price;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bcnc.model.price.entities.Price;
import com.bcnc.model.price.value.objects.PriceParam;
import com.bcnc.model.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(MockitoExtension.class)
class PriceUseCaseTest {

  @Mock
  private PriceRepository priceRepository;

  @InjectMocks
  private PriceUseCase priceUseCase;

  private PodamFactory podamFactory;
  private PriceParam priceParam;

  @BeforeEach
  void setUp() {
    podamFactory = new PodamFactoryImpl();
    priceParam = podamFactory.manufacturePojo(PriceParam.class);
  }

  @Test
  void shouldReturnHighestPriorityPrice() {
    Price highPriorityPrice = podamFactory.manufacturePojo(Price.class).toBuilder()
        .priority(1)
        .build();

    when(priceRepository.getPrices(priceParam))
        .thenReturn(Mono.just(highPriorityPrice));

    StepVerifier.create(priceUseCase.execute(priceParam))
        .assertNext(finalPrice -> {
          assertEquals(highPriorityPrice.productId(), finalPrice.productId());
          assertEquals(highPriorityPrice.brandId(), finalPrice.brandId());
          assertEquals(highPriorityPrice.priceList(), finalPrice.priceList());
          assertEquals(highPriorityPrice.price(), finalPrice.price());
        })
        .verifyComplete();

    verify(priceRepository).getPrices(priceParam);
  }

  @Test
  void shouldReturnEmptyWhenNoPricesAvailable() {
    when(priceRepository.getPrices(priceParam)).thenReturn(Mono.empty());

    StepVerifier.create(priceUseCase.execute(priceParam))
        .verifyComplete();

    verify(priceRepository).getPrices(priceParam);
  }

  @Test
  void shouldHandleErrorWhenRepositoryFails() {
    when(priceRepository.getPrices(priceParam))
        .thenReturn(Mono.error(new RuntimeException("Repository error")));

    StepVerifier.create(priceUseCase.execute(priceParam))
        .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
            throwable.getMessage().equals("Repository error"))
        .verify();

    verify(priceRepository).getPrices(priceParam);
  }
}