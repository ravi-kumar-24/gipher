package com.stackroute.gipherrecommendersystem.service;

import com.stackroute.gipherrecommendersystem.domain.FavouriteGIF;

import java.util.List;

public interface RecommendationService {
    List<FavouriteGIF> getAllRecommendations() throws Exception;
    FavouriteGIF saveGIF2Recommendations(FavouriteGIF favouriteGIF) throws Exception;
}
