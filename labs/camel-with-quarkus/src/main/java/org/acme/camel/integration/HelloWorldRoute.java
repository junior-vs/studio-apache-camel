package org.acme.camel.integration;

import org.apache.camel.builder.RouteBuilder;

public class HelloWorldRoute extends RouteBuilder {

  /* (non-Javadoc)
   * @see org.apache.camel.builder.RouteBuilder#configure()
   */
  @Override
  public void configure() throws Exception {
    
    from("timer:example?period=1000")
    .routeId("timer-hello-world")
    .setBody(constant("Hello World"))
    .to("log:" + HelloWorldRoute.class.getName());
  }
  
}
