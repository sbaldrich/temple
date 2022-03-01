package com.bcorp.{{cookiecutter.repo_name}}.presentation.grpc;

import io.grpc.Server;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

public final class GrpcServerFeature extends AbstractBinder {

  @Override
  protected void configure() {
    bind(ZenServiceGrpc.class).to(ZenServiceGrpc.class).in(Singleton.class);

    bindFactory(GrpcServerFactory.class).to(Server.class).in(Singleton.class);
  }
}
