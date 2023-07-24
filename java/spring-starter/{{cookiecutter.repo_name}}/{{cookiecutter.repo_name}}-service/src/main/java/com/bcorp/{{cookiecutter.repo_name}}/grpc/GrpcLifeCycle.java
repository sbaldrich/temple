package com.bcorp.{{cookiecutter.repo_name}}.grpc;

import com.bcorp.signup.config.GrpcServerProperties;
import com.google.common.collect.Lists;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerServiceDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * Manages the grpc server lifecycle. This class is adapted poorly from the
 * <a href="https://github.com/yidongnan/grpc-spring-boot-starter">grpc-spring-boot-starter project</a>.
 */
@Component
public class GrpcLifeCycle implements SmartLifecycle, ApplicationContextAware {
    private static final Logger log = LoggerFactory.getLogger(GrpcLifeCycle.class);
    private final GrpcServerProperties grpcServerProperties;
    private ApplicationContext applicationContext;
    private Server grpcServer;

    public GrpcLifeCycle(GrpcServerProperties grpcServerProperties) {
        this.grpcServerProperties = grpcServerProperties;
    }

    private Collection<BindableService> findGrpcServices() {
        Collection<String> beanNames =
                Arrays.asList(this.applicationContext.getBeanNamesForAnnotation(GrpcService.class));

        final List<BindableService> services = Lists.newArrayListWithCapacity(beanNames.size());
        for (String beanName : beanNames) {
            BindableService bindableService = this.applicationContext.getBean(beanName, BindableService.class);
            ServerServiceDefinition serviceDefinition = bindableService.bindService();
            log.debug("Found gRPC service: {}, bean: {}, class: {}", serviceDefinition.getServiceDescriptor().getName(),
                    beanName, bindableService.getClass().getName());
            services.add(bindableService);
        }
        return services;
    }

    private void startGrpcServer() throws IOException {
        final ServerBuilder<?> serverBuilder = ServerBuilder.forPort(grpcServerProperties.getPort());
        log.debug("Searching for gRPC services...");
        for (BindableService service : findGrpcServices()) {
            serverBuilder.addService(service);
        }
        grpcServer = serverBuilder.build();
        // Prevent the JVM from shutting down while the server is running
        final Thread awaitThread = new Thread(() -> {
            try {
                grpcServer.awaitTermination();
            } catch (final InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        awaitThread.setName("grpc-server-container");
        awaitThread.setDaemon(false);
        awaitThread.start();
        grpcServer.start();
    }

    @Override
    public void start() {
        try {
            startGrpcServer();
            log.info("Started gRPC server");
        } catch (IOException e) {
            throw new IllegalStateException("Could not start gRPC server, aborting startup.", e);
        }
    }

    @Override
    public void stop() {
        if (grpcServer != null) {
            final long millis = grpcServerProperties.getShutdownGracePeriod().toMillis();
            log.debug("about to shutdown gRPC server with a grace period of {} millis", millis);
            grpcServer.shutdown();
            // Wait for the server to shut down completely before continuing with destroying the spring context
            try {
                if (millis >= 0) {
                    grpcServer.awaitTermination(millis, MILLISECONDS);
                } else {
                    // Wait indefinitely
                    grpcServer.awaitTermination();
                }
            } catch (final InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                grpcServer.shutdownNow();
                grpcServer = null;
            }
            log.info("Completed gRPC server shutdown");
        }
    }

    @Override
    public boolean isRunning() {
        return grpcServer != null && !grpcServer.isShutdown();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
