package com.bcnc.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Utility class for scope information.
 */
@Slf4j
public final class ScopeUtils {

  public static final String SCOPE_SUFFIX = "SCOPE_SUFFIX";

  public static final String ENV_SCOPE = "SCOPE";

  public static final String LOCAL_SCOPE = "local";

  public static final String TEST_SUFFIX = "test";

  public static final String PROD_SUFFIX = "prod";

  private ScopeUtils() {
    // Hide constructor
  }

  public static void calculateScopeSuffix() {
    String scopeValue = getScopeValue();
    log.info("ENV SCOPE ->>> ".concat(scopeValue));
    String[] tokens = StringUtils.split(scopeValue, '-');
    String scopeSuffix = tokens[tokens.length - 1];
    log.info(SCOPE_SUFFIX.concat(" ->>> ").concat(scopeSuffix));
    System.setProperty(SCOPE_SUFFIX, scopeSuffix);
  }

  public static boolean isLocalScope() {
    return LOCAL_SCOPE.equalsIgnoreCase(getScopeValue());
  }

  public static boolean isTestScope() {
    return getScopeValue()
        .endsWith(TEST_SUFFIX);
  }

  public static boolean isProdScope() {
    return getScopeValue()
        .endsWith(PROD_SUFFIX);
  }

  public static String getScopeValue() {
    String scope = System.getenv(ENV_SCOPE);
    if (StringUtils.isNoneBlank(scope)) {
      return scope;
    }
    return LOCAL_SCOPE;
  }

}
