package com.stackroute.gipherrecommendersystem.service;

import com.stackroute.gipherrecommendersystem.domain.FavouriteGIF;
import com.stackroute.gipherrecommendersystem.repository.RecommendationsRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class RecommendationServiceTest {

    @Mock
    RecommendationsRepository recommendationsRepository;

    @InjectMocks
    RecommendationsServiceImpl recommendationsService;

    FavouriteGIF favouriteGIF;
    List<FavouriteGIF> gifsList;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        favouriteGIF=new FavouriteGIF("123","abc","http://url",1);
        gifsList=new ArrayList<>();

        gifsList.add(favouriteGIF);
    }

    @After
    public void tearDown(){
        favouriteGIF=null;
        gifsList=null;
    }

    @Test
    public void existingRecommendationsTest() throws Exception{
        when(recommendationsRepository.findById(favouriteGIF.getId())).thenReturn(Optional.of(favouriteGIF));
        FavouriteGIF fetchedGif=recommendationsService.saveGIF2Recommendations(favouriteGIF);
        Assert.assertEquals(favouriteGIF.getId(),fetchedGif.getId());
        Assert.assertEquals(2,fetchedGif.getDownloadsCount());
        verify(recommendationsRepository,times(1)).findById(favouriteGIF.getId());
        verify(recommendationsRepository,times(1)).save(favouriteGIF);
    }

    @Test
    public void newRecommendationsTest() throws Exception{
        when(recommendationsRepository.findById(favouriteGIF.getId())).thenReturn(Optional.ofNullable(null));
        FavouriteGIF fetchedGif=recommendationsService.saveGIF2Recommendations(favouriteGIF);
        Assert.assertEquals(favouriteGIF.getId(),fetchedGif.getId());
        Assert.assertEquals(1,fetchedGif.getDownloadsCount());
        verify(recommendationsRepository,times(1)).findById(favouriteGIF.getId());
        verify(recommendationsRepository,times(1)).insert(favouriteGIF);
    }

    @Test
    public void getAllRecommendations() throws Exception{
        favouriteGIF=new FavouriteGIF("234","abc","http://url",1);
        gifsList.add(favouriteGIF);
        when(recommendationsRepository.findAll()).thenReturn(gifsList);
        List<FavouriteGIF> fetchedList=recommendationsService.getAllRecommendations();
        Assert.assertEquals(2,fetchedList.size());
        verify(recommendationsRepository,times(1)).findAll();
    }

}
