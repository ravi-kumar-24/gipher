package com.stackroute.gipherrecommendersystem.repository;

import com.stackroute.gipherrecommendersystem.domain.FavouriteGIF;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecommendationsRepositoryTest {

    @Autowired
    RecommendationsRepository recommendationsRepository;

    FavouriteGIF gif;

    @Before
    public void setup(){
        gif=new FavouriteGIF();
        gif.setId("123");
        gif.setTitle("abcdefgh");
        gif.setUrl("http://abc");
    }

    @After
    public void tearDown(){
        gif=null;
        recommendationsRepository.deleteAll();
    }

    @Test
    public void saveRecommendation(){
        recommendationsRepository.save(gif);
        Optional<FavouriteGIF> gifs=recommendationsRepository.findById(gif.getId());
        FavouriteGIF fetchedGIF=gifs.get();

        Assert.assertEquals(gif.getId(),fetchedGIF.getId());
    }

}
