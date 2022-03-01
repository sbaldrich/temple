package com.bcorp.{{cookiecutter.repo_name}}.presentation.http;

import com.bcorp.{{cookiecutter.repo_name}}.domain.messages.Message;
import com.bcorp.{{cookiecutter.repo_name}}.domain.messages.MessageProvider;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

@Path("zen")
public class ZenResource {

  private final MessageProvider messageProvider;

  @Inject
  public ZenResource(MessageProvider messageProvider) {
    this.messageProvider = messageProvider;
  }

  @GET
  @Path("sync")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getIt() {
    final Message message = Message.of(messageProvider.get());
    return Response.ok(message).build();
  }

  @GET
  @Path("/async")
  @Produces(MediaType.TEXT_PLAIN)
  public void asyncGetIt(@Suspended AsyncResponse response) {
    CompletableFuture.completedFuture(messageProvider.get())
        .thenApply(it -> it.toUpperCase(Locale.ROOT))
        .thenApply(response::resume);
  }
}
