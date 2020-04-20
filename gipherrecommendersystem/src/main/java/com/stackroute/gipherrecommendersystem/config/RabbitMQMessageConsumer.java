package com.stackroute.gipherrecommendersystem.config;

import com.stackroute.gipherrecommendersystem.domain.FavouriteGIF;
import com.stackroute.gipherrecommendersystem.service.RecommendationService;
import com.stackroute.rabbitmq.domain.FavouriteGIFDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQMessageConsumer {

    RecommendationService recommendationService;

    @Autowired
    RabbitMQMessageConsumer(RecommendationService recommendationService){
        this.recommendationService=recommendationService;
    }

    @RabbitListener(queues="recommendations_queue")
    public void getFavouriteGIFDTO(FavouriteGIFDTO favouriteGIFDTO) throws Exception{
        FavouriteGIF favouriteGIF=new FavouriteGIF();
        favouriteGIF.setId(favouriteGIFDTO.getId());
        favouriteGIF.setTitle(favouriteGIFDTO.getTitle());
        favouriteGIF.setUrl(favouriteGIFDTO.getUrl());

        recommendationService.saveGIF2Recommendations(favouriteGIF);
    }

}
