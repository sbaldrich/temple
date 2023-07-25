package com.bcorp.{{cookiecutter.repo_name}}.presentation.grpc;

import com.bcorp.{{cookiecutter.repo_name}}.domain.messages.MessageProvider;
import com.bcorp.{{cookiecutter.repo_name}}.grpc.GrpcService;
import com.bcorp.{{cookiecutter.repo_name}}.grpc.zen.WiseMessage;
import com.bcorp.{{cookiecutter.repo_name}}.grpc.zen.ZenServiceGrpc;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

@GrpcService
public class ZenGrpcService extends ZenServiceGrpc.ZenServiceImplBase {

    private final MessageProvider messageProvider;

    public ZenGrpcService(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    @Override
    public void provideWisdom(Empty request, StreamObserver<WiseMessage> responseObserver) {
        final var wiseMessage = WiseMessage.newBuilder().setMessage(messageProvider.get()).build();
        responseObserver.onNext(wiseMessage);
        responseObserver.onCompleted();
    }
}
