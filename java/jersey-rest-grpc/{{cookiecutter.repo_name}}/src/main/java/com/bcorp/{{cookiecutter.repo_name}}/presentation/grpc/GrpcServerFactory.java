package com.bcorp.{{cookiecutter.repo_name}}.presentation.grpc;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import org.glassfish.hk2.api.Factory;

import javax.inject.Inject;

public class GrpcServerFactory implements Factory<Server> {

  private final ZenServiceGrpc zenServiceGrpc;

  @Inject
  public GrpcServerFactory(ZenServiceGrpc zenServiceGrpc) {
    this.zenServiceGrpc = zenServiceGrpc;
  }

  @Override
  public Server provide() {
    final Config config = ConfigFactory.load();
    return ServerBuilder.forPort(config.getInt("grpc.port"))
        .addService(zenServiceGrpc)
        .addService(ProtoReflectionService.newInstance())
        .build();
  }

  @Override
  public void dispose(Server server) {}
}
