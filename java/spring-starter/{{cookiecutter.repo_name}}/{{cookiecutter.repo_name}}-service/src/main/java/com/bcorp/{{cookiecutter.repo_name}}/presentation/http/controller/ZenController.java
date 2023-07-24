package com.bcorp.{{cookiecutter.repo_name}}.presentation.http.controller;

import com.bcorp.signup.domain.messages.MessageProvider;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("zen")
public class ZenController {
    private final MessageProvider messageProvider;

    public ZenController(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Zen getZen() {
        return new Zen(messageProvider.get());
    }

    record Zen(String zen) {}
}
