package com.bcorp.{{cookiecutter.repo_name}}.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@ConfigurationPropertiesScan
@Configuration
@ConfigurationProperties(prefix = "grpc.server")
public class GrpcServerProperties {
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration shutdownGracePeriod = Duration.of(30, ChronoUnit.SECONDS);

    private int port = 5990;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Duration getShutdownGracePeriod() {
        return shutdownGracePeriod;
    }

    public void setShutdownGracePeriod(Duration shutdownGracePeriod) {
        this.shutdownGracePeriod = shutdownGracePeriod;
    }
}
