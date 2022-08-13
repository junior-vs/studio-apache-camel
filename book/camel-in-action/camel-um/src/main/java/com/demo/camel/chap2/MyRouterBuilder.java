package com.demo.camel.chap2;

import org.apache.camel.builder.RouteBuilder;

public class MyRouterBuilder extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    from("file:data/inbox?noop=true").to("jms:incomingOrders");


  }
}
