package com.demo.camel.chap2;

import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class FileToJmsMyRouter {

  public static void main(String[] args) throws Exception {

    CamelContext context = new DefaultCamelContext();

    ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
    context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
    // add our route to the CamelContext
    context.addRoutes(new MyRouterBuilder());

    // start the route and let it do its work
    context.start();
    Thread.sleep(10000);

    // stop the CamelContext
    context.stop();
  }

}
