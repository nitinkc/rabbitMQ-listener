package com.rabbitMQ.listener.RabbitMQListener;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.event.DocumentEvent;

/**
 * Created by nichaurasia on Thursday, January/23/2020 at 5:46 PM
 */

@Configuration
public class RMQExchangeConfiguration {

    @Bean
    Exchange exampleEx(){
        return new TopicExchange("ExExchange");
    }

    @Bean
    Exchange example2Ex(){
        return ExchangeBuilder.directExchange("Ex2ndExchange")
                .autoDelete()
                .internal()
                .build();
    }

    @Bean
    Exchange newEx(){
        return ExchangeBuilder.topicExchange("Example_topic_exchange")
                .autoDelete()
                .durable(true)
                .internal()
                .build();
    }

    @Bean
    Exchange newExFanout(){
        return ExchangeBuilder.fanoutExchange("Example_fanout_exchange")
                .autoDelete()
                .durable(false)
                .internal()
                .build();
    }

    @Bean
    Exchange newExHEaders(){
        return ExchangeBuilder.headersExchange("Example_headers_exchange")
                .autoDelete()
                .durable(false)
                .internal()
                .build();
    }
}
