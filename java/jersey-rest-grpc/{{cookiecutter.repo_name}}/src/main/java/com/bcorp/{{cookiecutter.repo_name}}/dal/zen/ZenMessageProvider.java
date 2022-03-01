package com.bcorp.{{cookiecutter.repo_name}}.dal.zen;

import com.bcorp.{{cookiecutter.repo_name}}.domain.messages.MessageProvider;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ZenMessageProvider implements MessageProvider {

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
