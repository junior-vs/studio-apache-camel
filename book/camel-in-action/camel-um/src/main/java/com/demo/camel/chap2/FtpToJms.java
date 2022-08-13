package com.demo.camel.chap2;




import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class FtpToJms {

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
       from("ftp://localhost:21/orders?username=admin&password=root")
           .process(new Processor() {
             public void process(Exchange exchange) throws Exception {
               System.out.println("We just downloaded: " + exchange.getIn().getHeader("CamelFileName"));
             }
           })
           .to("jms:incomingOrders");
     //  from("ftp://rider.com/orders?username=rider&password=secret").to("jms:incomingOrders");
      }
    });

    // start the route and let it do its work
    context.start();
    Thread.sleep(10000);

    // stop the CamelContext
    context.stop();
  }

}
