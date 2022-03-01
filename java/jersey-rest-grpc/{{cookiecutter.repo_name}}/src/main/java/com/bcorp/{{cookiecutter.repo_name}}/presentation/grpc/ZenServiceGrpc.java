package com.bcorp.{{cookiecutter.repo_name}}.presentation.grpc;

import com.bcorp.{{cookiecutter.repo_name}}.domain.messages.MessageProvider;
import com.bcorp.{{cookiecutter.repo_name}}.zen.ZenGrpc;
import com.bcorp.{{cookiecutter.repo_name}}.zen.ZenRequest;
import com.bcorp.{{cookiecutter.repo_name}}.zen.ZenResponse;
import io.grpc.stub.StreamObserver;

import javax.inject.Inject;

public class ZenServiceGrpc extends ZenGrpc.ZenImplBase {

  private final MessageProvider provider;

  @Inject
  public ZenServiceGrpc(MessageProvider provider) {
    this.provider = provider;
  }

  @Override
  public void zen(ZenRequest request, StreamObserver<ZenResponse> observer) {
    final String name = " " + request.getName().trim();
    final String message = String.format("Hey%s, your zen message is: %s", name, provider.get());
    observer.onNext(ZenResponse.newBuilder().setMessage(message).build());
    observer.onCompleted();
  }
}
