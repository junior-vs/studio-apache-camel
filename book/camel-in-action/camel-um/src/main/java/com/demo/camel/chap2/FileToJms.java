package com.demo.camel.chap2;

import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileToJms {

 private final static Logger logger = LoggerFactory.getLogger(FileToJms.class);

  public static void main(String[] args) throws Exception {

    CamelContext context = new DefaultCamelContext();

    ConnectionFactory connectionFactory =
        new ActiveMQConnectionFactory("tcp://localhost:61616");
    context.addComponent("jms",
        JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
    // add our route to the CamelContext
    context.addRoutes(new RouteBuilder() {
      @Override
      public void configure() {
        from("file:data/inbox?noop=true")
            .process(new Processor() {
          public void process(Exchange exchange) throws Exception {
            logger.info("We just downloaded: " + exchange.getIn().getHeader("CamelFileName"));
          }
        }).to("jms:incomingOrders");
      }
    });

    // start the route and let it do its work
    context.start();
    Thread.sleep(10000);

    // stop the CamelContext
    context.stop();
  }

}
