package com.bcorp.{{cookiecutter.repo_name}}.dal;

import com.bcorp.signup.domain.messages.MessageProvider;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class GithubZenMessageProvider implements MessageProvider {

    private final GithubClient github;

    public GithubZenMessageProvider(GithubClient github) {
        this.github = github;
    }

    @Override
    public String get() {
        return github.zen();
    }
}
