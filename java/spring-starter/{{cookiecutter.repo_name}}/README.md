## Spring Boot + gRPC Starter

A simple spring-boot starter service with basic support for gRPC.
Some notable points:

- Add gRPC services by adding to the protobuf files in the `<service>-proto` project. Implementations of these services can be added to the `<service>-service` project by using the `@GrpcService` annotation. Check the `ZenGrpcService` for an example of how to do this.
- If you need more complex support for gRPC, take a look at the `GrpcLifecycle` class, where the services are picked up and the server is set up. A good alternative is to directly use the [grpc-spring-boot-starter](https://github.com/yidongnan/grpc-spring-boot-starter) project.

