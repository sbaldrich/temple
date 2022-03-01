package com.bcorp.{{cookiecutter.repo_name}}.dal.zen;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

@Provider
public class MessengerFeature implements Feature {
  @Override
  public boolean configure(FeatureContext featureContext) {
    featureContext.register(MessengerModule.create());
    return true;
  }
}
