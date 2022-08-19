package org.acme.camel.integration;

import org.apache.camel.builder.RouteBuilder;

public class RestHelloWorldRoute extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    rest("/helloWorld")
        .get()
        .route()
        .routeId("rest-hello-world")
        .setBody(constant("Hello World \n"))
        .log("Request responded with body: ${body}")
        .endRest();
  }
}
