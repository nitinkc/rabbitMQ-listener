package com.rabbitMQ.listener.RabbitMQListener;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by nitin on Sunday, January/26/2020 at 1:11 AM
 */

@Configuration
public class MessageConfigurer {

    //NOT WORKING
    @Value("${rabbitmq.hostname}")
    private String hostName;

    private final String HOSTNAME = "localhost";
    private final String QUEUE_NAME = "MyProgrammaticQueue";

    @Bean
    ConnectionFactory connectionFactory(){
        System.out.println(HOSTNAME);
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(HOSTNAME);
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        return cachingConnectionFactory;
    }

    @Bean
    MessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setQueueNames(QUEUE_NAME);
        simpleMessageListenerContainer.setMessageListener(new RabbitMQMessageListener());
        return simpleMessageListenerContainer;
    }
}
