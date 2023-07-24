package com.bcorp.{{cookiecutter.repo_name}}.presentation.grpc;

import com.bcorp.signup.domain.messages.MessageProvider;
import com.bcorp.signup.grpc.GrpcService;
import com.bcorp.starter.grpc.zen.WiseMessage;
import com.bcorp.starter.grpc.zen.ZenServiceGrpc;
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
