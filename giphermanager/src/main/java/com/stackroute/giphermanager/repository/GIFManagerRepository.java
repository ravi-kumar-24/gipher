package com.stackroute.giphermanager.repository;

import com.stackroute.giphermanager.domain.FavouriteGIF;
import com.stackroute.giphermanager.domain.GipherUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GIFManagerRepository extends MongoRepository<GipherUser,String> {
    public GipherUser findByUsername(String username);
}
