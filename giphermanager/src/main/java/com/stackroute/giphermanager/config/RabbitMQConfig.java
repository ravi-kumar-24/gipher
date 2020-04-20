package com.stackroute.giphermanager.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    private String recommendationExchangeName="recommendations_exchange";
    private String recommendationQueueName="recommendations_queue";


    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(recommendationExchangeName);
    }

    @Bean
    Queue recommendationQueue(){
        return new Queue(recommendationQueueName);
    }

    @Bean
    Binding bindingRecommendations(Queue queue,DirectExchange exchange){
        return BindingBuilder.bind(recommendationQueue()).to(directExchange()).with("recommendations_routing");
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
