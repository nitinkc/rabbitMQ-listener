package com.rabbitMQ.listener.RabbitMQListener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by nichaurasia on Thursday, January/23/2020 at 1:41 PM
 */

public class RabbitMQMessageListener implements MessageListener {
	
    @Override
    public void onMessage(Message message) {
    	
        String receivedMessage = new String(message.getBody());
        System.out.println("message = " + receivedMessage);
    }
}
