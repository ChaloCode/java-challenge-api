package com.bcnc.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static uk.org.webcompere.systemstubs.SystemStubs.withEnvironmentVariable;

import org.junit.jupiter.api.Test;

class ScopeUtilsTest {

  @Test
  void shouldReturnLocalScopeWhenEnvScopeIsNotSet() throws Exception {
    withEnvironmentVariable(ScopeUtils.ENV_SCOPE, null).execute(() -> {
      assertEquals("local", ScopeUtils.getScopeValue());
      assertTrue(ScopeUtils.isLocalScope());
    });
  }

  @Test
  void shouldReturnTestScopeWhenEnvScopeEndsWithTest() throws Exception {
    withEnvironmentVariable(ScopeUtils.ENV_SCOPE, "some-test").execute(() -> {
      assertTrue(ScopeUtils.isTestScope());
      assertFalse(ScopeUtils.isProdScope());
      assertFalse(ScopeUtils.isLocalScope());
    });
  }

  @Test
  void shouldReturnProdScopeWhenEnvScopeEndsWithProd() throws Exception {
    withEnvironmentVariable(ScopeUtils.ENV_SCOPE, "some-prod").execute(() -> {
      assertTrue(ScopeUtils.isProdScope());
      assertFalse(ScopeUtils.isTestScope());
      assertFalse(ScopeUtils.isLocalScope());
    });
  }

  @Test
  void shouldCalculateScopeSuffixCorrectly() throws Exception {
    withEnvironmentVariable(ScopeUtils.ENV_SCOPE, "some-test").execute(() -> {
      ScopeUtils.calculateScopeSuffix();
      assertEquals("test", System.getProperty(ScopeUtils.SCOPE_SUFFIX));
    });
  }

  @Test
  void shouldHandleInvalidScopeGracefully() throws Exception {
    withEnvironmentVariable(ScopeUtils.ENV_SCOPE, "invalid-scope").execute(() -> {
      ScopeUtils.calculateScopeSuffix();
      assertEquals("scope", System.getProperty(ScopeUtils.SCOPE_SUFFIX));
    });
  }
}