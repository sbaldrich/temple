package com.bcorp.{{cookiecutter.repo_name}}.domain.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonDeserialize(builder = AutoValue_Message.Builder.class)
public abstract class Message {
    @JsonProperty("message")
    public abstract String message();

    public static Message of(final String message) {
        return builder()
                .message(message)
                .build();
    }


    public static Builder builder() {
        return new AutoValue_Message.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder message(String message);
        public abstract Message build();
    }
}
