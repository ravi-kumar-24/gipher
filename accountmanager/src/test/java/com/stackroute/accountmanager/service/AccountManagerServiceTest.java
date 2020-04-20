package com.stackroute.accountmanager.service;

import com.stackroute.accountmanager.config.RabbitMQMessageProducer;
import com.stackroute.accountmanager.domain.GipherUser;
import com.stackroute.accountmanager.exception.UserNotFoundException;
import com.stackroute.accountmanager.repository.AccountManagerRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class AccountManagerServiceTest {

    @Mock
    AccountManagerRepository accountManagerRepository;
    @Mock
    RabbitMQMessageProducer producer;
    GipherUser gipherUser;

    @InjectMocks
    AccountManagerServiceImpl accountManagerService;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        gipherUser=new GipherUser();
        gipherUser.setUsername("abc");
        gipherUser.setPassword("xyz");
        gipherUser.setEmail("abc@xyz.com");
    }

    @After
    public void tearDown(){
        gipherUser=null;
    }

    @Test
    public void registerUserTest(){
        when(accountManagerRepository.save(gipherUser)).thenReturn(gipherUser);
        GipherUser user=accountManagerService.saveUser(gipherUser);
        Assert.assertEquals(user.getUsername(),gipherUser.getUsername());
        verify(accountManagerRepository,times(1)).save(gipherUser);
    }

    @Test
    public void loginSuccessTest() throws UserNotFoundException {
        when(accountManagerRepository.findByUsernameAndPassword(gipherUser.getUsername(),gipherUser.getPassword())).thenReturn(gipherUser);
        GipherUser savedUser=accountManagerService.findByUsernameAndPassword(gipherUser.getUsername(), gipherUser.getPassword());
        Assert.assertEquals(gipherUser.getUsername(),savedUser.getUsername());
        verify(accountManagerRepository,times(1)).findByUsernameAndPassword(gipherUser.getUsername(),gipherUser.getPassword());
    }

    @Test(expected = UserNotFoundException.class)
    public void loginFailureTest() throws UserNotFoundException{
        when(accountManagerRepository.findByUsernameAndPassword(gipherUser.getUsername(),gipherUser.getPassword())).thenReturn(null);
        GipherUser savedUser=accountManagerService.findByUsernameAndPassword(gipherUser.getUsername(), gipherUser.getPassword());
        Assert.assertEquals(gipherUser.getUsername(),savedUser.getUsername());
        verify(accountManagerRepository,times(1)).findByUsernameAndPassword(gipherUser.getUsername(),gipherUser.getPassword());

    }

}
