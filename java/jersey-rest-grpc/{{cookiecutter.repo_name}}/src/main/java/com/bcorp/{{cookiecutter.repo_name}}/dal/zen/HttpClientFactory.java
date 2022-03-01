package com.bcorp.{{cookiecutter.repo_name}}.dal.zen;

import org.glassfish.hk2.api.Factory;

import java.net.http.HttpClient;
import java.time.Duration;

public class HttpClientFactory implements Factory<HttpClient> {

  @Override
  public HttpClient provide() {
    return HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5L)).build();
  }

  @Override
  public void dispose(HttpClient httpClient) {}
}
