package mymanga.rabbitmq.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class RabbitMQController {

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void sendEmailChat(String mangaName) {
        System.out.println("Consumed message, " + mangaName);
    }
}
