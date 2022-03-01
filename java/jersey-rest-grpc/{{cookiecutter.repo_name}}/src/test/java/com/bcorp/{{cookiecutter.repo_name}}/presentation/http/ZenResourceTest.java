package com.bcorp.{{cookiecutter.repo_name}}.presentation.http;

import com.bcorp.{{cookiecutter.repo_name}}.dal.zen.ZenMessageProvider;
import com.bcorp.{{cookiecutter.repo_name}}.domain.messages.MessageProvider;
import org.assertj.core.api.Assertions;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

public class ZenResourceTest extends JerseyTest {

  @Override
  protected Application configure() {
    return new ResourceConfig(ZenResource.class)
        .register(
            new AbstractBinder() {
              @Override
              protected void configure() {
                bind(ZenMessageProvider.class).to(MessageProvider.class);
              }
            });
  }

  @Test
  public void shouldFetchSomeZen() {
    final Response zenResponse = target("zen/sync").request().get();
    Assertions.assertThat(zenResponse.getStatus()).isEqualTo(200);
    Assertions.assertThat(zenResponse.readEntity(String.class)).isNotBlank();
  }
}
