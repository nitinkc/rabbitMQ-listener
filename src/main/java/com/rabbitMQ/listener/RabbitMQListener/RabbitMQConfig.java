package com.rabbitMQ.listener.RabbitMQListener;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by nichaurasia on Thursday, January/23/2020 at 1:44 PM
 */

@Configuration
public class RabbitMQConfig {

    private static final String QUEUE_NAME = "MyProgrammaticQueue";
    private static final String EXCHANGE_NAME = "MyProgrammaticTopicExchange";


    //Programatically Creating Queue without using the dashboard
    @Bean
    Queue myQueue(){
        return new Queue(QUEUE_NAME,true);
    }

    @Bean
    Exchange myExchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME)
                .durable(true)
                .build();
    }

    @Bean
    Binding binding(){
        //return new Binding(QUEUE_NAME,Binding.DestinationType.QUEUE,EXCHANGE_NAME,"topic",null);
        return BindingBuilder
                .bind(myQueue())
                .to(myExchange())
                .with("topic")
                .noargs();
    }

    @Bean
    ConnectionFactory connectionFactory(){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        return cachingConnectionFactory;
    }

    @Bean
    MessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setQueues(myQueue());
        simpleMessageListenerContainer.setMessageListener(new RabbitMQMessageListener());
        return simpleMessageListenerContainer;
    }
}
