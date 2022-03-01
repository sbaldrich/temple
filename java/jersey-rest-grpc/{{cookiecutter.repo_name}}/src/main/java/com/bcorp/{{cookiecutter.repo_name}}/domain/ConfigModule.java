package com.bcorp.{{cookiecutter.repo_name}}.domain;

import com.typesafe.config.Config;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;
import javax.ws.rs.ext.Provider;

@Provider
public class ConfigModule extends AbstractBinder {
  @Override
  protected void configure() {
    bindFactory(ConfigurationFactory.class).to(Config.class).in(Singleton.class);
  }

  public static class ConfigurationFactory implements Factory<Config> {
    @Override
    public Config provide() {
      return com.typesafe.config.ConfigFactory.load();
    }

    @Override
    public void dispose(Config config) {}
  }
}
