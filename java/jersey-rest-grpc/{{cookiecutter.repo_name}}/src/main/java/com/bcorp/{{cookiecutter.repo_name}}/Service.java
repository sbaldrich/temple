package com.bcorp.{{cookiecutter.repo_name}};

import com.bcorp.{{cookiecutter.repo_name}}.presentation.grpc.GrpcServerFeature;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.grpc.Server;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spi.Container;
import org.glassfish.jersey.server.spi.ContainerLifecycleListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.atomic.AtomicReference;

public class Service {

  private static final Logger log = LoggerFactory.getLogger(Service.class);

  /**
   * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
   *
   * @return Grizzly HTTP server.
   * @param config the http configuration
   */
  public static HttpServer startServer(final Config config) throws IOException {
    final AtomicReference<InjectionManager> injector = new AtomicReference<>();
    final ResourceConfig rc =
        new ResourceConfig()
            .packages("com.bcorp.{{cookiecutter.repo_name}}")
            .register(JacksonFeature.class)
            .register(GrpcServerFeature.class)
            .register(
                new ContainerLifecycleListener() {
                  @Override
                  public void onStartup(Container container) {
                    injector.set(container.getApplicationHandler().getInjectionManager());
                  }

                  @Override
                  public void onReload(Container container) {}

                  @Override
                  public void onShutdown(Container container) {}
                });

    final URI uri =
        UriBuilder.fromUri("http://{host}:{port}")
            .build(config.getString("host"), config.getInt("port"));

    // create and start a new instance of grizzly http server
    log.info("Service is now listening at {}...", uri.toString());
    final HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(uri, rc);
    final Server grpc = injector.get().getInstance(Server.class);
    grpc.start();
    return httpServer;
  }

  public static void main(String[] args) throws IOException, InterruptedException {
    final Config config = ConfigFactory.load();
    final HttpServer server = startServer(config.getConfig("http"));
    System.in.read();
    server.stop();
  }
}
