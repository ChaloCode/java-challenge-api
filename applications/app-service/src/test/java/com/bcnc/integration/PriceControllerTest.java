package com.bcnc.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import lombok.val;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class PriceControllerTest extends ControllerTest {

  private final String URL = "/api/brands/%s/products/%s/prices/%s";

  private String getURL(String brandId, String productId, String consultationDate) {
    return String.format(URL, brandId, productId, consultationDate);
  }

  private void assertResponse(String brandId, String productId, String consultationDate, String expectedJson, HttpStatus expectedStatus)
      throws Exception {
    ResponseEntity<String> responseEntity =
        this.testRestTemplate.exchange(
            getURL(brandId, productId, consultationDate), HttpMethod.GET, this.getDefaultRequestEntity(), String.class);

    assertEquals(expectedStatus, responseEntity.getStatusCode());
    if (expectedJson != null) {
      val expected = expectedMap(expectedJson);
      val actual = getMap(responseEntity.getBody());
      assertEquals(expected, actual);
    } else {
      assertNull(responseEntity.getBody());
    }
  }

  @Nested
  class ValidResponses {

    @ParameterizedTest
    @CsvSource({
        "1, 35455, 2020-06-14 10:00:00, ResponseOK1.json",
        "1, 35455, 2020-06-14 16:00:00, ResponseOK2.json",
        "1, 35455, 2020-06-14 21:00:00, ResponseOK3.json",
        "1, 35455, 2020-06-15 10:00:00, ResponseOK4.json",
        "1, 35455, 2020-06-16 21:00:00, ResponseOK5.json"
    })
    void shouldGetPriceResponseOK(String brandId, String productId, String consultationDate, String expectedJson) throws Exception {
      assertResponse(brandId, productId, consultationDate, expectedJson, HttpStatus.OK);
    }
  }

  @Nested
  class InvalidResponses {

    @Test
    void shouldGetPriceResponseBadRequest() throws Exception {
      assertResponse("-1", "35455", "2020-06-14 10:00:00", "ResponseBadRequest.json", HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldGetPriceResponseNotContent() throws Exception {
      assertResponse("100", "35455", "2020-06-14 10:00:00", null, HttpStatus.NO_CONTENT);
    }

    @Test
    void shouldGetPriceResponseNotFound() throws Exception {
      String url = getURL("100", "35455", "2020-06-14 10:00:00");
      ResponseEntity<String> responseEntity =
          testRestTemplate.exchange(
              String.format("%s/error", url), HttpMethod.GET, getDefaultRequestEntity(), String.class);

      val expected = expectedMap("ResponseNotFound.json");
      val actual = getMap(responseEntity.getBody());

      assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
      assertEquals(expected, actual);
    }
  }
}