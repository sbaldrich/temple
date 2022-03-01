package com.bcorp.{{cookiecutter.repo_name}}.dal.zen;

import com.bcorp.{{cookiecutter.repo_name}}.dal.zen.github.GithubZenMessageProvider;
import com.bcorp.{{cookiecutter.repo_name}}.domain.messages.MessageProvider;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;
import java.net.http.HttpClient;

public class MessengerModule extends AbstractBinder {

  private MessengerModule() {}

  public static MessengerModule create() {
    return new MessengerModule();
  }

  @Override
  protected void configure() {

    bindFactory(HttpClientFactory.class).to(HttpClient.class).in(Singleton.class);

    bind(GithubZenMessageProvider.class).to(MessageProvider.class).in(Singleton.class);
  }
}
