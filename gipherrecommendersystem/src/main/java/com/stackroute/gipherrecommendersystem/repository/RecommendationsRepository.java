package com.stackroute.gipherrecommendersystem.repository;

import com.stackroute.gipherrecommendersystem.domain.FavouriteGIF;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationsRepository extends MongoRepository<FavouriteGIF,String> {
}
