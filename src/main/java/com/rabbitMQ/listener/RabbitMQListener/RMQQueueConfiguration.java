package com.rabbitMQ.listener.RabbitMQListener;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by nichaurasia on Thursday, January/23/2020 at 4:52 PM
 */
@Component
public class RMQQueueConfiguration {

    //Programatically Creating Queue without using the dashboard
    @Bean
    Queue myNewQueue(){
        return new Queue("example_Queue",true);
    }

    @Bean
    Queue newQueue(){
        return QueueBuilder.durable("another_queue")
                .autoDelete()
                .exclusive()
                .build();
    }

}
