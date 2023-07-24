package com.bcorp.{{cookiecutter.repo_name}}.dal;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Component
public class GithubClient {
    private WebClient client;
    private static final String GITHUB_API = "https://api.github.com";

    public GithubClient() {
        client = WebClient.create(GITHUB_API);
    }

    public String zen(){
        return client.get().uri("/zen").retrieve().bodyToMono(String.class).block(Duration.ofSeconds(5));
    }
}
