package com.bcorp.{{cookiecutter.repo_name}}.dal.zen.github;

import com.bcorp.{{cookiecutter.repo_name}}.domain.messages.MessageProvider;
import com.google.common.base.Suppliers;
import com.typesafe.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class GithubZenMessageProvider implements MessageProvider {

  private static final Logger log = LoggerFactory.getLogger(GithubZenMessageProvider.class);
  private static final String ZEN_API = "https://api.github.com/zen";
  private final HttpClient client;
  private final Supplier<String> messageSupplier;

  @Inject
  public GithubZenMessageProvider(final HttpClient httpClient, final Config config) {
    this.client = httpClient;
    final int zenCacheSeconds = config.getInt("zen.cacheSeconds");
    log.debug("Caching zen for {} seconds", zenCacheSeconds);
    this.messageSupplier =
        Suppliers.memoizeWithExpiration(
            this::fetchZenFromGithub, zenCacheSeconds, TimeUnit.SECONDS);
  }

  private String fetchZenFromGithub() {
    log.debug("Fetching message from github...");
    final HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create(ZEN_API))
            .timeout(Duration.ofSeconds(2))
            .GET()
            .build();
    try {
      return client
          .send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8))
          .body();
    } catch (IOException | InterruptedException e) {
      return "Prioritize systems over goals";
    }
  }

  @Override
  public String get() {
    return messageSupplier.get();
  }
}
