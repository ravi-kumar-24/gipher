package com.stackroute.gipherrecommendersystem.controller;

import com.stackroute.gipherrecommendersystem.domain.FavouriteGIF;
import com.stackroute.gipherrecommendersystem.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/recommendations")
public class RecommendationsController<requestMapping> {

    RecommendationService recommendationService;

    @Autowired
    public RecommendationsController(RecommendationService recommendationService){
        this.recommendationService=recommendationService;
    }

    @GetMapping
    public List<FavouriteGIF> getAllRecommendations() throws Exception{
        return recommendationService.getAllRecommendations();
    }
}
