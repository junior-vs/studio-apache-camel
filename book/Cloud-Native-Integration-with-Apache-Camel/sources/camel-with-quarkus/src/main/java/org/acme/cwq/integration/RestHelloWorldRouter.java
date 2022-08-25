package org.acme.cwq.integration;


import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import org.acme.cwq.model.Fruit;
import org.acme.cwq.model.Legume;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class RestHelloWorldRouter extends RouteBuilder {

  private final Set<Fruit> fruits = Collections.synchronizedSet(new LinkedHashSet<>());
  private final Set<Legume> legumes = Collections.synchronizedSet(new LinkedHashSet<>());

  public RestHelloWorldRouter() {

    /* Let's add some initial fruits */
    this.fruits.add(new Fruit("Apple", "Winter fruit"));
    this.fruits.add(new Fruit("Pineapple", "Tropical fruit"));

    /* Let's add some initial legumes */
    this.legumes.add(new Legume("Carrot", "Root vegetable, usually orange"));
    this.legumes.add(new Legume("Zucchini", "Summer squash"));
  }
  @Override
  public void configure() throws Exception {

    restConfiguration().bindingMode(RestBindingMode.json);

    rest("/fruitss")
        .get()
        .to("direct:getFruits")

        .post()
        .type(Fruit.class)
        .to("direct:addFruit");

    rest("/legumess")
        .get()
        .to("direct:getLegumes");

    from("direct:getFruits")
        .setBody().constant(fruits);

    from("direct:addFruit")
        .process().body(Fruit.class, fruits::add)
        .setBody().constant(fruits);

    from("direct:getLegumes")
        .setBody().constant(legumes);
  }
}
