package com.stackroute.gipherrecommendersystem.service;

import com.stackroute.gipherrecommendersystem.domain.FavouriteGIF;
import com.stackroute.gipherrecommendersystem.repository.RecommendationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecommendationsServiceImpl implements RecommendationService{

    RecommendationsRepository recommendationsRepository;

    @Autowired
    public RecommendationsServiceImpl(RecommendationsRepository recommendationsRepository){
        this.recommendationsRepository=recommendationsRepository;
    }

    @Override
    public List<FavouriteGIF> getAllRecommendations() throws Exception {
        return recommendationsRepository.findAll();
    }

    @Override
    public FavouriteGIF saveGIF2Recommendations(FavouriteGIF favouriteGIF) throws Exception {
       Optional<FavouriteGIF> fetchedOptionsl=recommendationsRepository.findById(favouriteGIF.getId());
       if(fetchedOptionsl.isPresent()){
           FavouriteGIF fetchedGIF=fetchedOptionsl.get();
           int presentCount=fetchedGIF.getDownloadsCount();
           fetchedGIF.setDownloadsCount(++presentCount);
           favouriteGIF=fetchedGIF;
           recommendationsRepository.save(fetchedGIF);
       }else{
           favouriteGIF.setDownloadsCount(1);
           recommendationsRepository.insert(favouriteGIF);
       }
       return favouriteGIF;
    }
}
