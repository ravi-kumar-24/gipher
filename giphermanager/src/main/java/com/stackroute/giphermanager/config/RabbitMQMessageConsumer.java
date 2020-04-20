package com.stackroute.giphermanager.config;

import com.stackroute.giphermanager.domain.GipherUser;
import com.stackroute.giphermanager.service.GIFManagerService;
import com.stackroute.rabbitmq.domain.GipherUserDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RabbitMQMessageConsumer {

    @Autowired
    GIFManagerService gifManagerService;

    @RabbitListener(queues="user_queue")
    public void registerUser(GipherUserDTO gipherUserDTO){
        GipherUser gipherUser=new GipherUser();
        gipherUser.setUsername(gipherUserDTO.getUsername());
        gipherUser.setEmail(gipherUserDTO.getEmail());
        gipherUser.setFavouriteGIFS(new ArrayList<>());
        gifManagerService.createNewUser(gipherUser);
    }
}
