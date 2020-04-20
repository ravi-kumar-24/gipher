package com.stackroute.giphermanager.service;

import com.stackroute.giphermanager.config.RabbitMQMessageProducer;
import com.stackroute.giphermanager.domain.FavouriteGIF;
import com.stackroute.giphermanager.domain.GipherUser;
import com.stackroute.giphermanager.exception.GIFAlreadyExistsException;
import com.stackroute.giphermanager.exception.GIFNotFoundException;
import com.stackroute.giphermanager.exception.UserNotFoundException;
import com.stackroute.giphermanager.repository.GIFManagerRepository;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

public class GIFManagerServiceTest {

    @Mock
    GIFManagerRepository gifManagerRepository;
    @Mock
    RabbitMQMessageProducer rabbitMQMessageProducer;

    private FavouriteGIF favouriteGIF;
    private GipherUser user;

    private Optional optional;
    private List<FavouriteGIF> list=null;

    @InjectMocks
    private GIFManagerServiceImpl gifServiceImpl;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        favouriteGIF =new FavouriteGIF("123","title","http://url",null);
        list=new ArrayList<>();
        list.add(favouriteGIF);
        user=new GipherUser("abc","email@gmail.com",list);
        optional=Optional.of(favouriteGIF);
    }

    @After
    public void tearDown(){
        favouriteGIF =null;
    }

    @Test
    public void saveFavouriteGIFSuccessTest() throws GIFAlreadyExistsException, UserNotFoundException {
        user=new GipherUser("xyz","email@gmail.com",null);
        when(gifManagerRepository.findByUsername(user.getUsername())).thenReturn(user);
        GipherUser fetchedGipherUser =gifServiceImpl.saveFavouriteGIF2DB(user.getUsername(),favouriteGIF);

        Assert.assertEquals(fetchedGipherUser, user);
        verify(gifManagerRepository,times(1)).findByUsername(user.getUsername());
        verify(gifManagerRepository,times(1)).save(user);
    }

    @Test(expected = GIFAlreadyExistsException.class)
    public void saveFavouriteGIFFailureTest() throws GIFAlreadyExistsException, UserNotFoundException {
        when(gifManagerRepository.findByUsername(user.getUsername())).thenReturn(user);

        GipherUser gipherUser =gifServiceImpl.saveFavouriteGIF2DB(user.getUsername(),favouriteGIF);

        Assert.assertEquals(gipherUser, user);
        verify(gifManagerRepository,times(1)).findByUsername(user.getUsername());
        verify(gifManagerRepository,times(1)).save(user);
    }

    @Test
    public void updateCommentsSuccessTest() throws GIFNotFoundException, UserNotFoundException {
        when(gifManagerRepository.findByUsername(user.getUsername())).thenReturn(user);
        favouriteGIF.setComments("comments updated");
        GipherUser gipherUser =gifServiceImpl.saveCommentsForFavouriteGIF(user.getUsername(),favouriteGIF.getComments(), favouriteGIF.getId());

        Assert.assertEquals("comments updated", gipherUser.getFavouriteGIFS().get(0).getComments());
        verify(gifManagerRepository,times(1)).findByUsername(user.getUsername());
        verify(gifManagerRepository,times(1)).save(user);
    }

    @Test(expected = GIFNotFoundException.class)
    public void updateCommentsFailureTest() throws GIFNotFoundException, UserNotFoundException {
        user=new GipherUser("xyz","email@gmail.com",new ArrayList<>());
        when(gifManagerRepository.findByUsername(user.getUsername())).thenReturn(user);
        favouriteGIF.setComments("comments updated");
        GipherUser gipherUser =gifServiceImpl.saveCommentsForFavouriteGIF(user.getUsername(),favouriteGIF.getComments(), favouriteGIF.getId());

        Assert.assertEquals("comments updated", gipherUser.getFavouriteGIFS().get(0).getComments());
        verify(gifManagerRepository,times(1)).findByUsername(user.getUsername());
        verify(gifManagerRepository,times(1)).save(user);
    }

    @Test
    public void deleteFavouriteGIF() throws GIFNotFoundException, UserNotFoundException {
        when(gifManagerRepository.findByUsername(user.getUsername())).thenReturn(user);
        GipherUser gipherUser=gifServiceImpl.deleteFavouriteGIFFromDB(user.getUsername(),favouriteGIF.getId());
        Assert.assertEquals(user,gipherUser);
        verify(gifManagerRepository,times(1)).findByUsername(user.getUsername());
        verify(gifManagerRepository,times(1)).save(user);
    }

    @Test(expected = GIFNotFoundException.class)
    public void deleteFavouriteGIFFailureTest() throws GIFNotFoundException, UserNotFoundException {
        user=new GipherUser("xyz","email@gmail.com",new ArrayList<>());
        when(gifManagerRepository.findByUsername(user.getUsername())).thenReturn(user);
        GipherUser gipherUser=gifServiceImpl.deleteFavouriteGIFFromDB(user.getUsername(),favouriteGIF.getId());
        Assert.assertEquals(gipherUser,user);
        verify(gifManagerRepository,times(1)).findByUsername(user.getUsername());
        verify(gifManagerRepository,times(1)).save(user);
    }


    @Test
    public void getAllFavouriteGIF() throws UserNotFoundException,Exception{
        when(gifManagerRepository.findByUsername(user.getUsername())).thenReturn(user);
        List<FavouriteGIF> fetchedList=gifServiceImpl.getAllFavouriteGIFromDB(user.getUsername());
        Assert.assertEquals(list,fetchedList);

        verify(gifManagerRepository,times(1)).findByUsername(user.getUsername());
    }
}
