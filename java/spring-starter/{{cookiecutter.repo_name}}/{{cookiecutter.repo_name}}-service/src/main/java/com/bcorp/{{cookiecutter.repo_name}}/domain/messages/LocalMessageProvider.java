package com.bcorp.{{cookiecutter.repo_name}}.domain.messages;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Primary
@Component
public class LocalMessageProvider implements MessageProvider {

    private static final List<String> messages =
            List.of(
                    "Explicit is better than implicit",
                    "Good is the enemy of the perfect",
                    "Value systems over goals");

    @Override
    public String get() {
        return messages.get(ThreadLocalRandom.current().nextInt(messages.size()));
    }
}
