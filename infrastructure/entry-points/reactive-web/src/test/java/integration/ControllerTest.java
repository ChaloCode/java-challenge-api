package integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(profiles = "test")
public class ControllerTest extends IntegrationTest {

  @Autowired
  protected TestRestTemplate testRestTemplate;

  protected <T> RequestEntity<T> getDefaultRequestEntity() {
    HttpHeaders headers = new HttpHeaders();
    return new RequestEntity<>(headers, HttpMethod.GET, null);
  }

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
  }

  public String loadJsonFromFile(String filePath) throws Exception {
    try (var is = getClass().getClassLoader().getResourceAsStream(filePath.startsWith("/") ? filePath.substring(1) : filePath)) {
      if (is == null) {
        throw new IllegalArgumentException("Archivo no encontrado: " + filePath);
      }
      return new String(is.readAllBytes());
    }
  }

  public Map getMap(String expectedJson) throws JsonProcessingException {
    return objectMapper.readValue(expectedJson, Map.class);
  }

  public Map expectedMap(String file) throws Exception {
    String expectedJson = loadJsonFromFile(file);
    return getMap(expectedJson);
  }

}
